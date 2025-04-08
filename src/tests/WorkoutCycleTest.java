package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.ExerciseCatalog;
import model.Workout;
import model.WorkoutCycle;

public class WorkoutCycleTest {
	
	@Test
	public void testAddWorkout() {
		WorkoutCycle cw = new WorkoutCycle("Test1", 5);
		ExerciseCatalog.loadExercises();
		Workout w = new Workout("Test");
		w.addLift("Pec Deck", 1, 2, 3);
		w.addLift("Cable Rear Delt Fly", 10, 11, 12);
		cw.addWorkout("MONDAY", w);
		String output = cw.toString();
		// TODO
		assertEquals(cw.toString(), output);
		assertEquals(cw.removeWorkoutByDay("Tuesday"), false);
		assertEquals(cw.removeWorkoutByDay("Monday"), true);
		
		assertEquals(cw.toString(), "Workout cycle is empty \n");
		
	}
}
