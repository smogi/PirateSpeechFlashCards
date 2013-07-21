package com.ivywire.piratespeechflashcards.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CardDatabaseHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "cardtable.db";
	private static final int DATABASE_VERSION = 1;
	
	public CardDatabaseHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	// Method is called during the creation of the database
	public void onCreate(SQLiteDatabase database){
		FlashCardTable.onCreate(database);
	}
	
	// Method is called during the upgrade of the database
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
		FlashCardTable.onUpgrade(database, oldVersion, newVersion);
	}
}
