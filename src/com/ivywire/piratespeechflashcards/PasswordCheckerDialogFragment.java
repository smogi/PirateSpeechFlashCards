package com.ivywire.piratespeechflashcards;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class PasswordCheckerDialogFragment extends DialogFragment {
    private Context context;
    private FragmentManager fm;
    
	public PasswordCheckerDialogFragment(Context context, FragmentManager fm){
		this.context = context;
		this.fm = fm;
	}
	
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
				
				SharedPreferences sp1 = context.getSharedPreferences("Password", 9);

				String pass = sp1.getString("Password", null);
				
				if(entered_password.equals(pass)){
					dismiss();
					Intent intent = new Intent(context, PasswordActivity.class);
					startActivity(intent);
				}else{
					Toast bread = Toast.makeText(context, "Passwords is incorrect", Toast.LENGTH_LONG);
	            	bread.show();
				}
			}
		});
		
		return password.create();
	}
}
		