package com.selina.teamplayer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.selina.teamplayer.R;

public class Checkin extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkin);
    }
    public void checkin(View v) {  
    	Toast.makeText(getApplicationContext(), "Very Good! Check in successful.", 
				Toast.LENGTH_SHORT).show();	
      }  
    
    public void checkin_back(View v){
    	this.finish();
    }
}
