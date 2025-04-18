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
        createDefaultWorkouts();
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

    private void createDefaultWorkouts() {
        Workout push = new Workout("push");
        push.addLift("barbell bench press", 0, 0, 0);
        push.addLift("incline barbell press", 0, 0, 0);
        push.addLift("pec deck ", 0, 0, 0);
        push.addLift("dumbbell side raises", 0, 0, 0);
        push.addLift("cable tricep pushdown", 0, 0, 0);
        push.addLift("skullcrushers", 0, 0, 0);

        Workout pull = new Workout("pull");
        pull.addLift("pronated lat pulldown", 0, 0, 0);
        pull.addLift("supinated lat pulldown", 0, 0, 0);
        pull.addLift("machine high row", 0, 0, 0);
        pull.addLift("cable row", 0, 0, 0);
        pull.addLift("cable rear delt fly", 0, 0, 0);
        pull.addLift("cable face pull", 0, 0, 0);
        pull.addLift("preacher curls", 0, 0, 0);

        Workout legs = new Workout("legs");
        legs.addLift("squats", 0, 0, 0);
        legs.addLift("deadlift", 0, 0, 0);
        legs.addLift("leg extension", 0, 0, 0);
        legs.addLift("leg press", 0, 0, 0);
        legs.addLift("hamstring curl", 0, 0, 0);
        legs.addLift("calf raise", 0, 0, 0);

        Workout chest = new Workout("chest");
        chest.addLift("dumbbell bench press", 0, 0, 0);
        chest.addLift("incline barbell press", 0, 0, 0);
        chest.addLift("pec deck ", 0, 0, 0);
        chest.addLift("push ups", 0, 0, 0);
        chest.addLift("crunch", 0, 0, 0);

        Workout back = new Workout("back");
        back.addLift("pronated lat pulldown", 0, 0, 0);
        back.addLift("supinated lat pulldown", 0, 0, 0);
        back.addLift("machine high row", 0, 0, 0);
        back.addLift("cable row", 0, 0, 0);
        back.addLift("pull ups", 0, 0, 0);
        back.addLift("crunch", 0, 0, 0);

        Workout arms = new Workout("arms");
        arms.addLift("dumbbell shoulder press", 0, 0, 0);
        arms.addLift("dumbbell side raises", 0, 0, 0);
        arms.addLift("cable tricep pushdown", 0, 0, 0);
        arms.addLift("skullcrushers", 0, 0, 0);
        arms.addLift("preacher curls", 0, 0, 0);
        arms.addLift("seated curls", 0, 0, 0);

        WorkoutCycle threeDay = new WorkoutCycle("threeDay", 4);
        threeDay.addWorkout("monday", push);
        threeDay.addWorkout("wednesday", pull);
        threeDay.addWorkout("friday", legs);

        WorkoutCycle fourDay = new WorkoutCycle("fourDay", 4);
        fourDay.addWorkout("monday", chest);
        fourDay.addWorkout("tuesday", back);
        fourDay.addWorkout("wednesday", legs);
        fourDay.addWorkout("thursday", arms);

        WorkoutCycle fiveDay = new WorkoutCycle("fiveDay", 4);
        fiveDay.addWorkout("monday", chest);
        fiveDay.addWorkout("tuesday", legs);
        fiveDay.addWorkout("wednesday", back);
        fiveDay.addWorkout("thursday", legs);
        fiveDay.addWorkout("friday", arms);

        addWorkoutCycle(threeDay);
        addWorkoutCycle(fourDay);
        addWorkoutCycle(fiveDay);
    }
}
