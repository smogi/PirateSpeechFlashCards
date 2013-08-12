package com.ivywire.piratespeechflashcards;

import com.ivywire.piratespeechflashcards.adapters.BeginnerCardCursorPagerAdapter;
import com.ivywire.piratespeechflashcards.adapters.ComplexCardCursorPagerAdapter;
import com.ivywire.piratespeechflashcards.adapters.DifficultCardCursorPagerAdapter;
import com.ivywire.piratespeechflashcards.adapters.MediumCardCursorPagerAdapter;
import com.ivywire.piratespeechflashcards.adapters.NaughtyCardCursorPagerAdapter;
import com.ivywire.piratespeechflashcards.adapters.StartingCardCursorPagerAdapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;

public class DeleteDialogFragment extends DialogFragment {
	ViewPager pager;
	String category;
	
	public Dialog onCreateDialog(Bundle savedInstanceState){
		// 1. Instantiate an AlertDialog.Builder with its constructor
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		// 2. Chain together various setter methods to set the dialog characteristics
		builder.setMessage(R.string.delete_dialog_message)
		       .setTitle(R.string.delete_dialog_title);
		builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id) {
	               if(pager != null){
	            	   //Cursor cursor = getContentResolver().query
	            	   pager.setCurrentItem(pager.getCurrentItem() + 1);
	               }
	               
	           }
		});
		builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id) {
	               //Nothing
	           }
		});
		AlertDialog dialog = builder.create();
		return dialog;
	}
	
	public void setFields(ViewPager mPager, String mCategory){
		pager = mPager;
		category = mCategory;
	}

}	
