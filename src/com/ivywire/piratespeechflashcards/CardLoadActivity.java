package com.ivywire.piratespeechflashcards;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.ivywire.piratespeechflashcards.database.CardDatabaseHelper;
import com.ivywire.piratespeechflashcards.database.FlashCardTable;
import com.ivywire.piratespeechflashcards.contentprovider.MyCardContentProvider;

import android.net.Uri;
import android.os.AsyncTask;
import android.app.LoaderManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Loader;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;

public class CardLoadActivity extends Activity {
	
	private Uri cardUri;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card_load);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.card_load, menu);
		return true;
	}
	
	public String makeCardString(InputStream input){
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		StringBuilder out = new StringBuilder();
		String str = "";
		try {
			while ((str = reader.readLine()) != null) {
			    out.append(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return str;
	}
	
	// AsyncTask portion
	private class LoadCardsTask extends AsyncTask<CardDatabaseHelper, Integer, Void>{
		public String[] cards;
		public String[] cardElements;
		public void onPreExecute(){
			//Prepare any pre-database query stuff, aka algorithm for getting words in txt and putting them 
			//into stored arrays
			AssetManager assetManager = getAssets();
			InputStream iS = null;
			try {
				iS = assetManager.open("cards.txt"); 
				String cardString = makeCardString(iS);
				iS.close();
				
		    } catch (IOException e) {
		    	e.printStackTrace();
		    }
		}
		
		protected Void doInBackground(CardDatabaseHelper... db) {
			String category;
			String title;
			String sentence;
			String definition;
			
			//Insert to tables 
			ContentValues values = new ContentValues();
			values.put(FlashCardTable.COLUMN_CATEGORY, category);
			values.put(FlashCardTable.COLUMN_TITLE, title);
			values.put(FlashCardTable.COLUMN_SENTENCE, sentence);
			values.put(FlashCardTable.COLUMN_DEFINITION, definition);
			
			getContentResolver().insert(MyCardContentProvider.CONTENT_URI, values);
			
			return null;
		}
		
		protected void onProgressUpdate(Integer... progress) {
	        //setProgressPercent(progress[0]);
	    }

		protected void onPostExecute(Long result) {
	       //Direct to MainActivity (menu)
	    }
	}
}
