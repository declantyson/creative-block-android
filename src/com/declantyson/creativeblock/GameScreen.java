package com.declantyson.creativeblock;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.declantyson.creativeblock.game.Background;
import com.declantyson.creativeblock.game.PesticidePowerup;
import com.declantyson.creativeblock.game.Plant;
import com.declantyson.creativeblock.game.Player;
import com.declantyson.creativeblock.game.Powerup;
import com.declantyson.creativeblock.game.RainPowerup;
import com.declantyson.creativeblock.game.Salt;
import com.declantyson.creativeblock.game.Slug;
import com.declantyson.creativeblock.game.SunPowerup;
import com.declantyson.creativeblock.game.SuperSlug;
import com.declantyson.creativeblock.game.Villager;
import com.declantyson.creativeblock.game.Water;
import com.kilobolt.framework.Animation;
import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Image;
import com.kilobolt.framework.Input.TouchEvent;
import com.kilobolt.framework.Screen;


public class GameScreen extends Screen {
	enum GameState {
		Ready, Running, Paused, GameOver, Winner
	}
	private Player playerOne = new Player();
	private ArrayList<Slug> activeSlugs = new ArrayList<Slug>();
	private ArrayList<Plant> activePlants = new ArrayList<Plant>();
	private ArrayList<Villager> activeVillagers = new ArrayList<Villager>();
	private ArrayList<Powerup> activePowerups = new ArrayList<Powerup>();
	
	private Background background = new Background(0,0);
	private Image image, characterFacingRight, characterFacingLeft, characterWateringLeft, characterWateringRight, currentSprite, bgImg, slugSprite, saltSprite, waterSprite, villagerSprite, shade, characterThinkingLeft, characterThinkingRight;
	private Animation characterWalkingRight, characterWalkingLeft, artPlant, musicPlant, blockAnim, superBlockAnim;
		
	private GameState state = GameState.Running;
	private int runningTime;
	private Paint paint, paint2;
	
	public CreativeBlock cb;
	private Difficulty difficulty;
	
	public GameScreen(Game game, Difficulty d, CreativeBlock c) {
		super(game);
		Assets.menuTheme.pause();
		Assets.theme.seekBegin();
		Assets.theme.play();
		
		difficulty = d;
		cb = c;
		
		characterFacingRight = Assets.characterFacingRight;
		characterFacingLeft = Assets.characterFacingLeft;
		characterWalkingRight = Assets.characterWalkingRight;
		characterWalkingLeft = Assets.characterWalkingLeft;
		characterThinkingRight = Assets.characterThinkingRight;
		characterThinkingLeft = Assets.characterThinkingLeft;
		
		characterWateringRight = Assets.characterWateringRight;
		characterWateringLeft = Assets.characterWateringLeft;

		bgImg = Assets.bgImg;
		shade = Assets.shade;
		
		waterSprite = Assets.waterSprite;
		slugSprite = Assets.slugSprite;
		saltSprite = Assets.saltSprite;
		villagerSprite = Assets.villagerSprite;
		
		blockAnim = Assets.blockAnimation;
		superBlockAnim = Assets.superBlockAnimation;
		artPlant = Assets.artPlant;
		musicPlant = Assets.musicPlant;
		currentSprite = characterFacingRight;

		for(int i=0;i<5;i++) {
			Random plantGenerator = new Random();
			int plantType = plantGenerator.nextInt(100);
			
			Plant plant = new Plant((96*i), 300);
			
			if(plantType > 50) {
				plant.setPlantAnim(musicPlant);
				plant.setSprite(Assets.musicPlantFinal);
			} else {
				plant.setPlantAnim(artPlant);
				plant.setSprite(Assets.artPlantFinal);
			}
			plant.setDeadSprite(Assets.deadPlant);
			plant.setColumn(i);
			plant.setWaterSpeed(difficulty.waterSpeed);
			
			activePlants.add(plant);
		}
		
		paint = new Paint();
		paint.setTextSize(30);
		paint.setTextAlign(Paint.Align.RIGHT);
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);
		
		paint2 = new Paint();
		
		animate();
	}

	private void animate() {
		characterWalkingRight.update(20);
		characterWalkingLeft.update(20);
		
		artPlant.update(5);
		musicPlant.update(5);
		blockAnim.update(3);
		superBlockAnim.update(3);
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		
		if (state == GameState.Ready)
            updateReady(touchEvents);
        if (state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        /*if (state == GameState.Paused)
            updatePaused(touchEvents);*/
        if (state == GameState.GameOver)
            updateGameOver(touchEvents);
	}
	
	private boolean inBounds(TouchEvent event, int x, int y, int width,
		int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}
	
	void updateGameOver(List touchEvents) {
		cb.goToGameOverScreen(game);
	}
	
	void updateRunning(List touchEvents, float deltaTime) {
		runningTime++;
		
		if(runningTime >= 100) {
			cb.goToSubmitHighScoreScreen(game, activePlants, difficulty);
			return;
		}
		
		int deathToll = 0;
		for(Plant plant: activePlants) {
			for(Iterator<Slug> it2 = activeSlugs.iterator(); it2.hasNext(); ) {
				Slug slug = it2.next();
				boolean collision = Rect.intersects(slug.collisionRect, plant.collisionRect);
				if(collision) {
					if(!plant.isDead()) {
						if(slug instanceof SuperSlug) {
							plant.setHealth(plant.getHealth() - difficulty.getSuperSlugDamage());							
						} else {
							plant.setHealth(plant.getHealth() - difficulty.getSlugDamage());
						}
						plant.update();
					}
					it2.remove();
				}
			}
			plant.update();
			if(plant.isDead()) deathToll++;
		}
		
		if(deathToll >= 5) { 
			state = GameState.GameOver;
			return;
		}
		
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = (TouchEvent) touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if(inBounds(event, 0, 680, 120, 120)) {
					playerOne.setDir(0);
					playerOne.moveLeft();
					currentSprite = characterWalkingLeft.getImage();
				} else if(inBounds(event, 360, 680, 120, 120)) {
					playerOne.setDir(1);
					playerOne.moveRight();
					currentSprite = characterWalkingRight.getImage();
				} else if(inBounds(event, 240, 680, 120, 120)) {
					playerOne.shoot();
					if(playerOne.getDir() == 1) {
						currentSprite = characterThinkingRight;
					} else {
						currentSprite = characterThinkingLeft;
					}
				} else if(inBounds(event, 120, 680, 120, 120)) {
					playerOne.water();
					if(playerOne.getDir() == 1) {
						currentSprite = characterWateringRight;
					} else {
						currentSprite = characterWateringLeft;
					}
				}
			} else if(event.type == TouchEvent.TOUCH_UP) {
				playerOne.stop();
				if(playerOne.getDir() == 1) {
					currentSprite = characterFacingRight;
				} else {
					currentSprite = characterFacingLeft;
				}			
			}
		}
		
		Random slugGenerator = new Random();
		Random villagerGenerator = new Random();
		Random powerupGenerator = new Random();
		Random columnGenerator = new Random();
		
		int slugType = slugGenerator.nextInt(10000);
		int slugThreshold = difficulty.getDefaultSlugThreshold();
		int superSlugThreshold = difficulty.getDefaultSuperSlugThreshold();
		
		int villagerType = villagerGenerator.nextInt(5000);
		int villagerThreshold = 10;
		int powerupThreshold = difficulty.getPowerupThreshold();
		
		switch(deathToll) {
			case 1:
				slugThreshold = difficulty.getSlugThreshold1();
				superSlugThreshold = difficulty.getSuperSlugThreshold1();
			break;
			case 2:
				slugThreshold = difficulty.getSlugThreshold2();
				superSlugThreshold = difficulty.getSuperSlugThreshold1();
			break;
			case 3:
				slugThreshold = difficulty.getSlugThreshold3();
				superSlugThreshold = difficulty.getSuperSlugThreshold1();
			break;
			
			// You're about to die
			// Ridiculous super-slug spawn rate to stop it getting too easy
			
			// Same on all difficulties!
			case 4:
				slugThreshold = 0;
				superSlugThreshold = 0;
			break;
		}
		
		
		int spawnColumn = -1;
		if(deathToll < 5) {
			while(spawnColumn == -1) {
				int i = columnGenerator.nextInt(5);
				for(Plant plant: activePlants) {
					if(i == plant.getColumn() && !plant.isDead()) spawnColumn = i;
				}
			}
		}
		
		if(activeSlugs.size() <= difficulty.getMaxSlugs()) {
			if(slugType < slugThreshold) {
				Slug slug = new Slug(spawnColumn, 820);
				slug.setSlugAnim(blockAnim);
				activeSlugs.add(slug);
			} else if (slugType > superSlugThreshold) {
				SuperSlug superSlug = new SuperSlug(spawnColumn, 820);
				superSlug.setSlugAnim(superBlockAnim);
				activeSlugs.add(superSlug);
			}
		}
		
		if(activeVillagers.size() < 1) {
			if(villagerType < powerupThreshold) {
				Villager villager = new Villager();
				villager.setX(-40);
				villager.setCarriesPowerup(true);
				activeVillagers.add(villager);
			} else if (villagerType < villagerThreshold) {
				Villager villager = new Villager();
				villager.setX(-40);
				activeVillagers.add(villager);
			}
		}
		
		playerOne.update();
		for(Slug slug: activeSlugs) {
			slug.update();
		}

		for(Iterator<Villager> it = activeVillagers.iterator(); it.hasNext(); ) { 
			Villager villager = it.next();
			villager.update();
			
			if(villager.isCarriesPowerup() && villager.getX() > 60 && villager.getX() < 420) {
				int powerupType = powerupGenerator.nextInt(5000);
				int sunThreshold = 20;
				int rainThreshold = 40;
				int pesticideThreshold = 60;
				
				if(powerupType < sunThreshold) {
					SunPowerup powerup = new SunPowerup(villager.getX(), villager.getY());
					activePowerups.add(powerup);
										
					villager.setCarriesPowerup(false);
				} else if(powerupType < rainThreshold) {
					RainPowerup powerup = new RainPowerup(villager.getX(), villager.getY());
					activePowerups.add(powerup);
										
					villager.setCarriesPowerup(false);
				} else if(powerupType < pesticideThreshold) {
					PesticidePowerup powerup = new PesticidePowerup(villager.getX(), villager.getY());
					activePowerups.add(powerup);
										
					villager.setCarriesPowerup(false);
				}
			}
			
			if(!villager.isVisible()) {
				it.remove();
			}
		}
		
		for(Iterator<Powerup> it = activePowerups.iterator(); it.hasNext(); ) { 
			Powerup powerup = it.next();
			boolean collision = Rect.intersects(playerOne.collisionRect, powerup.collisionRect);
			if(collision) {
				powerup.setVisible(false);
				powerup.activate(activeSlugs, activePlants);
			}
			powerup.update();
			if(!powerup.isVisible()) {
				it.remove();
			}
		}
		
		for(Iterator<Salt> it = playerOne.getActiveSalts().iterator(); it.hasNext(); ) { 
			Salt salt = it.next();
			for(Iterator<Slug> it2 = activeSlugs.iterator(); it2.hasNext(); ) {
				Slug slug = it2.next();
				boolean collision = Rect.intersects(slug.collisionRect, salt.collisionRect);
				if(collision) {
					salt.setVisible(false);
					slug.die();
					it2.remove();
				}
			}
			if(salt.isVisible()) {
				salt.update();
			} else {
				it.remove();
			}
		}
		
		for(Iterator<Slug> it = activeSlugs.iterator(); it.hasNext(); ) {
			Slug slug = it.next();
			if(!slug.isAlive()) {
				it.remove();
			}
		}
		
		for(Iterator<Water> it = playerOne.getActiveWater().iterator(); it.hasNext(); ) { 
			Water water = it.next();
			for(Iterator<Plant> it2 = activePlants.iterator(); it2.hasNext(); ) {
				Plant plant = it2.next();
				boolean collision = Rect.intersects(plant.collisionRect, water.collisionRect);
				if(collision) {
					water.setVisible(false);
					plant.setWater(plant.getWater() + 250);
					plant.update();
				}
			}
			if(water.isVisible()) {
				water.update();
			} else {
				it.remove();
			}
		}
		animate();
		
	}


	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawImage(bgImg, 0, background.getBgY());
		if(difficulty.diffName == "debug") g.drawString("" + runningTime, 450, 60, paint);
		
		for(Villager villager: activeVillagers) {
			g.drawImage(villagerSprite, villager.getX(), villager.getY());
		}
		
		for(Powerup powerup: activePowerups) {
			if(powerup.direction == "up") {
				g.drawImage(powerup.smallSprite, powerup.getPosX(), powerup.getPosY());
			}
		}
		
		if(playerOne.isMoving()) {
			int dir = playerOne.getDir();
			if(dir == 1) {
				currentSprite = characterWalkingRight.getImage();
			} else {
				currentSprite = characterWalkingLeft.getImage();
			}
		}
		
		g.drawImage(currentSprite, playerOne.getPosX() - 35, playerOne.getPosY() - 58);
		ArrayList<Salt> activeSalts = playerOne.getActiveSalts();
		ArrayList<Water> activeWater = playerOne.getActiveWater();
		
		for(Powerup powerup: activePowerups) {
			if(powerup.direction == "down") {
				g.drawImage(powerup.sprite, powerup.getPosX(), powerup.getPosY());
			}
		}
		
		for(Plant plant: activePlants) {
			if(plant.dead) {
				g.drawImage(plant.getDeadSprite(), plant.getPosX(), plant.getPosY());
			} else {
				g.drawImage(plant.getPlantAnim().getImage(), plant.getPosX(), plant.getPosY());
			}
			g.drawRect(plant.getPosX() + 55, plant.getPosY() + 175, 10, (int) -(Math.round(0.05*plant.getHealth())), Color.GREEN);
			g.drawRect(plant.getPosX() + 65, plant.getPosY() + 175, 10, (int) -(Math.round(0.05*plant.getWater())), Color.CYAN);
		}
		
		for(Slug slug: activeSlugs) {
			g.drawImage(slug.getSlugAnim().getImage(), slug.getPosX(), slug.getPosY());
		}
		
		for(Salt salt: activeSalts) {
			g.drawImage(saltSprite, salt.getX(), salt.getY());
		}
		
		for(Water water: activeWater) {
			g.drawImage(waterSprite, water.getX(), water.getY());
		}
		
		// Draw UI
		//g.drawImage(shade, 0, 0);
		
		g.drawImage(Assets.controls, 0, 680);
		
		/*g.drawImage(Assets.leftIcon, 20, 730);
		g.drawImage(Assets.rightIcon, 410, 730);
		g.drawImage(Assets.shootIcon, 340, 730);
		g.drawImage(Assets.shootWaterIcon, 90, 730);*/
		
	}
	
	private void updateReady(List touchEvents) {
		//if(touchEvents.size() > 0) state = GameState.Running;
	}
	
	private void nullify() {
		paint = null;
		System.gc();
	}

	@Override
	public void pause() {
		Assets.theme.pause();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		Assets.theme.play();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	


	@Override
	public void backButton() {
		pause();
	}

}
