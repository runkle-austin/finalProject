// User.java
package model;

// This class stores all of the information about a user
public class User {
	// INSTANCE VARIABLES
	private String userName;
	private String password;
	/*
	TODO do we want to change this to a integer, or figure out another way to do this that way
	   we can easily turn this class into a json file?

	   I didnt do this for LA 2 so im def going to be relying on you guys here
	*/
	private byte[] salt;
	
	// CONSTRUCTOR
	public User(String name, String pw) {
		this.userName = name;
		this.password = pw;
	}
	
	// METHODS
	// store the salt for the password
	public void setSalt(byte[] salt) {
		this.salt = salt;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public String getPassword() {
		return this.password;
	}

	public byte[] getSalt() {
		return this.salt;
	}
}
