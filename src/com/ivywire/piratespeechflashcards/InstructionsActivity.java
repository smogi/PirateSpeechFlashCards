package com.ivywire.piratespeechflashcards;

import com.external.verticalviewpager.AnimationPager;
import com.external.verticalviewpager.VerticalViewPager;
import com.ivywire.piratespeechflashcards.adapters.InstructionsCardCursorPagerAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

public class InstructionsActivity extends FragmentActivity {
	InstructionsCardCursorPagerAdapter adapter;
	VerticalViewPager pager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card_slide_vertical);
		
		adapter = new InstructionsCardCursorPagerAdapter(this);
		pager = (VerticalViewPager) findViewById(R.id.flashcard_pager_vertical);
		pager.setAdapter(adapter);
		pager.setPageTransformer(true, new AnimationPager());
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.slide, menu);
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_previous:
        		pager.setCurrentItem(pager.getCurrentItem() - 1);
                return true;

            case R.id.action_next:
                // Advance to the next step in the wizard. If there is no next step, setCurrentItem
                // will do nothing.
                pager.setCurrentItem(pager.getCurrentItem() + 1);
                return true;
                
            case R.id.action_mainmenu:
            	Intent intent = new Intent(this, MainActivity.class);
            	startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
 
}

