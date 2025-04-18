// UserDatabaseTest.java
package tests;


import org.junit.jupiter.api.Test;
import model.UserDatabase;

public class UserDatabaseTest {

//	@Test
//	public void testPassword() {
//		db = new UserDatabase();
//		assertEquals(db.isStrongPassword("A!b2%s;lkjsdhdssjsdh"), "");
//		assertEquals(db.isStrongPassword("A!b"), "Needs to be more than 8 letters\n");
//		assertEquals(db.isStrongPassword("Abcdjdjdjdj"), "Missing special character\n");
//		assertEquals(db.isStrongPassword("!!!!"), "Missing capital character\nMissing lower character\nNeeds to be more than 8 letters\n");
//		assertEquals(db.isStrongPassword(""), "Missing special character\nMissing capital character\nMissing lower character\nNeeds to be more than 8 letters\n");
//	}

	@Test
	public void testCreateUser() {
        UserDatabase db = new UserDatabase();
		db.addUser("a", "b");
		db.saveToJSONFile();
	}

	
}
