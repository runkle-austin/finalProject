// UserTest.java
package tests;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
import model.User;

public class UserTest {
	
	@Test
	public void testUserCreation() {
		User u = new User("Danny Devito", "AlwaysSunny");
		assertEquals(u.getPassword(), "AlwaysSunny");
		assertEquals(u.getUserName(), "Danny Devito");
	}

}
