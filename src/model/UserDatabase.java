// UserDatavase.java
package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// Code retrieved from LA2 UserDatabase
public class UserDatabase {
	// hashmap with user name as the key and User as the value
	private ArrayList<User> accounts = new ArrayList<>();
	
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
			
			// add the user to the ArrayList of users
			accounts.add(curUser);			
		}
		
		scanner.close();
		
	}
	
	// add user to the database, returns true if successfully creates, false otherwise
	public boolean addUser(String username, String password) {
		for (User user : accounts) {
			byte[] salt = user.getSalt();
			String encodedName = encode(username, salt);
			if (encodedName == user.getUserName()) {
				return false;
			} 
		}
		//generate a salt for the password
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[64];
		// salt = 64 random bytes
		random.nextBytes(salt);
		
		// encrypt the password
		String encodedPassword = encode(password, salt);
		String encodedUsername = encode(username, salt);
		
		User newUser = new User(encodedUsername, encodedPassword);
		// add the salt to the user
		newUser.setSalt(salt);
		// add the user to the ArrayList of users
		accounts.add(newUser);
		
		// write the new user to User.txt
		try(FileWriter fw = new FileWriter("Users.txt", true)){
			fw.write(encodedUsername + "," + encodedPassword + "," + byteToHex(salt)+"\n");
		} catch (IOException e) {
			System.out.println("Error writing to file");
		}
		
		return true;
	}
	
	// determine if the entered user name and password match the expected information
	public User login(String username, String pass) {
		for (User user : accounts) {
			byte[] salt = user.getSalt();
			String encodedName = encode(username, salt);
			if (encodedName == user.getUserName()) {
				return user;
			}
		}
		return null;
	}
	
	// encodes the given string
	public String encode(String input, byte[] salt) {
		//
		try {
			// sha256 encodes using SHA-256
			MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
			String encodedStr = "";
			//add the salt and password to sha256
			sha256.update(input.getBytes());
			sha256.update(salt);
			//hash the password
			byte[] hash = sha256.digest();
			//convert the hash into a string
			for (byte b : hash) {
				encodedStr += String.format("%02x", b);
			}
			return encodedStr;

		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error with encode");
			return null;
		}
	}
	
	// ensures that the entered password is strong
	// returns a string with all of the passwords issues
	// returns null if the password is strong
	public String isStrongPassword(String pw) {
		boolean specialChar = false;
		boolean captialChar = false;
		boolean lowerChar   = false;
		boolean is8Letters  = pw.length() >= 8;
		
		String output = "";
		
		for (int i = 0; i < pw.length(); i ++) {
			char cur = pw.charAt(i);
			// check for special characters 
			if (!Character.isLetter(cur) && !Character.isDigit(cur) && !Character.isWhitespace(cur)) {
				specialChar = true;
			}
			// check for capital letters
			if (Character.isUpperCase(cur)) {
				captialChar = true;
			}
			// check for lower case letters
			if (Character.isLowerCase(cur)) {
				lowerChar = true;
			}
		}
		
		if (!specialChar) {
			output += "Missing special character\n";
		} 
		if (!captialChar) {
			output += "Missing capital character\n";
		}
		if (!lowerChar) {
			output += "Missing lower character\n";
		}
		if (!is8Letters) {
			output += "Needs to be more than 8 letters\n";
		}
		
		
		//returns true only if all conditions are met
		return output;
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
