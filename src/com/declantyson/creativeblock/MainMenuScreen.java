package com.declantyson.creativeblock;

import java.util.List;

import android.app.Activity;
import android.content.Context;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Input.TouchEvent;
import com.kilobolt.framework.Screen;

public class MainMenuScreen extends Screen {
	public Game g;
	public CreativeBlock cb;
	
	public MainMenuScreen(Game game, CreativeBlock c) {
		super(game);
		g = game;
		cb = c;
		Assets.gameoverTheme.pause();
		Assets.menuTheme.seekBegin();
		Assets.menuTheme.play();
		
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 54, 380, 160, 35)) {
                	cb.goToGameScreen(game, "easy");
                } else if (inBounds(event, 54, 480, 160, 35)) {
                	cb.goToGameScreen(game, "med"); 
                } else if (inBounds(event, 54, 580, 160, 35)) {
                	cb.goToGameScreen(game, "hard");               
                } else if (inBounds(event, 0, 0, 50, 50)) {
                	//cb.goToGameScreen(game, "debug");               
                } else if (inBounds(event, 54, 615, 160, 35)) {
                	Difficulty d = new Difficulty("hard");
                	cb.goToViewHighScoreScreen(cb, -1, d, "");  
                } else if (inBounds(event, 54, 515, 160, 35)) {
                	Difficulty d = new Difficulty("med");
                	cb.goToViewHighScoreScreen(cb, -1, d, "");  
                } else if (inBounds(event, 54, 415, 160, 35)) {
                	Difficulty d = new Difficulty("easy");
                	cb.goToViewHighScoreScreen(cb, -1, d, "");  
                } 
            }
		}
	}

    private boolean inBounds(TouchEvent event, int x, int y, int width,
            int height) {
        if (event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1)
            return true;
        else
            return false;
    }

	
	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.menu, 0, 0);		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		Assets.menuTheme.pause();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		Assets.menuTheme.play();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void backButton() {
		android.os.Process.killProcess(android.os.Process.myPid());
	}

}
