package com.yang.gof.decorate;


public class ComponentDecorateMain {

	public static void main(String[] args) {
		ConcreteComponent component = new ConcreteComponent();
		DecorateA a = new DecorateA();
		DecorateB b = new DecorateB();
		
		a.decorate(component);
		b.decorate(a);
		
		b.dispaly();
	}
	
}
