package com.yang.gof.decorate;

public abstract class AbstractDecorate extends Component {

	private Component component;
	
	@Override
	public void dispaly() {
		if(component != null)
			component.dispaly();
	}

	public void decorate(Component component) {
		this.component = component;
	}
}
