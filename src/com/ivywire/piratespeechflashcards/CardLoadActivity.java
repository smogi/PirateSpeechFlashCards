package com.ivywire.piratespeechflashcards;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.ivywire.piratespeechflashcards.database.CardDatabaseHelper;
import com.ivywire.piratespeechflashcards.database.FlashCardTable;
import com.ivywire.piratespeechflashcards.contentprovider.MyCardContentProvider;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.AssetManager;
import android.view.Menu;

public class CardLoadActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card_load);	
		LoadCardsTask task = new LoadCardsTask();
		task.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.card_load, menu);
		return true;
	}
	
	public String makeCardString(InputStream input){
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line;
		
		try {
 
			br = new BufferedReader(new InputStreamReader(input));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
 
		return sb.toString();
	}
	
	// AsyncTask portion
	private class LoadCardsTask extends AsyncTask<CardDatabaseHelper, Integer, Void>{
		private String[] cards = new String[450];
		private String[] cardElements = new String[3];
		
		public void onPreExecute(){
			//Prepare any pre-database query stuff, aka algorithm for getting words in txt and putting them 
			//into stored arrays
			AssetManager assetManager = getAssets();
			InputStream iS = null;
			try {
				iS = assetManager.open("cards.txt"); 
				//cardString is null in next line for some reason. Why? Why? Why? Why? 
				//Is it because of the assetManager? <--(Likely not)
				//Or the makeCardString method? <-- Likely this
				String cardString = "";
				cardString = makeCardString(iS);
				iS.close();
				cards = cardString.split(";");	
		    } catch (IOException e) {
		    	e.printStackTrace();
		    }
		}
		
		protected Void doInBackground(CardDatabaseHelper... db) {
			String title;
			String category;
			String sentence;
			String definition;
			
			for(int i = 0; i < cards.length; i++){
				//Insert to tables 
				cardElements = cards[i].split("=");
				
				title = cardElements[0];
				category = cardElements[1];
				sentence = cardElements[2];
				definition = cardElements[3];
				
				ContentValues values = new ContentValues();
				values.put(FlashCardTable.COLUMN_CATEGORY, category);
				values.put(FlashCardTable.COLUMN_TITLE, title);
				values.put(FlashCardTable.COLUMN_SENTENCE, sentence);
				values.put(FlashCardTable.COLUMN_DEFINITION, definition);
				
				getContentResolver().insert(MyCardContentProvider.CONTENT_URI, values);
			}
			return null;
		}
		
		protected void onProgressUpdate(Integer... progress) {
	        //setProgressPercent(progress[0]);
	    }

		protected void onPostExecute(Void result) {
			//Direct to MainActivity (menu)
			startActivity(new Intent(CardLoadActivity.this, MainActivity.class));
	    }
	}
}
