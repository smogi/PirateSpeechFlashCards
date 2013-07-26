package com.ivywire.piratespeechflashcards;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

class CardGestureListener extends GestureDetector.SimpleOnGestureListener{
	private static final int SWIPE_MIN_DISTANCE = 25;
	private static final int SWIPE_MAX_OFF_PATH = 100;
	private static final int SWIPE_THRESHOLD_VELOCITY = 10;
	
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		float dX = e2.getX()-e1.getX();
		float dY = e1.getY()-e2.getY();
		
		 if (Math.abs(dX)<SWIPE_MAX_OFF_PATH && Math.abs(velocityY)>=SWIPE_THRESHOLD_VELOCITY &&
				 Math.abs(dY)>=SWIPE_MIN_DISTANCE ) {
			 if (dY>0) {
				 System.out.print("Up swipe");
			 } else {
				 System.out.print("Down swipe");
			 }return true;
		 }return false;
	}
}