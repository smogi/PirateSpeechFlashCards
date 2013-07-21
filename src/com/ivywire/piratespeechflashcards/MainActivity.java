package com.ivywire.piratespeechflashcards;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;

public class MainActivity extends Activity {
	
	//Fields
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
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
