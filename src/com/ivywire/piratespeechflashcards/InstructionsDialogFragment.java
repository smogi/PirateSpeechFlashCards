package com.ivywire.piratespeechflashcards;

import com.external.verticalviewpager.VerticalViewPager;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.view.Window;

@SuppressLint("ValidFragment")
public class InstructionsDialogFragment extends DialogFragment{
	private VerticalViewPager pager;
	private Context context;
	
	public InstructionsDialogFragment(Context context, VerticalViewPager pager){
		this.context = context;
		this.pager = pager;
	}
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // The only reason you might override this method when using onCreateView() is
        // to modify any dialog characteristics. For example, the dialog includes a
        // title by default, but your custom layout might not need it. So here you can
        // remove the dialog title, but you must call the superclass to get the Dialog.
    	AlertDialog.Builder builder = new AlertDialog.Builder(context);
    	
        
        builder.setMessage("Please press OK for the first card.");
        
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//Nothing
				pager.setCurrentItem(pager.getCurrentItem() + 1);
			}
		});
        return builder.create();
    }
}
