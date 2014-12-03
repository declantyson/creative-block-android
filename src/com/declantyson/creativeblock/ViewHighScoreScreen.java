package com.declantyson.creativeblock;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.util.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import android.graphics.Color;
import android.graphics.Paint;
import android.net.ParseException;
import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.Input.TouchEvent;

public class ViewHighScoreScreen extends Screen {
	public boolean playersScoreIsInTopTen = false;
	public JSONArray scores;
	public CreativeBlock cb;
	private Paint paint;
	private String playername;
	private int playerscore, timer;
	
	public ViewHighScoreScreen(Game game, int score, Difficulty difficulty, String name, CreativeBlock creativeblock) {
		super(game);
		
		paint = new Paint();
		paint.setTextSize(25);
		paint.setColor(Color.LTGRAY);
		paint.setTextAlign(Paint.Align.LEFT);
		paint.setAntiAlias(true);
		
		playername = name;
		if(playername == "" || playername == null) playername = "You";
		playerscore = score;
		
		cb = creativeblock;
		timer = 0;
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(SOMEURL);
		httpget.setHeader("Accept", "application/json");
		try {
			HttpResponse response = httpclient.execute(httpget);
			if(response != null)
			{
				String data = EntityUtils.toString(response.getEntity());
				scores = new JSONArray(data);
				for(int i = 0; i < scores.length(); i++) {
					JSONObject jsonObject = scores.getJSONObject(i);
					/*Log.d("Player name", playername);
					Log.d("Player HS name", jsonObject.getString("name"));*/
					if(jsonObject.getString("name").equalsIgnoreCase(playername) && jsonObject.getString("score").equalsIgnoreCase(Integer.toString(playerscore))) {
						playersScoreIsInTopTen = true;
					}
				}
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
            	cb.goToMainMenuScreen(game);
            }
		}
		
		timer++;
		Graphics g = game.getGraphics();
		g.drawImage(Assets.highScoreBg, 0, 0);
		for(int i = 0; i < scores.length(); i++) {
			try {
				outputScore(scores.getJSONObject(i), i);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(!playersScoreIsInTopTen && playerscore != -1) {
			paint.setColor(Color.WHITE);
			g.drawString("" + playername, 120, 680, paint);
			g.drawString("" + playerscore, 300, 680, paint);
		}
		
		if(timer >= 250) {
			cb.goToMainMenuScreen(game);
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

	private void outputScore(JSONObject jsonObject, int i) {
		Graphics g = game.getGraphics();
		try {
			paint.setColor(Color.LTGRAY);
			if(jsonObject.getString("name").equalsIgnoreCase(playername) && jsonObject.getString("score").equalsIgnoreCase(Integer.toString(playerscore))) {
				paint.setColor(Color.WHITE);
			}
			g.drawString("" + jsonObject.getString("name"), 120, 200+(i*40), paint);
			g.drawString("" + jsonObject.getString("score"), 300, 200+(i*40), paint);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*try {
			g.drawString("" + jsonArray.getString("score"), 240, (i*40), paint);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/		
	}

}
