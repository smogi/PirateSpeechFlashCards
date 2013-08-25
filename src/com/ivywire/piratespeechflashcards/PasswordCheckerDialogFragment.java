package com.ivywire.piratespeechflashcards;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class PasswordCheckerDialogFragment extends DialogFragment {
    private Context context;
    
	public PasswordCheckerDialogFragment(){
		this.context = context;
	}
	
	public boolean contains(String Password){
		return true;
		
	}
	
	public boolean getBoolean(String Password, boolean defValue){
		
		return true;
	}
	
	public String getString(String Password, boolean defValue){
		
		return Password;	
	}
}
		