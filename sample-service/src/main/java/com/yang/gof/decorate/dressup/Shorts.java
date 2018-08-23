package com.yang.gof.decorate.dressup;

/**
 * 短裤
 * @author yangyaming
 */
public class Shorts extends Finery{
	
	
	@Override
	public void show() {
		super.show();
		System.out.println("穿一条短裤!");
	}
	
}
