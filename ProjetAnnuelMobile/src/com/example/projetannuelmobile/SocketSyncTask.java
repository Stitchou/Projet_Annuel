package com.example.projetannuelmobile;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import android.os.AsyncTask;
import android.util.Log;

public class SocketSyncTask extends AsyncTask<String, Void, String>{
	private String serverResponse = "false";
	@Override
	protected String doInBackground(String... params) {
		try {
	       Socket s = new Socket("10.0.2.2", 4444);
	       DataOutputStream dout = new DataOutputStream(s.getOutputStream());        
	        dout.writeUTF(params[0]);
	        dout.flush();
	        
	       /* BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
	        while(true)
	        {
	        	if((serverResponse = br.readLine()) != null)
	        		Log.i("Server Answer", serverResponse);
	        		break;
	        }
	        br.close();*/
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	        
	        return serverResponse;
	    }

	protected void onPostExecute(String result) {
	     //Post execute
	}
	protected void onPreExecute() {
	   //Start
	} 
}
