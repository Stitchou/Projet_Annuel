/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package traitment;

import java.io.IOException;
import java.net.*;

/**
 *
 * @author 626
 */

public class Transmission {
     ServerSocket socket;
     Logs servError;
    public Transmission () {
         try {
             socket = new ServerSocket(1989);
         } 
         catch ( IOException e ) {
            servError.ecrire("./Logs/serveur.txt", e.getMessage());
        }
        Thread t = new Thread(new connectClient(socket));
        t.start();
        System.out.println("Mes employeurs sont prêts !");
        
    } 
}

// thrading des connections afin de pouvroi gérer plsuieurs conenxiosn au serveur
class connectClient implements Runnable {

    private ServerSocket socketserver;
    private Socket socket;
    private int nbrclient = 1;
         public connectClient(ServerSocket s){
                 socketserver = s;
         }
         public void run() {

         try {
                 while(true){
                   socket = socketserver.accept(); // Un client se connecte on l'accepte
                   System.out.println("Le client numéro "+nbrclient+ " est connecté !");
                   nbrclient++;
                   socket.close();
                 }

         } catch (IOException e) {
                         e.printStackTrace();
                 }
         }
}