/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testplug;
import java.awt.Color; 
import java.awt.Graphics; 
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JFrame; 
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import traitment.BDDConnect;
        /**
 *
 * @author 626
 */
public class Testplug implements Plugins.Gestionnaire{
    private String name;
    ArrayList<String> noms = new ArrayList<String>();
    ArrayList<String> nombre = new ArrayList<String>();
        
    public void Testplug()
    {
        name="Testplug";
        System.out.println("plug in construit ");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
    }

    @Override
    public void plug() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   // représente le main représente le main
    public void start() {
        System.out.println("plug in demarrer ");
        stats();
    }

    @Override
    public void unplug() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public String getName() {
        return name;
    }
    
    private void stats()
    {
        noms.clear();
        nombre.clear();
        listUserEventsStats();
        for(int i =0;i<noms.size();i++)
        {
            System.out.println(noms.get(i)+" a signaler "+nombre.get(i));
        }
         
        histogramme();
    }
    private void listUserEventsStats()
    {
        ResultSet dataUser;       
        BDDConnect sqlConnection = new BDDConnect("localhost:3306/warning_comunity","root","");
        String[] champs = {"COUNT(*)","u.pseudo"};
        try {
                sqlConnection.select("users u , events e ", champs, "WHERE u.users_id=e.users_id group by u.users_id");
                
        } catch (Exception e) {
                e.printStackTrace();
        }
        dataUser = sqlConnection.getRs();
        try {
                
                while(dataUser.next()){
                    noms.add(dataUser.getString("u.pseudo"));
                    nombre.add(dataUser.getString("COUNT(*)"));
                }
               
              
            } catch (Exception e) {
                    e.printStackTrace();
            }
    }
    private void histogramme()
    {
       
        int tableau[] = { 19, 3, 15, 7, 11, 9, 13, 5, 17, 1 };

      String sortie = " Relevé des Signalements ";
      // Pour chaque élément du tableau, afficher une barre d'histogramme.
      for ( int compteur = 0; compteur < nombre.size(); compteur++ ) {
         sortie += 
            "\n" + noms.get(compteur) + "\t" + nombre.get(compteur) + "\t";

         // Afficher la barre d'astérisques.
        try
        {
            for ( int etoiles = 0; etoiles < Integer.parseInt(nombre.get(compteur)); etoiles++ )
            sortie += "*";
        }
        catch (NumberFormatException e){
            e.printStackTrace();
        }
      }
        UIManager UI=new UIManager();
        UI.put("OptionPane.background", Color.white);
        UI.put("Panel.background", Color.white);
        JTextArea zoneSortie = new JTextArea();
        zoneSortie.setText( sortie );

        JOptionPane.showMessageDialog( null, zoneSortie,
         "Programme d'affichage d'histogramme",
         JOptionPane.PLAIN_MESSAGE );
        sortie = " Relevé des Signalements ";
    }    
}
