// UserDatabaseTest.java
package tests;


import model.ExerciseCatalog;
import model.User;
import model.Workout;
import org.junit.jupiter.api.Test;
import model.UserDatabase;
import org.junit.jupiter.api.TestTemplate;

import static org.junit.Assert.*;

public class UserDatabaseTest {

	@Test
	public void testStrippedUserName(){
		UserDatabase db = new UserDatabase();
		db.loadUsers();
		// note the leading and trailing white space (also used good password)
		db.createAccount(" Testing ", "Qq1234567890-");

		User user = db.login("Testing", "Qq1234567890-");
		// if login didn't work, user will be null
		assertNotNull(user);
	}


	@Test
	public void testPassword() {
		ExerciseCatalog.loadExercises();
		UserDatabase db = new UserDatabase();
		assertEquals(db.isStrongPassword("A!b2%s;lkjsdhdssjsdh"), "");
		assertEquals(db.isStrongPassword("A!b"), "Needs to be more than eight letters\n");
		assertEquals(db.isStrongPassword("Abcdjdjdjdj"), "Missing special character\n");
		assertEquals(db.isStrongPassword("!!!!"), "Missing capital character\nMissing lower character\nNeeds to be more than eight letters\n");
		assertEquals(db.isStrongPassword(""), "Missing special character\nMissing capital character\nMissing lower character\nNeeds to be more than eight letters\n");
	}


	@Test
	public void testCreateUser() {
		ExerciseCatalog.loadExercises();
        UserDatabase db = new UserDatabase();
		db.createAccount("a", "b");
		// bad password
		assertEquals(db.createAccount("a", "a"), false);
		// good password
		assertEquals(db.createAccount("asdfghjk", "qwertyuiop1234567890-"), true);
	}

	@Test
	public void testBadLogin() {
		ExerciseCatalog.loadExercises();
		UserDatabase db = new UserDatabase();
		db.createAccount("me", "qwertyuiop1234567890-");
		// test wrong password
		assertNull(db.login("me", "notCorrectPassword"));
	}

	@Test
	public void testLogin() {
		ExerciseCatalog.loadExercises();
		UserDatabase db = new UserDatabase();
		db.createAccount("me", "qwertyuiop1234567890-");
		assertNotNull(db.login("me", "qwertyuiop1234567890-"));

		User me = db.login("me", "qwertyuiop1234567890-");
		assertNotNull(me.getUserName());
	}

	@Test
	public void testSerializableUser() {
		ExerciseCatalog.loadExercises();
		UserDatabase db = new UserDatabase();
		db.loadUsers();
		db.createAccount("me", "qwertyuiop1234567890-");
		db.logout();
		db.loadUsers();
		assertEquals(db.createAccount("me", "qwertyuiop1234567890-"), false);
	}

	@Test
	public void testLoadingDataProperly() {
		ExerciseCatalog.loadExercises();
		Workout chest =  new Workout("chest");
		chest.addLift("pec deck", 0, 0, 0);
		chest.addLift("barbell bench press", 0, 0, 0);

		UserDatabase db = new UserDatabase();
		db.loadUsers();
		db.createAccount("me", "qwertyuiop1234567890-");
		User u = db.login("me", "qwertyuiop1234567890-");
		u.getMyFullLog().addWorkout(chest);
		db.logout();
		db.loadUsers();
		User u2 = db.login("me", "qwertyuiop1234567890-");
		assertEquals(u,u2);

	}
}
