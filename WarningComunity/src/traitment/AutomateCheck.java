/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package traitment;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author 626
 */
public class AutomateCheck {
    ResultSet dataEvents;
    BDDConnect sqlConnection;
    private ScheduledExecutorService scheduler;
    public AutomateCheck()
    {
        sqlConnection = new BDDConnect("localhost:3306/warning_comunity","root","");
        scheduler = Executors.newSingleThreadScheduledExecutor();
    }
    // timer de répetition
    public void routine(){
        scheduler.scheduleAtFixedRate(new Runnable()
        {
            public void run()
            {
               autoCheck();
            }
        }, 0, 30, TimeUnit.MINUTES);
    }
    // methode de verification des dates de fin
    private void autoCheck()
    {
        
            String[] champs = {"event_id","date_fin"};
            try {
                    sqlConnection.select("events", champs, "WHERE 1");
            } catch (Exception e) {
                    e.printStackTrace();
            }
            dataEvents = sqlConnection.getRs();
            try {
                while (dataEvents.next())
                {
                    String id=dataEvents.getString("event_id");
                    String datefin =dataEvents.getString("date_fin");
                    if(testDate(datefin))
                        autoDelete(id);
                }
             } catch (Exception e) {
                        e.printStackTrace();
                }
    }
    private boolean testDate(String date)
    {
        String heure=date.substring(date.indexOf(" ")+1, date.length()-2);
        String jour=date.substring(0,date.indexOf(" "));
        String [] decomDate = new String [3];
        decomDate[0]=jour.substring(0,jour.indexOf("-"));
        jour=jour.substring(jour.indexOf("-")+1);
        decomDate[1]=jour.substring(0,jour.indexOf("-"));
        decomDate[2]=jour.substring(jour.indexOf("-")+1,jour.length());
        String [] decompo = new String[3];
        decompo[0]=heure.substring(0,heure.indexOf(":"));
        heure=heure.substring(heure.indexOf(":")+1);
        decompo[1]=heure.substring(0,heure.indexOf(":"));
        decompo[2]=heure.substring(heure.indexOf(":")+1,heure.length());
//	* Choix de la langue francaise
	Locale locale = Locale.getDefault();
	Date actuelle = new Date();
//	* Definition du format utilise pour les dates
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//	* Donne la date au format "aaaa-mm-jj	
        String dat = dateFormat.format(actuelle);
// reccuperation du calendrier pour l'heure systeme
        Calendar cal = Calendar.getInstance();
        
        if(Integer.parseInt(decomDate[0])<=cal.get(Calendar.YEAR) && 
           Integer.parseInt(decomDate[1])<=cal.get(Calendar.MONTH)+1 && 
           Integer.parseInt(decomDate[2])<cal.get(Calendar.DAY_OF_MONTH))
            return true;
        else if (Integer.parseInt(decomDate[0])<=cal.get(Calendar.YEAR) && 
           Integer.parseInt(decomDate[1])<=cal.get(Calendar.MONTH)+1 && 
           Integer.parseInt(decomDate[2])==cal.get(Calendar.DAY_OF_MONTH))
            if(Integer.parseInt(decompo[0])<=cal.get(Calendar.HOUR_OF_DAY))
                if( Integer.parseInt(decompo[0])==cal.get(Calendar.HOUR_OF_DAY) && Integer.parseInt(decompo[1])<=cal.get(Calendar.MINUTE))
                    return true;          
                
        return false;
    }
    private void autoDelete (String e_id)
    {
        String condition="event_id="+e_id;
        try {
            sqlConnection.delete("events", condition);
            System.out.println("Event numero : "+e_id+" supprime");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
}
