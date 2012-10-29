package com.github.ant2.exceedvote.view.animation;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public interface Animation {

	void setComponent(Component component);
	void draw(double value, Drawable before, Drawable after, Graphics2D g);
	
}
