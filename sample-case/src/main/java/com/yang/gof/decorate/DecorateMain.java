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
		System.out.println("手枪-[裸枪]-" + gun.fire());
		
		//创建一个瞄准镜,裸枪上，安装瞄准镜
		CollimationMirror mirrorGun = new CollimationMirror();
		mirrorGun.install(gun);
		System.out.println("手枪-[瞄准镜]-" + mirrorGun.fire());
		
		//创建一个消音器,在安装了瞄准镜的基础上，再安装消音器
		Silencer silencerMirrorGun = new Silencer();
		silencerMirrorGun.install(mirrorGun);
		System.out.println("手枪-[瞄准镜,消音器]-" + silencerMirrorGun.fire());
		
		//创建一个消音器,裸枪上，安装消音器
		Silencer silencerGun = new Silencer();
		silencerGun.install(gun);
		System.out.println("手枪-[消音器]-" + silencerGun.fire());
	}
}
