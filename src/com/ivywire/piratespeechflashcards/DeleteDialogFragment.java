package com.ivywire.piratespeechflashcards;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;

public class DeleteDialogFragment extends DialogFragment {
	CardCursorPagerAdapter adapter;
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
		adapter = mAdapter;
	}
}	
