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
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;

public class DeleteDialogFragment extends DialogFragment {
	ViewPager pager;
	
	public Dialog onCreateDialog(Bundle savedInstanceState){
		// 1. Instantiate an AlertDialog.Builder with its constructor
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		// 2. Chain together various setter methods to set the dialog characteristics
		builder.setMessage(R.string.delete_dialog_message)
		       .setTitle(R.string.delete_dialog_title);
		builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id) {
				
	               if(pager != null){
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
	
	public void setFields(ViewPager mPager, CardCursorPagerAdapter mAdapter){
		pager = mPager;
		CardCursorPagerAdapter adapter = mAdapter;
	}
	
	public void setFields(ViewPager mPager, MediumCardCursorPagerAdapter mAdapter){
		pager = mPager;
		MediumCardCursorPagerAdapter adapter = mAdapter;
	}
	
	public void setFields(ViewPager mPager, BeginnerCardCursorPagerAdapter mAdapter){
		pager = mPager;
		BeginnerCardCursorPagerAdapter adapter = mAdapter;
	}
	
	public void setFields(ViewPager mPager, ComplexCardCursorPagerAdapter mAdapter){
		pager = mPager;
		ComplexCardCursorPagerAdapter adapter = mAdapter;
	}
	
	public void setFields(ViewPager mPager, DifficultCardCursorPagerAdapter mAdapter){
		pager = mPager;
		DifficultCardCursorPagerAdapter adapter = mAdapter;
	}
	
	public void setFields(ViewPager mPager, NaughtyCardCursorPagerAdapter mAdapter){
		pager = mPager;
		NaughtyCardCursorPagerAdapter adapter = mAdapter;
	}
	
	public void setFields(ViewPager mPager, StartingCardCursorPagerAdapter mAdapter){
		pager = mPager;
		StartingCardCursorPagerAdapter adapter = mAdapter;
	}
}	
