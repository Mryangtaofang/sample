package com.yang.gof.decorate.dressup;


public  class Gentleman {
	String name;
	
	public Gentleman(String name) {
		this.name = name;
	}
	
	public void show() {
	}
	public Gentleman() {}

	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
}
