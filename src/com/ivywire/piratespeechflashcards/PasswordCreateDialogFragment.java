package com.ivywire.piratespeechflashcards;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordCreateDialogFragment extends DialogFragment {

	public Dialog onCreateDialog(Bundle savedInstanceState){
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.password_dialog, null);
	    AlertDialog.Builder password = new AlertDialog.Builder(getActivity());
		password.setTitle("Enter password");
		
		
		final EditText password1 = (EditText) view.findViewById(R.id.password1);
		final EditText password2 = (EditText) view.findViewById(R.id.password2);
		
		password.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String entered_password1 = password1.getText().toString();
				String entered_password2 = password2.getText().toString();
				
				if(password1.equals(password2)){
					SharedPreferences sp = getActivity().getSharedPreferences("Password", 9);
					SharedPreferences.Editor Ed=sp.edit();
					Ed.putString("Psw", entered_password1 );
					Ed.commit();
					
					Toast.makeText(getActivity().getApplicationContext(), "Password changed", Toast.LENGTH_LONG).show();
				}else{
					Toast.makeText(getActivity().getApplicationContext(), "Passwords don't match", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		password.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id) {
	               //Nothing
	           }
		});
		
		AlertDialog dialog = password.create();
		return dialog;
	}
}
