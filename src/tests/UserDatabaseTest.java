// UserDatabaseTest.java
package tests;


import model.User;
import org.junit.jupiter.api.Test;
import model.UserDatabase;
import org.junit.jupiter.api.TestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserDatabaseTest {

	@Test
	public void testPassword() {
		UserDatabase db = new UserDatabase();
		assertEquals(db.isStrongPassword("A!b2%s;lkjsdhdssjsdh"), "");
		assertEquals(db.isStrongPassword("A!b"), "Needs to be more than eight letters\n");
		assertEquals(db.isStrongPassword("Abcdjdjdjdj"), "Missing special character\n");
		assertEquals(db.isStrongPassword("!!!!"), "Missing capital character\nMissing lower character\nNeeds to be more than eight letters\n");
		assertEquals(db.isStrongPassword(""), "Missing special character\nMissing capital character\nMissing lower character\nNeeds to be more than eight letters\n");
	}

	@Test
	public void testLoadFromJaon(){
		UserDatabase db = new UserDatabase();
		db.loadFromJSONFile();
		db.saveToJSONFile();
	}

	@Test
	public void testCreateUser() {
        UserDatabase db = new UserDatabase();
		db.addUser("a", "b");
		db.saveToJSONFile();
		// bad password
		assertEquals(db.addUser("a", "a"), false);
		// good password
		assertEquals(db.addUser("asdfghjk", "qwertyuiop1234567890-"), true);
	}

	@Test
	public void testLogin() {
		UserDatabase db = new UserDatabase();
		db.addUser("me", "qwertyuiop1234567890-");
		db.saveToJSONFile();
		assertNotNull(db.login("me", "qwertyuiop1234567890-"));

		User me = db.login("me", "qwertyuiop1234567890-");
		assertNotNull(me.getUserName());
	}
	
}
