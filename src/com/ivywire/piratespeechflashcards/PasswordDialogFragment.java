package com.ivywire.piratespeechflashcards;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.InputType;
import android.widget.EditText;

public class PasswordDialogFragment extends DialogFragment{
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState){
		final EditText password_input = new EditText(getActivity()); // create an text input field
	    password_input.setHint("Enter Password"); // put a hint in it
	    password_input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD); // change it to password type
		
	    AlertDialog.Builder password = new AlertDialog.Builder(getActivity());
		password.setTitle("Enter password");
		password.setView(password_input);
		
		password.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String entered_password = password_input.getText().toString();
				
				SharedPreferences sp1 = getActivity().getApplicationContext().getSharedPreferences("Psw", 0);

				String pass = sp1.getString("Psw", null);
				
				if(entered_password == pass){
					sendToNaughty();
				}
			}
		});
		
		return password.create();
	}
	
	public void sendToNaughty(){
		Intent intent = new Intent(getActivity(), NaughtyActivity.class);
	}
}