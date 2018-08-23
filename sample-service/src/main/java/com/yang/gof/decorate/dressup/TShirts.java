package com.yang.gof.decorate.dressup;

public class TShirts extends Finery{
	
	@Override
	public void show() {
		super.show();
		System.out.println("穿一件Tshirt!");
	}
	
}
