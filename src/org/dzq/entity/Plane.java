package org.dzq.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Plane extends GameObject{
	
	private boolean left,up,down,right; 
	public boolean live=true; 
	
	public Plane(Image img, double planeX, double planeY, int planeWidth, int planeHeight, int speed) {
		super();
		this.img = img;
		this.x = planeX;
		this.y = planeY;
		this.width = planeWidth;
		this.height = planeHeight;
		this.speed = speed;
	}
	public Plane() {
	}
	public void drawSelf(Graphics g) {
		if(live) {
			g.drawImage(img,(int) x, (int)y, null );
			if(left) {
				x-=speed;
			}
			if(up) {
				y-=speed;
			}
			if(right) {
				x+=speed;
			}
			if(down) {
				y+=speed;
			}
		}
	}
	
	public void addDirection(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left=true;
			break;
		case KeyEvent.VK_UP:
			up=true;
			break;
		case KeyEvent.VK_RIGHT:
			right=true;
			break;
		case KeyEvent.VK_DOWN:
			down=true;
			break;
		}
	}
	public void cancelDirection(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left=false;
			break;
		case KeyEvent.VK_UP:
			up=false;
			break;
		case KeyEvent.VK_RIGHT:
			right=false;
			break;
		case KeyEvent.VK_DOWN:
			down=false;
			break;
		}
	}
}
