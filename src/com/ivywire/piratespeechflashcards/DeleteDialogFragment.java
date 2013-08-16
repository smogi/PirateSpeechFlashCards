package com.ivywire.piratespeechflashcards;

import com.external.verticalviewpager.VerticalViewPager;
import com.ivywire.piratespeechflashcards.contentprovider.MyCardContentProvider;
import com.ivywire.piratespeechflashcards.database.CardDatabaseHelper;
import com.ivywire.piratespeechflashcards.database.FlashCardTable;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;

public class DeleteDialogFragment extends DialogFragment {
	CardDatabaseHelper helper;
	ViewPager pager2;
	VerticalViewPager pager;
	String category;
	Context context;
	int position;

	public Dialog onCreateDialog(Bundle savedInstanceState){
		// 1. Instantiate an AlertDialog.Builder with its constructor
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		// 2. Chain together various setter methods to set the dialog characteristics
		builder.setMessage(R.string.delete_dialog_message)
		       .setTitle(R.string.delete_dialog_title);
		builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id) {
	               if(pager != null && category != null){
	            	   String [] projection = {FlashCardTable.COLUMN_DISABLED, FlashCardTable.COLUMN_ID };
	            	   String selection = " category =" + " '" + category + "' ";
	            	   Cursor cursor = context.getContentResolver().query(MyCardContentProvider.CONTENT_URI, projection, selection, null, null );
	            	   cursor.moveToPosition(position);
	            	   String columnId = cursor.getString(cursor.getColumnIndex(FlashCardTable.COLUMN_ID));
	            	   int columnId2 = Integer.parseInt(columnId);
	            	   columnId2--;
	            	   helper.updateDisabled(columnId2, "true");
	            	   pager.getAdapter().notifyDataSetChanged();
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
	
	public void setFields(Context mContext, ViewPager mPager, String mCategory, int mPosition){
		context = mContext;
		pager2 = mPager;
		category = mCategory;
		position = mPosition;
	}
	public void setFields(Context mContext, VerticalViewPager mPager, String mCategory, int mPosition, CardDatabaseHelper mHelper){
		context = mContext;
		pager = mPager;
		category = mCategory;
		position = mPosition;
		helper = mHelper;
	}
	
	public void setFields(Context mContext, VerticalViewPager mPager, String mCategory, int mPosition){
		context = mContext;
		pager = mPager;
		category = mCategory;
		position = mPosition;
	}

}	
