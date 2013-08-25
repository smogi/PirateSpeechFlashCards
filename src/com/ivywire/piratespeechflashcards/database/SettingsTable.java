package com.ivywire.piratespeechflashcards.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SettingsTable {
	// the table
	public static final String TABLE_SETTINGS = "settings";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_PASSWORD_ENABLED = "enabled";
	public static final String COLUMN_PASSWORD = "password";
	
	// Database creation SQL statement
	private static final String DATABASE_CREATE = "create table " 
			+ TABLE_SETTINGS
			+ "("
			+ COLUMN_ID + " integer primary key autoincrement, "
			+ COLUMN_PASSWORD_ENABLED + " text not null, "
			+ COLUMN_PASSWORD + " text not null, "
			+ ");";
	
	public static void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		Log.w(SettingsTable.class.getName(), "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
	    database.execSQL("DROP TABLE IF EXISTS " + TABLE_SETTINGS);
	    onCreate(database);
	}
}
