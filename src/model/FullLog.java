/* FullLog.java
 *
 * Class that handles all of user's data within the app,
 * tracking workouts, exercises and lift data.
 */
package src.model;

import java.util.ArrayList;

public class FullLog {
    private ArrayList<model.WorkoutCycle> myWorkoutCycles;

    //Constructor for FullLog
    public FullLog() {
        this.myWorkoutCycles = new ArrayList<>();
    }

    // Getter for FullLog
    // ToDo: Fix Encapsulation
    public ArrayList<model.WorkoutCycle> getMyWorkoutCycles() {
        return myWorkoutCycles;
    }

    public void setMyWorkoutCycles(ArrayList<model.WorkoutCycle> myWorkoutCycles) {
        this.myWorkoutCycles = myWorkoutCycles;
    }

    // Add a WorkoutCycle to Log
    public boolean addWorkoutCycle(model.WorkoutCycle workoutCycle) {
        if (myWorkoutCycles.size() < 8) {
            myWorkoutCycles.add(workoutCycle);
            return true;
        }
        return false;
    }

    // Remove a WorkoutCycle to Log
    public boolean removeWorkoutCycle(model.WorkoutCycle workoutCycle) {
        if (myWorkoutCycles.contains(workoutCycle)) {
            myWorkoutCycles.remove(workoutCycle);
            return true;
        }
        return false;
    }
}
