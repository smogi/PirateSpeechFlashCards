package com.ivywire.piratespeechflashcards.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SettingsDatabaseHelper extends SQLiteOpenHelper{
	private static final String DATABASE_NAME = "settingstable.db";
	private static final int DATABASE_VERSION = 1;
	
	public SettingsDatabaseHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	// Method is called during the creation of the database
		public void onCreate(SQLiteDatabase database){
			FlashCardTable.onCreate(database);
		}
		
		// Method is called during the upgrade of the database
		public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
			SettingsTable.onUpgrade(database, oldVersion, newVersion);
		}
		
		public void updateDisabled(int id, String disabled){
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
	 		values.put(SettingsTable.COLUMN_PASSWORD_ENABLED, "true");
	 		db.update(SettingsTable.TABLE_SETTINGS, values, "_id=" + id, null);
		}
}
