/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Testplug2;

import java.sql.ResultSet;
import java.util.ArrayList;
//import warningcomunity.*;
import traitment.BDDConnect;
import java.awt.Color; 
import java.awt.Graphics; 
import javax.swing.JFrame; 

/**
 *
 * @author 626
 */
public class Testplug2 implements Plugins.Gestionnaire{
    private String name;
    ArrayList<String> noms = new ArrayList<String>();
    ArrayList<String> nombre = new ArrayList<String>();
        
    public void PlugDeux()
    {
        name="plugdeux";
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
        new Histogramme();
        
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
  
    public class Histogramme extends JFrame 

    { 
        private static final long serialVersionUID = 1L; 
        private int[] valeurs; 
        private int max; 
        private Color[] couleurs; 
        private static final int DEC_X = 40; 
        private static final int DEC_Y = 40; 
        private static final int DEC_TX = DEC_X + 5; 
        private static final int DEC_TY = DEC_Y + 2;
        private static final int DEC_FH = 4; 
        private static final int DEC_FL = 8; 
        private static final int LG_B = 30;
        private static final int INCR = 10; 

        public Histogramme() 
       { 

          super("Histogramme");
          this.valeurs = new int[6];
          this.valeurs[0] = 12; 
          this.valeurs[1] = 10; 
          this.valeurs[2] = 17; 
          this.valeurs[3] = 5; 
          this.valeurs[4] = 13; 
          this.valeurs[5] = 8; 
          this.max = 20;     

          // Initialisation des couleurs. 

          this.couleurs = new Color[6];
          this.couleurs[0] = Color.RED;
          this.couleurs[1] = Color.GREEN;
          this.couleurs[2] = Color.BLUE;
          this.couleurs[3] = Color.YELLOW;
          this.couleurs[4] = Color.MAGENTA;
          this.couleurs[5] = new Color(255, 102, 0);    

          // Propriétés de la fenêtre. 

          setLocation(50, 50); 
          setSize(600, 300); 
          setVisible(true); 
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
       } 

        public void paint(Graphics g) 
       { 
          int x, y, x1, y1, x2, y2, largeur, hauteur; 
          for(int i = 0; i < this.valeurs.length; i++) 
          { 
             x = DEC_X + i * (LG_B + 1); 
             y = getHeight() - DEC_Y - this.valeurs[i] * INCR; 
             largeur = LG_B; 
             hauteur = this.valeurs[i] * INCR; 
             g.setColor(this.couleurs[i]); 
             g.fillRect(x, y, largeur, hauteur);
             x = DEC_TX + i * (LG_B + 1); 
             y = getHeight() - DEC_TY - this.valeurs[i] * INCR;
             g.setColor(Color.BLACK); 
             g.drawString("" + this.valeurs[i], x, y); 

          } 

     

          // Affichage de l'axe X. 

          g.setColor(Color.BLACK); 

          x1 = DEC_X; 

          y1 = getHeight() - DEC_Y; 

          x2 = x1 + this.valeurs.length * LG_B + LG_B; 

          y2 = y1; 

          g.drawLine(x1, y1, x2, y2); 

          g.drawLine(x2, y2, x2 - DEC_FL, y2 - DEC_FH); 

          g.drawLine(x2, y2, x2 - DEC_FL, y2 + DEC_FH); 

           

          // Affichage de l'axe Y. 

          x1 = DEC_X; 

          y1 = getHeight() - DEC_Y; 

          x2 = x1; 

          y2 = y1 - this.max * INCR; 

          g.drawLine(x1, y1, x2, y2); 

          g.drawLine(x2, y2, x2 - DEC_FH, y2 + DEC_FL); 

          g.drawLine(x2, y2, x2 + DEC_FH, y2 + DEC_FL); 

    } 

    } 
}

