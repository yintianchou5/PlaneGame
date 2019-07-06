package org.dzq.entity;

import java.awt.Image;
import java.awt.Rectangle;

public class GameObject {
	Image img;
	public double x;
	public double y;
	int width;
	int height;
	int speed;
	public Rectangle getRect() {
		return new Rectangle((int)x,(int)y, width, height);
	}
}
