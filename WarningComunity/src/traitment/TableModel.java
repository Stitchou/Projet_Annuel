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
	
	//Event table Result sets
	private ResultSet dataEvent;
	private ResultSet dataUserForEvent;
	private ResultSet dataTypeEvent;
	
	//User table Result set
	private ResultSet dataUser;
	
	//Content de la table par defaut  (Event)
	private String[][] tableContent;
	
	//Content de la table user
	private String[][] tableUserContent;
	
	private Logs classLog = new Logs();
	private String msgLogs;
	private String tableModelType;
	
	
	//User's table constructor
	public TableModel(String id){
		super();
		tableModelType = id;
		switch(id)
		{
		case "user" :
			//Recuperation ResultSet de la BDD
			
			BDDConnect sqlConnection = new BDDConnect("localhost:3306/warning_comunity","root","");
			String[] champs = {"users_id","pseudo","nom","prenom","mail","level"};
			try {
				sqlConnection.select("users", champs, "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			dataUser = sqlConnection.getRs();
			
			//allocation tableContentUser
			try {
				dataUser.last();
				tableUserContent = new String[dataUser.getRow()][6];
				dataUser.beforeFirst();
			} catch (Exception e) {
				e.printStackTrace();
			}
			//Copying data from ResultSet to tableContent
			int i = 0;
			try {
				while(dataUser.next()){
					tableUserContent[i][0] = dataUser.getString("users_id");
					tableUserContent[i][1] =  dataUser.getString("pseudo");
					tableUserContent[i][2] =  dataUser.getString("nom");
					tableUserContent[i][3] =  dataUser.getString("prenom");
					tableUserContent[i][4] =  dataUser.getString("mail");
					tableUserContent[i][5] =  dataUser.getString("level");
					i++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			setDataVector(
					tableUserContent,
					new Object[]{"ID User","Pseudo","Nom User","Prenom User","Mail","Level"});
			addTableModelListener(this);
			break;
		
		case "appli" :
			Logs classLog = new Logs();
			String[][] user_list;
			String content = classLog.lire("./Logs/user_list.txt");
			if(content.equals(""))
			{
				user_list = new String[10][5];
			}
			else
			{
				String[] test1 = content.split("\n");
				user_list = new String[test1.length][test1[0].split("&").length];
				for(int j =0;j < user_list.length; j++)
				{
					user_list[j] = test1[j].split("&");
				}
			}
			
			setDataVector(
					user_list,
					new Object[]{"N","Identifiant","Mot de passe","Date de connexion","Heure de connexion"});
			addTableModelListener(this);
			break;
		}
	}
	
	//Default constructor (Event table)
	public TableModel(){
		
		super();
		tableModelType = "event";
		//Get all data from database
		BDDConnect sqlConnection = new BDDConnect("localhost:3306/warning_comunity","root","");
		String[] champs = {"event_id","users_id","type_event","date_debut","date_fin"};
		try {
			sqlConnection.select("events", champs, "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		dataEvent = sqlConnection.getRs();
		try {
			sqlConnection.selectTwo("users", "*", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		dataUserForEvent = sqlConnection.getRs();
		try {
			sqlConnection.selectTwo("type_event", "*", "");
		} catch (Exception e) {
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
				tableContent[i][2] = getRelation("type_event","nom", "type_event", dataEvent.getString("type_event"));
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
					while(dataUserForEvent.next()){
						if(valeurCondition.equals(dataUserForEvent.getString(champCondition)))
						{
							value = dataUserForEvent.getString(champ);
							dataUserForEvent.beforeFirst();
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
	
	public String getTableModelType(){
		return tableModelType;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex){
		switch( columnIndex ){
			case 2:
			case 3:
			case 4:
				return true;
			case 0:
			case 1:
				return false;
			default:
				return true;
		}
	}
	
	//A FAIRE : enregistrement BDD
	@Override
	public void tableChanged(TableModelEvent e) {
		if( isCellEditable(e.getLastRow(), e.getColumn()) == false && tableModelType.equals("event") ) return; 
		Calendar cal = Calendar.getInstance();
		String date = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).format(new Date())+" à "+cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND);
		switch(e.getType())
		{
		case TableModelEvent.INSERT :		
			switch(tableModelType)
			{
			case "event" :
				msgLogs = "Insertion "+tableModelType+" le "+date+" - ID : "+ tableContent[e.getLastRow()][0]
						+"|| Nouvelle valeur : "+getValueAt(e.getLastRow(),e.getColumn());
				classLog.ecrire("./Logs/events.txt", msgLogs);
				break;
			case "user" :
				msgLogs = "Insertion "+tableModelType+" le "+date+" - ID : "+ tableUserContent[e.getLastRow()][0]
						+"|| Nouvelle valeur : "+getValueAt(e.getLastRow(),e.getColumn());
				classLog.ecrire("./Logs/users.txt", msgLogs);
				break;
			}
			System.out.println(msgLogs);
			break;
		case TableModelEvent.UPDATE :	
			switch(tableModelType)
			{
			case "event" :
				msgLogs = "Update "+tableModelType+" le "+date+" - ID : "+ tableContent[e.getLastRow()][0]
						+"|| Nouvelle valeur : "+getValueAt(e.getLastRow(),e.getColumn());
				classLog.ecrire("./Logs/events.txt", msgLogs);
				break;
			case "user" :
				msgLogs = "Update "+tableModelType+" le "+date+" - ID : "+ tableUserContent[e.getLastRow()][0]
						+"|| Nouvelle valeur : "+getValueAt(e.getLastRow(),e.getColumn());
				classLog.ecrire("./Logs/users.txt", msgLogs);
				break;
			}	
			System.out.println(msgLogs);
			break;
		case TableModelEvent.DELETE :
			switch(tableModelType)
			{
			case "event" :
				msgLogs = "Delete "+tableModelType+" le "+date+" - ID : "+ tableContent[e.getLastRow()][0]
						+"|| Nouvelle valeur : "+getValueAt(e.getLastRow(),e.getColumn());
				classLog.ecrire("./Logs/events.txt", msgLogs);
				break;
			case "user" :
				msgLogs = "Delete "+tableModelType+" le "+date+" - ID : "+ tableUserContent[e.getLastRow()][0]
						+"|| Nouvelle valeur : "+getValueAt(e.getLastRow(),e.getColumn());
				classLog.ecrire("./Logs/users.txt", msgLogs);
				break;
			}	
			System.out.println(msgLogs);
			break;
		default :
			break;
		}
		switch(tableModelType)
		{
		case "event" :
			if(e.getLastRow()<tableContent.length && e.getColumn()<tableContent[0].length)
				tableContent[e.getLastRow()][e.getColumn()] = getValueAt(e.getLastRow(),e.getColumn()).toString();
			break;
		case "user" :
			if(e.getLastRow()<tableUserContent.length && e.getColumn()<tableUserContent[0].length)
				tableUserContent[e.getLastRow()][e.getColumn()] = getValueAt(e.getLastRow(),e.getColumn()).toString();
			break;
		}	
		
			
		
	}

}
