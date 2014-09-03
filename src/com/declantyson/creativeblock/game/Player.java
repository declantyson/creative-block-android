package com.declantyson.creativeblock.game;

import java.util.ArrayList;

import android.graphics.Rect;

public class Player {
	final int GROUND = 250;
	
	private int posX = 240;
	private int posY = GROUND;
	private boolean watering = false;
	private boolean moving = false;
	private int dir = 0;
	
	private ArrayList<Salt> activeSalts = new ArrayList<Salt>();
	private ArrayList<Water> activeWater = new ArrayList<Water>(); 
	
	private int speedX = 0;
	private int speedY = 1;

	public Rect collisionRect = new Rect(0,0,0,0);
	
	public void update() {
		
		// Moves Character or Scrolls Background accordingly.
		if (speedX < 0) {
			posX += speedX;
		} else if (speedX == 0) {
			

		} else {
			if (posX <= 420) {
				posX += speedX;
			} else {
			
			}
		}
		
		// Updates Y Position
		if (posY + speedY >= GROUND) {
			posY = GROUND;
		}else{                       
             posY += speedY;
        }

		// Prevents going beyond X coordinate of 0
		if (posX + speedX <= 20) {
			posX = 21;
		}
		
		collisionRect.set(posX, posY, posX + 102, posY + 166);
	}

	public void moveRight() {
		setMoving(true);
		speedX = 6;
	}

	public void moveLeft() {
		setMoving(true);
		speedX = -6;
	}

	public void stop() {
		setMoving(false);
		speedX = 0;
	}

	public void startWatering() {	
		if (watering == false) {
			setWatering(true);
		}
	}
	
	public void stopWatering() {		
		if (watering == true) {
			setWatering(false);
		}
	}
	
	public void shoot() {
		if(activeSalts.size() < 2) {
			Salt s = new Salt(posX, posY + 50);
			activeSalts.add(s);
		}
	}
	
	public void water() {
		if(activeWater.size() < 1) {
			Water w = new Water(posX, posY + 50);
			activeWater.add(w);
		}
	}
	
	public ArrayList<Salt> getActiveSalts() {
		return activeSalts;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public boolean isWatering() {
		return watering;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void setWatering(boolean watering) {
		this.watering = watering;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public ArrayList<Water> getActiveWater() {
		return activeWater;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	
	
}
