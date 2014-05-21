package defaultmain;

import ihm.*;
import traitement.*;
import javax.swing.SwingUtilities;

public class application {
	
	public static void main ( String [] args)
	{
		Runnable r = new Runnable ()
		{
		    public void run()  {
		        new MyUI();
		    }
		};
		SwingUtilities.invokeLater(r);
	}

}
