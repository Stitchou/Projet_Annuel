package com.example.projetannuelmobile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/*
public class BDD{
  private String connexion;
  private String root;
  private String pass;
  private ResultSet rs;

  public BDD(){
	  //this.connexion = "jdbc:mysql://localhost/sitepa";
	  this.connexion = "jdbc:sqlite://localhost/sitepa.db";
	  this.root = "root";
	  this.pass = "";
  }

  public BDD(String connexion, String root, String pass){
	this.connexion = ("jdbc:sqlite://" + connexion);
	//this.connexion = ("jdbc:sqlite://" + connexion);
	this.root = root;
    this.pass = pass;
  }
  
  public Connection getConnection() throws Exception{
	  Connection c = null;
      //String pilote = "com.mysql.jdbc.Driver";
      //String pilote = "org.gjt.mm.mysql.Driver";
      String pilote = "org.sqlite.JDBC";
      try {
		Class.forName(pilote);
	    c = DriverManager.getConnection(this.connexion, this.root, this.pass);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return c;
  }
  
  public void setConn(String c){
    this.connexion = c;
  }
  
  public String getConn(){
    return this.connexion;
  }
  
  public void setRoot(String r){
    this.root = r;
  }
  
  public String getRoot(){
    return this.root;
  }
  
  public void setPass(String p){
    this.pass = p;
  }
  
  public String getPass(){
    return this.pass;
  }
  
  public ResultSet getRs(){
    return this.rs;
  }
  
  public void insert_into(String table, String[] champs, String[] value) throws Exception{
    try
    {
      String insert = "INSERT INTO " + table + "(";
      
      int i = 0;
      for (i = 0; i < champs.length; i++)
      {
        if (i == 0) {
          insert = insert + champs[i];
        } else {
          insert = insert + ", " + champs[i];
        }
        if (i == champs.length - 1) {
          insert = insert + ")";
        }
      }
      insert = insert + " Values(";
      for (i = 0; i < value.length; i++)
      {
        if (i == 0) {
          insert = insert + "'" + value[i];
        } else {
          insert = insert + "', '" + value[i];
        }
        if (i == value.length - 1) {
          insert = insert + "')";
        }
      }
      Connection c = getConnection();
      Statement s = c.createStatement();
      s.executeUpdate(insert);
    }catch (ClassNotFoundException e){
      e.printStackTrace();
    }catch (SQLException e){
      System.out.println(e.getMessage());
    }
  }
  
  public void insertInto(String table, String champs, String value) throws Exception{
	  
    String[] champ = champs.split(",");
    String[] val = value.split(",");
    
    try{
      String insert = "INSERT INTO " + table + "(";
      
      int i = 0;
      for (i = 0; i < champ.length; i++)
      {
        if (i == 0) {
          insert = insert + champ[i];
        } else {
          insert = insert + ", " + champ[i];
        }
        if (i == champ.length - 1) {
          insert = insert + ")";
        }
      }
      insert = insert + " Values(";
      for (i = 0; i < val.length; i++){

        if (i == 0) {
          insert = insert + "'" + val[i];
        } else {
          insert = insert + "', '" + val[i];
        }
        if (i == val.length - 1) {
          insert = insert + "')";
        }

      }
      Connection c = getConnection();
      Statement s = c.createStatement();
      s.executeUpdate(insert);
    }catch (ClassNotFoundException e){
      e.printStackTrace();
    }catch (SQLException e){
      System.out.println(e.getMessage());
    }
  }
  
  public void update(String table, String[] champs, String[] value, String condition) throws Exception{
    try{
      String up = "UPDATE " + table + " SET ";
      
      int i = 0;
      for (i = 0; i < champs.length; i++) {
        if (i == 0) {
          up = up + champs[i] + "='" + value[i] + "'";
        } else {
          up = up + ", " + champs[i] + "='" + value[i] + "'";
        }
      }
      up = up + " WHERE " + condition;

      Connection c = getConnection();
      Statement s = c.createStatement();
      s.executeUpdate(up);
    }catch (ClassNotFoundException e){
      e.printStackTrace();
    }catch (SQLException e){
      System.out.println(e.getMessage());
    }
  }
  
  public void updateTwo(String table, String champs, String value, String condition) throws Exception
  {
	if(champs.split(",").length > 1){
	    String[] champ = champs.split(",");
	    String[] val = value.split(",");
	    try
	    {
	      String up = "UPDATE " + table + " SET ";
	      
	      int i = 0;
	      for (i = 0; i < champ.length; i++) {
	        if (i == 0) {
	          up = up + champ[i] + "='" + val[i] + "'";
	        } else {
	          up = up + ", " + champ[i] + "='" + val[i] + "'";
	        }
	      }
	      up = up + " WHERE " + condition;
	
	      Connection c = getConnection();
	      Statement s = c.createStatement();
	      s.executeUpdate(up);
	    }catch (ClassNotFoundException e){
	      e.printStackTrace();
	    }catch (SQLException e){
	      System.out.println(e.getMessage());
	    }
	}else{
	    try {
	    	String up = "UPDATE " + table + " SET " + champs + "=" + value + " " + condition;
	    	
		    Connection c = getConnection();
		    Statement s = c.createStatement();
		    s.executeUpdate(up);
		 }catch (ClassNotFoundException e){
		    e.printStackTrace();
		 }catch (SQLException e){
		    System.out.println(e.getMessage());
		 }
	}
  }
  
  public void delete(String table, String condition) throws Exception{
    try{
      String del = "DELETE FROM " + table;
      del = del + " WHERE " + condition;

      Connection c = getConnection();
      Statement s = c.createStatement();
      s.executeUpdate(del);
    }catch (ClassNotFoundException e){
      e.printStackTrace();
    }catch (SQLException e){
      System.out.println(e.getMessage());
    }
  }
  
  public void select(String table, String[] champs, String condition) throws Exception{
    try{
      String sel = "SELECT ";
      int i = 0;
      for (i = 0; i < champs.length; i++) {
        if (i == 0) {
          sel = sel + champs[i];
        } else if (i > 0) {
          sel = sel + ", " + champs[i];
        }
      }
      sel = sel + " FROM " + table + " " + condition;

      Connection c = getConnection();
      Statement s = c.createStatement();
      this.rs = s.executeQuery(sel);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
  
  public void selectTwo(String table, String champs, String condition) throws Exception{
    try {
      String sel = "SELECT ";
      sel = sel + champs;
      sel = sel + " FROM " + table + " " + condition;

      Connection c = getConnection();
      Statement s = c.createStatement();
      this.rs = s.executeQuery(sel);
    } catch (ClassNotFoundException e){
      e.printStackTrace();
    } catch (SQLException e){
      System.out.println(e.getMessage());
    }
  }
}
*/

public class BDD  extends SQLiteOpenHelper {

	public static final String USER_KEY = "id";
	public static final String USER_LOGIN = "login";
	public static final String USER_PWD = "pwd";
	public static final String USER_MAIL = "mail";
	public static final String USER_ACTIF = "actif";
	    
	public static final String USER_TABLE_NAME = "user";
	public static final String USER_TABLE_CREATE =
							"CREATE TABLE " + USER_TABLE_NAME + " (" +
							USER_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
							USER_LOGIN + " VARCHER(100), " +
							USER_PWD + " VARCHER(100)," +
							USER_MAIL + " VARCHER(100)," +
							USER_ACTIF + " INTEGER" +
							");";
	public static final String USER_TABLE_DROP = "DROP TABLE IF EXISTS " + USER_TABLE_NAME + ";";
	  
	public BDD(Context context, String name, CursorFactory factory, int version) {
		  super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL( USER_TABLE_CREATE );
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL( USER_TABLE_DROP );
		onCreate(db);
	}
	
}