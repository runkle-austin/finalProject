/* FullLog.java
 *
 * Class that handles all of user's data within the app,
 * tracking workouts, exercises and lift data.
 */
package model;

import java.util.ArrayList;

public class FullLog {
    private ArrayList<WorkoutCycle> myWorkoutCycles;

    //Constructor for FullLog
    public FullLog() {
        this.myWorkoutCycles = new ArrayList<>();
    }

    // Getter for FullLog
    // TODO: Fix Encapsulation
    public ArrayList<WorkoutCycle> getMyWorkoutCycles() {
        return myWorkoutCycles;
    }

    public void setMyWorkoutCycles(ArrayList<WorkoutCycle> myWorkoutCycles) {
        this.myWorkoutCycles = myWorkoutCycles;
    }

    // Add a WorkoutCycle to Log
    public boolean addWorkoutCycle(WorkoutCycle workoutCycle) {
        if (myWorkoutCycles.size() < 8) {
            myWorkoutCycles.add(workoutCycle);
            return true;
        }
        return false;
    }

    // Remove a WorkoutCycle to Log
    public boolean removeWorkoutCycle(WorkoutCycle workoutCycle) {
        if (myWorkoutCycles.contains(workoutCycle)) {
            myWorkoutCycles.remove(workoutCycle);
            return true;
        }
        return false;
    }
}
