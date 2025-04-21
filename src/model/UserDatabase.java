package model;// UserDatabase.java

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;

// Code adapted from LA2 UserDatabase
public class UserDatabase {
	private ArrayList<User> accounts = new ArrayList<>();

	// this method fills the UserDatabase with the information from previous uses
	public void loadUsers() {
		// read from the UserInfo.txt file
		try(ObjectInputStream input = new ObjectInputStream(new FileInputStream("UserInfo.txt"))){
			// remove any pre-existing users
			accounts.clear();
			// get the numbers of users saved in the file
			int numUsers = input.readInt();
			// create the user objects from the file information
			for (int i = 0; i < numUsers; i ++) {
				User u = (User) input.readObject();
				accounts.add(u);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Login function to grab specific User Data
	public User login(String username, String pw) {
		for (User user : accounts) {
			byte[] salt = user.getSalt();
			String encodedName = encode(username, salt);
			String encodedPw = encode(pw, salt);
			if (encodedName.equals(user.getUserName()) && encodedPw.equals(user.getPassword()) ) {
				return user;
			}
		}
		return null;
	}

	//ADDING A USER
	public boolean createAccount(String username, String password) {

		// TODO probably split into mult method
		// ToDo: Add isStrongPassword to check if Password is good enough
		if (isStrongPassword(password).equals("")) {
			// Display it to the user
			return false;
		}

		// checking if the user alr exists in the accounts
		for (User user : accounts) {
			String encodedUsername = encode(username, user.getSalt());
			if (encodedUsername.equals(user.getUserName())) {
				// TODO send info to GUI
				//System.out.println("User already exists.");
				return false;
			}
		}

		// generate a random new salt
		byte[] salt = new byte[64];
		new SecureRandom().nextBytes(salt);

		// encode both username and password with salt
		// this is how I saw online for guides to doing databases of users
		String encodedUsername = encode(username, salt);
		String encodedPassword = encode(password, salt);

		User newUser = new User(encodedUsername, encodedPassword);
		newUser.setSalt(salt);

		accounts.add(newUser);
		return true;
	}

	// this method updates the UserDatabase with all of the new information
	public void logout() {
		// writes the UserInfo.txt file
		try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("UserInfo.txt"))){
			// write the number of users in the file for the loadUsers method to use
			output.writeInt(accounts.size());
			// add all of the users to the file
			for (User u: accounts) {
				output.writeObject(u);
			}
		} catch (IOException e) {
			System.out.println("error with updateDatabase");
		}
	}



	public String encode(String input, byte[] salt) {
		try {
			MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
			sha256.update(input.getBytes());
			sha256.update(salt);
			byte[] hash = sha256.digest();

			return Base64.getEncoder().encodeToString(hash);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	// Extra functionality for user password protection.
	public String isStrongPassword(String pw) {
		boolean specialChar = false;
		boolean capitalChar = false;
		boolean lowerChar  = false;
		boolean isEightLetters = pw.length() >= 8;

		String output = "";

		for (int i = 0; i < pw.length(); i++) {
			char cur = pw.charAt(i);
			if (!Character.isLetter(cur) && !Character.isDigit(cur) && !Character.isWhitespace(cur)) {
				specialChar = true;
			}
			if (Character.isUpperCase(cur)) {
				capitalChar = true;
			}
			if (Character.isLowerCase(cur)) {
				lowerChar = true;
			}
		}
		if (!specialChar) {
			output += "Missing special character\n";
		}
		if (!capitalChar) {
			output += "Missing capital character\n";
		}
		if (!lowerChar) {
			output += "Missing lower character\n";
		}
		if (!isEightLetters) {
			output += "Needs to be more than eight letters\n";
		}

		return output;
	}
}






