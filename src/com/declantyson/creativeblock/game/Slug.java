package com.declantyson.creativeblock.game;

import android.graphics.Rect;

import com.kilobolt.framework.Animation;

public class Slug {
	private boolean alive = true;
	private double speedY, posY;
	private int column, posX;
	public Animation slugAnim;
	
	public Rect collisionRect = new Rect(0,0,0,0);
		
	public Slug() {}
	
	public Slug(int column, int posY) {
		setSpeedY(-0.5);
		setColumn(column);
		setPosY(posY);
		setPosX((column * 96) + 8);
	}
	
	public void update() {
		posY += speedY;
		collisionRect.set(posX, (int)posY, posX + 80, (int)posY + 84);
	}
	
	public void die() {
		alive = false;
	}

	public boolean isAlive() {
		return alive;
	}

	public int getSpeedY() {
		return (int) speedY;
	}

	public int getColumn() {
		return column;
	}

	public int getPosY() {
		return (int) posY;
	}
	
	public int getPosX() {
		return posX;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public void setSpeedY(double d) {
		this.speedY = d;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}

	public Rect getCollisionRect() {
		return collisionRect;
	}

	public void setCollisionRect(Rect collisionRect) {
		this.collisionRect = collisionRect;
	}

	public Animation getSlugAnim() {
		return slugAnim;
	}

	public void setSlugAnim(Animation slugAnim) {
		this.slugAnim = slugAnim;
	}
	
	
}
