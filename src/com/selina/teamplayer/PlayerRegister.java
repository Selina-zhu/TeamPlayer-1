package cn.buaa.myweixin;
import com.selina.teamplayer.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PlayerRegister extends Activity {
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.player_register);
	    }
	    
	    public void register(View v) {  
	      	Intent intent = new Intent();
			//intent.setClass(Welcome.this,MainWeixin.class);
	      	//intent.setClass(PlayerRegister.this,MainWeixin.class);
	      	 //Intent intent = new Intent();
	      	//isPlayer = true;
             intent.setClass(PlayerRegister.this,LoadingActivity.class);
             startActivity(intent);
	      	//startActivity(intent);
			//this.finish();
	      }  
	    
	    public void register_back(View v) {     //标题栏 返回按钮
	      	this.finish();
	      }  

}
