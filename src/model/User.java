// User.java
package model;

// This class stores all of the information about a user
public class User {
	// INSTANCE VARIABLES
	private String userName;
	private String password;
	
	// CONSTRUCTOR
	public User(String name, String pw) {
		this.userName = name;
		this.password = pw;
	}
	
	// METHODS
	public String getUserName() {
		return this.userName;
	}
	
	public String getPassword() {
		return this.password;
	}
}
