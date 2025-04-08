package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import model.Exercise;
import model.ExerciseCatalog;
import model.Intensity;
import model.LiftData;
import model.MuscleGroup;
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

		//assertEquals(w.toString(), "Workout Test\nCable Rear Delt Fly\nPec Deck\n");
		
		w.removeLift("Pec Deck");
		
		assertEquals(w.removeLift("Doing the Safetey Dance"), false);
		
		assertEquals(w.getName(), "Test");
		
		//assertEquals(w.toString(), "Workout Test\nCable Rear Delt Fly\n");
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
	
	@Test
	public void toArrayList() {
		ExerciseCatalog.loadExercises();
		Workout w = new Workout("Test");
		w.addLift("Pec Deck", 1, 2, 3);
		w.addLift("Cable Rear Delt Fly", 10, 11, 12);
		
		Exercise e1 = new Exercise("Pec Deck", MuscleGroup.CHEST, Intensity.HIGH);
		Exercise e2 = new Exercise("Cable Rear Delt Fly", MuscleGroup.SHOULDERS, Intensity.HIGH);
		LiftData test1 = new LiftData(e1, 1, 2, 3);
		LiftData test2 = new LiftData(e2, 10, 11, 12);
		
		ArrayList<LiftData> test= new ArrayList<>();
		ArrayList<LiftData> test3 = new ArrayList<>();
		test3.add(test1);
		test3.add(test2);
		test = w.toArrayList();
		assertEquals(test.toString(), test3.toString());
	}
}
