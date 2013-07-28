package com.ivywire.piratespeechflashcards;

import com.ivywire.piratespeechflashcards.contentprovider.MyCardContentProvider;
import com.ivywire.piratespeechflashcards.database.FlashCardTable;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGestureListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.LoaderManager;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import android.widget.Toast;

public class MediumActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<Cursor>{
	GestureDetector gesturedetector = null;
	CardCursorPagerAdapter adapter;
	ViewPager pager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_card_slide);
		
		adapter = new CardCursorPagerAdapter(this, null);
		pager = (ViewPager) findViewById(R.id.flashcard_pager);
		pager.setAdapter(adapter);
		
		getSupportLoaderManager().initLoader(-1, null, this);
		
        /*mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
        	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
			@Override
            public void onPageSelected(int position) {
                // When changing pages, reset the action bar actions since they are dependent
                // on which page is currently active. An alternative approach is to have each
                // fragment expose actions itself (rather than the activity exposing actions),
                // but for simplicity, the activity provides the actions in this sample.
                invalidateOptionsMenu();
            }
        });*/		
		
		//Gesture detector stuff for onFlign
		/*
		View view = findViewById(R.id.cardScreen);
		gesturedetector = new GestureDetector(this, new MyGestureListener());
		view.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				gesturedetector.onTouchEvent(event);
				return true;
			}
		});
		*/
	}

	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		String[] projection = { FlashCardTable.COLUMN_TITLE, FlashCardTable.COLUMN_DEFINITION};
		String WHERE = "category='Beginner'";
		
	    return new CursorLoader(this, MyCardContentProvider.CONTENT_URI, null, WHERE, null, null);
	
	}

	public void onLoadFinished(Loader<Cursor> arg0, Cursor cursor) {
		
	    adapter.swapCursor(cursor);
	    /*
	    this.cursor = cursor;

	    pager.setCurrentItem((int) (adapter.getCount() / 2), false);
	    pager.startAnimation(animation);*/
	}
	
	public void onLoaderReset(Loader<Cursor> arg0) {
	    adapter.swapCursor(null);
	} 
    /*
    
	public boolean dispatchTouchEvent(MotionEvent ev){
		super.dispatchTouchEvent(ev);
		return gesturedetector.onTouchEvent(ev);

	}
	
	*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.medium, menu);
		
		//Use this in future when adding prev and next buttons
		/*
		menu.findItem(R.id.action_previous).setEnabled(mPager.getCurrentItem() > 0);

        // Add either a "next" or "finish" button to the action bar, depending on which page
        // is currently selected.
        MenuItem item = menu.add(Menu.NONE, R.id.action_next, Menu.NONE,
                (mPager.getCurrentItem() == mPagerAdapter.getCount() - 1)
                        ? R.string.action_finish
                        : R.string.action_next);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        */
        return true;
	}
	
	//Use in the future when adding prev and next buttons
	/*
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Navigate "up" the demo structure to the launchpad activity.
                // See http://developer.android.com/design/patterns/navigation.html for more.
                NavUtils.navigateUpTo(this, new Intent(this, MainActivity.class));
                return true;

            case R.id.action_previous:
                // Go to the previous step in the wizard. If there is no previous step,
                // setCurrentItem will do nothing.
                mPager.setCurrentItem(mPager.getCurrentItem() - 1);
                return true;

            case R.id.action_next:
                // Advance to the next step in the wizard. If there is no next step, setCurrentItem
                // will do nothing.
                mPager.setCurrentItem(mPager.getCurrentItem() + 1);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
	
	class MyGestureListener extends GestureDetector.SimpleOnGestureListener{
		private static final int SWIPE_MIN_DISTANCE = 25;
		private static final int SWIPE_MAX_OFF_PATH = 100;
		private static final int SWIPE_THRESHOLD_VELOCITY = 10;
		
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			float dX = e2.getX()-e1.getX();
			float dY = e1.getY()-e2.getY();
			
			 if (Math.abs(dX)<SWIPE_MAX_OFF_PATH && Math.abs(velocityY)>=SWIPE_THRESHOLD_VELOCITY &&
					 Math.abs(dY)>=SWIPE_MIN_DISTANCE ) {
				 if (dY>0) {
					 Toast.makeText(MediumActivity.this, "Up Swipe", Toast.LENGTH_SHORT).show();
				 } else {
					 Toast.makeText(MediumActivity.this, "Down Swipe", Toast.LENGTH_SHORT).show();
				 }return true;
			 }return false;
		}
	}
}
