/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package warningcomunity;

/**
 *
 * @author 626
 */

import graphics.*;
import traitment.*;
import javax.swing.SwingUtilities;


public class WarningComunity {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         Runnable r = new Runnable ()
		{
		    public void run()  {
		        new MyUI().setVisible(true);
		    }
		};
		SwingUtilities.invokeLater(r);
    }
    
}
