package com.ivywire.piratespeechflashcards;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import com.ivywire.piratespeechflashcards.contentprovider.MyCardContentProvider;
import com.ivywire.piratespeechflashcards.database.FlashCardTable;

import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.view.GestureDetector;


public class ComplexActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {
    GestureDetector gesturedetector=null;
    CardCursorPagerAdapter adapter;
    ViewPager pager;
        
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_complex);
		
		adapter = new CardCursorPagerAdapter(this, null);
		pager= (ViewPager) findViewById(R.id.flashcard_pager);
		pager.setAdapter(adapter);
		
		getSupportLoaderManager().initLoader(-1, this, null);
	}
	
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		String[] projection = { FlashCardTable.COLUMN_TITLE, FlashCardTable.COLUMN_DEFINITION};
		String WHERE = "category='Beginner'";
		
	    return new CursorLoader(this, MyCardContentProvider.CONTENT_URI, null, WHERE, null, null);
	
	}
	
    public void onLoadFinished(Loader<Cursor> arg0, Cursor cursor) {
		
	    adapter.swapCursor(cursor);
    }

	public void onLoaderReset(Loader<Cursor> arg0) {
	    adapter.swapCursor(null);
	} 
	

	private LoaderManager getSupportLoaderManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.complex, menu);
		return true;
	}

}
