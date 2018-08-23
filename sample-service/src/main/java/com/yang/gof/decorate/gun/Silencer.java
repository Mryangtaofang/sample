package com.yang.gof.decorate.gun;

/**
 * 消音器
 * @author yangyaming
 */
public class Silencer extends DecorateParts{
	
	@Override
	public Fire fire() {
		Fire fireResult = super.fire();
		fireResult.setVoice(true);
		return fireResult;
	}
	
}
