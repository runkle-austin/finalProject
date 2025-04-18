package controller;

import model.FullLog;
import model.WorkoutCycle;

public class WorkoutController {
    private final FullLog model;

    public WorkoutController(FullLog model) {
        this.model = model;
    }

    // Set the active workout cycle (e.g. user selects Push/Pull/Legs)
    public void setActiveCycle(WorkoutCycle cycle) {
        model.setActiveCycle(cycle);  // Notifies observers
    }

    // Add a new workout cycle to the log
    public boolean addWorkoutCycle(WorkoutCycle cycle) {
        return model.addWorkoutCycle(cycle);  // Notifies observers if successful
    }

    // Remove a workout cycle
    public boolean removeWorkoutCycle(WorkoutCycle cycle) {
        return model.removeWorkoutCycle(cycle);  // Notifies observers if successful
    }
}
