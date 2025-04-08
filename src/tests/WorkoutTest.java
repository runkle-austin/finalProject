package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import model.ExerciseCatalog;
import model.Workout;

public class WorkoutTest {

//	@BeforeEach
//	void setUp() {
//		ExerciseCatalog.loadExercises();
//	}
	// TODO tear down
//	@AfterEach
//	void tearDown() {
//		ExerciseCatalog.empty();
//	}
	
	@Test
	public void createWorkout() {
		ExerciseCatalog.loadExercises();
		Workout w = new Workout("Test");
		w.addLift("Pec Deck", 1, 2, 3);
		w.addLift("Cable Rear Delt Fly", 10, 11, 12);

		assertEquals(w.toString(), "Workout Test\nCable Rear Delt Fly\nPec Deck\n");
		
		w.removeLift("Pec Deck");
		
		assertEquals(w.removeLift("Doing the Safetey Dance"), false);
		
		assertEquals(w.getName(), "Test");
		
		assertEquals(w.toString(), "Workout Test\nCable Rear Delt Fly\n");
	}
	
	@Test
	public void copyWorkout() {
		ExerciseCatalog.loadExercises();
		Workout w = new Workout("Test");
		w.addLift("Pec Deck", 1, 2, 3);
		w.addLift("Cable Rear Delt Fly", 10, 11, 12);
		
		Workout c = w.copy();
		assertEquals(w.toString(),c.toString());
	}
}
