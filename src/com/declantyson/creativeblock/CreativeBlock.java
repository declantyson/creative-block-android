package com.declantyson.creativeblock;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Looper;
import android.text.InputType;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.declantyson.creativeblock.R;
import com.declantyson.creativeblock.SubmitHighScoreScreen;
import com.declantyson.creativeblock.game.Plant;
import com.kilobolt.framework.Game;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.implementation.AndroidGame;

public class CreativeBlock extends AndroidGame {
	public static String playername;
	public Context mContext;
	public SubmitHighScoreScreen hss;
	public Game finishedGame;
	
	public CreativeBlock() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Screen getInitScreen() {
		setContentView(R.layout.defaultlayout);
		Assets.load(this);
		return new LoadingScreen(this, this);
	}
	
	public void goToMainMenuScreen(Game game) {
		this.setScreen(new MainMenuScreen(game, this));
	}
	
	public void goToGameScreen(Game game, String d) {
		game.setScreen(new GameScreen(game, new Difficulty(d), this));
	}

	public void goToGameOverScreen(Game game) {
		game.setScreen(new GameOverScreen(game, this));
	}
	
	public void goToSubmitHighScoreScreen(Game game, ArrayList<Plant> activePlants, Difficulty d) {
		finishedGame = game;
		this.highScoreAlert();
		hss = new SubmitHighScoreScreen(game, activePlants, d, this);
		finishedGame.setScreen(hss);
	}
	
	public void goToViewHighScoreScreen(Game game, int score, Difficulty d, String name) {
		game.setScreen(new ViewHighScoreScreen(game, score, d, name, this));
	}
	
	@Override
    public void onBackPressed() {
        getCurrentScreen().backButton();
    }
	
	public void highScoreAlert() {
		mContext = this;
		this.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
				
				alert.setTitle("Enter your name");
				alert.setMessage("Enter your name to send your high scores to the leaderboards");
		
				// Set an EditText view to get user input 
				final EditText input = new EditText(mContext);
				alert.setView(input);
		
				alert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					playername = input.getText().toString();
					hss.timer = 0;
					hss.playername = playername;
				  }
				});
				
				alert.setNegativeButton("No thanks", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						playername = "You";
						hss.timer = 1;
						hss.playername = playername;
					}
				});
				
				AlertDialog alertDialog = alert.create();
				alertDialog.show();
			}
		});
	}
}
