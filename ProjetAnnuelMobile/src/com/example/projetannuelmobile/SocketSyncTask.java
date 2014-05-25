package com.example.projetannuelmobile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import android.os.AsyncTask;

public class SocketSyncTask extends AsyncTask<String, Void, String>{
	@Override
	protected String doInBackground(String... params) {
		String advice = "empty";
		try {
	       Socket s = new Socket("10.0.2.2", 4444);
	       PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
	        pw.write(params[0]);
	        pw.flush();
	        pw.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	        
	        return advice;
	    }

	protected void onPostExecute(String result) {
	     //Post execute
	}
	protected void onPreExecute() {
	   //Start
	} 
}
