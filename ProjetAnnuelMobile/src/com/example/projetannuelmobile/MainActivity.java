package com.example.projetannuelmobile;

import android.location.LocationManager;
import android.location.Location;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	long start;
	//LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE); 
	//Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	double longitude;
	double latitude;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button b1 = (Button)findViewById(R.id.button1);
		Button b2 = (Button)findViewById(R.id.button2);
		Button b3 = (Button)findViewById(R.id.button3);
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Toast t;
		switch (v.getId())
		{
			case R.id.button1:
				// instruction si on click sur le 1er bouton
				//longitude = l.getLongitude();
				//latitude = l.getLatitude();
				//t = Toast.makeText(this, " 1er BOUTON \nLatitude : " + latitude + "\nLongitude : " + longitude, Toast.LENGTH_LONG);
				t = Toast.makeText(this, " 1er BOUTON ", Toast.LENGTH_LONG);
				t.show();
				break;
			case R.id.button2:
				// instruction si on click sur le 2eme bouton
				t = Toast.makeText(this, " 2eme BOUTON ", Toast.LENGTH_LONG);
				//longitude = location.getLongitude();
				//latitude = location.getLatitude();
				t.show();
				break;
			case R.id.button3:
				// instruction si on click sur le 3eme bouton
				t = Toast.makeText(this, " 3eme BOUTON ", Toast.LENGTH_LONG);
				//longitude = location.getLongitude();
				//latitude = location.getLatitude();
				t.show();
				break;
		}
	}

}
