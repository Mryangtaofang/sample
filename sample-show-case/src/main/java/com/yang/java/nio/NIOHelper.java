package com.yang.java.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class NIOHelper {

	public static final int PORT = 8989;
	
	public static final String HOST = "127.0.0.1";
	
	public static final int SELECTOR_TIME_OUT = 3000;
	
	/**
	 * 写出数据
	 */
	protected void doWrite(SocketChannel sc, String data) throws IOException{
		if(sc == null || data == null)
			return;
		
		System.out.println("write data :" + data);
		
		byte[] bytes = data.getBytes();
		ByteBuffer writeBuf = ByteBuffer.allocate(bytes.length);
		writeBuf.put(bytes);
		writeBuf.flip();
		sc.write(writeBuf);
		System.out.println("write success!");
	}
	
	
	/**
	 * select监听
	 */
	protected void doSelect(Selector selector) throws IOException{
		System.out.println("selector.select start.....");
		selector.select(SELECTOR_TIME_OUT);
		
		System.out.println("selector.select end.....");
		Set<SelectionKey> selectedKeys = selector.selectedKeys();
		
		if(selectedKeys.size() <= 0){
			System.out.println("selector.select is null");
			return;
		}
		
		System.out.println("selector.select " + selectedKeys.size() + " key");
		Iterator<SelectionKey> it = selectedKeys.iterator();
		SelectionKey key = null;
		while (it.hasNext()) {
			key = it.next();
			it.remove();
			try {
				handleInput(key);
			} catch (Exception e) {
				e.printStackTrace();
				close(key);
			}
		}
	}

	protected void closeSelector(Selector selector) {
		if(selector == null)
			return;
		
		try {
			selector.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected String doRead(SelectionKey key) throws IOException{
		SocketChannel socketChannel = (SocketChannel) key.channel();
		ByteBuffer buf = ByteBuffer.allocate(1024);
		int readBytes = socketChannel.read(buf);
		if(readBytes > 0){
			buf.flip();
			byte[] bytes = new byte[buf.remaining()];
			buf.get(bytes);
			String body = new String(bytes,"utf-8");
			System.out.println("读取的数据为：" + body);
			String currentTime = "QUERY TIME ORDER".equals(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
			return currentTime;
		}else if(readBytes < 0){
			System.out.println("读取结束.......");
			close(key);
		}else{
			System.out.println("读到0字节");
		}
		return null;
	}
	
	protected void close(SelectionKey key) throws IOException{
		if(key != null)
			key.cancel();
		if(key != null && key.channel() != null)
			key.channel().close();
		
	}
	
	protected void handleInput(SelectionKey key) throws IOException  {
		
	}
}
