package com.selina.teamplayer;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.selina.teamplayer.R;

public class Login extends Activity {
	private EditText mUser; // ÕÊºÅ±à¼­¿ò
	private EditText mPassword; // ÃÜÂë±à¼­¿ò

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        mUser = (EditText)findViewById(R.id.firstname_edit);
        mPassword = (EditText)findViewById(R.id.confirm_passwd_edit);
        
    }

    public void login_mainweixin(View v) {
    	if("Player".equals(mUser.getText().toString()) && "123".equals(mPassword.getText().toString()))   //ÅÐ¶Ï ÕÊºÅºÍÃÜÂë
        {
    		 Welcome.setPlayer(true);
             Intent intent = new Intent();
             intent.setClass(Login.this,LoadingActivity.class);
             startActivity(intent);
             //Toast.makeText(getApplicationContext(), "µÇÂ¼³É¹¦", Toast.LENGTH_SHORT).show();
          } else if("Coach".equals(mUser.getText().toString()) && "123".equals(mPassword.getText().toString())) {
    		 Welcome.setPlayer(false);
             Intent intent = new Intent();
             intent.setClass(Login.this,LoadingActivity.class);
             startActivity(intent);
             //Toast.makeText(getApplicationContext(), "µÇÂ¼³É¹¦", Toast.LENGTH_SHORT).show();
          }
        else if("".equals(mUser.getText().toString()) || "".equals(mPassword.getText().toString()))   //ÅÐ¶Ï ÕÊºÅºÍÃÜÂë
        {
        	new AlertDialog.Builder(Login.this)
			.setIcon(getResources().getDrawable(R.drawable.login_error_icon))
			.setTitle("Team Player")
			.setMessage("The user name or password is empty,please input it.")
			.create().show();
         }
        else{
           
        	new AlertDialog.Builder(Login.this)
			.setIcon(getResources().getDrawable(R.drawable.login_error_icon))
			.setTitle("Team Player")
			.setMessage("The user name or password is incorrect.")
			.create().show();
        }
    	
    	//µÇÂ¼°´Å¥
    	/*
      	Intent intent = new Intent();
		intent.setClass(Login.this,Whatsnew.class);
		startActivity(intent);
		Toast.makeText(getApplicationContext(), "µÇÂ¼³É¹¦", Toast.LENGTH_SHORT).show();
		this.finish();*/
      }  
    public void login_back(View v) {     //±êÌâÀ¸ ·µ»Ø°´Å¥
      	this.finish();
      }  
    public void login_pw(View v) {     //Íü¼ÇÃÜÂë°´Å¥
    	Uri uri = Uri.parse("http://www.utdallas.edu/~sxc150030/"); 
    	Intent intent = new Intent(Intent.ACTION_VIEW, uri); 
    	startActivity(intent);
    	//Intent intent = new Intent();
    	//intent.setClass(Login.this,Whatsnew.class);
        //startActivity(intent);
      }  
}
