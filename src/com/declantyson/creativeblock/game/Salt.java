package com.declantyson.creativeblock.game;

import android.graphics.Rect;

import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.implementation.AndroidGraphics;

public class Salt {

	private boolean visible;
	private int x, y, speedY;
	
	public Rect collisionRect = new Rect(0,0,0,0);
	
	public Salt(int startX, int startY) {
		visible = true;
		x = startX;
		y = startY;
		speedY = 4;
	}

	public void update() {
		y += speedY;
		if (y > 800) {
			visible = false;
		}
		collisionRect.set(x, y, x + 70, y + 40);
	}

	public boolean isVisible() {
		return visible;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
		
}
