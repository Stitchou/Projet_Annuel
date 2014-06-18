/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package traitment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author 626
 */

public class Transmission {
     ServerSocket socket;
     Logs servError;
     Thread t;
     private String[][] listConnected;
    public Transmission () {
         try {
        	 socket = new ServerSocket(4444);
         } catch (IOException e)
         {
        	 servError.ecrire("./Logs/serveur.txt", e.getMessage());
         }
         
        t = new Thread(new connectClient(socket));
        t.start();
        System.out.println("Server UP sur le port : "+socket.getLocalPort());
        
    } 
    
    public void stop(){
    	t.interrupt();
    }
}

// thrading des connections afin de pouvroi gérer plsuieurs conenxiosn au serveur
class connectClient implements Runnable {

    private ServerSocket socketserver;
    private Socket socket;
    private Logs log = new Logs();
    private String contentSocket;
    private int nbrclient = 1;
    private BufferedReader br;
    private Calendar cal;
    private String msg;
         public connectClient(ServerSocket s){
                 socketserver = s;
         }
         public void run() {

         try {	
                 while(!Thread.currentThread().isInterrupted()){
                   socket = socketserver.accept(); // Un client se connecte on l'accepte
                    br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                   contentSocket = br.readLine();
                   br.close();
                   System.out.println("Appli n"+nbrclient+ " est connectee !\nUtilisateur :"+contentSocket);
                   cal = Calendar.getInstance();
           		   msg=new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).format(new Date())+"&"+cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND);
                   log.ecrire("./Logs/user_list.txt", nbrclient+"&"+contentSocket+"&"+msg);
                   nbrclient++;
                   socket.close();
                 }

         } catch (IOException e) {
                         e.printStackTrace();
                 }
         }
}