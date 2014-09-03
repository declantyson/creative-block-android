package com.declantyson.creativeblock.game;

import java.util.ArrayList;
import java.util.Iterator;

import com.declantyson.creativeblock.Assets;

public class PesticidePowerup extends Powerup {

	public PesticidePowerup(int x, int y) {
		super(x, y);
		sprite = Assets.pesticideSprite;
		smallSprite = Assets.pesticideSpriteSmall;
	}

	public void activate(ArrayList<Slug> activeSlugs, ArrayList<Plant> activePlants) {
		for(Slug slug: activeSlugs) {
			slug.die();
		}
	}
}
