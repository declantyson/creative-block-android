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
	public int timer;
	
	public int score;
	private Paint paint, paint2;
	public Difficulty difficulty;
	public CreativeBlock cb;
	public String playername;
	
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
		}
		
		score=1;
		
		difficulty = d;
		
		timer = -1;
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
