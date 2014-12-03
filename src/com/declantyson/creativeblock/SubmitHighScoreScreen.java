package com.declantyson.creativeblock;


import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Looper;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.declantyson.creativeblock.game.Plant;
import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Screen;

public class SubmitHighScoreScreen extends Screen {
	public ArrayList<Plant> finalPlants;
	public int timer, musicCount, artCount, bookCount, summaryCount;
	
	public int score;
	private Paint paint, paint2;
	public Difficulty difficulty;
	public CreativeBlock cb;
	public String playername, artDescription1, artDescription2, musicDescription1, musicDescription2, bookDescription1, bookDescription2;
	
	public SubmitHighScoreScreen(Game game, ArrayList<Plant> activePlants, Difficulty d, CreativeBlock c) {
		super(game);
		finalPlants = activePlants;	
		cb = c;
		
		paint = new Paint();
		paint.setTextSize(25);
		paint.setTextAlign(Paint.Align.LEFT);
		paint.setAntiAlias(true);
		
		for(Plant plant: finalPlants) {
			score += plant.getWater();
			score += plant.getHealth();
			
			if(plant.type == 1) artCount++;
			if(plant.type == 2) musicCount++;
			if(plant.type == 3) bookCount++;
		}
		
		difficulty = d;
		
		timer = -1;
		
		switch(artCount) {
			case 1:
				artDescription1 = "One of the villagers has hung your painting";
				artDescription2 = "on their fridge.";
				break;
			case 2:
				artDescription1 = "Your paintings are shown at a town exhibition.";
				artDescription2 = "";
				break;
			case 3:
				artDescription1 = "Celebrating your fantastic art, many townspeople";
				artDescription2 = "are inspired to paint whenever they see a fruit bowl.";
				break;
			case 4:
				artDescription1 = "The Louvre has bought one of your paintings!";
				artDescription2 = "";
				break;
			case 5:
				artDescription1 = "An art gallery has been opened in your honour";
				artDescription2 = "and you are pronounced Pablo Leonardo de Van Gogh.";
				break;
		}
				
		switch(musicCount) {
			case 1:
				musicDescription1 = "You earnt a couple of quid busking on the tube.";
				musicDescription2 = "";
				break;
			case 2:
				musicDescription1 = "You landed a pub gig! People drank and were";
				musicDescription2 = "merry to your music";
				break;
			case 3:
				musicDescription1 = "Your song was featured on a hit radio show.";
				musicDescription2 = "Adoring fans are covering your recordings.";
				break;
			case 4:
				musicDescription1 = "Your record's gone gold and you're selling";
				musicDescription2 = "out gigs across the country!";
				break;
			case 5:
				musicDescription1 = "You perform the first live concert on the moon.";
				musicDescription2 = "";
				break;
		}
		
		switch(bookCount) {
			case 1:
				bookDescription1 = "You self-publish a novella, and it sells";
				bookDescription2 = "a few copies.";
				break;
			case 2:
				bookDescription1 = "You're offered a publishing contract!";
				bookDescription2 = "Enjoy those royalties!";
				break;
			case 3:
				bookDescription1 = "Your story is a hit and people eagerly wait";
				bookDescription2 = "five years for the sequel.";
				break;
			case 4:
				bookDescription1 = "Your 200-page book is converted into seven";
				bookDescription2 = "seperate 3-hour long films!";
				break;
			case 5:
				bookDescription1 = "In 1000 years, the world's predominant religion";
				bookDescription2 = "will be founded on your works of fiction.";
				break;
		}
	}

	@Override
	public void update(float deltaTime) {
		if(timer == 0) {
			SubmitHighScore();
		}
		if(timer != -1) {
		
			timer++;
			Graphics g = game.getGraphics();
			
			g.drawImage(Assets.scoreTallyBg, 0, 0);
			
			summaryCount = 0;
			
			paint.setColor(Color.WHITE);
			paint.setTextAlign(Paint.Align.LEFT);
			paint.setTextSize(20);
			
			if(artCount > 0) {
				g.drawString(artDescription1, 20, 540 + (70*summaryCount), paint);
				g.drawString(artDescription2, 20, 565 + (70*summaryCount), paint);
				summaryCount++;
			}
			
			if(musicCount > 0) {
				g.drawString(musicDescription1, 20, 540 + (70*summaryCount), paint);
				g.drawString(musicDescription2, 20, 565 + (70*summaryCount), paint);
				summaryCount++;
			}
			
			if(bookCount > 0) {
				g.drawString(bookDescription1, 20, 540 + (70*summaryCount), paint);
				g.drawString(bookDescription2, 20, 565 + (70*summaryCount), paint);
				summaryCount++;
			}
			
			int i = 0;
			for(Plant plant: finalPlants) {
				if(plant.dead) {
					g.drawImage(plant.getDeadSprite(), plant.getPosX(), plant.getPosY());
				} else {
					g.drawImage(plant.getSprite(), plant.getPosX(), plant.getPosY());
				}
				g.drawRect(plant.getPosX() + 55, plant.getPosY() + 175, 10, (int) -(Math.round(0.05*plant.getHealth())), Color.GREEN);
				g.drawRect(plant.getPosX() + 65, plant.getPosY() + 175, 10, (int) -(Math.round(0.05*plant.getWater())), Color.CYAN);
				
				if(timer >= 100 && timer < 500){
					paint.setColor(Color.CYAN);
					g.drawString("" + plant.getWater(), (i*96)+10, 155, paint);
				}			
				
				if(timer >= 200 && timer < 500){
					paint.setColor(Color.GREEN);
					g.drawString("" + plant.getHealth(), (i*96)+10, 185, paint);
				}
				
				if(timer >= 300 && timer < 500){
					paint.setColor(Color.WHITE);
					g.drawString("" + (plant.getWater() + plant.getHealth()), (i*96)+10, 225, paint);
				}
				
				if(timer >= 500) {
					paint.setColor(Color.WHITE);
					paint.setTextAlign(Paint.Align.CENTER);
					paint.setTextSize(40);
					g.drawString("" + score, 240, 175, paint);
				}
				i++;
			
				if(timer >= 700) {
					cb.goToViewHighScoreScreen(game, score, difficulty, playername);
				}
			}
		}
	}
	
	public void SubmitHighScore() {
		HttpClient httpclient = new DefaultHttpClient();
		if(playername == "") return;
		HttpGet httpget = new HttpGet(SOMEURL);
		try {
			HttpResponse response = httpclient.execute(httpget);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void paint(float deltaTime) {
		// TODO Auto-generated method stub
		
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
