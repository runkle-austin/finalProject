package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.Exercise;
import model.ExerciseCatalog;
import model.Intensity;
import model.LiftData;
import model.MuscleGroup;
import model.Workout;

public class WorkoutTest {

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
	public void testAddList(){
		ExerciseCatalog.loadExercises();
		Workout w = new Workout("Test");
		assertTrue(w.addLift("Pec Deck", 1, 2, 3));
		assertFalse(w.addLift("Nonsense Exercise", 10, 11, 12));
	}

	@Test
	public void testEquals(){
		ExerciseCatalog.loadExercises();
		Workout w1 = new Workout("Test");
		Workout w2 = new Workout("Test");
		w1.addLift("Pec Deck", 1, 2, 3);
		w2.addLift("Pec Deck", 10, 11, 12);
		Workout w3 = new Workout("NOT TEST");
		Object obj = new Object();

		// same name
		assertEquals(w1,w1);

		assertEquals(w1, w2);
		assertEquals(w1.hashCode(), w2.hashCode());
		assertNotEquals(w1, w3);
		assertNotEquals(w1.hashCode(), w3.hashCode());
		assertNotEquals(w1, obj);
		assertNotEquals(w1, null);


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

		w.addLift("Dumbbell Shoulder Press", 0, 0, 0);
		w.addLift("Push Ups", 0, 0, 0);
		w.addLift("Pec Deck", 0, 0, 0);
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

		assertEquals(w.toString(), "Workout Test\n"
				+ "Pec Deck, reps = 0, weight = 0.0, sets = 0\n"
				+ "Dumbbell Shoulder Press, reps = 0, weight = 0.0, sets = 0\n"
				+ "Push Ups, reps = 0, weight = 0.0, sets = 0\n");

		assertFalse(w.removeLift("Nonsense Lift"));
		assertTrue(w.removeLift("Pec Deck"));

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
		assertEquals(w.toString(), "Workout Test\nPec Deck, reps = 1, weight = 2.0, sets = 3\nCable Rear Delt Fly, reps = 10, weight = 11.0, sets = 12\n");

		Workout c = w.copy();
		assertEquals(w.toString(), c.toString());
		assertEquals(w, c);
		assertEquals(w.getLifts(), c.getLifts());
	}

	@Test
	public void getLiftsTest() {
		ExerciseCatalog.loadExercises();
		Workout w = new Workout("Test");
		w.addLift("Pec Deck", 1, 2, 3);
		w.addLift("Cable Rear Delt Fly", 10, 11, 12);

		Exercise e1 = new Exercise("Pec Deck", MuscleGroup.CHEST, Intensity.HIGH);
		Exercise e2 = new Exercise("Cable Rear Delt Fly", MuscleGroup.SHOULDERS, Intensity.HIGH);
		LiftData lift1 = new LiftData(e1, 1, 2, 3);
		LiftData lift2 = new LiftData(e2, 10, 11, 12);
		ArrayList<LiftData> liftList2 = new ArrayList<>();
		liftList2.add(lift1);
		liftList2.add(lift2);

		ArrayList<LiftData> liftList1 = w.getLifts();
		assertEquals(liftList2.toString(), liftList1.toString());

		assertEquals(w.removeLift("Doing the Safetey Dance"), false);
		assertEquals(w.getName(), "Test");
	}

	@Test
	public void copyWorkout() {
		ExerciseCatalog.loadExercises();
		Workout w = new Workout("Test");
		w.addLift("Pec Deck", 1, 2, 3);
		w.addLift("Cable Rear Delt Fly", 10, 11, 12);

		Workout c = w.copy();
		assertEquals(w.toString(), c.toString());
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

		ArrayList<LiftData> test3 = new ArrayList<>();
		test3.add(test1);
		test3.add(test2);

		ArrayList<LiftData> test = w.toArrayList();
		assertEquals(test.toString(), test3.toString());
	}

	@Test
	public void setLifts(){
		ExerciseCatalog.loadExercises();
		Workout w = new Workout("Test");

		Exercise e1 = new Exercise("Pec Deck", MuscleGroup.CHEST, Intensity.HIGH);
		Exercise e2 = new Exercise("Cable Rear Delt Fly", MuscleGroup.SHOULDERS, Intensity.HIGH);
		LiftData test1 = new LiftData(e1, 1, 2, 3);
		LiftData test2 = new LiftData(e2, 10, 11, 12);

		ArrayList<LiftData> arr = new ArrayList<>();
		arr.add(test1);
		arr.add(test2);

		w.setLifts(arr);

		String str = "Workout Test\n"
				+ "Pec Deck, reps = 1, weight = 2.0, sets = 3\n"
				+ "Cable Rear Delt Fly, reps = 10, weight = 11.0, sets = 12\n";
		assertEquals(w.getLifts(), arr);
		assertEquals(str, w.toString());
	}

	@Test
	public void setName(){
		ExerciseCatalog.loadExercises();
		Workout w = new Workout("Test");

		w.setName("New test");

		String str = "Workout New test\n";
		assertEquals(str, w.toString());
	}

}
