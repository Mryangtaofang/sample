package com.yang.gof.decorate.gun;

/**
 * 瞄准镜
 * @author yangyaming
 */
public class CollimationMirror extends DecorateParts{
	//增加的射程
	private int addRange = 100;
	
	@Override
	public Fire fire() {
		Fire fireResult = super.fire();
		fireResult.setRange(fireResult.getRange() + addRange);
		return fireResult;
	}
	
}
