package com.selina.teamplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MedOptionsActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.med_options);
	}
	
	public void addMedication(View v) {  
	  Intent intent = new Intent(MedOptionsActivity.this,AddMedicationActivity.class);
   	  startActivity(intent);
  
      }  
	
	public void editMedication(View v) {  
		  Intent intent = new Intent(MedOptionsActivity.this,EditMedicationActivity.class);
	   	  startActivity(intent);
	  
	   }  
	
	public void returnPre(View v)
	{
		//go back
		finish();
	}

}
