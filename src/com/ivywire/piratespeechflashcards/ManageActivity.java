package com.ivywire.piratespeechflashcards;

import com.ivywire.piratespeechflashcards.contentprovider.MyCardContentProvider;
import com.ivywire.piratespeechflashcards.database.FlashCardTable;

import android.os.Bundle;
import android.content.ContentValues;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
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
     
public class ManageActivity extends FragmentActivity implements OnClickListener {
	private Button removeNaughtyButton;
	private String disabled;
	private String naughty;
	private PopupWindow popUp;
	private LinearLayout layout;
	private TextView tv;
	private LayoutParams params;
	private LinearLayout mainLayout;
	private boolean click = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage);
		
		popUp = new PopupWindow(this);
		layout = new LinearLayout(this);
		mainLayout = new LinearLayout(this);
		tv = new TextView(this);
		removeNaughtyButton = new Button(this);
		removeNaughtyButton = (Button) findViewById(R.id.remove_naughty_button);
		removeNaughtyButton.setOnClickListener(this);
		mainLayout.addView(removeNaughtyButton, params);
		
		
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
            	if (click) {
            		popUp.showAtLocation(mainLayout, Gravity.BOTTOM, 10, 10);
            		popUp.update(50, 50, 300, 80);
            		click = false;
            	}else{
            		popUp.dismiss();
            		click = true;
            	}
            	params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            	layout.setOrientation(LinearLayout.VERTICAL);
            	tv.setText("All naughty cards are now disabled.");
            	layout.addView(tv, params);
            	popUp.setContentView(layout); 
            	turnNaughtyOff();
                break;
            case R.id.naughty_password_button:
            	PasswordCreateDialogFragment frag = new PasswordCreateDialogFragment();
            	frag.show(getSupportFragmentManager(), "PasswordCreateDialogFragment");
        }
		
	}
}
