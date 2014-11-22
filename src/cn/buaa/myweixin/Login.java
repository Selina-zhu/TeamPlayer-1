package cn.buaa.myweixin;
import com.selina.teamplayer.R;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
	private EditText mUser; // ’ ∫≈±‡º≠øÚ
	private EditText mPassword; // √‹¬Î±‡º≠øÚ

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        mUser = (EditText)findViewById(R.id.firstname_edit);
        mPassword = (EditText)findViewById(R.id.confirm_passwd_edit);
        
    }

    public void login_mainweixin(View v) {
    	if("DST2".equals(mUser.getText().toString()) && "123".equals(mPassword.getText().toString()))   //≈–∂œ ’ ∫≈∫Õ√‹¬Î
        {
             Intent intent = new Intent();
             intent.setClass(Login.this,LoadingActivity.class);
             startActivity(intent);
             //Toast.makeText(getApplicationContext(), "µ«¬º≥…π¶", Toast.LENGTH_SHORT).show();
          }
        else if("".equals(mUser.getText().toString()) || "".equals(mPassword.getText().toString()))   //≈–∂œ ’ ∫≈∫Õ√‹¬Î
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
    	
    	//µ«¬º∞¥≈•
    	/*
      	Intent intent = new Intent();
		intent.setClass(Login.this,Whatsnew.class);
		startActivity(intent);
		Toast.makeText(getApplicationContext(), "µ«¬º≥…π¶", Toast.LENGTH_SHORT).show();
		this.finish();*/
      }  
    public void login_back(View v) {     //±ÍÃ‚¿∏ ∑µªÿ∞¥≈•
      	this.finish();
      }  
    public void login_pw(View v) {     //Õ¸º«√‹¬Î∞¥≈•
    	Uri uri = Uri.parse("http://www.utdallas.edu/~sxc150030/"); 
    	Intent intent = new Intent(Intent.ACTION_VIEW, uri); 
    	startActivity(intent);
    	//Intent intent = new Intent();
    	//intent.setClass(Login.this,Whatsnew.class);
        //startActivity(intent);
      }  
}
