package com.ivywire.piratespeechflashcards;

import com.ivywire.piratespeechflashcards.contentprovider.MyCardContentProvider;
import com.ivywire.piratespeechflashcards.database.CardDatabaseHelper;
import com.ivywire.piratespeechflashcards.database.FlashCardTable;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnClickListener {
	private Cursor naughtyCursor;
	private String [] nProjection;
	private String nSelection;
	private String[] nSelectionArgs;
	private String nSortOrder;

	// Fields
	private Button instructionsButton;
	private Button startingButton;
	private Button beginnerButton;
	private Button mediumButton;
	private Button difficultButton;
	private Button complexButton;
	private Button naughtyButton;
	private Button shareButton;
	private Button rateButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		instructionsButton = (Button) findViewById(R.id.button1_asd);
		mediumButton = (Button) findViewById(R.id.button2);
		startingButton = (Button) findViewById(R.id.button3);
		difficultButton = (Button) findViewById(R.id.button4);
		beginnerButton = (Button) findViewById(R.id.button5);
		complexButton = (Button) findViewById(R.id.button6);
		naughtyButton = (Button) findViewById(R.id.button7);
		rateButton = (Button) findViewById(R.id.ratingButton1_beginner);
		
		shareButton = (Button) findViewById(R.id.button8);
				
		instructionsButton.setOnClickListener(this);
		mediumButton.setOnClickListener(this);
		startingButton.setOnClickListener(this);
		difficultButton.setOnClickListener(this);
		beginnerButton.setOnClickListener(this);
		complexButton.setOnClickListener(this);
		naughtyButton.setOnClickListener(this);
		shareButton.setOnClickListener(this);
		rateButton.setOnClickListener(this);
		naughtyCheck();
		if(passwordCheck()){
			naughtyButton.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					PasswordDialogFragment frag = new PasswordDialogFragment(getApplicationContext());
					frag.show(getSupportFragmentManager(), "PassworDialogFragment");
				}
				
			});
		}
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		naughtyCheck();
		final Context context = getApplicationContext();
		if(passwordCheck()){
			naughtyButton.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					PasswordDialogFragment frag = new PasswordDialogFragment(getApplicationContext());
					frag.show(getSupportFragmentManager(), "PassworDialogFragment");
				}
				
			});
		}
	}

	// Reactions for button onClicks
	@Override
    public void onClick(View v) {
		Intent intent;
        switch(v.getId()){
            case R.id.button1_asd:
            	intent = new Intent(this, InstructionsActivity.class);
            	startActivity(intent);
                break;
            case R.id.button2:
            	intent = new Intent(this, MediumActivity.class);
            	startActivity(intent);
                break;
            case R.id.button3:
            	intent = new Intent(this, StartingActivity.class);
            	startActivity(intent);
                break;
            case R.id.button4:
            	intent = new Intent(this, DifficultActivity.class);
            	startActivity(intent);
                break;
            case R.id.button5:
            	intent = new Intent(this, BeginnerActivity.class);
            	startActivity(intent);
                break;
            case R.id.button6:
            	intent = new Intent(this, ComplexActivity.class);
            	startActivity(intent);
                break;
            case R.id.button7:
            	intent = new Intent(this, NaughtyActivity.class);
            	startActivity(intent);
                break;
            case R.id.ratingButton1_beginner:
            	try{
            		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.ivywire.piratespeechflashcards")));		        
			    }
			    catch (ActivityNotFoundException e){
			    	Toast.makeText(this, "Could not open Android market, please install the market app.", Toast.LENGTH_SHORT).show(); 
			    }
            	break;
            case R.id.button8:
            	Intent shareIntent=new Intent(Intent.ACTION_SEND);
				shareIntent.setType("text/plain");
				shareIntent.putExtra(Intent.EXTRA_TEXT,"Learn to talk like a pirate! https://play.google.com/store/apps/details?id=com.ivywire.piratespeechflashcards");
				shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out this app!");
				startActivity(Intent.createChooser(shareIntent, "Share..."));
                break;
        }
    }
	
	public boolean passwordCheck(){
		SharedPreferences pr = getSharedPreferences("PasswordEnabled", 10);
		Boolean enabled = pr.getBoolean("PasswordEnabled", false);
		return enabled;
	}
	
	public void naughtyCheck(){
		Button button2 = (Button) findViewById(R.id.button7);

		nProjection = new String[] {FlashCardTable.COLUMN_CATEGORY, FlashCardTable.COLUMN_TITLE};
		nSelection = FlashCardTable.COLUMN_CATEGORY + " = " + " 'naughtyDisabled' ";
		naughtyCursor = getContentResolver().query(
				MyCardContentProvider.CONTENT_URI,
				nProjection,
				nSelection,
				null,
				null
				);
		if(naughtyCursor != null && naughtyCursor.getCount()>0){
			naughtyCursor.moveToFirst();
			String disableNaughty = naughtyCursor.getString(naughtyCursor.getColumnIndex(FlashCardTable.COLUMN_TITLE));
			if(disableNaughty.equals("Yes")){
				button2.setClickable(false);
			}else{
				// Nothing
			}
		}naughtyCursor.close();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        	case R.id.action_manage:
        		Intent intent = new Intent(this, ManageActivity.class);
            	startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
	}

}
