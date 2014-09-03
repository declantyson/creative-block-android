package com.declantyson.creativeblock.game;

import android.graphics.Rect;

public class Water {
	private boolean visible;
	private int x, y, speedY;
	
	public Rect collisionRect = new Rect(0,0,0,0);
	
	public Water(int startX, int startY) {
		visible = true;
		x = startX;
		y = startY;
		speedY = 4;
	}
	
	public void update() {
		y += speedY;
		if (y > 530) {
			visible = false;
		}
		collisionRect.set(x, y, x + 28, y + 58);
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
