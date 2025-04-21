// ExerciseTest.java
package tests;

import static org.junit.Assert.*;

import model.*;
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
        WorkoutCycle WC1 = new WorkoutCycle("WC", 5);
        fullLog1.addWorkoutCycle(WC1);
        WorkoutCycle WC2 = new WorkoutCycle("WC", 5);
        fullLog2.addWorkoutCycle(WC2);

        assertEquals(fullLog1, fullLog2);
        assertEquals(fullLog1.hashCode(), fullLog2.hashCode());

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



}
