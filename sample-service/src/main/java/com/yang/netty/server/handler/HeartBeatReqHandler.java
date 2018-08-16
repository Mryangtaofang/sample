package com.yang.netty.server.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.ScheduledFuture;

import java.util.concurrent.TimeUnit;

import com.yang.netty.pojo.Header;
import com.yang.netty.pojo.MessageType;
import com.yang.netty.pojo.NettyMessage;

public class HeartBeatReqHandler extends ChannelHandlerAdapter {

    private volatile ScheduledFuture<?> heartBeat;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        // 握手成功，主动发送心跳消息
        //HeartBeatReqHandler接收到之后对消息进行判断
        if (message.getHeader() != null
                && message.getHeader().getType() == MessageType.LOGIN_RESP.value()) {
            //当握手成功之后，握手请求Handler会继续将握手成功消息向下透传
            //如果是握手成功消息，则启动无限循环定时器用于定期发送心跳消息。
            //由于NioEventLoop是一个schedule，因此它支持定时器的执行。
            // 心跳定时器的单位是毫秒，默认为5000，即每5秒发送一条心跳消息。
            heartBeat = ctx.executor().scheduleAtFixedRate(new HeartBeatReqHandler.HeartBeatTask(ctx), 0, 5000,TimeUnit.MILLISECONDS);
        } else if (message.getHeader() != null
                && message.getHeader().getType() == MessageType.HEARTBEAT_RESP.value()) {
            //接收服务端发送的心跳应答消息，并打印客户端接收和发送的心跳消息。
            System.out.println("Client receive server heart beat message : ---> "+ message);
        } else {
            //当握手成功之后，握手请求Handler会继续将握手成功消息向下透传
            ctx.fireChannelRead(msg);
        }
    }

    private class HeartBeatTask implements Runnable {
        private final ChannelHandlerContext ctx;

        public HeartBeatTask(final ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            NettyMessage heatBeat = buildHeatBeat();
            System.out.println("Client send heart beat messsage to server : ---> "+ heatBeat);
            ctx.writeAndFlush(heatBeat);
        }

        private NettyMessage buildHeatBeat() {
            NettyMessage message = new NettyMessage();
            Header header = new Header();
            header.setType(MessageType.HEARTBEAT_REQ.value());
            message.setHeader(header);
            return message;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (heartBeat != null) {
            heartBeat.cancel(true);
            heartBeat = null;
        }
        ctx.fireExceptionCaught(cause);
    }
}
