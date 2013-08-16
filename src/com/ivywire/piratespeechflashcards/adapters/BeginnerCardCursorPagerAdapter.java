package com.ivywire.piratespeechflashcards.adapters;

import com.external.verticalviewpager.PagerAdapter;
import com.external.verticalviewpager.VerticalViewPager;
import com.ivywire.piratespeechflashcards.R;
import com.ivywire.piratespeechflashcards.database.FlashCardTable;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BeginnerCardCursorPagerAdapter extends PagerAdapter {
	private Cursor cursor;
	private LayoutInflater inflater;
	private Context context2;
	
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
	        return 90;
	    }
	}
	
	@Override
	public Object instantiateItem(View view, int position) {
		RelativeLayout layout = null;
		int position2 = position;
		if(position == 0){
			layout = (RelativeLayout) inflater.inflate(R.layout.activity_slide_info, null);
		}if(position == 89){
			layout = (RelativeLayout) inflater.inflate(R.layout.activity_advertisement_beginner, null);
			Button rateButton = (Button) layout.findViewById(R.id.ratingButton1_beginner);
			rateButton.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {				   
					try{
						context2.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.imangi.templerun]")));
									        
				    }
				    catch (ActivityNotFoundException e){
				    	Toast.makeText(context2, "Could not open Android market, please install the market app.", Toast.LENGTH_SHORT).show(); 
				    }
					
				}
				
			});
		    
		}
		else if(position > 0 && position < 89){
			position2--;
		    cursor.moveToPosition(position2);
		    layout = (RelativeLayout) inflater.inflate(R.layout.activity_card, null);
		    layout.setBackgroundResource(R.drawable.card_beginner);
		    TextView cardTitle = (TextView) layout.findViewById(R.id.pirate_card_title);
		    TextView cardExample = (TextView) layout.findViewById(R.id.pirate_card_example);
		    TextView cardDefinition = (TextView) layout.findViewById(R.id.pirate_card_definition);
	
		    cardTitle.setText(cursor.getString(cursor.getColumnIndex(FlashCardTable.COLUMN_TITLE)));
		    cardExample.setText(cursor.getString(cursor.getColumnIndex(FlashCardTable.COLUMN_SENTENCE)));
		    cardDefinition.setText(cursor.getString(cursor.getColumnIndex(FlashCardTable.COLUMN_DEFINITION)));
		}
		((VerticalViewPager) view).addView(layout);
	    return layout;
	}
	
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

}
