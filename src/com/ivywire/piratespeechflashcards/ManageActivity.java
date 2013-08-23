package com.ivywire.piratespeechflashcards;

import com.ivywire.piratespeechflashcards.contentprovider.MyCardContentProvider;
import com.ivywire.piratespeechflashcards.database.FlashCardTable;

import android.os.Bundle;
import android.content.ContentValues;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ManageActivity extends FragmentActivity implements OnClickListener {
	private Button removeNaughtyButton;
	private String disabled;
	private String naughty;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage);
		
		removeNaughtyButton = (Button) findViewById(R.id.remove_naughty_button);
		
		removeNaughtyButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.manage, menu);
		return true;
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
                break;
            case R.id.naughty_password_button:
            	PasswordCreateDialogFragment frag = new PasswordCreateDialogFragment();
            	frag.show(getSupportFragmentManager(), "PasswordCreateDialogFragment");
        }
		
	}
}
