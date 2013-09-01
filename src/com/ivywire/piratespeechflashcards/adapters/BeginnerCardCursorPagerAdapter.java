package com.ivywire.piratespeechflashcards.adapters;

import com.external.verticalviewpager.PagerAdapter;
import com.external.verticalviewpager.VerticalViewPager;
import com.ivywire.piratespeechflashcards.R;
import com.ivywire.piratespeechflashcards.database.FlashCardTable;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BeginnerCardCursorPagerAdapter extends PagerAdapter {
	private Cursor cursor;
	private LayoutInflater inflater;
	private Context context2;
	private Typeface boldFont;
	private Typeface boldItalicFont;
	private Typeface font;
	
	public BeginnerCardCursorPagerAdapter(Context context, Cursor cursor){
		this.cursor = cursor;
	    this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    context2 = context;
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
	        return 91;
	    }
	}
	
	@Override
	public Object instantiateItem(View view, int position) {
		RelativeLayout layout = null;
		font = Typeface.createFromAsset(context2.getAssets(), "times.ttf");
		boldFont = Typeface.createFromAsset(context2.getAssets(), "times-bold.ttf");
		boldItalicFont = Typeface.createFromAsset(context2.getAssets(), "times-bold-italic.ttf");
		
		int position2 = position;
		if(position == 0){
			layout = (RelativeLayout) inflater.inflate(R.layout.activity_blank, null);
		}if(position == 1){
			layout = (RelativeLayout) inflater.inflate(R.layout.activity_slide_info, null);
		}if(position == 90){
			layout = (RelativeLayout) inflater.inflate(R.layout.activity_advertisement_beginner, null);
		    
		}
		else if(position > 1 && position < 90){
			position2--;
		    cursor.moveToPosition(position2);
		    layout = (RelativeLayout) inflater.inflate(R.layout.activity_card, null);
		    layout.setBackgroundResource(R.drawable.card_beginner);
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
		((ViewGroup) view).addView(layout);
	    return layout;
	}
	
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}
	
	public int getItemPosition(Object object){
		return POSITION_NONE;
		
	}
}
