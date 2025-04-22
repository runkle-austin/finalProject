// UserTest.java
package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import model.FullLog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import model.User;

public class UserTest {
	@Test
	public void testUserCreation() {
		User u = new User("Danny Devito", "AlwaysSunny");
		assertEquals(u.getPassword(), "AlwaysSunny");
		assertEquals(u.getUserName(), "Danny Devito");
	}

	@Test
	public void testUserCreation2() {
		User u = new User("Danny Devito", "AlwaysSunny");
		assertNotEquals(u, null);
		assertNotEquals(u, new Object());
	}

	@Test
	public void testUser() {
		User u = new User("Danny Devito", "AlwaysSunny");
		FullLog uFullLog = new FullLog();
		uFullLog.addExercise("dumbbell side raise", "shoulders", "medium");

		User b = new User("Danny Devito", "AlwaysSunnyInPhilly");
		FullLog bFullLog = new FullLog();
		bFullLog.addExercise("pec deck", "chest", "high");

		assertNotEquals(u, b);
	}

	@Test
	public void testUser2() {
		User u = new User("Danny Devito", "AlwaysSunny");
		FullLog uFullLog = new FullLog();
		User b = new User("Danny Devito", "AlwaysSunny");
		FullLog bFullLog = new FullLog();
		assertEquals(u, b);
	}
}
