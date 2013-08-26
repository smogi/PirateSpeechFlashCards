package com.ivywire.piratespeechflashcards;

import com.ivywire.piratespeechflashcards.contentprovider.MyCardContentProvider;
import com.ivywire.piratespeechflashcards.database.FlashCardTable;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
     
public class ManageActivity extends FragmentActivity implements OnClickListener {
	private Button removeNaughtyButton;
	private Button changePasswordButton;
	private String disabled;
	private String naughty;
	private PopupWindow popUp;
	private LinearLayout layout;
	private TextView tv;
	private LayoutParams params;
	private LinearLayout mainLayout;
	private FragmentManager fm;
	private boolean click = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage);
		
		removeNaughtyButton = (Button) findViewById(R.id.remove_naughty_button);
		changePasswordButton = (Button) findViewById(R.id.naughty_password_button);
		
		removeNaughtyButton.setOnClickListener(this);
		changePasswordButton.setOnClickListener(this);
		final Context ctx = this;
		if(disabledCheck()){
			removeNaughtyButton.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					PasswordCheckerDialogFragment2 frag = new PasswordCheckerDialogFragment2(ctx, getSupportFragmentManager());
	            	frag.show(getSupportFragmentManager(), "PasswordDialogFragment2");
				}
			});
			
			changePasswordButton.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					PasswordCheckerDialogFragment frag = new PasswordCheckerDialogFragment(getApplicationContext(), getSupportFragmentManager());
	            	frag.show(getSupportFragmentManager(), "PasswordDialogFragment");
				}
			});
		}
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		final Context ctx = this;
		if(disabledCheck()){
			removeNaughtyButton.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					PasswordCheckerDialogFragment2 frag = new PasswordCheckerDialogFragment2(ctx, getSupportFragmentManager());
	            	frag.show(getSupportFragmentManager(), "PasswordDialogFragment2");
				}
			});
			
			changePasswordButton.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					PasswordCheckerDialogFragment frag = new PasswordCheckerDialogFragment(getApplicationContext(), getSupportFragmentManager());
	            	frag.show(getSupportFragmentManager(), "PasswordDialogFragment");
				}
			});
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.manage, menu);
		return true;
	}
	
	public boolean disabledCheck(){
		SharedPreferences pr = getSharedPreferences("PasswordEnabled", 10);
		Boolean enabled = pr.getBoolean("PasswordEnabled", false);
		return enabled;
	}
	
	public void turnNaughtyOff(){
		naughty = "naughtyDisabled";
		disabled = "Yes";
		
		ContentValues values = new ContentValues();
		values.put(FlashCardTable.COLUMN_CATEGORY, naughty);
		values.put(FlashCardTable.COLUMN_TITLE, disabled);
		
		getContentResolver().insert(MyCardContentProvider.CONTENT_URI, values);
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        	case R.id.action_main:
        		Intent intent = new Intent(this, MainActivity.class);
            	startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
        switch(v.getId()){
            case R.id.remove_naughty_button:            
            	turnNaughtyOff();
            	Toast bread = Toast.makeText(getApplicationContext(), "The naughty section is now disabled", Toast.LENGTH_LONG);
            	bread.show();
                break;	
            case R.id.naughty_password_button:
            	PasswordCreateDialogFragment frag = new PasswordCreateDialogFragment(this);
            	frag.show(getSupportFragmentManager(), "PasswordCreateDialogFragment");
            	break;
        }
		
	}
}
