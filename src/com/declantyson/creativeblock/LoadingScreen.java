package com.declantyson.creativeblock;

import android.app.Activity;
import android.content.Context;

import com.kilobolt.framework.Animation;
import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Graphics.ImageFormat;
import com.kilobolt.framework.Screen;

public class LoadingScreen extends Screen {
	public Game g;
	public CreativeBlock cb;
	
	public LoadingScreen(Game game, CreativeBlock c) {
		super(game);
		g = game;
		cb = c;
	}

	@Override
	public void update(float deltaTime) {
		Assets.theme = game.getAudio().createMusic("game.mp3");
		Assets.theme.setLooping(true);
		Assets.theme.setVolume(0.85f);
		
		Assets.menuTheme = game.getAudio().createMusic("menu.mp3");
		Assets.menuTheme.setLooping(true);
		Assets.menuTheme.setVolume(0.85f);
		
		Assets.gameoverTheme = game.getAudio().createMusic("gameover.mp3");
		Assets.gameoverTheme.setLooping(true);
		Assets.gameoverTheme.setVolume(0.85f); 
		
		Graphics g = game.getGraphics();
		
		Assets.loading = g.newImage("loading.jpg", ImageFormat.RGB565);
		Assets.menu = g.newImage("menu.jpg", ImageFormat.RGB565);
		Assets.scoreTallyBg = g.newImage("hiscorebgv1.png", ImageFormat.RGB565);
		Assets.highScoreBg = g.newImage("hiscorebgv2.png", ImageFormat.RGB565);
		Assets.characterFacingRight = g.newImage("StanleyStandingRight.png", ImageFormat.RGB565);
		Assets.characterFacingLeft = g.newImage("StanleyStandingLeft.png", ImageFormat.RGB565);
		Assets.characterThinkingRight = g.newImage("StanleyThinkingRight.png", ImageFormat.RGB565);
		Assets.characterThinkingLeft = g.newImage("StanleyThinkingLeft.png", ImageFormat.RGB565);
		Assets.characterWateringRight = g.newImage("StanleyWateringRight.png", ImageFormat.RGB565);
		Assets.characterWateringLeft = g.newImage("StanleyWateringLeft.png", ImageFormat.RGB565);
		
		Animation walkRight = new Animation();
		walkRight.addFrame(g.newImage("stanleyWalkRight/frame1.PNG", ImageFormat.RGB565), 100);
		walkRight.addFrame(g.newImage("stanleyWalkRight/frame2.PNG", ImageFormat.RGB565), 100);
		walkRight.addFrame(g.newImage("stanleyWalkRight/frame3.PNG", ImageFormat.RGB565), 100);
		walkRight.addFrame(g.newImage("stanleyWalkRight/frame4.PNG", ImageFormat.RGB565), 100);
		
		Assets.characterWalkingRight = walkRight;
		
		Animation walkLeft = new Animation();
		walkLeft.addFrame(g.newImage("stanleyWalkLeft/frame1.PNG", ImageFormat.RGB565), 100);
		walkLeft.addFrame(g.newImage("stanleyWalkLeft/frame2.PNG", ImageFormat.RGB565), 100);
		walkLeft.addFrame(g.newImage("stanleyWalkLeft/frame3.PNG", ImageFormat.RGB565), 100);
		walkLeft.addFrame(g.newImage("stanleyWalkLeft/frame4.PNG", ImageFormat.RGB565), 100);
				
		Assets.characterWalkingLeft = walkLeft;
		
		Animation artPlantAnim = new Animation();
		for(int i = 1; i <= 30; i++) {
			artPlantAnim.addFrame(g.newImage("plants/frame" + i + ".png", ImageFormat.RGB565), 2000);
		}
			
		artPlantAnim.addFrame(g.newImage("plants/art/frame31.png", ImageFormat.RGB565), 2000);
		artPlantAnim.addFrame(g.newImage("plants/art/frame32.png", ImageFormat.RGB565), 2000);
		artPlantAnim.addFrame(g.newImage("plants/art/frame33.png", ImageFormat.RGB565), 2000);
		artPlantAnim.addFrame(g.newImage("plants/art/frame34.png", ImageFormat.RGB565), 2000);
		artPlantAnim.addFrame(g.newImage("plants/art/frame35.png", ImageFormat.RGB565), 2000);
		
		Assets.artPlant = artPlantAnim;
		Assets.artPlantFinal = g.newImage("plants/art/frame35.png", ImageFormat.RGB565);
		
		Animation musicPlantAnim = new Animation();
		for(int i = 1; i <= 30; i++) {
			musicPlantAnim.addFrame(g.newImage("plants/frame" + i + ".png", ImageFormat.RGB565), 2000);
		}
			
		musicPlantAnim.addFrame(g.newImage("plants/music/frame31.png", ImageFormat.RGB565), 2000);
		musicPlantAnim.addFrame(g.newImage("plants/music/frame32.png", ImageFormat.RGB565), 2000);
		musicPlantAnim.addFrame(g.newImage("plants/music/frame33.png", ImageFormat.RGB565), 2000);
		musicPlantAnim.addFrame(g.newImage("plants/music/frame34.png", ImageFormat.RGB565), 2000);
		musicPlantAnim.addFrame(g.newImage("plants/music/frame35.png", ImageFormat.RGB565), 2000);
		
		Assets.musicPlant = musicPlantAnim;
		Assets.musicPlantFinal = g.newImage("plants/music/frame35.png", ImageFormat.RGB565);
		
		Animation blockAnim = new Animation();
		for(int i = 1; i < 36; i++) {
			blockAnim.addFrame(g.newImage("block/frame" + i + ".png", ImageFormat.RGB565), 1);
		}
		for(int i = 72; i > 36; i--) {
			blockAnim.addFrame(g.newImage("block/frame" + i + ".png", ImageFormat.RGB565), 1);
		}
		
		Assets.blockAnimation = blockAnim;
		
		Animation superBlockAnim = new Animation();
		for(int i = 1; i < 36; i++) {
			superBlockAnim.addFrame(g.newImage("superblock/frame" + i + ".png", ImageFormat.RGB565), 1);
		}
		for(int i = 72; i > 36; i--) {
			superBlockAnim.addFrame(g.newImage("superblock/frame" + i + ".png", ImageFormat.RGB565), 1);
		}
		
		Assets.superBlockAnimation = superBlockAnim;

		Assets.bgImg = g.newImage("backgroundv3.png", ImageFormat.RGB565);
		Assets.gameOver = g.newImage("gameover.png", ImageFormat.RGB565);
		Assets.shade = g.newImage("shade.png", ImageFormat.RGB565);
		
		Assets.waterSprite = g.newImage("raindrop-multi.png", ImageFormat.RGB565);
		Assets.slugSprite = g.newImage("slug.png", ImageFormat.RGB565);
		Assets.saltSprite = g.newImage("brainwave.png", ImageFormat.RGB565);
		Assets.villagerSprite = g.newImage("nikoo.png", ImageFormat.RGB565);
		//Assets.plant = g.newImage("box.png", ImageFormat.RGB565);
		Assets.deadPlant = g.newImage("deadbox.png", ImageFormat.RGB565);
		
		Assets.sunSprite = g.newImage("sun.png", ImageFormat.RGB565);
		Assets.sunSpriteSmall = g.newImage("sun_small.png", ImageFormat.RGB565);
		Assets.rainSprite = g.newImage("rain.png", ImageFormat.RGB565);
		Assets.rainSpriteSmall = g.newImage("rain_small.png", ImageFormat.RGB565);
		Assets.pesticideSprite = g.newImage("idea.png", ImageFormat.RGB565);
		Assets.pesticideSpriteSmall = g.newImage("idea_small.png", ImageFormat.RGB565);
		
		Assets.controls = g.newImage("controls.png", ImageFormat.RGB565);
		
		/*Assets.leftIcon = g.newImage("left.png",ImageFormat.RGB565);
		Assets.rightIcon = g.newImage("right.png",ImageFormat.RGB565);
		Assets.shootIcon = g.newImage("shoot.png",ImageFormat.RGB565);
		Assets.shootWaterIcon = g.newImage("shootwater.png", ImageFormat.RGB565);*/
		
		cb.goToMainMenuScreen(game);
	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.loading, 0, 0);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void backButton() {
		// TODO Auto-generated method stub
		
	}

}
