
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
                    else // reponse sspécifique à l'envoyeur
                    {
                        String msg="";
                        while(st.hasMoreTokens())
                        {
                            msg=msg+" " +st.nextToken();
                        }
                        for(iCount=0;iCount<LoginNames.size();iCount++)
                        {
                            System.out.println("passe run "+LoginNames.elementAt(iCount)+" MSG "+msg);
                            if(LoginNames.elementAt(iCount).equals(Sendto))
                            {   
                                Socket tSoc=(Socket)ClientSockets.elementAt(iCount);
                                DataOutputStream tdout=new DataOutputStream(tSoc.getOutputStream());
                                tdout.writeUTF(msg);                            
                                break;
                            }
                        }
                        if(iCount==LoginNames.size())
                        {
                            dout.writeUTF("I am offline ");
                        }
                        else
                        {

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