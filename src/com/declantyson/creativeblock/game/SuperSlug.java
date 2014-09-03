package com.declantyson.creativeblock.game;

public class SuperSlug extends Slug {

	public SuperSlug(int column, int posY) {
		setSpeedY(-1);
		setColumn(column);
		setPosY(posY);
		setPosX((column * 96) + 8);
	}

}
