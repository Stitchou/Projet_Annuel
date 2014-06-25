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
import Plugins.Loader;
import graphics.*;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import traitment.*;


public class WarningComunity {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        Login auth=new Login();
        boolean valide=false,continu=true ;

        auth.setVisible(true);
        while (continu && valide == false)
        {
            
            valide=auth.getValide();
           
            Thread.sleep(100);
            continu=auth.getresult();
        }
        
        //Thread.sleep(1000);
        if (valide)
        {   
            Loader.load();
            Runnable r = new Runnable ()
                   {
                       public void run()  {
                           new MyUI().setVisible(true);                         
                       }
                   };
                   SwingUtilities.invokeLater(r);
                   Transmission server=new Transmission();    
        }
        else
            System.exit(0);
    }
}
