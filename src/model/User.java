// User.java
package model;

import java.util.ArrayList;

// This class stores all of the information about a user
public class User {
	// INSTANCE VARIABLES
	private String userName;
	private String password;
	private byte[] salt;
	private FullLog myFullLog;

	/*
	TODO do we want to change this to a integer, or figure out another way to do this that way
	   we can easily turn this class into a json file?

	   I didnt do this for LA 2 so im def going to be relying on you guys here
	*/
	
	// CONSTRUCTOR
	public User(String name, String pw) {
		this.userName = name;
		this.password = pw;
		this.myFullLog = new FullLog();
	}

	// METHODS
	public byte[] getSalt() {
		return this.salt;
	}

	// store the salt for the password
	public void setSalt(byte[] salt) {
		this.salt = salt;
	}
	
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public FullLog getMyFullLog() {
		return myFullLog;
	}

	public void setMyFullLog(FullLog myFullLog) {
		this.myFullLog = myFullLog;
	}
}
