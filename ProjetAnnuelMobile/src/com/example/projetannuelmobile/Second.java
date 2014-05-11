package com.example.projetannuelmobile;

import android.app.Activity;
//import android.content.Context;
//import android.location.Location;
//import android.location.LocationListener;
//import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Second extends Activity implements OnClickListener {

	//LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE); 
	//Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	double longitude;
	double latitude;
	private int id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		id = (Integer) getIntent().getExtras().get("id");
		/*
		Button b1 = (Button)findViewById(R.id.button1);
		Button b2 = (Button)findViewById(R.id.button2);
		Button b3 = (Button)findViewById(R.id.button3);
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}

/*
	LocationManager  lmo  = (LocationManager)getSystemService(Context.LOCATION_SERVICE) ;
	LocationListener llo  = new LocationManagerHelper(){
		@Override
		public void onLocationChanged(Location lc) {
			super.onLocationChanged(lc) ; // On execute la méthode parente

			latitude = LocationManagerHelper.getLatitude(); 
			longitude = LocationManagerHelper.getLongitude(); 
		}
	};
*/
	
	@Override
	public void onClick(View v) {
	/*	// TODO Auto-generated method stub
		Toast t;
		switch (v.getId())
		{
			case R.id.button1:
				// instruction si on click sur le 1er bouton
				//t = Toast.makeText(this, " 1er BOUTON \nLatitude : " + latitude + "\nLongitude : " + longitude, Toast.LENGTH_LONG);
				t = Toast.makeText(this, " 1er BOUTON ", Toast.LENGTH_LONG);
				t.show();
				break;
			case R.id.button2:
				// instruction si on click sur le 2eme bouton
				//t = Toast.makeText(this, " 2eme BOUTON \nLatitude : " + latitude + "\nLongitude : " + longitude, Toast.LENGTH_LONG);
				t = Toast.makeText(this, " 2eme BOUTON ", Toast.LENGTH_LONG);
				//longitude = location.getLongitude();
				//latitude = location.getLatitude();
				t.show();
				break;
			case R.id.button3:
				// instruction si on click sur le 3eme bouton
				//t = Toast.makeText(this, " 3eme BOUTON \nLatitude : " + latitude + "\nLongitude : " + longitude, Toast.LENGTH_LONG);
				t = Toast.makeText(this, " 3eme BOUTON ", Toast.LENGTH_LONG);
				//longitude = location.getLongitude();
				//latitude = location.getLatitude();
				t.show();
				break;
		}
		*/
	}
}