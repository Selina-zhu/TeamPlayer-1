package com.selina.teamplayer;

import android.app.IntentService;
import android.content.Intent;

public class RemainderService extends IntentService{
	
	public RemainderService() {
		super("RemainderService");
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
    protected void onHandleIntent(Intent workIntent) {
        // Gets data from the incoming Intent
        String dataString = workIntent.getDataString();
        
    }


}
