package com.declantyson.creativeblock;

import java.util.Map;

public class Difficulty {

	public int slugDamage, superSlugDamage, maxSlugs, 
				defaultSlugThreshold, slugThreshold1, slugThreshold2, slugThreshold3,
				defaultSuperSlugThreshold, superSlugThreshold1, superSlugThreshold2, superSlugThreshold3,
				powerupThreshold, waterSpeed;
	
	public String diffName;
	
	public Difficulty(String diff) {
		diffName = diff;
		if(diff == "easy") {
			superSlugDamage = 250;
			slugDamage = 100;
			defaultSlugThreshold = 100;
			defaultSuperSlugThreshold = 9999;
			slugThreshold1 = 150;
			slugThreshold2 = 200;
			slugThreshold3 = 250;
			superSlugThreshold1 = 9995;
			superSlugThreshold2 = 9990;
			superSlugThreshold3 = 9980;
			powerupThreshold = 10;
			maxSlugs = 5;
			waterSpeed = 1;
		} else if(diff == "med") {
			superSlugDamage = 500;
			slugDamage = 100;
			defaultSlugThreshold = 80;
			defaultSuperSlugThreshold = 9990;
			slugThreshold1 = 120;
			slugThreshold2 = 160;
			slugThreshold3 = 200;
			superSlugThreshold1 = 9975;
			superSlugThreshold2 = 9950;
			superSlugThreshold3 = 9900;
			powerupThreshold = 5;
			maxSlugs = 10;
			waterSpeed = 1;
		} else if(diff == "hard") {
			superSlugDamage = 500;
			slugDamage = 200;
			defaultSlugThreshold = 100;
			defaultSuperSlugThreshold = 9950;
			slugThreshold1 = 120;
			slugThreshold2 = 160;
			slugThreshold3 = 200;
			superSlugThreshold1 = 9900;
			superSlugThreshold2 = 9850;
			superSlugThreshold3 = 9800;
			powerupThreshold = 0;
			maxSlugs = 20;
			waterSpeed = 1;
		} else if(diff == "debug") {
			superSlugDamage = 0;
			slugDamage = 0;
			defaultSlugThreshold = 80;
			defaultSuperSlugThreshold = 9980;
			slugThreshold1 = 120;
			slugThreshold2 = 160;
			slugThreshold3 = 200;
			superSlugThreshold1 = 9925;
			superSlugThreshold2 = 9875;
			superSlugThreshold3 = 9800;
			powerupThreshold = 10;
			maxSlugs = 10;
			waterSpeed = 0;
		}
	}

	public int getSuperSlugDamage() {
		return superSlugDamage;
	}

	public int getSlugDamage() {
		return slugDamage;
	}

	public int getDefaultSlugThreshold() {
		return defaultSlugThreshold;
	}

	public int getSlugThreshold1() {
		return slugThreshold1;
	}

	public int getSlugThreshold2() {
		return slugThreshold2;
	}

	public int getSlugThreshold3() {
		return slugThreshold3;
	}

	public int getDefaultSuperSlugThreshold() {
		return defaultSuperSlugThreshold;
	}

	public int getSuperSlugThreshold1() {
		return superSlugThreshold1;
	}

	public int getSuperSlugThreshold2() {
		return superSlugThreshold2;
	}

	public int getSuperSlugThreshold3() {
		return superSlugThreshold3;
	}

	public int getPowerupThreshold() {
		return powerupThreshold;
	}

	public int getMaxSlugs() {
		return maxSlugs;
	}

	public String getDiffName() {
		return diffName;
	}

	
}
