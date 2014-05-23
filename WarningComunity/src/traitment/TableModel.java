package traitment;


import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;


public class TableModel extends DefaultTableModel implements TableModelListener {
	
	private ResultSet dataEvent;
	private ResultSet dataUser;
	private ResultSet dataTypeEvent;
	private String[][] tableContent;
	private Logs classLog = new Logs();
	private String msgLogs;
	
	public TableModel(){
		
		super();
		//Get all data from database
		BDDConnect sqlConnection = new BDDConnect("localhost:3306/warning_comunity","root","");
		String[] champs = {"event_id","users_id","type_event","date_debut","date_fin"};
		try {
			sqlConnection.select("events", champs, "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dataEvent = sqlConnection.getRs();
		try {
			sqlConnection.selectTwo("users", "*"/*new String[]{"pseudo","users_id"}*/, "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dataUser = sqlConnection.getRs();
		try {
			sqlConnection.selectTwo("type_event", "*"/*new String[]{"nom","type_event"}*/, "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dataTypeEvent = sqlConnection.getRs();
		
		
		//allocation tableContent
		try {
			dataEvent.last();
			tableContent = new String[dataEvent.getRow()][5];
			dataEvent.beforeFirst();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Copying data from ResultSet to tableContent
		int i = 0;
		try {
			while(dataEvent.next()){
				tableContent[i][0] = dataEvent.getString("event_id");
				tableContent[i][1] = getRelation("users","pseudo", "users_id", dataEvent.getString("users_id"));
				tableContent[i][2] = getRelation("type_event","nom", "type_event", dataEvent.getString("type_event"));;
				tableContent[i][3] =  dataEvent.getString("date_debut");
				tableContent[i][4] =  dataEvent.getString("date_fin");
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		setDataVector(
				tableContent,
				new Object[]{"ID Event","Pseudo","Nom Event","Date de debut","Date de fin"});	
		addTableModelListener(this);
		
	}

	//Send all event Data to another class for ComboBox
	public String[] getDataEvent()
	{
		int i = 0;
		String[] temp = null;
			try {
			dataTypeEvent.last();
			temp = new String[dataTypeEvent.getRow()];
			dataTypeEvent.beforeFirst();
			while(dataTypeEvent.next()){
				temp[i] = dataTypeEvent.getString("nom");
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}
	
	//Get Relation data from 2 Results sets
	private String getRelation(String table, String champ, String champCondition, String valeurCondition)
	{
		String value;
		switch(table)
		{
			case "users" :
				try {
					while(dataUser.next()){
						if(valeurCondition.equals(dataUser.getString(champCondition)))
						{
							value = dataUser.getString(champ);
							dataUser.beforeFirst();
							return value;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "type_event":
				try {
					while(dataTypeEvent.next()){
						if(valeurCondition.equals(dataTypeEvent.getString(champCondition)))
						{
							value = dataTypeEvent.getString(champ);
							dataTypeEvent.beforeFirst();
							return value;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
		}		
		return "Erreur Relation";
	}
	
	//A FAIRE
	@Override
	
	
	
	public void tableChanged(TableModelEvent e) {
		Calendar cal = Calendar.getInstance();
		String date = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).format(new Date())+" à "+cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND);
		switch(e.getType())
		{
		case TableModelEvent.INSERT :
			msgLogs = "Insertion EVENT le "+date+" - Nom Event : "+ tableContent[e.getLastRow()][2]
					+"|| Nouvelle valeur : "+getValueAt(e.getLastRow(),e.getColumn());
			System.out.println(msgLogs);
			classLog.ecrire("./Logs/events.txt", msgLogs);
			break;
		case TableModelEvent.UPDATE :
			msgLogs = "Update EVENT le "+date+" - Nom Event : "+ tableContent[e.getLastRow()][2]
					+"|| Nouvelle valeur : "+getValueAt(e.getLastRow(),e.getColumn());
			System.out.println(msgLogs);
			classLog.ecrire("./Logs/events.txt", msgLogs);
			break;
		case TableModelEvent.DELETE :
			msgLogs = "Delete EVENT le "+date+" - Nom Event : "+ tableContent[e.getLastRow()][2]
					+"|| Nouvelle valeur : "+getValueAt(e.getLastRow(),e.getColumn());
			System.out.println(msgLogs);
			classLog.ecrire("./Logs/events.txt", msgLogs);
			break;
		default :
			break;
		}
		if(e.getLastRow()<tableContent.length && e.getColumn()<tableContent[0].length)
		{
			tableContent[e.getLastRow()][e.getColumn()] = getValueAt(e.getLastRow(),e.getColumn()).toString();
		}
			
		
	}

}
