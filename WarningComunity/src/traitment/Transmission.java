
package traitment;

import java.net.*;
import java.util.*;
import java.io.*;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class Transmission
{
    private void insertEvent ( String [] value)
    {
        BDDConnect sqlConnection = new BDDConnect("localhost:3306/warning_comunity","root","");
        String[] champs = {"longitute","latitude","date_debut","date_fin","users_id","type_event"};
        try {
                sqlConnection.insert_into("events", champs,value);
                
        } catch (Exception e) {
                e.printStackTrace();
        }
    }
    private int findId (String log,String pass)
    {
        ResultSet dataUser;
        String login=log, mdp=pass;
        System.out.println(" FIND ID :"+login+" pass:"+mdp);
        BDDConnect sqlConnection = new BDDConnect("localhost:3306/warning_comunity","root","");
        String[] champs = {"users_id"};
        try {
                sqlConnection.select("users", champs, "WHERE pseudo='"+login+"' AND mdp='"+mdp+"'");
                
        } catch (Exception e) {
                e.printStackTrace();
        }
        dataUser = sqlConnection.getRs();
        try {
                int count=-1;
                if(dataUser.next()){
                    count=dataUser.getInt("users_id");
                }
                return count;
              
            } catch (Exception e) {
                    e.printStackTrace();
            }
        return -1;
    }
    private  boolean verifLogin(String log,String pass)
    {
        ResultSet dataUser;
        String login=log, mdp=pass;
       
        BDDConnect sqlConnection = new BDDConnect("localhost:3306/warning_comunity","root","");
        String[] champs = {"COUNT(*)"};
        try {
                sqlConnection.select("users", champs, "WHERE pseudo='"+login+"' AND mdp='"+mdp+"'");
                
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
                    return true;
                }
                    
              
            } catch (Exception e) {
                    e.printStackTrace();
            }
        return false;
    }
    private double DistanceTo(double lat1, double lon1, double lat2, double lon2)
    {
        double rlat1 = Math.PI * lat1/180;
        double rlat2 = Math.PI * lat2/180;
        double rlon1 = Math.PI * lon1/180;
        double rlon2 = Math.PI * lon2/180;

        double theta = lon1-lon2;
        double rtheta = Math.PI * theta/180;

        double dist = Math.sin(rlat1) * Math.sin(rlat2) + Math.cos(rlat1) * Math.cos(rlat2) * Math.cos(rtheta);
        dist = Math.acos(dist);
        dist = dist * 180/Math.PI;
        dist = dist * 60 * 1.1515;

        dist = dist * 1.609344 * 1000; 
        return dist;
    }
    private String [] testLocation (String longi, String lati)
    {
        String [] result = new String[5];
        result[0]="0";
        result[1]="0";
        result[2]="0";
        result[3]="0";
        result[4]="0";
        double distance=0;
        ResultSet dataUser;
       
        BDDConnect sqlConnection = new BDDConnect("localhost:3306/warning_comunity","root","");
        String[] champs = {"longitute","latitude","type_event","event_id"};
        try {
                sqlConnection.select("events", champs, "WHERE 1");
                
        } catch (Exception e) {
                e.printStackTrace();
        }
        dataUser = sqlConnection.getRs();
        try {
               
                while(dataUser.next()){
                    result[0]=dataUser.getString("longitute");
                    result[1]=dataUser.getString("latitude");
                    result[2]=dataUser.getString("type_event");
                    result[4]=dataUser.getString("event_id");
                    distance=DistanceTo(Double.parseDouble(result[1]),Double.parseDouble(result[0]), Double.parseDouble(lati), Double.parseDouble(longi));
                    
                    if(distance<=800)
                    {                                               
                        result[3]=String.valueOf(distance);
                        return result;
                    }
                    result[0]="0";
                    result[1]="0";
                    result[2]="0";                    
                    result[3]="0";
                    result[4]="0";
                }        
              
            } catch (Exception e) {
                    e.printStackTrace();
            }   
        return result;
    }
    private String findEventName(String id)
    {
        String nom="";
        ResultSet dataUser;
        BDDConnect sqlConnection = new BDDConnect("localhost:3306/warning_comunity","root","");
        String[] champs = {"nom"};
        try {
                sqlConnection.select("type_event", champs, "WHERE type_event="+id);
                
        } catch (Exception e) {
                e.printStackTrace();
        }
        dataUser = sqlConnection.getRs();
        try {
               
                if(dataUser.next()){
                    nom=dataUser.getString("nom");
                }
                return nom;                                                
            } catch (Exception e) {
                    e.printStackTrace();
            }   
        return nom;
    }
    private void deleteEvent ( String id)
    {
        BDDConnect sqlConnection = new BDDConnect("localhost:3306/warning_comunity","root","");
        
        try {
                sqlConnection.delete("events", "event_id='"+id+"'");
                // UPDATE `events` SET `date_fin`=DATE_ADD(date_fin, INTERVAL 2 HOUR) where `event_id`=1
        } catch (Exception e) {
                e.printStackTrace();
        }
    }
    private void updateEvent ( String id)
    {
        BDDConnect sqlConnection = new BDDConnect("localhost:3306/warning_comunity","root","");

        try {
                sqlConnection.updateTwo("events","date_fin","DATE_ADD(date_fin, INTERVAL 2 HOUR)", "WHERE event_id='"+id+"'");
        } catch (Exception e) {
                e.printStackTrace();
        }
    }
    
    Logs save_connexion=new Logs();
    static Vector ClientSockets;
    static Vector LoginNames;
    static Vector LoginPass;
    ServerSocket soc;
    public Transmission() throws Exception
    {
         soc=new ServerSocket(4444);
        ClientSockets=new Vector();
        LoginNames=new Vector();
        LoginPass=new Vector();
        while(true)
        {    
            Socket CSoc=soc.accept();        
            AcceptClient obClient=new AcceptClient(CSoc);
        }
    }
    public void stopServ() throws IOException{
    	soc.close();
    }


    public class AcceptClient extends Thread
    {
        String pseudo,pass;
        Socket ClientSocket;
        DataInputStream din;
        DataOutputStream dout;
        AcceptClient (Socket CSoc) throws Exception
        {
            ClientSocket=CSoc;
            
            din=new DataInputStream(ClientSocket.getInputStream());
            dout=new DataOutputStream(ClientSocket.getOutputStream());

            String LoginName=din.readUTF(); // lecture identite
            
            pseudo=LoginName.substring(0,LoginName.indexOf("&"));
            pass=LoginName.substring(LoginName.indexOf("&")+1,LoginName.length());
            //test user in base
            if(verifLogin(pseudo, pass))
            {
                System.out.println("User Logged In :" + pseudo);
                Calendar cal = Calendar.getInstance();
                String msg=new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).format(new Date())+" à "+cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND);
                save_connexion.ecrire("./Logs/users_list.txt", "Connection Users : "+pseudo+" le "+msg);
                LoginNames.add(pseudo);
                LoginPass.add(pass);
                ClientSockets.add(ClientSocket);    
                start();
            }
        }

        public void run()
        {
            while(true)
            {

                try
                {
                    String msgFromClient=new String();
                    msgFromClient=din.readUTF();
                    StringTokenizer st=new StringTokenizer(msgFromClient,"&");
                    String Sendto=st.nextToken();                
                    String MsgType=st.nextToken();
                    System.out.println("passe run message entier : "+msgFromClient);
                    System.out.println("passe run envoyer vers "+Sendto); 
                    System.out.println("passe run message type "+MsgType);
                    int iCount=0;

                    if(MsgType.equals("LOGOUT")) // deconenction
                    {
                        for(iCount=0;iCount<LoginNames.size();iCount++)
                        {   System.out.println("passe run 4 LOGOUT  "+LoginNames.elementAt(iCount));
                            if(LoginNames.elementAt(iCount).equals(Sendto))
                            {
                                LoginNames.removeElementAt(iCount);
                                ClientSockets.removeElementAt(iCount);
                                System.out.println("User " + Sendto +" Logged Out ...");
                                Calendar cal = Calendar.getInstance();
                                String msge=new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).format(new Date())+" à "+cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND);
                                save_connexion.ecrire("./Logs/users_list.txt", "Deconnexion User : "+pseudo+" le "+msge);
                                break;
                            }
                        }
                    }
                    else if(MsgType.equals("EVENTS")) // receptiond e type signalement 
                    {
                        int i=0;
                        String [] msg=new String [4];
                        while(st.hasMoreTokens())
                        {
                            msg[i]=st.nextToken();
                            i++;
                        }
                        
                        // recherche mot de passe
                        
                        for(iCount=0;iCount<LoginNames.size();iCount++)
                        {
                            if(LoginNames.elementAt(iCount).equals(Sendto))
                            {   
                                i=findId(LoginNames.elementAt(iCount).toString(),LoginPass.elementAt(iCount).toString());
                            }
                        }
                        // création des dates
                        String datedebut,datefin="";
                        Calendar cal = Calendar.getInstance();
                        datedebut = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DAY_OF_MONTH)
                                    +" "+cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND);
                       try{
                           switch(Integer.parseInt(msg[2]))
                                {
                                    case 1:
                                    case 2:
                                    case 4:
                                        cal.add(Calendar.HOUR_OF_DAY,2);
                                        datefin=cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DAY_OF_MONTH)
                                    +" "+(cal.get(Calendar.HOUR_OF_DAY))+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND);
                                        break;
                                    
                                    case 3:
                                        cal.add(Calendar.HOUR_OF_DAY,3);
                                        datefin=cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DAY_OF_MONTH)
                                    +" "+(cal.get(Calendar.HOUR_OF_DAY))+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND);
                                        break;
                                }
                       }
                       catch(Exception e)
                       {
                           e.printStackTrace();
                       }
                       
                       String [] listOfvalues={msg[0],msg[1],datedebut,datefin,String.valueOf(i),msg[2]};
                        insertEvent(listOfvalues);
                        
                    }
                    else if(MsgType.equals("LOCATION"))// reponse sspécifique à l'envoyeur
                    {
                        int i=0;
                        String [] msg=new String [2];
                        while(st.hasMoreTokens())
                        {
                            msg[i]=st.nextToken();
                            i++;
                        }
                        String [] resultat = new String[5];
                        resultat=testLocation(msg[0],msg[1]);
                        if(!resultat[2].equals("0"))
                        {
                            String nomEvent=findEventName(resultat[2]);
                            
                            for(iCount=0;iCount<LoginNames.size();iCount++)
                            {
                                if(LoginNames.elementAt(iCount).equals(Sendto))
                                {   
                                    Socket tSoc=(Socket)ClientSockets.elementAt(iCount);
                                    DataOutputStream tdout=new DataOutputStream(tSoc.getOutputStream());
                                    tdout.writeUTF(nomEvent+"&"+resultat[3]+" m&"+resultat[4]);                            
                                    break;
                                }
                            }
                        }
                    }
                    else if(MsgType.equals("CONFIRM"))// reponse sspécifique à l'envoyeur
                    {
                        int i=0;
                        String [] msg=new String [2];
                        while(st.hasMoreTokens())
                        {
                            msg[i]=st.nextToken();
                            i++;
                        }
                        try
                        {
                            i=Integer.parseInt(msg[1]);
                            switch (i){
                                case 0:
                                    deleteEvent(msg[0]);
                                    break;
                                case 1:
                                    updateEvent(msg[0]);
                                    break;
                            }
                        }catch(NumberFormatException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    if(MsgType.equals("LOGOUT"))
                    {
                        break;
                    }

                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }        
            }
        }
    }
}