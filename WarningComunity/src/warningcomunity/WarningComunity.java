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
import java.sql.ResultSet;
import graphics.*;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import traitment.*;
import javax.swing.SwingUtilities;


public class WarningComunity {
private static boolean verifLogin()
{
    ResultSet dataUser;
    String login, mdp;
    for(int i=1; i<4;i++)
    {
        login="";
        mdp="";
        Object[] message = new Object[ 4 ];
        message[ 0 ] = "login :";
        message[ 1 ] = new JTextField();
        message[ 2 ] = "mot de passe";
        message[ 3 ] = new JPasswordField();

        String option[] = { "OK", "Annuler" };

        int result = JOptionPane.showOptionDialog(
            null,
            message,
            "connexion",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            option,
            message[1] );

        if( result == 0 )
        {
          System.out.println( 
            "login : " + ( (JTextField)message[ 1 ] ).getText() +
            "\npasse : " + new String( ( (JPasswordField)message[ 3 ] ).getPassword() ) );
          login=((JTextField)message[ 1 ] ).getText();
          mdp=new String( ( (JPasswordField)message[ 3 ] ).getPassword() );
        }
        else
            return false;
          //  login=JOptionPane.showInputDialog("Essai: "+i+" Entrer votre login");
          //  mdp=JOptionPane.showInputDialog("Essai: "+i+" Entrer votre  mot de passe");
          
       
        BDDConnect sqlConnection = new BDDConnect("localhost:3306/warning_comunity","root","");
        String[] champs = {"COUNT(*)"};
        try {
                sqlConnection.select("users", champs, "WHERE pseudo='"+login+"' AND mdp='"+mdp+"' and level=0");
        } catch (Exception e) {
                e.printStackTrace();
        }
        dataUser = sqlConnection.getRs();
        try {
                int count=0;
                if(dataUser.next()){
                    count=dataUser.getInt("COUNT(*)");
                }
                if(count!=0)
                {
                    JOptionPane.showMessageDialog(null, "Bienvenue sur l'interface d'administration WarningComunnity "+login);
                    return true;
                }
                    
                else
                    JOptionPane.showMessageDialog(null, "Les informations rentrées sont incorrectes ");

            } catch (Exception e) {
                    e.printStackTrace();
            }
			
    
    }
    return false;
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /* boolean valide ;
         
         valide=verifLogin();
        if (valide)
        {   */
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
    //}
}
