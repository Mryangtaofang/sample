package com.yang.gof.decorate.dressup;

/**
 * 配件
 */
public class DecorateParts extends Gun{

	protected Gun gun;

	@Override
	public Fire fire() {
		return (gun != null) ? gun.fire() : null;
	}

	public void dressUp(Gun gun) {
		this.gun = gun;
	}
	
}
