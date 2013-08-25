package com.ivywire.piratespeechflashcards.adapters;

import com.external.verticalviewpager.PagerAdapter;
import com.external.verticalviewpager.VerticalViewPager;
import com.ivywire.piratespeechflashcards.R;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class InstructionsCardCursorPagerAdapter extends PagerAdapter {
	private LayoutInflater inflater;
	private Context context;
	
		
	public InstructionsCardCursorPagerAdapter(Context context){
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;
	}
	
	public void destroyItem(View view, int position, Object object){
		 ((VerticalViewPager) view).removeView((RelativeLayout) object);
	}
	
	@Override
	public Object instantiateItem(View view, int position){
		RelativeLayout layout = null;
		switch(position){
			case 0:
				layout = (RelativeLayout) inflater.inflate(R.layout.activity_instructions1, null);
				break;
			case 1:
				layout = (RelativeLayout) inflater.inflate(R.layout.activity_instructions2, null);
				break;
			case 2:
				layout = (RelativeLayout) inflater.inflate(R.layout.activity_instructions3, null);
				ImageButton link = (ImageButton) layout.findViewById(R.id.youtube);
				link.setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View v){
						Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=fqMu6e5Dgtg"));
						context.startActivity(i);
					}
				});
				break;
			case 3:
				layout = (RelativeLayout) inflater.inflate(R.layout.activity_instructions4, null);
				break;
			case 4:
				layout = (RelativeLayout) inflater.inflate(R.layout.activity_credits, null);
				break;
		}
		((VerticalViewPager) view).addView(layout);
	    return layout;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		// TODO Auto-generated method stub
		return view == object;
	}
	
	public int getItemPosition(Object object){
		return POSITION_NONE;
		
	}

}
