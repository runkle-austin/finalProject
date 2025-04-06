// View.java
package view;

import java.util.*;
import model.User;
import model.UserDatabase;

public class View {
	private Scanner scanner;
	private User user;
	private UserDatabase userDB;
	
	public View () {
        scanner = new Scanner(System.in);
        userDB = new UserDatabase();
	}

	public void run() {
        while (true) {
            System.out.println("---- Workout Schedule ----");
            System.out.println("1. Login");
            System.out.println("2. Create Account");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();
            System.out.println();
            
            switch (choice) {
                case "1":
                    // TODO login
                    break;
                case "2":
                    // TODO create account
                    break;
                case "3":
                    System.out.println("Goodbye!🎵");
                    return;
                default:
                    System.out.println("Invalid input. Please enter a number between 1 and 3.");
            }
        }
	}
	
	
    public static void main(String[] args) {
        new View().run();
    }
}
