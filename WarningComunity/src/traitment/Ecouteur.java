/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package traitment;

/**
 *
 * @author 626
 */

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

// definition de la classe 
public class Ecouteur implements WindowListener {

	Logs fermeture = new Logs();
	
	public void windowClosing(WindowEvent arg0) {
		
		// vérification contre les fermetures intempestives 
		int rep = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment fermer le serveur ?");
		
		// récupération de la date et heure pour le log
		Calendar cal = Calendar.getInstance();
		String msg=new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).format(new Date())+" à "+cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND);
		if(rep==0)
		{
			fermeture.ecrire("./Logs/fermeture.txt","Fermeture le " +msg );
			fermeture.clear("./Logs/user_list.txt");
			System.exit(0);
		}
		else
		{
			fermeture.ecrire("./Logs/fermeture.txt", "tentative de fermeture le " +msg);
			System.out.println(rep);
		}
	}
	
	//Méthode non utilisée
	public void windowActivated(WindowEvent arg0) {}

	public void windowClosed(WindowEvent arg0) {}
	
	public void windowDeactivated(WindowEvent arg0){}

	public void windowDeiconified(WindowEvent arg0) {}

	public void windowIconified(WindowEvent arg0) {}

	public void windowOpened(WindowEvent arg0) {}

}

