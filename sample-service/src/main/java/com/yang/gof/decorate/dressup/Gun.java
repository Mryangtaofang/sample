package com.yang.gof.decorate.dressup;


public abstract class Gun {
	//射程
	protected int range; 
	//开枪是否有声音
	protected boolean voice; 
	
	public abstract Fire fire();
	
}
