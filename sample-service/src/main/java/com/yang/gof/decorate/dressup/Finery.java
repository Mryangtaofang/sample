package com.yang.gof.decorate.dressup;

/**
 * Finery : 服饰
 * @author yangyaming
 */
public class Finery extends Gentleman{

	protected Gentleman man;

	@Override
	public void show() {
		System.out.println("服饰装饰器!");
		if(man != null)
			man.show();
	}
	

	public Gentleman getMan() {
		return man;
	}

	public void dressUp(Gentleman man) {
		this.man = man;
	}
	
}
