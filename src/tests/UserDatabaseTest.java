// UserDatabaseTest.java
package tests;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
import model.UserDatabase;

public class UserDatabaseTest {
	
	private UserDatabase base;

	@Test
	public void testPassword() {
		base = new UserDatabase();
		assertEquals(base.isStrongPassword("A!b2%s;lkjsdhdssjsdh"), "");
		assertEquals(base.isStrongPassword("A!b"), "Needs to be more than 8 letters\n");
		assertEquals(base.isStrongPassword("Abcdjdjdjdj"), "Missing special character\n");
		assertEquals(base.isStrongPassword("!!!!"), "Missing capital character\nMissing lower character\nNeeds to be more than 8 letters\n");
		assertEquals(base.isStrongPassword(""), "Missing special character\nMissing capital character\nMissing lower character\nNeeds to be more than 8 letters\n");
	}
	
}
