package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import model.ExerciseCatalog;
import model.Workout;
import model.WorkoutCycle;

public class WorkoutCycleTest {
	
	// works when I run all tests, doesnt work when I test just this class
	// switches order??, when running all tests it prints pec first, when just this class it prints cable first
	
	
	// TODO theres some witch craft going on. If I have setUp and tearDown AND loadEx inside the test, everytthing goes smooth
	// if any one of those is missing, it no longer works
	
	
	
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
		assertEquals(cw.removeWorkoutByDay("Tuesday"), false);
		assertEquals(cw.removeWorkoutByDay("Monday"), true);
		assertEquals(cw.toString(), "Workout cycle is empty \n");
	}
	
	
}
