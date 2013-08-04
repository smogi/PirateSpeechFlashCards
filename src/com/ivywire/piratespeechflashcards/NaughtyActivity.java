package com.ivywire.piratespeechflashcards;

import com.directionalviewpager.DirectionalViewPager;
import com.ivywire.piratespeechflashcards.contentprovider.MyCardContentProvider;
import com.ivywire.piratespeechflashcards.database.FlashCardTable;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class NaughtyActivity<DirectionalViewPager> extends FragmentActivity implements LoaderManager.LoaderCallbacks<Cursor> {
	CardCursorPagerAdapter adapter;
	DirectionalViewPager pager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card_slide);
		
		adapter = new CardCursorPagerAdapter(this, null);
		pager= (DirectionalViewPager) findViewById(R.id.flashcard_pager);
		((ViewPager) pager).setAdapter(adapter);
		
		getSupportLoaderManager().initLoader(-1, null, this);
	}
	
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		String[] projection = { FlashCardTable.COLUMN_TITLE, FlashCardTable.COLUMN_DEFINITION};
		String WHERE = "category='Complex'";
		
	    return new CursorLoader(this, MyCardContentProvider.CONTENT_URI, null, WHERE, null, null);
	
	}
	
    public void onLoadFinished(Loader<Cursor> arg0, Cursor cursor) {
		
	    adapter.swapCursor(cursor);
    }

	public void onLoaderReset(Loader<Cursor> arg0) {
	    adapter.swapCursor(null);
	} 
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.naughty, menu);
		return true;
	}

}