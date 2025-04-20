// User.java
package model;

import java.io.Serializable;

// This class stores all of the information about a user
public class User implements Serializable {
	// INSTANCE VARIABLES
	private String userName;
	private String password;
	private byte[] salt;
	// contains info about a users workout cycles and exercises
	private FullLog myFullLog;
	private static final long serialVersionUID = 1L;

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
		// TODO make decode function that gets the actual username
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
