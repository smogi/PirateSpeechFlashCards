package com.ivywire.piratespeechflashcards.contentprovider;

import java.util.Arrays;
import java.util.HashSet;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import com.ivywire.piratespeechflashcards.database.CardDatabaseHelper;
import com.ivywire.piratespeechflashcards.database.FlashCardTable;

public class MyCardContentProvider extends ContentProvider{
	
	// database
	private CardDatabaseHelper database;
	
	// used for the UriMatcher
	private static final int CARDS = 10;
	private static final  int CARD_ID = 20;
	
	private static final String AUTHORITY = "com.ivywire.piratespeechflashcards.contentprovider";
	
	private static final String BASE_PATH = "cards";
	
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
			+ "/" + BASE_PATH);
	
	public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
			+ "/cards";
	public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
			+ "/card";
	
	private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	static{
		sURIMatcher.addURI(AUTHORITY, BASE_PATH, CARDS);
	    sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", CARD_ID);
	}
	
	@Override
	public boolean onCreate() {
		database = new CardDatabaseHelper(getContext());
		return false;
	}
	
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		// Uisng SQLiteQueryBuilder instead of query() method
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		
		// Check if the caller has requested a column which does not exists
		checkColumns(projection);
		
		// Set the table
		queryBuilder.setTables(FlashCardTable.TABLE_CARDS);

	    int uriType = sURIMatcher.match(uri);
	    switch (uriType) {
		    case CARDS:
		    	break;
		    case CARD_ID:
		    	// Adding the ID to the original query
		    	queryBuilder.appendWhere(FlashCardTable.COLUMN_ID + "="
		    			+ uri.getLastPathSegment());
		    	break;
		    default:
		    	throw new IllegalArgumentException("Unknown URI: " + uri);
	    }

	    SQLiteDatabase db = database.getWritableDatabase();
	    Cursor cursor = queryBuilder.query(db, projection, selection,
	        selectionArgs, null, null, sortOrder);
	    // Make sure that potential listeners are getting notified
	    cursor.setNotificationUri(getContext().getContentResolver(), uri);

	    return cursor;
	  }
	
	@Override
	public String getType(Uri uri) {
		return null;
	}
	
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int uriType = sURIMatcher.match(uri);
		SQLiteDatabase sqlDB = database.getWritableDatabase();
		int rowsDeleted = 0;
	    long id = 0;
	    switch (uriType) {
		    case CARDS:
		    	id = sqlDB.insert(FlashCardTable.TABLE_CARDS, null, values);
		    	break;
		    default:
		    	throw new IllegalArgumentException("Unknown URI: " + uri);
	    }
	    getContext().getContentResolver().notifyChange(uri, null);
	    return Uri.parse(BASE_PATH + "/" + id);
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
	    int uriType = sURIMatcher.match(uri);
	    SQLiteDatabase sqlDB = database.getWritableDatabase();
	    int rowsDeleted = 0;
	    switch (uriType) {
		    case CARDS:
		    	rowsDeleted = sqlDB.delete(FlashCardTable.TABLE_CARDS, selection,
		          selectionArgs);
		      break;
		    case CARD_ID:
		    	String id = uri.getLastPathSegment();
		    	if (TextUtils.isEmpty(selection)) {
		    		rowsDeleted = sqlDB.delete(FlashCardTable.TABLE_CARDS,
		            FlashCardTable.COLUMN_ID + "=" + id, 
		            null);
		    	} else {
		    		rowsDeleted = sqlDB.delete(FlashCardTable.TABLE_CARDS,
		            FlashCardTable.COLUMN_ID + "=" + id 
		            + " and " + selection,
		            selectionArgs);
		    	}
		    	break;
		    default:
		    	throw new IllegalArgumentException("Unknown URI: " + uri);
	    }
	    getContext().getContentResolver().notifyChange(uri, null);
	    return rowsDeleted;
	}
	
	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
	    int uriType = sURIMatcher.match(uri);
	    SQLiteDatabase sqlDB = database.getWritableDatabase();
	    int rowsUpdated = 0;
	    switch (uriType) {
		    case CARDS:
		    	rowsUpdated = sqlDB.update(FlashCardTable.TABLE_CARDS, 
		    			values, 
		    			selection,
		    			selectionArgs);
		    	break;
		    case CARD_ID:
		    	String id = uri.getLastPathSegment();
		    	if (TextUtils.isEmpty(selection)) {
		    		rowsUpdated = sqlDB.update(FlashCardTable.TABLE_CARDS, 
		            values,
		            FlashCardTable.COLUMN_ID + "=" + id, 
		            null);
		    	} else {
		    		rowsUpdated = sqlDB.update(FlashCardTable.TABLE_CARDS, 
		            values,
		            FlashCardTable.COLUMN_ID + "=" + id 
		            + " and " 
		            + selection,
		            selectionArgs);
		    	}
		    	break;
		    default:
		    	throw new IllegalArgumentException("Unknown URI: " + uri);
	    }
	    getContext().getContentResolver().notifyChange(uri, null);
	    return rowsUpdated;
	}
	
	private void checkColumns(String[] projection) {
		String[] available = { FlashCardTable.COLUMN_CATEGORY,
				FlashCardTable.COLUMN_TITLE, FlashCardTable.COLUMN_SENTENCE,
				FlashCardTable.COLUMN_DEFINITION, FlashCardTable.COLUMN_ID, 
				FlashCardTable.COLUMN_DISABLED };
	    if (projection != null) {
	    	HashSet<String> requestedColumns = new HashSet<String>(Arrays.asList(projection));
	    	HashSet<String> availableColumns = new HashSet<String>(Arrays.asList(available));
	    	// Check if all columns which are requested are available
	    	if (!availableColumns.containsAll(requestedColumns)) {
	    		throw new IllegalArgumentException("Unknown columns in projection");
	    	}
	    }
	}
}
