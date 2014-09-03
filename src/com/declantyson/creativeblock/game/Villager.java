package com.declantyson.creativeblock.game;

public class Villager {
	final int GROUND = 310;
	public int x, y, speedX = 2;
	public boolean visible, carriesPowerup;
	
	public Villager() {
		y = GROUND;
		visible = true;
		carriesPowerup = false;
	}
	
	public void update() {
		x = x + speedX;
		if(x > 480) {
			setVisible(false);
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSpeedX() {
		return speedX;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isCarriesPowerup() {
		return carriesPowerup;
	}

	public void setCarriesPowerup(boolean carriesPowerup) {
		this.carriesPowerup = carriesPowerup;
	}

}
