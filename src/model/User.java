// User.java
package model;

import observer.UserObservable;
import observer.UserObserver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// This class stores all of the information about a user
public class User implements UserObservable, Serializable {
	// INSTANCE VARIABLES
	private String userName;
	private String password;
	private byte[] salt;
	// contains info about a users workout cycles and exercises
	private FullLog myFullLog;
	private static final long serialVersionUID = 1L;
	private final List<UserObserver> observers = new ArrayList<>();;

	// CONSTRUCTOR
	public User(String name, String pw) {
		this.userName = name;
		this.password = pw;
		this.myFullLog = new FullLog();
	}

	@Override
	public void addObserver(UserObserver o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(UserObserver o) {
		observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		for (UserObserver o : observers) {
			o.modelChanged();
		}
	}

	// METHODS
	public byte[] getSalt() {
		return this.salt;
	}

	// store the salt for the password
	public void setSalt(byte[] salt) {
		this.salt = salt;
	}
	
	// get the user name
	public String getUserName() {
		return this.userName;
	}

	// get the encoded password
	public String getPassword() {
		return this.password;
	}

	public FullLog getMyFullLog() {
		return myFullLog;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(userName, user.userName) && Objects.equals(password, user.password) && Objects.equals(myFullLog, user.myFullLog);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userName, password, myFullLog);
	}
}
