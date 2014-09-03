package com.declantyson.creativeblock.game;

import android.graphics.Rect;

import com.kilobolt.framework.Animation;
import com.kilobolt.framework.Image;
import java.net.URL;

public class Plant {
	public int posX, posY, health, water, column, waterSpeed;
	public boolean dead = false;
	public Image sprite, deadSprite;
	public Animation plantAnim;
	
	public Rect collisionRect = new Rect(0,0,0,0);
	
	public Plant(int x, int y) {
		setHealth(1000);
		setWater(1000);
		setPosX(x);
		setPosY(y);
		collisionRect.set(posX, posY + 109, posX + 70, posY + 177);
	}
	
	public void update() {
		if(water > 1000) water = 1000;
		if(health <= 0) dead = true;
		if(!dead) {
			setWater(water-waterSpeed);
			if(water <= 0) {
				dead = true;
			}
		} else {
			setWater(0);
			setHealth(0);
		}
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public int getHealth() {
		return health;
	}

	public int getWater() {
		return water;
	}

	public Image getSprite() {
		return sprite;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setWater(int water) {
		this.water = water;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public Image getDeadSprite() {
		return deadSprite;
	}

	public void setDeadSprite(Image deadSprite) {
		this.deadSprite = deadSprite;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public Animation getPlantAnim() {
		return plantAnim;
	}

	public void setPlantAnim(Animation plantAnim) {
		this.plantAnim = plantAnim;
	}

	public int getWaterSpeed() {
		return waterSpeed;
	}

	public void setWaterSpeed(int waterSpeed) {
		this.waterSpeed = waterSpeed;
	}

	
}
