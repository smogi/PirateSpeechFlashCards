package com.ivywire.piratespeechflashcards;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordActivity extends Activity implements OnClickListener {
	private Button button;
	private EditText edit1;
	private EditText edit2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_password);
		
		edit1 = (EditText) findViewById(R.id.editTextpassword1);
		edit2 = (EditText) findViewById(R.id.editTextpassword2);
		
		button = (Button) findViewById(R.id.finished_button);
		
		button.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.manage, menu);
		return true;
	}
	
	public void changePassword(){
		edit1 = (EditText) findViewById(R.id.editTextpassword1);
		edit2 = (EditText) findViewById(R.id.editTextpassword2);
		
		String pass1 = edit1.getText().toString();
		String pass2 = edit2.getText().toString();
		
		if(pass1.equals(pass2)){
			SharedPreferences sp = this.getSharedPreferences("Password", 9);
			SharedPreferences sp2 = this.getSharedPreferences("PasswordEnabled", 10);
			
			SharedPreferences.Editor ed = sp.edit();
			SharedPreferences.Editor ed2 = sp2.edit();
			
			ed.putString("Password", pass1);
			ed2.putBoolean("PasswordEnabled", true);
			
			ed.commit();
			ed2.commit();
			
		}else{
			Toast.makeText(this.getApplicationContext(), "Passwords don't match", Toast.LENGTH_LONG).show();
		}
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        	case R.id.action_main:
        		Intent intent = new Intent(this, ManageActivity.class);
            	startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
        case R.id.finished_button:
        	changePassword();
        	Intent intent = new Intent(this, ManageActivity.class);
        	startActivity(intent);
            break;	
		}
	}

}
