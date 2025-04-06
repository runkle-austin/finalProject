// UserDatavase.java
package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Scanner;

// Code retrieved from LA2 UserDatabase
public class UserDatabase {
	// hashmap with user name as the key and User as the value
	private HashMap<String, User> accounts = new HashMap<String, User>();
	
	// load the current users in the file accounts.txt
	public void loadDatabase() throws FileNotFoundException {
		
		// this file will be formated "userName,password,salt"
		File fr = new File("accounts.txt");
		Scanner scanner = new Scanner(fr);
		
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] accountInfo = line.split(",");
			// create a user an add it to the user map
			User curUser = new User(accountInfo[0], accountInfo[1]);

			// TODO generate salt
			
			// add the user to the map of users
			accounts.put(accountInfo[0], curUser);			
		}
		
		scanner.close();
		
	}
	
	// add user to the database, returns true if successfully creates, false otherwise
	public boolean addUser(String username, String password) {
		if (accounts.containsKey(username)) {
			return false;
		}

		//generate a salt for the password
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[64];
		// salt = 64 random bytes
		random.nextBytes(salt);
		
		// encrypt the password
		String encodedPassword = encode(password, salt);
		
		//
		User newUser = new User(username, encodedPassword);
		// add the salt to the user
		newUser.setSalt(salt);
		// add the user to the hashMap of users
		accounts.put(username,newUser);
		
		// write the new user to User.txt
		try(FileWriter fw = new FileWriter("Users.txt", true)){
			fw.write(username + "," + encodedPassword + "," + byteToHex(salt)+"\n");
		} catch (IOException e) {
			System.out.println("Error writing to file");
		}
		
		return true;
	}
	
	// determine if the entered user name and password match the expected information
	public User login(String username, String pass) {
		
		if (accounts.containsKey(username)) {
			// get the users encoded password and salt
			String password = accounts.get(username).getPassword();
			byte[] salt = accounts.get(username).getSalt();
			//encode the entered password
			String encodedPW = encode(pass, salt);
			// check if the passwords are the same
			if (password.equals(encodedPW)) {
				// if they are the same then return the 
				return accounts.get(username);
			}
		}
		return null;
	}
	
	// encodes the given string
	public String encode(String password, byte[] salt) {
		//
		try {
			// sha256 encodes using SHA-256
			MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
			String encodedPassword = "";
			//add the salt and password to sha256
			sha256.update(password.getBytes());
			sha256.update(salt);
			//hash the password
			byte[] hash = sha256.digest();
			//convert the hash into a string
			for (byte b : hash) {
				encodedPassword += String.format("%02x", b);
			}
			return encodedPassword;

		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error with encode");
			return null;
		}
	}
	
	// converts the hexadecimal values into bytes so that the salt can be used
	public byte[] hexToByte(String hex) {
		// convert the hex string into a byte array
		byte[] bytes = new byte[hex.length()/2];
		for (int i = 0; i < bytes.length; i ++) {
			bytes[i] = (byte)(Integer.parseInt(hex.substring(i * 2, i * 2 + 2), 16));
		}
		
		return bytes;
	}
	
	// converts the byte array to a string so it can be saved in the Users.txt file
	public String byteToHex(byte[] bytes) {
		String hex = "";
		for (int i = 0; i < bytes.length; i ++) {
			// convert each byte to hex
			hex = hex + String.format("%02x", bytes[i]);
		}
		return hex;
	}
	
}
