package com.yang.gof.decorate;

import com.yang.gof.decorate.gun.CollimationMirror;
import com.yang.gof.decorate.gun.Pistol;
import com.yang.gof.decorate.gun.Silencer;


/**
 * 情景：游戏中经常使用抢，有手枪(英文:Pistol)，狙击枪(英文:SniperRifle)等等。
 * 然后枪有个基本功能,肯定是fire,如果给枪装饰瞄准镜(Collimation Mirror)，可以增加射程，如果装上消音器(Silencer)，可以消音。
 * 
 * 用装饰器模式来实现
 * @author yangyaming
 */
public class DecorateMain {
	
	public static void main(String[] args) {
		//创建一把手枪
		Pistol gun = new Pistol();
		//创建一个瞄准镜
		CollimationMirror mirror = new CollimationMirror();
		//创建一个消音器
		Silencer silencer = new Silencer();
		
		//安装瞄准镜
		mirror.dressUp(gun);
		//安装消音器
		silencer.dressUp(mirror);
		
		System.out.println("手枪-[无配件]-" + gun.fire());
		System.out.println("手枪-[瞄准镜]-" + mirror.fire());
		System.out.println("手枪-[瞄准镜,消音器]-" + silencer.fire());
	}
}
