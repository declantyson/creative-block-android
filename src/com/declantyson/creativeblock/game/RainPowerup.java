package com.declantyson.creativeblock.game;

import java.util.ArrayList;

import com.declantyson.creativeblock.Assets;

public class RainPowerup extends Powerup {

	public RainPowerup(int x, int y) {
		super(x, y);
		sprite = Assets.rainSprite;
		smallSprite = Assets.rainSpriteSmall;
	}
	
	@Override
	public void activate(ArrayList<Slug> activeSlugs, ArrayList<Plant> activePlants) {
		for(Plant plant: activePlants) {
			if(!plant.isDead()) {
				plant.setWater(1000);
			}
		}
	}

}
