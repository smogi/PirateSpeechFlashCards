package com.ivywire.piratespeechflashcards.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class FlashCardTable {
	// Our table
	public static final String TABLE_CARDS = "card";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_CATEGORY = "category";
	public static final String COLUMN_TITLE = "title";
	public static final String COLUMN_SENTENCE = "sentence";
	public static final String COLUMN_DEFINITION = "definition";
	
	// Database creation SQL statement
	private static final String DATABASE_CREATE = "create table " 
			+ TABLE_CARDS
			+ "("
			+ COLUMN_ID + " integer primary key autoincrement, "
			+ COLUMN_CATEGORY + " text not null, "
			+ COLUMN_TITLE + " text not null, "
			+ COLUMN_SENTENCE + " text not null, "
			+ COLUMN_DEFINITION + " text not null"
			+ ");";
	
	public static void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		Log.w(FlashCardTable.class.getName(), "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
	    database.execSQL("DROP TABLE IF EXISTS " + TABLE_CARDS);
	    onCreate(database);
	}
}
