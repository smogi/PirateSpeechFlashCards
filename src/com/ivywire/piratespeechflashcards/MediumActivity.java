package com.ivywire.piratespeechflashcards;

import com.ivywire.piratespeechflashcards.adapters.MediumCardCursorPagerAdapter;
import com.ivywire.piratespeechflashcards.contentprovider.MyCardContentProvider;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.NavUtils;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MediumActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<Cursor>{
	GestureDetector gesturedetector = null;
	MediumCardCursorPagerAdapter adapter;
	ViewPager pager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_card_slide);
		
		adapter = new MediumCardCursorPagerAdapter(this, null);
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
	}

	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		//String[] projection = { FlashCardTable.COLUMN_TITLE, FlashCardTable.COLUMN_DEFINITION};
		String WHERE = "category='Medium'";
		
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.slide, menu);
		
		//Use this in future when adding prev and next buttons
		/*
		menu.findItem(R.id.action_previous).setEnabled(pager.getCurrentItem() > 0);

        // Add either a "next" or "finish" button to the action bar, depending on which page
        // is currently selected.
        MenuItem item = menu.add(Menu.NONE, R.id.action_next, Menu.NONE,
                (pager.getCurrentItem() == adapter.getCount() - 1)
                        ? R.string.action_finish
                        : R.string.action_next);

        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		*/
        return true;
	}
	
	//Use in the future when adding prev and next buttons
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        	case R.id.action_delete:
        		DeleteDialogFragment dialog = new DeleteDialogFragment();
        		dialog.setFields(pager, "Medium");
        		dialog.show(getSupportFragmentManager(), "DeleteDialogFragment");
        	/*
            case android.R.id.home:
                // Navigate "up" the demo structure to the launchpad activity.
                // See http://developer.android.com/design/patterns/navigation.html for more.
                NavUtils.navigateUpTo(this, new Intent(this, MainActivity.class));
                return true;
			*/
            case R.id.action_previous:
                // Go to the previous step in the wizard. If there is no previous step,
                // setCurrentItem will do nothing.
        		pager.setCurrentItem(pager.getCurrentItem() - 1);
                return true;

            case R.id.action_next:
                // Advance to the next step in the wizard. If there is no next step, setCurrentItem
                // will do nothing.
                pager.setCurrentItem(pager.getCurrentItem() + 1);
                return true;
            
        }

        return super.onOptionsItemSelected(item);
    }
}

