/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package traitment;

/**
 *
 * @author 626
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BDDConnect {




  private String connexion;
  private String root;
  private String pass;
  private ResultSet rs;
  
  public BDDConnect(){
	  /*
	  this.connexion = "jdbc:mysql://db412384129.db.1and1.com/db412384129";
	  this.root = "dbo412384129";
	  this.pass = "yad2626yad";
	  */
	  this.connexion = "jdbc:mysql://localhost/warning_comunity";
	  this.root = "root";
	  this.pass = "";
  }

  public BDDConnect(String connexion, String root, String pass){
    this.connexion = ("jdbc:mysql://" + connexion);
    this.root = root;
    this.pass = pass;
  }
  
  public Connection getConnection() throws Exception{
	  Connection c = null;
      String pilote = "com.mysql.jdbc.Driver";
      //String pilote = "org.gjt.mm.mysql.Driver";
      try {
		Class.forName(pilote);
	    c = DriverManager.getConnection(this.connexion, this.root, this.pass);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		System.out.println(e);
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
            String up="";
	    try
	    {
	      up = "UPDATE " + table + " SET ";
	      
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
	      System.out.println(e.getMessage()+ "\n requete :"+up);
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
      //JOptionPane.showMessageDialog(null, sel, "SELECT", 2);
      this.rs = s.executeQuery(sel);
    } catch (ClassNotFoundException e){
      e.printStackTrace();
    } catch (SQLException e){
      System.out.println(e.getMessage());
    }
  }
}
