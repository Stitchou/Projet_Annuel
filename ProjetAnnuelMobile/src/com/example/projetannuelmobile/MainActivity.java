package com.example.projetannuelmobile;

import java.sql.ResultSet;

import com.example.projetannuelmobile.Second;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener {
	
	private static final Context MainActivity = null;
	Bundle objetbunble = new Bundle();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button connect = (Button)findViewById(R.id.connection);
		Button inscription = (Button)findViewById(R.id.inscription);
		connect.setOnClickListener(this);
		inscription.setOnClickListener(this);
		
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	public void onClick(View v) {
		EditText et1 = (EditText) findViewById(R.id.editText1);
		EditText et2 = (EditText) findViewById(R.id.editText2);
		
		Editable ed1 = et1.getText();
		Editable ed2= et2.getText();

		String login = ed1.toString();
		String mdp = ed2.toString();
		Intent i = new Intent(this, Second.class);
		Toast t = null;
		switch(v.getId())
		{
			//Bouton Se connecter
			case R.id.connection :
				//Envoie par socket le login et mdp entré
				i.putExtra("LOGIN", login+"&"+mdp);
				startActivity(i);
				break;
				/*else
				{
					t = Toast.makeText(this,"Erreur dans le login ou le mot de passe !", Toast.LENGTH_LONG );
					t.show();
				}*/	
			//Bouton S'inscrire
			case R.id.inscription :
				//Renvoi vers site web 
				//t = Toast.makeText(this,"Bouton non fonctionnel", Toast.LENGTH_LONG );
                Intent webRedirect = new Intent(Intent.ACTION_VIEW);
                webRedirect.setData(Uri.parse("http://10.0.2.2/site_projetAnnuel/index.php?page=account"));
                startActivity(webRedirect);
				break;
			
		}
		
		

	}
}
