package com.ivywire.piratespeechflashcards;

import com.ivywire.piratespeechflashcards.contentprovider.MyCardContentProvider;
import com.ivywire.piratespeechflashcards.database.CardDatabaseHelper;
import com.ivywire.piratespeechflashcards.database.FlashCardTable;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.view.ViewPager;


public class CardOnPageListener implements ViewPager.OnPageChangeListener{
	private Context context;
	private Cursor cursor;
	private Cursor deleteCursor;
	private int mScrollPosition;
	private int mIntPosition;
	
	@Override
	public void onPageScrollStateChanged(int state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int position) {
		// TODO Auto-generated method stub
		mScrollPosition = position;
		cursor.moveToPosition(mScrollPosition);
		mIntPosition = cursor.getInt(mScrollPosition);	
	}

	public void deleteItem(){
		//getContentResolver().delete(MyCardContentProvider.CONTENT_URI, FlashCardTable.COLUMN_ID + " = '" + mIntPosition + "' ", null );
	}
}
