package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.ExerciseCatalog;
import model.Workout;

public class WorkoutTest {

	@Test
	public void createWorkout() {
		ExerciseCatalog.loadExercises();
		Workout w = new Workout("Test");
		w.addLift("Pec Deck", 1, 2, 3);
		w.addLift("Cable Rear Delt Fly", 10, 11, 12);

		assertEquals(w.toString(), "Workout Test\nPec Deck\nCable Rear Delt Fly\n");
		
		w.removeLift("Pec Deck");
		
		assertEquals(w.removeLift("Doing the Saftey Dance"), false);
		
		assertEquals(w.getName(), "Test");
		
		assertEquals(w.toString(), "Workout Test\nCable Rear Delt Fly\n");
	}
}
