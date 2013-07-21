package com.ivywire.piratespeechflashcards;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	
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
		
		instructionsButton.setOnClickListener(this);
		mediumButton.setOnClickListener(this);
		startingButton.setOnClickListener(this);
		difficultButton.setOnClickListener(this);
		beginnerButton.setOnClickListener(this);
		complexButton.setOnClickListener(this);
		naughtyButton.setOnClickListener(this);
		shareButton.setOnClickListener(this);
	}

	//Reactions for button onClicks
	@Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button1:
                break;
            case R.id.button2:
                break;
            case R.id.button3:
                break;
            case R.id.button4:
                break;
            case R.id.button5:
                break;
            case R.id.button6:
                break;
            case R.id.button7:
                break;
            case R.id.button8:
                break;
        }
    }
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
