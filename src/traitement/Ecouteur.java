package traitement;


import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


// definition de la classe 
public class Ecouteur implements WindowListener {

	Logs fermeture = new Logs();
	
	public void windowClosing(WindowEvent arg0) {
		
		// v�rification contre les fermetures intempestives 
		int rep = JOptionPane.showConfirmDialog(null, "Sur?");
		
		// r�cup�ration de la date et heure pour le log
		Calendar cal = Calendar.getInstance();
		String msg=new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).format(new Date())+" � "+cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND);
		if(rep==0)
		{
			fermeture.ecrire("./Logs/fermeture.txt","Fermeture le " +msg );
			System.exit(0);
		}
		else
		{
			fermeture.ecrire("./Logs/fermeture.txt", "tentative de fermeture le " +msg);
			System.out.println(rep);
		}
	}
	
	//M�thode non utilis�e
	public void windowActivated(WindowEvent arg0) {}

	public void windowClosed(WindowEvent arg0) {}
	
	public void windowDeactivated(WindowEvent arg0){}

	public void windowDeiconified(WindowEvent arg0) {}

	public void windowIconified(WindowEvent arg0) {}

	public void windowOpened(WindowEvent arg0) {}

}