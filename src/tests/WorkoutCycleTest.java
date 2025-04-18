package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import model.ExerciseCatalog;
import model.Workout;
import model.WorkoutCycle;

public class WorkoutCycleTest {
	@Test
	public void testAddWorkout() {
		ExerciseCatalog.loadExercises();
		WorkoutCycle cw = new WorkoutCycle("Test1", 5);
		Workout w = new Workout("Test");

		assertEquals(cw.toString(), "Workout cycle is empty \n");

		w.addLift("Pec Deck", 1, 2, 3);
		w.addLift("Cable Rear Delt Fly", 10, 11, 12);
		cw.addWorkout("MONDAY", w);

		String expected = "Workout Test1\n" +
				" ==== MONDAY ---> Workout Test\n" +
				"Pec Deck, reps = 1, weight = 2.0, sets = 3\n" +
				"Cable Rear Delt Fly, reps = 10, weight = 11.0, sets = 12\n" +
				"  ==== TUESDAY ==== \n" +
				"REST DAY\n" +
				"  ==== WEDNESDAY ==== \n" +
				"REST DAY\n" +
				"  ==== THURSDAY ==== \n" +
				"REST DAY\n" +
				"  ==== FRIDAY ==== \n" +
				"REST DAY\n" +
				"  ==== SATURDAY ==== \n" +
				"REST DAY\n" +
				"  ==== SUNDAY ==== \n" +
				"REST DAY\n";

		assertEquals(expected, cw.toString());

		// Now test removing
		assertFalse(cw.removeWorkoutByDay("TUESDAY"));
		assertEquals(true, cw.removeWorkoutByDay("MONDAY"));
		assertEquals(cw.toString(), "Workout cycle is empty \n");
	}

	@Test
	public void testCreateFullCycle() {
		ExerciseCatalog.loadExercises();
		WorkoutCycle cw = new WorkoutCycle("Test1", 5);
		Workout w = new Workout("Test");

		assertEquals(cw.toString(), "Workout cycle is empty \n");

		// Intensity HIGH: Pec Deck
		// Intensity LOW: Cable Rear Delt Fly, Pronated Lat Pulldown
		w.addLift("Pec Deck", 1, 2, 3);
		w.addLift("Cable Rear Delt Fly", 10, 11, 12);
		w.addLift("Pronated Lat Pulldown", 10, 11, 12);
		cw.addWorkout("MONDAY", w);

		cw.createFullCycle();

		String expected = """
				Full Cycle
				Week: 1
				{MONDAY=Workout Test
				Pec Deck, reps = 1, weight = 2.0, sets = 3
				Pronated Lat Pulldown, reps = 10, weight = 11.0, sets = 12
				Cable Rear Delt Fly, reps = 10, weight = 11.0, sets = 12
				}
				=================
				Week: 2
				{MONDAY=Workout Test
				Pec Deck, reps = 2, weight = 2.0, sets = 3
				Pronated Lat Pulldown, reps = 12, weight = 11.0, sets = 12
				Cable Rear Delt Fly, reps = 13, weight = 11.0, sets = 12
				}
				=================
				Week: 3
				{MONDAY=Workout Test
				Pec Deck, reps = 3, weight = 2.0, sets = 3
				Pronated Lat Pulldown, reps = 14, weight = 11.0, sets = 12
				Cable Rear Delt Fly, reps = 16, weight = 11.0, sets = 12
				}
				=================
				Week: 4
				{MONDAY=Workout Test
				Pec Deck, reps = 4, weight = 2.0, sets = 3
				Pronated Lat Pulldown, reps = 16, weight = 11.0, sets = 12
				Cable Rear Delt Fly, reps = 19, weight = 11.0, sets = 12
				}
				=================
				Week: 5
				{MONDAY=Workout Test
				Pec Deck, reps = 3, weight = 1.0, sets = 3
				Pronated Lat Pulldown, reps = 7, weight = 6.0, sets = 12
				Cable Rear Delt Fly, reps = 7, weight = 6.0, sets = 12
				}
				=================
				""";

		assertEquals(expected, cw.getFullCycle());
	}

	@Test
	public void testRemoveWorkoutByDay() {
		ExerciseCatalog.loadExercises();
		WorkoutCycle cw = new WorkoutCycle("Test1", 5);
		Workout w = new Workout("Test");

		assertEquals(cw.toString(), "Workout cycle is empty \n");

		w.addLift("Pec Deck", 1, 2, 3);
		w.addLift("Cable Rear Delt Fly", 10, 11, 12);
		cw.addWorkout("MONDAY", w);

		Workout w2 = new Workout("Test2");
		w2.addLift("Cable Rear Delt Fly", 15, 100, 2);
		cw.addWorkout("TUESDAY", w2);

		String ex1 = "Workout Test1\n" +
				" ==== MONDAY ---> Workout Test\n" +
				"Pec Deck, reps = 1, weight = 2.0, sets = 3\n" +
				"Cable Rear Delt Fly, reps = 10, weight = 11.0, sets = 12\n" +
				" ==== TUESDAY ---> Workout Test2\n" +
				"Cable Rear Delt Fly, reps = 15, weight = 100.0, sets = 2\n" +
				"  ==== WEDNESDAY ==== \n" +
				"REST DAY\n" +
				"  ==== THURSDAY ==== \n" +
				"REST DAY\n" +
				"  ==== FRIDAY ==== \n" +
				"REST DAY\n" +
				"  ==== SATURDAY ==== \n" +
				"REST DAY\n" +
				"  ==== SUNDAY ==== \n" +
				"REST DAY\n";

		assertEquals(cw.toString(), ex1);

		cw.removeWorkoutByDay("MONDAY");

		String ex2 = "Workout Test1\n" +
				"  ==== MONDAY ==== \n" +
				"REST DAY\n" +
				" ==== TUESDAY ---> Workout Test2\n" +
				"Cable Rear Delt Fly, reps = 15, weight = 100.0, sets = 2\n" +
				"  ==== WEDNESDAY ==== \n" +
				"REST DAY\n" +
				"  ==== THURSDAY ==== \n" +
				"REST DAY\n" +
				"  ==== FRIDAY ==== \n" +
				"REST DAY\n" +
				"  ==== SATURDAY ==== \n" +
				"REST DAY\n" +
				"  ==== SUNDAY ==== \n" +
				"REST DAY\n";

		assertEquals(cw.toString(), ex2);
	}

	@Test
	public void testRemoveWorkoutDayEmpty() {
		ExerciseCatalog.loadExercises();
		WorkoutCycle cw = new WorkoutCycle("Test1", 5);
		Workout w = new Workout("Test");

		w.addLift("Pec Deck", 1, 2, 3);
		cw.addWorkout("MONDAY", w);

		assertFalse(cw.removeWorkoutByDay("TUESDAY"));
	}
}
