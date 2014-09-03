package com.declantyson.creativeblock.game;

import java.util.ArrayList;

import com.kilobolt.framework.Image;

import android.graphics.Rect;

public class Powerup {
	public int posX, posY, speedY;
	public int timer = 200;
	public boolean visible = true;
	public String direction = "up";
	public Image sprite, smallSprite;
	
	public Rect collisionRect = new Rect(0,0,0,0);
	
	public Powerup(int x, int y) {
		speedY = -5;
		posX = x;
		posY= y;
	}
	
	public void activate(ArrayList<Slug> activeSlugs, ArrayList<Plant> activePlants) {
		
	}
	
	public void update() {
		posY = posY + speedY;
		
		if(posY <= -130) {
			setDirection("down");
			posX = posX - 25;
			setSpeedY(2);
		}
		
		if(direction == "down") {
			collisionRect.set(posX, posY, posX + 100, posY + 100);
			timer = timer-1;
			if(timer <= 0) {
				setVisible(false);
			}
		}
		
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public int getSpeedY() {
		return speedY;
	}

	public int getTimer() {
		return timer;
	}

	public boolean isVisible() {
		return visible;
	}

	public String getDirection() {
		return direction;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	
}
