package com.yang.netty.pojo;

public class NettyMessage {
	private Header header;// 消息头

	private Object body;// 消息体

	@Override
	public String toString() {
		return "NettyMessage [header=" + header + "]";
	}
	
	
	/****************************getter and setter***********************************/
	public Header getHeader() {return header;}
	public void setHeader(Header header) {this.header = header;}

	public Object getBody() {return body;}
	public void setBody(Object body) {this.body = body;}
	/****************************getter and setter***********************************/
}
