package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import model.ExerciseCatalog;
import model.Workout;
import model.WorkoutCycle;

public class WorkoutCycleTest {
	
//	@BeforeEach
//	void setUp() {
//		ExerciseCatalog.loadExercises();
//	}
//	@AfterEach
//	void tearDown() {
//		ExerciseCatalog.empty();
//	}
	
	
	@Test
	public void testAddWorkout() {
		ExerciseCatalog.loadExercises();
		WorkoutCycle cw = new WorkoutCycle("Test1", 5);
		Workout w = new Workout("Test");
		assertEquals(cw.toString(), "Workout cycle is empty \n");
		w.addLift("Pec Deck", 1, 2, 3);
		w.addLift("Cable Rear Delt Fly", 10, 11, 12);
		cw.addWorkout("MONDAY", w);
		String str = "Workout Test1\n"
				+ " ==== MONDAY ---> Workout Test\n"
				+ "Pec Deck, reps = 1, weight = 2.0, sets = 3\n"
				+ "Cable Rear Delt Fly, reps = 10, weight = 11.0, sets = 12\n"
				+ "  ==== TUESDAY ==== \n"
				+ "REST DAY\n"
				+ "  ==== WEDNESDAY ==== \n"
				+ "REST DAY\n"
				+ "  ==== THURSDAY ==== \n"
				+ "REST DAY\n"
				+ "  ==== FRIDAY ==== \n"
				+ "REST DAY\n"
				+ "  ==== SATURDAY ==== \n"
				+ "REST DAY\n"
				+ "  ==== SUNDAY ==== \n"
				+ "REST DAY\n";
		assertEquals(cw.toString(), str);
	}
	
	@Test
	public void testCreateFullCycle() {
		ExerciseCatalog.loadExercises();
		WorkoutCycle cw = new WorkoutCycle("Test1", 5);
		Workout w = new Workout("Test");
		assertEquals(cw.toString(), "Workout cycle is empty \n");
		// intensity is high, should be incrementing by 1
		w.addLift("Pec Deck", 1, 2, 3);
		// intensity is low, should increment by 3
		w.addLift("Cable Rear Delt Fly", 10, 11, 12);
		cw.addWorkout("MONDAY", w);
		String str = "Full Cycle\n"
				+ "Week: 1\n"
				+ "{MONDAY=Workout Test\n"
				+ "Pec Deck, reps = 1, weight = 2.0, sets = 3\n"
				+ "Cable Rear Delt Fly, reps = 10, weight = 11.0, sets = 12\n"
				+ "}\n"
				+ "=================\n"
				+ "Week: 2\n"
				+ "{MONDAY=Workout Test\n"
				+ "Pec Deck, reps = 2, weight = 2.0, sets = 3\n"
				+ "Cable Rear Delt Fly, reps = 13, weight = 11.0, sets = 12\n"
				+ "}\n"
				+ "=================\n"
				+ "Week: 3\n"
				+ "{MONDAY=Workout Test\n"
				+ "Pec Deck, reps = 3, weight = 2.0, sets = 3\n"
				+ "Cable Rear Delt Fly, reps = 16, weight = 11.0, sets = 12\n"
				+ "}\n"
				+ "=================\n"
				+ "Week: 4\n"
				+ "{MONDAY=Workout Test\n"
				+ "Pec Deck, reps = 4, weight = 2.0, sets = 3\n"
				+ "Cable Rear Delt Fly, reps = 19, weight = 11.0, sets = 12\n"
				+ "}\n"
				+ "=================\n"
				+ "Week: 5\n"
				+ "{MONDAY=Workout Test\n"
				+ "Pec Deck, reps = 3, weight = 1.0, sets = 3\n"
				+ "Cable Rear Delt Fly, reps = 3, weight = 6.0, sets = 12\n"
				+ "}\n"
				+ "=================\n";
		cw.createFullCycle();
		assertEquals(cw.getFullCycle(), str);
	}
	
	
}
