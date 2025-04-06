package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

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
}
