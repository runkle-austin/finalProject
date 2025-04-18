package model;

import observer.WorkoutObservable;
import observer.WorkoutObserver;

import java.util.ArrayList;
import java.util.List;

public class FullLog implements WorkoutObservable {
    private ArrayList<WorkoutCycle> myWorkoutCycles;
    private WorkoutCycle activeCycle;
    private final List<WorkoutObserver> observers;

    public FullLog() {
        this.myWorkoutCycles = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public ArrayList<WorkoutCycle> getMyWorkoutCycles() {
        return myWorkoutCycles;
    }

    public void setMyWorkoutCycles(ArrayList<WorkoutCycle> myWorkoutCycles) {
        this.myWorkoutCycles = myWorkoutCycles;
        notifyObservers();
    }

    public boolean addWorkoutCycle(WorkoutCycle workoutCycle) {
        if (myWorkoutCycles.size() < 8) {
            myWorkoutCycles.add(workoutCycle);
            notifyObservers();
            return true;
        }
        return false;
    }

    public boolean removeWorkoutCycle(WorkoutCycle workoutCycle) {
        if (myWorkoutCycles.contains(workoutCycle)) {
            myWorkoutCycles.remove(workoutCycle);
            notifyObservers();
            return true;
        }
        return false;
    }

    public void setActiveCycle(WorkoutCycle wc) {
        this.activeCycle = wc;
        notifyObservers();
    }

    public WorkoutCycle getActiveCycle() {
        return this.activeCycle;
    }

    @Override
    public void addObserver(WorkoutObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(WorkoutObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (WorkoutObserver o : observers) {
            o.modelChanged();
        }
    }
}
