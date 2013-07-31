package com.ivywire.piratespeechflashcards;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.GestureDetector;
import android.view.Menu;

public class BeginnerActivity extends Activity {
	GestureDetector gesturedetector = null;
	CardCursorPagerAdapter adapter;
	ViewPager pager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beginner);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.beginner, menu);
		return true;
	}

}
