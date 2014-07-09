package com.example.projetannuelmobile;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Second extends Activity implements OnClickListener, SocketSyncResponse {

 private SocketSyncTask asyncTask;
 private String datas;
 Timer timer;
 MyTimerTask myTimerTask;
 private double longitude;
 private double latitude;
 private int id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			datas = extras.getString("LOGIN");
			if (datas != null) {
				 asyncTask = new SocketSyncTask();
				 asyncTask.delegate=this;
			     asyncTask.execute(datas);
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

		timer = new Timer();
	    myTimerTask = new MyTimerTask();
	    
		timer.schedule(myTimerTask, 1000, 30000);
		// id = (Integer) getIntent().getExtras().get("id");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
	    if ((keyCode == KeyEvent.KEYCODE_BACK))
	    {
	        asyncTask.onProgressUpdate(datas.substring(0,datas.indexOf("&"))+"&LOGOUT");
	    }
	    return super.onKeyDown(keyCode, event);
	}
	 class MyTimerTask extends TimerTask {

		  @Override
		  public void run() {
		  
		   
		   runOnUiThread(new Runnable(){

		    @Override
		    public void run() {
		    	asyncTask.onProgressUpdate(datas.substring(0,datas.indexOf("&"))+"&LOCATION&"+longitude+"&"+latitude);
		    }});
		  }
		  
		 }
	
	
	
 
	
	@Override
	public void onClick(View v) {
		/*
		 * // TODO Auto-generated method stub
		 */
		Toast t;

		GPSTracker gps = new GPSTracker(this);
		latitude = gps.getLatitude();
		longitude = gps.getLongitude();
		
		switch (v.getId()) {
		case R.id.radars:
			// instruction si on click sur le 1er bouton
		 t = Toast.makeText(this, " Signalement Radar en cours ! \nLatitude : " + latitude +
			 "\nLongitude : " + longitude, Toast.LENGTH_LONG);
			t.show();
			asyncTask.onProgressUpdate(datas.substring(0,datas.indexOf("&"))+"&EVENTS&"+longitude+"&"+latitude+"&"+1);
			break;
		case R.id.accicents:
			// instruction si on click sur le 2eme bouton
			 t = Toast.makeText(this, " Signalement Accidents en cours ! \nLatitude : " + latitude
			 + "\nLongitude : " + longitude, Toast.LENGTH_LONG);
			t.show();
			asyncTask.onProgressUpdate(datas.substring(0,datas.indexOf("&"))+"&EVENTS&"+longitude+"&"+latitude+"&"+3);
			break;
		case R.id.controle:
			// instruction si on click sur le 3eme bouton
		 t = Toast.makeText(this, " Signalement Controle en cours ! \nLatitude : " + latitude
		 + "\nLongitude : " + longitude, Toast.LENGTH_LONG);
			t.show();
			asyncTask.onProgressUpdate(datas.substring(0,datas.indexOf("&"))+"&EVENTS&"+longitude+"&"+latitude+"&"+4);
			break;
		case R.id.bouchon:
			// instruction si on click sur le 4eme bouton
			 t = Toast.makeText(this, " Signalement Bouchon en cours ! \nLatitude : " + latitude
			 + "\nLongitude : " + longitude, Toast.LENGTH_LONG);
				t.show();
				asyncTask.onProgressUpdate(datas.substring(0,datas.indexOf("&"))+"&EVENTS&"+longitude+"&"+latitude+"&"+2);
				break;
		}
	}

	@Override
	public void processFinish(String output) {
		Toast t;
		String [] result = output.split("&");
		if(result[0].equals("LOCATION"))
		{
			
		}
		else
		{
			switch(result[1])
			{
			case "OK":
				return;
			case "NOK":
				t = Toast.makeText(this, "Login et/ou mot de passe incorrect", Toast.LENGTH_LONG);
			     t.show();
				Intent i = new Intent(this, MainActivity.class);
				startActivity(i);
			default:
				return;
			}
		}
		
	}
}