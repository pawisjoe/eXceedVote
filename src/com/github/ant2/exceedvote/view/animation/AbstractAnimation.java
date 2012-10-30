package com.github.ant2.exceedvote.view.animation;

import java.awt.Component;

public abstract class AbstractAnimation implements Animation {

	protected Component component;

	@Override
	public void setComponent(Component component) {
		this.component = component;
	}

}
