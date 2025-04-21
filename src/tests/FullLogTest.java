// ExerciseTest.java
package tests;

import static org.junit.Assert.*;

import model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

public class FullLogTest {

    @Test
    public void testEmptyConstructor() {
        FullLog fullLog = new FullLog();
        assertNotNull(fullLog);
        // 3 defualt workout cycles
        assertEquals(fullLog.getMyWorkoutCycles().size(), 3);
    }

    @Test
    public void testEqualsAndHash(){
        FullLog fullLog1 = new FullLog();
        FullLog fullLog2 = new FullLog();
        String workout = "workout";
        WorkoutCycle WC1 = new WorkoutCycle("WC", 5);
        fullLog1.addWorkoutCycle(WC1);
        WorkoutCycle WC2 = new WorkoutCycle("WC", 5);
        fullLog2.addWorkoutCycle(WC2);

        assertEquals(fullLog1, fullLog2);
        assertEquals(fullLog1.hashCode(), fullLog2.hashCode());
        assertNotEquals(fullLog1, null);
        assertNotEquals(fullLog1, workout);
    }

    @Test
    public void testEqualsAndHash2(){
        FullLog fullLog1 = new FullLog();
        FullLog fullLog2 = new FullLog();
        WorkoutCycle WC1 = new WorkoutCycle("WC", 5);
        WorkoutCycle WC2 = new WorkoutCycle("WC2", 5);
        fullLog1.addWorkoutCycle(WC1);
        fullLog1.addWorkoutCycle(WC2);
        fullLog2.addWorkoutCycle(WC1);

        assertNotEquals(fullLog1, fullLog2);
        assertNotEquals(fullLog1.hashCode(), fullLog2.hashCode());
    }

    @Test
    public void testEqualsAndHash3(){
        FullLog fullLog1 = new FullLog();
        FullLog fullLog2 = new FullLog();
        ExerciseCatalog.loadExercises();
        fullLog1.addExercise("push ups", "core", "high");

        assertNotEquals(fullLog1, fullLog2);
    }
    @Test
    public void testConstructor() {
        ExerciseCatalog.loadExercises();
        WorkoutCycle wC = new WorkoutCycle("Test1", 5);

        ArrayList<WorkoutCycle> listCycles = new ArrayList<>();
        ArrayList<Exercise> exercises = new ArrayList<>();
        Exercise e = new Exercise("Lat pulls", MuscleGroup.BACK, Intensity.HIGH);
        exercises.add(e);
        listCycles.add(wC);
        ArrayList<Workout> workouts = new ArrayList<>();

        FullLog fullLog = new FullLog(listCycles, exercises,workouts);
        assertEquals(fullLog.getMyExercises(), exercises);
        // now should only have the one workoutcycle put in constructor
        assertEquals(fullLog.getMyWorkoutCycles().size(), 1);
        // should be null bc we didn't add any
        assertTrue(fullLog.getMyWorkouts().isEmpty());
    }


    @Test
    public void testWorkoutsInConstructor() {
        ExerciseCatalog.loadExercises();
        WorkoutCycle wC = new WorkoutCycle("Test1", 5);

        ArrayList<WorkoutCycle> listCycles = new ArrayList<>();
        ArrayList<Exercise> exercises = new ArrayList<>();
        Exercise e = new Exercise("Lat pulls", MuscleGroup.BACK, Intensity.HIGH);
        exercises.add(e);
        listCycles.add(wC);
        ArrayList<Workout> workouts = new ArrayList<>();
        workouts.add(new Workout("workout"));

        FullLog fullLog = new FullLog(listCycles, exercises,workouts);

        assertEquals(fullLog.getMyWorkouts(),workouts);
    }

    @Test
    public void testSetAndAddWorkouts() {
        ExerciseCatalog.loadExercises();

        ArrayList<Workout> workouts = new ArrayList<>();
        workouts.add(new Workout("workout"));

        // set workouts
        FullLog fullLog1 = new FullLog();
        fullLog1.setMyWorkouts(workouts);
        assertEquals(fullLog1.getMyWorkouts(),workouts);

        // add workouts
        FullLog fullLog2 = new FullLog();
        fullLog2.addWorkout(new Workout("workout"));
        assertEquals(fullLog2.getMyWorkouts(),workouts);

        // test adding more then 8
        fullLog2.addWorkout(new Workout("workout"));
        fullLog2.addWorkout(new Workout("workout"));
        fullLog2.addWorkout(new Workout("workout"));
        fullLog2.addWorkout(new Workout("workout"));
        fullLog2.addWorkout(new Workout("workout"));
        fullLog2.addWorkout(new Workout("workout"));
        fullLog2.addWorkout(new Workout("workout"));
        assertFalse(fullLog2.addWorkout(new Workout("workout")));
    }

    @Test
    public void testAddExercise() {
        FullLog fullLog1 = new FullLog();
        Exercise superNew = new Exercise("super push ups",  MuscleGroup.CORE, Intensity.HIGH);
        fullLog1.addExercise("super push ups", "Core", "high");
        assertEquals(true, fullLog1.getMyExercises().contains(superNew));
    }

    @Test
    public void testWorkoutCycleLimit() {
        FullLog fullLog1 = new FullLog();
        WorkoutCycle WC1 = new WorkoutCycle("WC1", 5);
        WorkoutCycle WC2 = new WorkoutCycle("WC2", 5);
        WorkoutCycle WC3 = new WorkoutCycle("WC3", 5);
        WorkoutCycle WC4 = new WorkoutCycle("WC4", 5);
        WorkoutCycle WC5 = new WorkoutCycle("WC5", 5);
        WorkoutCycle WC6 = new WorkoutCycle("WC6", 5);
        WorkoutCycle WC7 = new WorkoutCycle("WC7", 5);
        WorkoutCycle WC8 = new WorkoutCycle("WC8", 5);
        WorkoutCycle WC9 = new WorkoutCycle("WC9", 5);

        fullLog1.addWorkoutCycle(WC1);
        fullLog1.addWorkoutCycle(WC2);
        fullLog1.addWorkoutCycle(WC3);
        fullLog1.addWorkoutCycle(WC4);
        fullLog1.addWorkoutCycle(WC5);
        fullLog1.addWorkoutCycle(WC6);
        fullLog1.addWorkoutCycle(WC7);
        fullLog1.addWorkoutCycle(WC8);
        assertEquals(false, fullLog1.addWorkoutCycle(WC9));
    }

    @Test
    public void testRemoveWorkoutCycle() {
        FullLog fullLog1 = new FullLog();
        WorkoutCycle WC1 = new WorkoutCycle("WC1", 5);
        WorkoutCycle WC2 = new WorkoutCycle("WC2", 5);
        WorkoutCycle WC3 = new WorkoutCycle("WC3", 5);
        WorkoutCycle WC4 = new WorkoutCycle("WC4", 5);
        WorkoutCycle WC5 = new WorkoutCycle("WC5", 5);
        fullLog1.addWorkoutCycle(WC1);
        fullLog1.addWorkoutCycle(WC2);

        assertEquals(true, fullLog1.removeWorkoutCycle(WC1));
        assertEquals(false, fullLog1.removeWorkoutCycle(WC5));
    }
}
