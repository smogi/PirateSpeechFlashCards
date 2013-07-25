package com.ivywire.piratespeechflashcards;

import android.os.Bundle;
import android.app.Activity;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGestureListener;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MediumActivity extends Activity{
	private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medium);
		View view = findViewById(R.id.cardScreen);
		
		// Gesture detection
        gestureDetector = new GestureDetector(this, new CardGestureDetector());
        gestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };
        
        // Attach listeners
        view.setOnGestureList
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.medium, menu);
		return true;
	}
	
	class CardGestureDetector extends SimpleOnGestureListener {
		 @Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		    try {
		        if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
		            return false;
		        // swipe
		        if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
		        	Toast.makeText(MediumActivity.this, "Down Swipe", Toast.LENGTH_SHORT).show();
		        } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
		        	Toast.makeText(MediumActivity.this, "Up Swipe", Toast.LENGTH_SHORT).show();
			    }
			} catch (Exception e) {
			    // nothing
			    }
			    return false;
			}
	}
}
