package com.ivywire.piratespeechflashcards;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.external.verticalviewpager.AnimationPager;
import com.external.verticalviewpager.VerticalViewPager;
import com.ivywire.piratespeechflashcards.adapters.ComplexCardCursorPagerAdapter;
import com.ivywire.piratespeechflashcards.contentprovider.MyCardContentProvider;

import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.GestureDetector;


public class ComplexActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    GestureDetector gesturedetector=null;
    ComplexCardCursorPagerAdapter adapter;
    VerticalViewPager pager;
        
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card_slide_vertical);
		getSupportLoaderManager().initLoader(-1, null, this);
		adapter = new ComplexCardCursorPagerAdapter(this, null);
		pager= (VerticalViewPager) findViewById(R.id.flashcard_pager_vertical);
		pager.setAdapter(adapter);
		pager.setPageTransformer(true, new AnimationPager());
		
		adapter.notifyDataSetChanged();
	}
	
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		//String[] projection = { FlashCardTable.COLUMN_TITLE, FlashCardTable.COLUMN_DEFINITION};
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

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        	case R.id.action_delete:
        		DeleteDialogFragment dialog = new DeleteDialogFragment();
        		dialog.setFields(this, pager, "Complex", pager.getCurrentItem());
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
