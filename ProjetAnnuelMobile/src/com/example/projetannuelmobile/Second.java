package com.example.projetannuelmobile;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Second extends Activity implements OnClickListener {



	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String datas = extras.getString("EXTRA_ID");
			if (datas != null) {

				 Button b1 = (Button)findViewById(R.id.radars);
				 Button b2 = (Button)findViewById(R.id.accicents);
				 Button b3 = (Button)findViewById(R.id.controle);
				 Button b4 = (Button)findViewById(R.id.bouchon);
				 b1.setOnClickListener(this);
				 b2.setOnClickListener(this);
				 b3.setOnClickListener(this);
				 b4.setOnClickListener(this);
			}
		}
		// id = (Integer) getIntent().getExtras().get("id");
	}


	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}

	
	private double longitude;
	private double latitude;
	private int id;
	

	

	

	public void onClick(View v) {
		/*
		 * // TODO Auto-generated method stub
		 */
		Toast t;
	
		LocationManager lmo = (LocationManager)getSystemService(Context.LOCATION_SERVICE) ;
		Location l = lmo.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		LocationListener llo = new LocationManagerHelper() {

			public void onLocationChanged(Location lc) {
				super.onLocationChanged(lc); // On execute la méthode parente

				latitude = LocationManagerHelper.getLatitude();
				longitude = LocationManagerHelper.getLongitude();
			}
		};
		switch (v.getId()) {
		case R.id.radars:
			// instruction si on click sur le 1er bouton
		 t = Toast.makeText(this, " Signalement Radar en cours ! \nLatitude : " + latitude +
			 "\nLongitude : " + longitude, Toast.LENGTH_LONG);
			t.show();
			break;
		case R.id.accicents:
			// instruction si on click sur le 2eme bouton
			 t = Toast.makeText(this, " Signalement Accidents en cours ! \nLatitude : " + latitude
			 + "\nLongitude : " + longitude, Toast.LENGTH_LONG);
			t.show();
			break;
		case R.id.controle:
			// instruction si on click sur le 3eme bouton
		 t = Toast.makeText(this, " Signalement Controle en cours ! \nLatitude : " + latitude
		 + "\nLongitude : " + longitude, Toast.LENGTH_LONG);
			t.show();
			break;
		case R.id.bouchon:
			// instruction si on click sur le 4eme bouton
			 t = Toast.makeText(this, " Signalement Bouchon en cours ! \nLatitude : " + latitude
			 + "\nLongitude : " + longitude, Toast.LENGTH_LONG);
				t.show();
				break;
		}
	}
}