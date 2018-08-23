package com.yang.gof.decorate;

import com.yang.gof.decorate.dressup.Finery;
import com.yang.gof.decorate.dressup.Gentleman;
import com.yang.gof.decorate.dressup.Shorts;
import com.yang.gof.decorate.dressup.TShirts;



public class DecorateMain {
	
	public static void main(String[] args) {
		Gentleman man = new Gentleman("yangyaming");
		
		Finery finery = new Finery();
		Shorts shorts = new Shorts();
		TShirts tShirts = new TShirts();
		
		finery.dressUp(man);
		shorts.dressUp(finery);
		tShirts.dressUp(shorts);
		
		tShirts.show();
	}
}
