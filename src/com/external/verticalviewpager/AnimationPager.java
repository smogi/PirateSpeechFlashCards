package com.external.verticalviewpager;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class AnimationPager extends VerticalViewPager implements VerticalViewPager.PageTransformer {
    
	public AnimationPager(Context context) {
		super(context);
	    initViewPager();
	    		
	}
	
	public AnimationPager(Context context, AttributeSet attr){
		super(context, attr);
		initViewPager();
	}


	private static float MIN_SCALE = 0.75f;
	private View view;
	
	
	@Override
	public void transformPage(View page, float position) {
		// TODO Auto-generated method stub
		int pageHeight = view.getHeight();
		
		if (position < -1){			
			view.setAlpha(0);
			
		}else if (position <= 0){
			view.setAlpha(1);
			view.setTranslationY(0);
			view.setScaleX(1);
			view.setScaleY(1);
			
		}else if (position <=1){
			
			view.setAlpha(1 - position);
			view.setTranslationY(pageHeight * -position);
			
			float scaleFactor = MIN_SCALE
					+ (1 - MIN_SCALE)*(1 - Math.abs(position));
			view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

		}else {
			view.setAlpha(0);
		}
	}

}
