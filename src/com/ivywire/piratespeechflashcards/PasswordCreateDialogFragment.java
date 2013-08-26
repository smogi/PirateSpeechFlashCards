package com.ivywire.piratespeechflashcards;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class PasswordCreateDialogFragment extends DialogFragment {

	private Context context;
	
	public PasswordCreateDialogFragment(Context context){
		this.context = context;
	}
	public Dialog onCreateDialog(Bundle savedInstanceState){
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.password_dialog, null);
	    AlertDialog.Builder password = new AlertDialog.Builder(context);
		password.setTitle("Enter password");
		
		password.setView(view);

		final EditText password1 = (EditText) view.findViewById(R.id.password1);
		final EditText password2 = (EditText) view.findViewById(R.id.password2);
		
		password.setPositiveButton("OK", new DialogInterface .OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String entered_password1 = password1.getText().toString();
				String entered_password2 = password2.getText().toString();
				
				if(entered_password1.equals(entered_password2)){
					SharedPreferences sp = context.getSharedPreferences("Password", 9);
					SharedPreferences sp2 = context.getSharedPreferences("PasswordEnabled", 10);
					
					SharedPreferences.Editor ed=sp.edit();
					SharedPreferences.Editor ed2 = sp2.edit();
					
					ed.putString("Password", entered_password1 );
					ed2.putBoolean("PasswordEnabled", true);
					
					ed.commit();
					ed2.commit();
					
					Toast.makeText(context.getApplicationContext(), "Password created", Toast.LENGTH_LONG).show();
				}else{
					Toast.makeText(context.getApplicationContext(), "Passwords don't match", Toast.LENGTH_LONG).show();
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
