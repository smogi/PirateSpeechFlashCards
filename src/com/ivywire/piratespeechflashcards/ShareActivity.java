package com.ivywire.piratespeechflashcards;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ShareActivity extends Activity implements OnClickListener {
	private Button button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_share);
		
		button = (Button) findViewById (R.id.Social);
		button.setOnClickListener(this);
	}
	
	public void onClick(View v) { 
	    switch(v.getId()){ 
		    case R.id.Social:
			    Intent shareIntent=new Intent(Intent.ACTION_SEND);
				shareIntent.setType("text/plain");
				shareIntent.putExtra(Intent.EXTRA_TEXT,"Learn to talk like a pirate!");
				shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out this app!");
				startActivity(Intent.createChooser(shareIntent, "Share..."));
	    }
    }
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.share, menu);
		return true;
	}
	
	
}
