package com.selina.teamplayer;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class EditMedicationActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_medication);
	}
	
	public void returnPre(View v)
	{
	  // some code
	  finish();
	}

}
