package com.selina.teamplayer;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.selina.teamplayer.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.TextView;

public class googlemap extends Activity  {
	 private GoogleMap googleMap;
	 public void onCreate(Bundle savedInstanceState) {
	       
	        //ListAdapter adapter = new CustomArrayAdapter(this, demos);

	        //setListAdapter(adapter);
    	 super.onCreate(savedInstanceState);


	        //=======  For Google Maps Check============
	        if (!this.isGoogleMapsInstalled()) {
	            AlertDialog.Builder builder = new AlertDialog.Builder(this);
	            builder.setMessage("Install Google Map ?");
	            builder.setCancelable(false);
	            builder.setPositiveButton("Install", getGoogleMapsListener());
	            AlertDialog dialog = builder.create();
	            dialog.show();
	        }
	        if(checkGooglePlayServices(this)){
	 	        setContentView(R.layout.googlemap);
	        	
	        }
	       

	         /*   int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

	            // Showing status
	            if(status!=ConnectionResult.SUCCESS){ // Google Play Services are not available

	                int requestCode = 10;
	                

	            }else { // Google Play Services are available

	                // Getting reference to the SupportMapFragment of activity_main.xml
	                SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

	                // Getting GoogleMap object from the fragment
	                googleMap = fm.getMap();

	                googleMap.setMyLocationEnabled(true);

	                LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

	                // Creating a criteria object to retrieve provider
	                Criteria criteria = new Criteria();

	                // Getting the name of the best provider
	                String provider = locationManager.getBestProvider(criteria, true);

	                // Getting Current Location
	                Location location = locationManager.getLastKnownLocation(provider);

	                if(location!=null){
	                    onLocationChanged(location);
	                }
	                locationManager.requestLocationUpdates(provider, 20000, 0, this);
	            }*/

	        }
	        
	 public static boolean checkGooglePlayServices(Activity activity) {
		    int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity);
		    if (resultCode != ConnectionResult.SUCCESS) {
		        if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
		            GooglePlayServicesUtil.getErrorDialog(resultCode, activity, 9000).show();
		        }
		        return false;
		    }
		    return true;
		}
	        public void onLocationChanged(Location location) {

	            //TextView tvLocation = (TextView) findViewById(R.id.tv_location);

	            // Getting latitude of the current location
	            double latitude = location.getLatitude();

	            // Getting longitude of the current location
	            double longitude = location.getLongitude();

	            // Creating a LatLng object for the current location
	            LatLng latLng = new LatLng(latitude, longitude);

	            // Showing the current location in Google Map
	            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

	            // Zoom in the Google Map
	            googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));

	            // Setting latitude and longitude in the TextView tv_location
	           // tvLocation.setText("Latitude:" +  latitude  + ", Longitude:"+ longitude );

	        }

	   
	    
	      
	 public boolean isGoogleMapsInstalled() {
	        try {
	            ApplicationInfo info = getPackageManager().getApplicationInfo("com.google.android.apps.maps", 0);
	            return true;
	        } catch (PackageManager.NameNotFoundException e) {
	            return false;
	        }
	 }

	 public OnClickListener getGoogleMapsListener() {
	        return new OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	                Intent intent = new Intent(Intent.ACTION_VIEW,
	                        Uri.parse("market://details?id=com.google.android.apps.maps"));
	                startActivity(intent);

	                //Finish the activity so they can't circumvent the check
	                finish();
	            }
	            };
	        
	 }
}
