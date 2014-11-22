package com.selina.teamplayer;


import java.util.ArrayList;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.selina.teamplayer.R;




public class MainWeixin extends Activity {
	
	public static MainWeixin instance = null;
	 
	private ViewPager mTabPager;	
	private ImageView mTabImg;// ¶¯»­�?¼Æ¬
	private ImageView mTab1,mTab2,mTab3,mTab4;
	private int zero = 0;// ¶¯»­�?¼Æ¬Æ«ÒÆ�?¿
	private int currIndex = 0;// µ±Ç°Ò³¿¨±àºÅ
	private int one;//µ¥¸öË®Æ½¶¯»­Î»ÒÆ
	private int two;
	private int three;
	private LinearLayout mClose;
    private LinearLayout mCloseBtn;
    private View layout;	
	private boolean menu_display = false;
	private PopupWindow menuWindow;
	private LayoutInflater inflater;
	private TextView m_checkin;
	//private Button mRightBtn;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_weixin);
         //Æô¶¯activityÊ±²»×Ô¶¯µ¯³öÈí¼üÅÌ
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); 
        instance = this;
        /*
        mRightBtn = (Button) findViewById(R.id.right_btn);
        mRightBtn.setOnClickListener(new Button.OnClickListener()
		{	@Override
			public void onClick(View v)
			{	showPopupWindow (MainWeixin.this,mRightBtn);
			}
		  });*/
        
        m_checkin = (TextView)findViewById(R.id.Text_Edit_Check);
        mTabPager = (ViewPager)findViewById(R.id.tabpager);
        mTabPager.setOnPageChangeListener(new MyOnPageChangeListener());
        
        mTab1 = (ImageView) findViewById(R.id.img_weixin);
        mTab2 = (ImageView) findViewById(R.id.img_address);
        mTab3 = (ImageView) findViewById(R.id.img_friends);
        mTab4 = (ImageView) findViewById(R.id.img_settings);
        mTabImg = (ImageView) findViewById(R.id.img_tab_now);
        
        if (Welcome.getPlayer()) {
			mTab3.setImageDrawable(getResources().getDrawable(R.drawable.teamplayer_checkin));
			m_checkin.setText("CheckIn");
        	
        }
        mTab1.setOnClickListener(new MyOnClickListener(0));
        mTab2.setOnClickListener(new MyOnClickListener(1));
        mTab3.setOnClickListener(new MyOnClickListener(2));
        mTab4.setOnClickListener(new MyOnClickListener(3));
        Display currDisplay = getWindowManager().getDefaultDisplay();//»ñÈ¡Æ�?Ä»µ±Ç°·Ö±æÂÊ
        int displayWidth = currDisplay.getWidth();
        int displayHeight = currDisplay.getHeight();
        one = displayWidth/4; //ÉèÖÃË®Æ½¶¯»­Æ½ÒÆ´ó�?¡
        two = one*2;
        three = one*3;
        //Log.i("info", "»ñÈ¡µÄÆ�?Ä»·Ö±æÂÊÎª" + one + two + three + "X" + displayHeight);
        
        //InitImageView();//Ê¹ÓÃ¶¯»­
      //½«Òª·ÖÒ³�?ÔÊ¾µÄView×°ÈëÊý×éÖ�?
        LayoutInflater mLi = LayoutInflater.from(this);
        View view1 = mLi.inflate(R.layout.main_tab_weixin, null);
        View view2 = mLi.inflate(R.layout.main_tab_address, null);
        View view3 = mLi.inflate(R.layout.main_tab_friends, null);
        View view4 = mLi.inflate(R.layout.main_tab_settings, null);
        
      //Ã¿¸öÒ³ÃæµÄviewÊý¾�?
        final ArrayList<View> views = new ArrayList<View>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);
      //Ìî³äViewPagerµÄÊý¾�?ÊÊÅäÆ÷
        PagerAdapter mPagerAdapter = new PagerAdapter() {
			
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}
			
			@Override
			public int getCount() {
				return views.size();
			}

			@Override
			public void destroyItem(View container, int position, Object object) {
				((ViewPager)container).removeView(views.get(position));
			}
			
			//@Override
			//public CharSequence getPageTitle(int position) {
				//return titles.get(position);
			//}
			
			@Override
			public Object instantiateItem(View container, int position) {
				((ViewPager)container).addView(views.get(position));
				return views.get(position);
			}
		};
		
		mTabPager.setAdapter(mPagerAdapter);
    }
    /**
	 * �?·±êµã»÷¼àÌý
	 */
	public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}
		@Override
		public void onClick(View v) {
			if (index == 3) {
				
				Intent email = new Intent(android.content.Intent.ACTION_SEND);  
				email.setType("text/plain");  
				String []emailReciver = new String[]{"", "sxz145530@utdallas.edu"};  
				String emailSubject = "I have new message";  
				String emailBody = "test";  
				  
				//设置邮件默认地�?�  
				email.putExtra(android.content.Intent.EXTRA_EMAIL, emailReciver);  
				//设置邮件默认标题  
				email.putExtra(android.content.Intent.EXTRA_SUBJECT, emailSubject);  
				//设置�?默认�?��?的内容  
				email.putExtra(android.content.Intent.EXTRA_TEXT, emailBody);  
				//调用系统的邮件系统  
				startActivity(Intent.createChooser(email, "please choose mail tools")); 
			        
			} else if (2 == index) {
				
	                //Intent intent = new Intent();
	        		//intent.setClass(Welcome.this,MainWeixin.class);
	              	//intent.setClass(MainWeixin.this,googlemap.class);
	        		//startActivity(intent);
				 if (Welcome.getPlayer()) {
						//mTab4.setImageDrawable(getResources().getDrawable(R.drawable.teamplayer_checkin));
					 Intent intent = new Intent();
						//intent.setClass(Welcome.this,MainWeixin.class);
				      	intent.setClass(MainWeixin.this,Checkin.class);
						startActivity(intent);
			        }else {
				Toast.makeText(getApplicationContext(), "I am still working on it...", 
						Toast.LENGTH_SHORT).show();	
				Intent intent = new Intent();
				//intent.setClass(Welcome.this,MainWeixin.class);
		      	intent.setClass(MainWeixin.this,googlemap.class);
				startActivity(intent);
			        }
				
			} else if (1 == index) {
				   String strMobile = "408-300-2033";
				   //此处应该对电�?�?��?进行验�?。。
				    Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+strMobile));
				    
				    startActivity(intent);
			}
			else {
			   mTabPager.setCurrentItem(index);
			}
		}
	};
    
	 /* Ò³¿¨Ç�?»»¼àÌý(Ô­×÷Õß:D.Winter)
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;
			switch (arg0) {
			case 0:
				//mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_weixin_pressed));
				mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_weixin_pressed));
				if (currIndex == 1) {
					animation = new TranslateAnimation(one, 0, 0, 0);
					mTab2.setImageDrawable(getResources().getDrawable(R.drawable.teamplay_phone));
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, 0, 0, 0);
					mTab3.setImageDrawable(getResources().getDrawable(R.drawable.teamplayer_location));
				}
				else if (currIndex == 3) {
					animation = new TranslateAnimation(three, 0, 0, 0);
					mTab4.setImageDrawable(getResources().getDrawable(R.drawable.teamplayer_mail));
				}
				break;
			case 1:
				mTab2.setImageDrawable(getResources().getDrawable(R.drawable.teamplay_phone));
				if (currIndex == 0) {
					animation = new TranslateAnimation(zero, one, 0, 0);
					mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_weixin_normal));
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, one, 0, 0);
					mTab3.setImageDrawable(getResources().getDrawable(R.drawable.teamplayer_location));
				}
				else if (currIndex == 3) {
					animation = new TranslateAnimation(three, one, 0, 0);
					mTab4.setImageDrawable(getResources().getDrawable(R.drawable.teamplayer_mail));
				}
				break;
			case 2:
				mTab3.setImageDrawable(getResources().getDrawable(R.drawable.teamplayer_location));
				if (currIndex == 0) {
					animation = new TranslateAnimation(zero, two, 0, 0);
					mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_weixin_normal));
				} else if (currIndex == 1) {
					animation = new TranslateAnimation(one, two, 0, 0);
					mTab2.setImageDrawable(getResources().getDrawable(R.drawable.teamplay_phone));
				}
				else if (currIndex == 3) {
					animation = new TranslateAnimation(three, two, 0, 0);
					mTab4.setImageDrawable(getResources().getDrawable(R.drawable.teamplayer_mail));
				}
				break;
			case 3:
				mTab4.setImageDrawable(getResources().getDrawable(R.drawable.teamplayer_mail));
				if (currIndex == 0) {
					animation = new TranslateAnimation(zero, three, 0, 0);
					mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_weixin_normal));
				} else if (currIndex == 1) {
					animation = new TranslateAnimation(one, three, 0, 0);
					mTab2.setImageDrawable(getResources().getDrawable(R.drawable.teamplay_phone));
				}
				else if (currIndex == 2) {
					animation = new TranslateAnimation(two, three, 0, 0);
					mTab3.setImageDrawable(getResources().getDrawable(R.drawable.teamplayer_location));
				}
				break;
			}
			currIndex = arg0;
			animation.setFillAfter(true);// True:�?¼Æ¬�?£ÔÚ¶¯»­½áÊøÎ»ÖÃ
			animation.setDuration(150);
			mTabImg.startAnimation(animation);
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {  //»ñÈ¡ back¼ü
    		
        	if(menu_display){         //Èç¹û MenuÒÑ¾­´ò¿ª £¬�?È¹Ø±ÕMenu
        		menuWindow.dismiss();
        		menu_display = false;
        		}
        	else {
        		Intent intent = new Intent();
            	intent.setClass(MainWeixin.this,Exit.class);
            	startActivity(intent);
        	}
    	}
    	
    	else if(keyCode == KeyEvent.KEYCODE_MENU){   //»ñÈ¡ Menu¼ü			
			if(!menu_display){
				//»ñÈ¡LayoutInflaterÊµÀý
				inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
				//ÕâÀïµÄmain²¼¾ÖÊÇÔÚinflateÖ�?¼ÓÈëµÄÅ¶£¬ÒÔÇ°¶¼ÊÇÖ±½Óthis.setContentView()µÄ°É£¿ºÇºÇ
				//¸Ã·½·¨·µ»ØµÄÊÇÒ»¸öViewµÄ¶Ô�?ó£¬ÊÇ²¼¾ÖÖ�?µÄ¸ù
				layout = inflater.inflate(R.layout.main_menu, null);
				
				//�?ÂÃæÎÒÃÇÒª¿¼ÂÇ�?Ë£¬ÎÒÔõÑù½«ÎÒµÄlayout¼ÓÈëµ½PopupWindowÖ�?ÄØ£¿£¿£¿ºÜ¼òµ¥
				menuWindow = new PopupWindow(layout,LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT); //ºó�?½¸ö²ÎÊýÊÇwidthº�?height
				//menuWindow.showAsDropDown(layout); //ÉèÖÃµ¯³ö�?§¹û
				//menuWindow.showAsDropDown(null, 0, layout.getHeight());
				menuWindow.showAtLocation(this.findViewById(R.id.mainweixin), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //ÉèÖÃlayoutÔÚPopupWindowÖ�?�?ÔÊ¾µÄÎ»ÖÃ
				//ÈçºÎ»ñÈ¡ÎÒÃÇmainÖ�?µÄ¿Ø¼þÄØ£¿Ò²ºÜ¼òµ¥
				mClose = (LinearLayout)layout.findViewById(R.id.menu_close);
				mCloseBtn = (LinearLayout)layout.findViewById(R.id.menu_close_btn);
				
				
				//�?ÂÃæ¶ÔÃ¿Ò»¸öLayout½ø�?�?µ¥»÷ÊÂ¼þµÄ×¢²á°É¡£¡£¡£
				//±ÈÈçµ¥»÷Ä³¸öMenuItemµÄÊ±ºò£¬ËûµÄ±³¾°É«¸Ä±ä
				//ÊÂ�?È×¼±¸ºÃÒ»�?©±³¾°�?¼Æ¬»òÕßÑÕÉ«
				mCloseBtn.setOnClickListener (new View.OnClickListener() {					
					@Override
					public void onClick(View arg0) {						
						//Toast.makeText(Main.this, "�?Ë³ö", Toast.LENGTH_LONG).show();
						Intent intent = new Intent();
			        	intent.setClass(MainWeixin.this,Exit.class);
			        	startActivity(intent);
			        	menuWindow.dismiss(); //�?ìÓ¦µã»÷ÊÂ¼þÖ®ºó¹Ø±ÕMenu
					}
				});				
				menu_display = true;				
			}else{
				//Èç¹ûµ±Ç°ÒÑ¾­Îª�?ÔÊ¾×´Ì¬£¬ÔòÒþ²ØÆðÀ´
				menuWindow.dismiss();
				menu_display = false;
				}
			
			return false;
		}
    	return false;
    }
	//ÉèÖÃ±êÌâÀ¸ÓÒ²à°´Å¥µÄ×÷ÓÃ
	public void btnmainright(View v) {  
		//Intent intent = new Intent (MainWeixin.this,MainTopRightDialog.class);
		Toast.makeText(getApplicationContext(), "I am still working on it...", 
				Toast.LENGTH_SHORT).show();	
		//startActivity(intent);	
		//Toast.makeText(getApplicationContext(), "µã»÷�?Ë¹¦ÄÜ°´Å¥", Toast.LENGTH_LONG).show();
      }  	
	public void startchat(View v) {      //�?¡ºÚ  ¶Ô»°½çÃæ
		Intent intent = new Intent (MainWeixin.this,ChatActivity.class);			
		startActivity(intent);	
		//Toast.makeText(getApplicationContext(), "µÇÂ¼³É¹¦", Toast.LENGTH_LONG).show();
      }  
	public void exit_settings(View v) {                           //�?Ë³ö  Î±¡°¶Ô»°¿ò¡±£¬ÆäÊµÊÇÒ»¸öactivity
		Intent intent = new Intent (MainWeixin.this,ExitFromSettings.class);			
		startActivity(intent);	
	 }
	public void btn_shake(View v) {                                   //ÊÖ»úÒ¡Ò»Ò¡
		Intent intent = new Intent (MainWeixin.this,ShakeActivity.class);			
		startActivity(intent);	
		Toast.makeText(getApplicationContext(), "I am still working on it...", 
				Toast.LENGTH_SHORT).show();	
	}
}
    
    

