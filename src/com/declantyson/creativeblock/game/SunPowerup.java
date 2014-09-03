package com.declantyson.creativeblock.game;

import java.util.ArrayList;

import com.declantyson.creativeblock.Assets;

public class SunPowerup extends Powerup {

	public SunPowerup(int x, int y) {
		super(x, y);
		sprite = Assets.sunSprite;
		smallSprite = Assets.sunSpriteSmall;
	}
	
	@Override
	public void activate(ArrayList<Slug> activeSlugs, ArrayList<Plant> activePlants) {
		for(Plant plant: activePlants) {
			if(!plant.isDead()) {
				plant.setHealth(1000);
			}
		}
	}

}
