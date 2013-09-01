package com.ivywire.piratespeechflashcards.adapters;

import com.external.verticalviewpager.PagerAdapter;
import com.external.verticalviewpager.VerticalViewPager;
import com.ivywire.piratespeechflashcards.R;
import com.ivywire.piratespeechflashcards.database.FlashCardTable;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NaughtyCardCursorPagerAdapter extends PagerAdapter{
	private Cursor cursor;
	private LayoutInflater inflater;
	private Typeface boldFont;
	private Typeface boldItalicFont;
	private Typeface font;
	private Context context;
	
	public NaughtyCardCursorPagerAdapter(Context context, Cursor cursor){
		this.cursor = cursor;
	    this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    this.context = context;
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
	        return 49;
	    }
	}
	
	@Override
	public Object instantiateItem(View view, int position) {
		RelativeLayout layout = null;
		font = Typeface.createFromAsset(context.getAssets(), "times.ttf");
		boldFont = Typeface.createFromAsset(context.getAssets(), "times-bold.ttf");
		boldItalicFont = Typeface.createFromAsset(context.getAssets(), "times-bold-italic.ttf");
		int position2 = position;
		if(position == 0){
			layout = (RelativeLayout) inflater.inflate(R.layout.activity_blank, null);
		}if(position == 1){
			layout = (RelativeLayout) inflater.inflate(R.layout.activity_card_load, null);
		}if(position == 48){
			layout = (RelativeLayout) inflater.inflate(R.layout.activity_advertisement_naughty, null);
		}
		else if(position > 1 && position < 48){
			position2--;
		    cursor.moveToPosition(position2);
		    layout = (RelativeLayout) inflater.inflate(R.layout.activity_card, null);
		    layout.setBackgroundResource(R.drawable.card_naughty);
		    TextView cardTitle = (TextView) layout.findViewById(R.id.pirate_card_title);
		    TextView cardExample = (TextView) layout.findViewById(R.id.pirate_card_example);
		    TextView cardDefinition = (TextView) layout.findViewById(R.id.pirate_card_definition);
	
		    cardTitle.setText(cursor.getString(cursor.getColumnIndex(FlashCardTable.COLUMN_TITLE)));
		    cardExample.setText(Html.fromHtml(cursor.getString(cursor.getColumnIndex(FlashCardTable.COLUMN_SENTENCE))));
		    cardDefinition.setText(cursor.getString(cursor.getColumnIndex(FlashCardTable.COLUMN_DEFINITION)));
		
		    cardTitle.setTypeface(boldFont);
		    cardExample.setTypeface(font);
		    cardDefinition.setTypeface(boldItalicFont);
		}
		((VerticalViewPager) view).addView(layout);
	    return layout;
	}
	
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}
}
