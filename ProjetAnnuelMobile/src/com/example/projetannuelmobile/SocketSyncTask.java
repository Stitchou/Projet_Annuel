package com.example.projetannuelmobile;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

public class SocketSyncTask extends AsyncTask<String, Void, String>{
	private String serverResponse = "false";
	
	private DataOutputStream dout;
	public SocketSyncResponse delegate = null;
	private BufferedReader br;
	private Socket s;
	@Override
	protected String doInBackground(String... params) {
		try {
	       s = new Socket("10.0.2.2", 4444);
	       dout = new DataOutputStream(s.getOutputStream());        
	        dout.writeUTF(params[0]);
	        dout.flush();
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
	     
	        if((serverResponse = br.readLine()) != null)
	        	Log.i("Server Answer", serverResponse);	        		
	        
	        br.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	        
	        return serverResponse;
	    }
	@SuppressLint("NewApi")
	protected void onProgressUpdate(String... params) {
		try {
			dout.writeUTF(params[0]);
			dout.flush();
			if(!br.readLine().isEmpty())
				serverResponse = br.readLine();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	public String getServerResponse(){
		return serverResponse;
	}
	
	protected void onPostExecute(String result) {
		delegate.processFinish(result);
	}
	protected void onPreExecute() {
	   //Start
	} 
}
