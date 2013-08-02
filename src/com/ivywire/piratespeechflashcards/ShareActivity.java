package com.ivywire.piratespeechflashcards;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class ShareActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_share);
	}
	
	public void onClick(View v) { 
	    switch(v.getId()){ 
	    case R.id.Social:
	    Intent shareIntent=new Intent(Intent.ACTION_SEND);
		shareIntent.setType("text/plain");
		shareIntent.putExtra(Intent.EXTRA_TEXT,"I want to share this with you!");
		shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Great Post");
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
