package cn.buaa.myweixin;
import com.selina.teamplayer.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Welcome extends Activity {
	 static public Boolean isPlayer = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
    }
    public void welcome_login(View v) {  
      	Intent intent = new Intent();
		intent.setClass(Welcome.this,Login.class);
		startActivity(intent);
		//this.finish();
      }  
    public void welcome_register(View v) {  
      	Intent intent = new Intent();
		//intent.setClass(Welcome.this,MainWeixin.class);
      	setPlayer( true);
      	intent.setClass(Welcome.this,PlayerRegister.class);
		startActivity(intent);
		//this.finish();
      }  
    public void Coach_register(View v) { 
    	setPlayer( false);
      	Intent intent = new Intent();
		//intent.setClass(Welcome.this,MainWeixin.class);
      	intent.setClass(Welcome.this,PlayerRegister.class);
		startActivity(intent);
		//this.finish();
      }  
    public static Boolean getPlayer(){
    	return isPlayer;
    }
    public  void setPlayer(Boolean isP){
    	 isPlayer = isP;
    }
    
}
