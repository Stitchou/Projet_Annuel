/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package secondplug;
import java.sql.ResultSet;
import java.util.ArrayList;
import traitment.BDDConnect;
import java.awt.Color; 
import java.awt.Graphics; 
import javax.swing.JFrame; 
/**
 *
 * @author 626
 */
public class Secondplug implements Plugins.Gestionnaire{
    private String name;
    ArrayList<String> noms = new ArrayList<String>();
    ArrayList<String> nombre = new ArrayList<String>();
        
    public void Secondplug()
    {
        name="Secondplug";
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
        listUserEventsStats();
        for(int i =0;i<noms.size();i++)
        {
            System.out.println(noms.get(i)+" a signaler "+nombre.get(i));
        }
        
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
}
