package com.yang.gof.decorate.dressup;

/**
 * 手枪
 * @author yangyaming
 */
public class Pistol extends Gun{

	@Override
	public Fire fire() {
		Fire fire = new Fire();
		fire.setRange(50);
		fire.setVoice(false);
		return fire;
	}

}
