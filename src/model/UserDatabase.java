package model;// UserDatabase.java

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;

// Code adapted from LA2 UserDatabase
public class UserDatabase {
	private ArrayList<User> accounts = new ArrayList<>();
	private final File jsonFile = new File("users.json");

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

	public void saveToJSONFile() {
		JSONArray userArray = new JSONArray();
		for (User user : accounts) {
			JSONObject userInfo = new JSONObject();
			userInfo.put("userName", user.getUserName());
			userInfo.put("password", user.getPassword());
			//JSON does not hold bytes[] well so we will store in base64
			// (since it is easy to use and built in)
			userInfo.put("salt", Base64.getEncoder().encodeToString(user.getSalt()));
			// puts user's fullLog into json
			userInfo.put("fullLog", user.getMyFullLog());
			userArray.put(userInfo);
		}
		try(FileWriter fw = new FileWriter(jsonFile)) {
			fw.write(userArray.toString(4));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadFromJSONFile() {
		if(!jsonFile.exists()) {
			return;
		}
		try{
			//removing previous accounts so no duplicates
			accounts.clear();

			String JSONString = new String(Files.readAllBytes(jsonFile.toPath()));

			JSONArray jsonArray = new JSONArray(JSONString);

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonUser = jsonArray.getJSONObject(i);

				String userName = jsonUser.getString("userName");
				String password = jsonUser.getString("password");
				String salt = jsonUser.getString("salt");
				//JSONObject jsonFullLog = jsonUser.getJSONObject("fullLog");
				//FullLog fullLog = new FullLog(jsonFullLog);

				byte[] saltBytes = Base64.getDecoder().decode(salt);
				User user = new User(userName, password);
				user.setSalt(saltBytes);
				accounts.add(user);
			}
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}


	//ADDING A USER
	public boolean addUser(String username, String password) {
		// checking if the user alr exists in the accounts

		// TODO probably split into mult method
		// ToDo: Add isStrongPassword to check if Password is good enough
		if (isStrongPassword(password).equals("")) {
			// Display it to the user
			return false;
		}

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






