package com.ivywire.piratespeechflashcards;

import com.ivywire.piratespeechflashcards.contentprovider.MyCardContentProvider;
import com.ivywire.piratespeechflashcards.database.FlashCardTable;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	private Cursor naughtyCursor;
	private String [] nProjection;
	private String nSelection;
	private String[] nSelectionArgs;
	private String nSortOrder;
	
	private String disableNaughty;
	// Fields
	private Button instructionsButton;
	private Button startingButton;
	private Button beginnerButton;
	private Button mediumButton;
	private Button difficultButton;
	private Button complexButton;
	private Button naughtyButton;
	private Button shareButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		instructionsButton = (Button) findViewById(R.id.button1);
		mediumButton = (Button) findViewById(R.id.button2);
		startingButton = (Button) findViewById(R.id.button3);
		difficultButton = (Button) findViewById(R.id.button4);
		beginnerButton = (Button) findViewById(R.id.button5);
		complexButton = (Button) findViewById(R.id.button6);
		naughtyButton = (Button) findViewById(R.id.button7);
		shareButton = (Button) findViewById(R.id.button8);
		
		instructionsButton.setOnClickListener(this);
		mediumButton.setOnClickListener(this);
		startingButton.setOnClickListener(this);
		difficultButton.setOnClickListener(this);
		beginnerButton.setOnClickListener(this);
		complexButton.setOnClickListener(this);
		naughtyButton.setOnClickListener(this);
		shareButton.setOnClickListener(this);
		
		naughtyCheck(naughtyButton);
	}

	// Reactions for button onClicks
	@Override
    public void onClick(View v) {
		Intent intent;
        switch(v.getId()){
            case R.id.button1:
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
            case R.id.button8:
            	intent = new Intent(this, ShareActivity.class);
            	startActivity(intent);
                break;
        }
    }
	
	public void naughtyCheck(Button myButton){
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
			disableNaughty = naughtyCursor.getString(naughtyCursor.getColumnIndex(FlashCardTable.COLUMN_TITLE));
			if(disableNaughty == "Yes"){
				myButton.setEnabled(false);
			}else{
				//Nothing
			}
		}naughtyCursor.close();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
