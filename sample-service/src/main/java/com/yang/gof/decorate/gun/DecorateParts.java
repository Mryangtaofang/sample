package com.yang.gof.decorate.gun;

/**
 * 配件
 */
public class DecorateParts extends Gun{

	protected Gun gun;

	/**
	 * 核心方法
	 */
	@Override
	public Fire fire() {
		return (gun != null) ? gun.fire() : null;
	}

	public void dressUp(Gun gun) {
		this.gun = gun;
	}
	
}
