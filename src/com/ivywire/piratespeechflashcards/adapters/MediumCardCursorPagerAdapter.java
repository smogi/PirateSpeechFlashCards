package com.ivywire.piratespeechflashcards.adapters;

import com.ivywire.piratespeechflashcards.R;
import com.ivywire.piratespeechflashcards.R.id;
import com.ivywire.piratespeechflashcards.R.layout;
import com.ivywire.piratespeechflashcards.database.FlashCardTable;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MediumCardCursorPagerAdapter extends PagerAdapter {
	private Cursor cursor;
	private LayoutInflater inflater;
	private String cardDeleted;
	
	public MediumCardCursorPagerAdapter(Context context, Cursor cursor){
		this.cursor = cursor;
	    this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public void swapCursor(Cursor cursor){
		this.cursor = cursor;
	}
	
	@Override
	public void destroyItem(View view, int position, Object object) {
	    ((ViewPager) view).removeView((LinearLayout) object);
	}
	
	@Override
	public int getCount() {
		if(cursor == null) {
	        return 0;
	    } else {
	        return 105;
	    }
	}
	
	@Override
	public Object instantiateItem(View view, int position) {
		LinearLayout layout = null;
		int position2 = position;
		if(position == 0){
			layout = (LinearLayout) inflater.inflate(R.layout.activity_slide_info, null);
		}if(position == 104){
			layout = (LinearLayout) inflater.inflate(R.layout.activity_slide_info, null);
		}
		else if(position > 0 && position < 104){
			position2--;
		    cursor.moveToPosition(position2);
		    cardDeleted = cursor.getString(cursor.getColumnIndex(FlashCardTable.COLUMN_DISABLED));
		    if(cardDeleted.equals("true")){
		    	layout = (LinearLayout) inflater.inflate(R.layout.activity_deleted_card, null);
		    }else{
			    layout = (LinearLayout) inflater.inflate(R.layout.activity_card, null);
			    
			    TextView cardTitle = (TextView) layout.findViewById(R.id.pirate_card_title);
			    TextView cardExample = (TextView) layout.findViewById(R.id.pirate_card_example);
			    TextView cardDefinition = (TextView) layout.findViewById(R.id.pirate_card_definition);
			    
			    cardTitle.setText(cursor.getString(cursor.getColumnIndex(FlashCardTable.COLUMN_TITLE)));
			    cardExample.setText(cursor.getString(cursor.getColumnIndex(FlashCardTable.COLUMN_SENTENCE)));
			    cardDefinition.setText(cursor.getString(cursor.getColumnIndex(FlashCardTable.COLUMN_DEFINITION)));
		    }
		}
		((ViewPager) view).addView(layout);
	    return layout;
	}
	
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}
}
