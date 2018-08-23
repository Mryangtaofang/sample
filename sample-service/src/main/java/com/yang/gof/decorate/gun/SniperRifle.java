package com.yang.gof.decorate.gun;

/**
 * 狙击枪
 * @author yangyaming
 */
public class SniperRifle extends Gun{

	@Override
	public Fire fire() {
		Fire fire = new Fire();
		fire.setRange(400);
		fire.setVoice(false);
		return fire;
	}

}