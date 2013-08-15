package com.ivywire.piratespeechflashcards.adapters;

import com.external.verticalviewpager.PagerAdapter;
import com.external.verticalviewpager.VerticalViewPager;
import com.ivywire.piratespeechflashcards.R;
import com.ivywire.piratespeechflashcards.R.id;
import com.ivywire.piratespeechflashcards.R.layout;
import com.ivywire.piratespeechflashcards.database.FlashCardTable;

import android.content.Context;
import android.database.Cursor;
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
	    ((VerticalViewPager) view).removeView((RelativeLayout) object);
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
		RelativeLayout layout = null;
		int position2 = position;
		if(position == 0){
			layout = (RelativeLayout) inflater.inflate(R.layout.activity_slide_info, null);
		}if(position == 104){
			layout = (RelativeLayout) inflater.inflate(R.layout.activity_slide_info, null);
		}
		else if(position > 0 && position < 104){
			position2--;
		    cursor.moveToPosition(position2);
		    cardDeleted = cursor.getString(cursor.getColumnIndex(FlashCardTable.COLUMN_DISABLED));
		    if(cardDeleted != null && cardDeleted.equals("true")){
		    	layout = (RelativeLayout) inflater.inflate(R.layout.activity_deleted_card, null);
		    }else{
			    layout = (RelativeLayout) inflater.inflate(R.layout.activity_card, null);
			    layout.setBackgroundResource(R.drawable.card_medium);
			    TextView cardTitle = (TextView) layout.findViewById(R.id.pirate_card_title);
			    TextView cardExample = (TextView) layout.findViewById(R.id.pirate_card_example);
			    TextView cardDefinition = (TextView) layout.findViewById(R.id.pirate_card_definition);
			    
			    cardTitle.setText(cursor.getString(cursor.getColumnIndex(FlashCardTable.COLUMN_TITLE)));
			    cardExample.setText(cursor.getString(cursor.getColumnIndex(FlashCardTable.COLUMN_SENTENCE)));
			    cardDefinition.setText(cursor.getString(cursor.getColumnIndex(FlashCardTable.COLUMN_DEFINITION)));
		    }
		}
		((VerticalViewPager) view).addView(layout);
	    return layout;
	}
	
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}
	
	public int getItemPosition(Object object) {
	    return POSITION_NONE;
	}
}
