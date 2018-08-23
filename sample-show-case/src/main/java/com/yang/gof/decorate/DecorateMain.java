package com.yang.gof.decorate;

import com.yang.gof.decorate.dressup.CollimationMirror;
import com.yang.gof.decorate.dressup.Pistol;
import com.yang.gof.decorate.dressup.Silencer;



public class DecorateMain {
	
	public static void main(String[] args) {
		Pistol gun = new Pistol();
		
		CollimationMirror mirror = new CollimationMirror();
		Silencer silencer = new Silencer();
		
		mirror.dressUp(gun);
		silencer.dressUp(mirror);
		
		System.out.println(silencer.fire());
	}
}
