package model;// UserDatabase.java

import org.json.JSONArray;
import org.json.JSONObject;

import javax.imageio.IIOException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;

// Code retrieved from LA2 UserDatabase
public class UserDatabase {
	private ArrayList<User> accounts = new ArrayList<>();
	private final File jsonFile = new File("users.json");

	public void saveToJSONFile() {
		JSONArray userArray = new JSONArray();
		for (User user : accounts) {
			JSONObject userInfo = new JSONObject();
			userInfo.put("userName", user.getUserName());
			userInfo.put("password", user.getPassword());
			//JSON does not hold bytes[] well so we will store in base64
			// (since it is easy to use and built in)
			userInfo.put("salt", Base64.getEncoder().encodeToString(user.getSalt()));
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
			System.out.println("File does not exist");
			return;
		}
		try{
			String JSONString = new String(Files.readAllBytes(jsonFile.toPath()));

			JSONArray jsonArray = new JSONArray(JSONString);
			//removing previous accounts so no duplicates
			accounts.clear();

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject user = jsonArray.getJSONObject(i);

				String userName = user.getString("userName");
				String password = user.getString("password");
				String salt = user.getString("salt");

				byte[] saltBytes = Base64.getDecoder().decode(salt);
				User u = new User(userName, password);
				u.setSalt(saltBytes);
				accounts.add(u);
			}

			//DEBUG
			System.out.println("Loaded " + accounts.size() + " users");
		}
		catch (IOException e){
			System.out.println("Error reading user data file:");
			e.printStackTrace();
		}
		catch (Exception e){
			System.out.println("Error parsing user data:");
			e.printStackTrace();
		}
	}

	//ADDING A USER
	public boolean addUser(String username, String password) {
		// checking if the user alr exists in the accounts
		for (User user : accounts) {
			String encodedUsername = encode(username, user.getSalt());
			if (encodedUsername.equals(user.getUserName())) {
				System.out.println("User already exists.");
				return false;
			}
		}

		// generate a random new salt
		byte[] salt = new byte[64];
		new SecureRandom().nextBytes(salt);

		// encode both username and password with salt
		// this is how i saw online for guides to doing databases of users
		String encodedUsername = encode(username, salt);
		String encodedPassword = encode(password, salt);

		User newUser = new User(encodedUsername, encodedPassword);
		newUser.setSalt(salt);

		accounts.add(newUser);

		System.out.println("User created successfully.");
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


}






