package com.example.projetannuelmobile;



public class User {
	
	private int id;
	private String login;
	private String pwd;
	private String mail;
	private int actif;
	
	public User(int id, String login, String pwd, String mail, int actif) {
		//super();
		this.id = id;
		this.login = login;
		this.pwd = pwd;
		this.mail = mail;
		this.actif = actif;
	}
	
	public User() {
		id = 0;
		login = null;
		pwd = null;
		mail = null;
		actif = 0;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getActif() {
		return actif;
	}
	public void setActif(int actif) {
		this.actif = actif;
	}
	
	
}
