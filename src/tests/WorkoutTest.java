package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import java.util.ArrayList;

import org.junit.Test;

import model.Exercise;
import model.ExerciseCatalog;
import model.Intensity;
import model.LiftData;
import model.MuscleGroup;
import model.Workout;

public class WorkoutTest {
<<<<<<< HEAD
	
//	TODO ask TA why this doesn't work
=======

>>>>>>> bc12d243156b0c0e0045e06f86fbce2e64fd688b
//	@BeforeEach
//	void setUp() {
//		ExerciseCatalog.loadExercises();
//	}
<<<<<<< HEAD
//	
=======
	// TODO tear down
>>>>>>> bc12d243156b0c0e0045e06f86fbce2e64fd688b
//	@AfterEach
//	void tearDown() {
//		ExerciseCatalog.empty();
//	}
<<<<<<< HEAD
//	
=======
	
>>>>>>> bc12d243156b0c0e0045e06f86fbce2e64fd688b
	@Test
	public void createWorkoutTest() {
		ExerciseCatalog.loadExercises();
		Workout w = new Workout("Test");
		
		assertEquals(w.getName(), "Test");
		
		w.addLift("Pec Deck", 1, 2, 3);
		w.addLift("Cable Rear Delt Fly", 10, 11, 12);
		
		assertEquals(w.toString(), "Workout Test\n"
				+ "Pec Deck, reps = 1, weight = 2.0, sets = 3\n"
				+ "Cable Rear Delt Fly, reps = 10, weight = 11.0, sets = 12\n");
	}
	
	@Test
	public void addByIntensityTest() {
		ExerciseCatalog.loadExercises();
		Workout w = new Workout("Test");
		
//		Desired order
//		Pec Deck, High 
//		Dumbbell Shoulder Press, Med 
//		Push Ups, Low 
		w.addLift("Dumbbell Shoulder Press", 0, 0, 0);
		w.addLift("Push Ups", 0, 0, 0);
		w.addLift("Pec Deck", 0, 0, 0);
		
		assertEquals(w.toString(), "Workout Test\n"
				+ "Pec Deck, reps = 0, weight = 0.0, sets = 0\n"
				+ "Dumbbell Shoulder Press, reps = 0, weight = 0.0, sets = 0\n"
				+ "Push Ups, reps = 0, weight = 0.0, sets = 0\n");
	}
	
	@Test
	public void addDuplicate() {
		ExerciseCatalog.loadExercises();
		Workout w = new Workout("Test");
		
//		Desired order
//		Pec Deck, High 
//		Dumbbell Shoulder Press, Med 
//		Push Ups, Low 
		w.addLift("Dumbbell Shoulder Press", 0, 0, 0);
		w.addLift("Push Ups", 0, 0, 0);
		w.addLift("Pec Deck", 0, 0, 0);
		// put all lifts in twice, test if order or number gets messed up
		w.addLift("Dumbbell Shoulder Press", 0, 0, 0);
		w.addLift("Push Ups", 0, 0, 0);
		w.addLift("Pec Deck", 0, 0, 0);
		
		assertEquals(w.toString(), "Workout Test\n"
				+ "Pec Deck, reps = 0, weight = 0.0, sets = 0\n"
				+ "Dumbbell Shoulder Press, reps = 0, weight = 0.0, sets = 0\n"
				+ "Push Ups, reps = 0, weight = 0.0, sets = 0\n");
	}
	
	
	@Test
	public void removeTest() {
		ExerciseCatalog.loadExercises();
		Workout w = new Workout("Test");
		w.addLift("Dumbbell Shoulder Press", 0, 0, 0);
		w.addLift("Push Ups", 0, 0, 0);
		w.addLift("Pec Deck", 0, 0, 0);
		// original
		assertEquals(w.toString(), "Workout Test\n"
				+ "Pec Deck, reps = 0, weight = 0.0, sets = 0\n"
				+ "Dumbbell Shoulder Press, reps = 0, weight = 0.0, sets = 0\n"
				+ "Push Ups, reps = 0, weight = 0.0, sets = 0\n");
		// boolean remove test
		assertFalse(w.removeLift("Nonsense Lift"));
		assertTrue(w.removeLift("Pec Deck"));
		// test toString w/ updated workout
		assertEquals(w.toString(), "Workout Test\n"
				+ "Dumbbell Shoulder Press, reps = 0, weight = 0.0, sets = 0\n"
				+ "Push Ups, reps = 0, weight = 0.0, sets = 0\n");
	}
	
	@Test
	public void copyWorkoutTest() {
		ExerciseCatalog.loadExercises();
		Workout w = new Workout("Test");
		w.addLift("Pec Deck", 1, 2, 3);
		w.addLift("Cable Rear Delt Fly", 10, 11, 12);
<<<<<<< HEAD
=======

		//assertEquals(w.toString(), "Workout Test\nCable Rear Delt Fly\nPec Deck\n");
>>>>>>> bc12d243156b0c0e0045e06f86fbce2e64fd688b
		
		Workout c = w.copy();
		// tests both the toString and equals of copied workout
		assertEquals(w.toString(),c.toString());
		assertEquals(w,c);
		assertEquals(w.getLifts(),c.getLifts());
	}
	
	
	@Test
	public void getLiftsTest() {
		ExerciseCatalog.loadExercises();
		Workout w = new Workout("Test");
		w.addLift("Pec Deck", 1, 2, 3);
		w.addLift("Cable Rear Delt Fly", 10, 11, 12);
		// put same exercises in a list and test toString
		Exercise e1 = new Exercise("Pec Deck", MuscleGroup.CHEST, Intensity.HIGH);
		Exercise e2 = new Exercise("Cable Rear Delt Fly", MuscleGroup.SHOULDERS, Intensity.HIGH);
		LiftData lift1 = new LiftData(e1, 1, 2, 3);
		LiftData lift2 = new LiftData(e2, 10, 11, 12);
		ArrayList<LiftData> liftList2 = new ArrayList<>();
		liftList2.add(lift1);
		liftList2.add(lift2);
		
<<<<<<< HEAD
		ArrayList<LiftData> liftList1 = w.getLifts();
		assertEquals(liftList2.toString(), liftList1.toString());
=======
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
>>>>>>> bc12d243156b0c0e0045e06f86fbce2e64fd688b
	}
}
