package ihm;

import traitement.*;
import java.awt.*;
import javax.swing.*;

public class MyUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Toolkit tk=Toolkit.getDefaultToolkit();
	public MyUI()
	{
		// listeneur 
		Ecouteur ec= new Ecouteur();
		addWindowListener(ec);
		// taille écran
		Dimension d = tk.getScreenSize();
		
		// paramétrage de la fenetre 
		setTitle("Exemple");
		setSize(d.width/2,d.height/2);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);		
	}
}
