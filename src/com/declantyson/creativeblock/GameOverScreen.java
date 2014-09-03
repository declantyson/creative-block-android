package com.declantyson.creativeblock;

import java.util.List;

import android.app.Activity;
import android.content.Context;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Input.TouchEvent;
import com.kilobolt.framework.Screen;

public class GameOverScreen extends Screen {
	public CreativeBlock cb;
	
	public GameOverScreen(Game game, CreativeBlock c) {
		super(game);
		cb = c;
		Assets.theme.pause();
		Assets.gameoverTheme.seekBegin();
		Assets.gameoverTheme.play();
	}

	@Override
	public void update(float deltaTime) {
		
		
		Graphics g = game.getGraphics();
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 0, 0, 480, 800)) {
                    cb.goToGameOverScreen(game);             
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
		g.drawImage(Assets.gameOver, 0, 0);	
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
