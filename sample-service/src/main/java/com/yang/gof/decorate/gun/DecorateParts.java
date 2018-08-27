package com.yang.gof.decorate.gun;

/**
 * 配件
 */
public abstract class DecorateParts extends Gun{

	protected Gun gun;

	/**
	 * 核心方法
	 */
	@Override
	public Fire fire() {
		return (gun != null) ? gun.fire() : null;
	}

	public void install(Gun gun) {
		this.gun = gun;
	}
	
}
