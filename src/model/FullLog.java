package model;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

// this class manages the users workout information
public class FullLog implements Serializable {
    //INSTANCE VARIABLES
    private ArrayList<WorkoutCycle> myWorkoutCycles;
    private WorkoutCycle activeCycle;
    private ArrayList<Exercise> myExercises;    // this is only exercises that aren't in the catalog
    private ArrayList<Workout> myWorkouts;
    private Map<LocalDate, Double> myWeightLog = new TreeMap<>();

    // CONSTRUCTOR
    public FullLog() {
        this.myWorkoutCycles = new ArrayList<>();
        this.myExercises = new ArrayList<>();
        this.myWorkouts = new ArrayList<>();
        this.myWeightLog = new TreeMap<>();
        createDefaultWorkouts();
        ExerciseCatalog.loadExercises();
    }

    // create a new full log
    public FullLog(ArrayList<WorkoutCycle> myWorkoutCycles, ArrayList<Exercise> myExercises, ArrayList<Workout> myWorkouts) {
        this.myWorkoutCycles = myWorkoutCycles;
        this.myExercises = myExercises;
        this.myWorkouts = myWorkouts;
    }

    // returns deep copy
    public ArrayList<Workout> getMyWorkouts(){
        ArrayList<Workout> workouts = new ArrayList<>();
        for(Workout curr: myWorkouts){
            if(curr == null){
                continue;
            }
            workouts.add(curr.getCopy());
        }
        return workouts;
    }

    // check if the cycle name is already being used
    public boolean containCycleName(String name){
        for (WorkoutCycle cycle : myWorkoutCycles) {
            if (cycle.getName().equals(name)) {
                return true;
            }
        } return false;
    }

    // returns true if there are duplicates, false otherwise
    public boolean checkDupWorkoutName(String name){
        for (Workout curr : myWorkouts) {
             if (curr != null && curr.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // Change: 4/23 Carlos: boolean to void return type (fix for GUI)
    public boolean addWorkout(Workout workout){
        if (!myWorkouts.contains(workout)) {
            myWorkouts.add(workout);
            return true;
        }
        return false;
    }

    public ArrayList<Exercise> getMyExercises() {
        return (ArrayList<Exercise>) myExercises.clone();
    }


    // creates a new exercise
    // @pre - only use if exercise was not found in exerciseCatalog
    public void addExercise(String name, String muscle, String inten) {
        MuscleGroup muscleGroup = MuscleGroup.valueOf(muscle.toUpperCase());
        Intensity intensity = Intensity.valueOf(inten.toUpperCase());
        Exercise newExercise = new Exercise(name, muscleGroup, intensity);
        myExercises.add(newExercise);
    }

    public ArrayList<WorkoutCycle> getMyWorkoutCycles() {

        return myWorkoutCycles;
    }

    // adds a workout toi the array workouts and return true if it is successfully added
    public boolean addWorkoutCycle(WorkoutCycle workoutCycle) {
        if (myWorkoutCycles.size() < 8) {
            myWorkoutCycles.add(workoutCycle);
            for (DayOfWeek day : DayOfWeek.values()) {
                addWorkout(workoutCycle.getWorkoutByDay(day));
            }
            return true;
        }
        return false;
    }

    // removes a workout cycle from the list and returns true if it was in the list
    public boolean removeWorkoutCycle(WorkoutCycle workoutCycle) {
        if (myWorkoutCycles.contains(workoutCycle)) {
            myWorkoutCycles.remove(workoutCycle);
            return true;
        }
        return false;
    }

    // removes the workout from the workout cycle
    public void removeWorkout(Workout workout) {
        for(WorkoutCycle cycle: myWorkoutCycles){
            cycle.removeWorkoutFromOneWeek(workout);
            myWorkouts.remove(workout);
        }
    }

    // set the active cycle for the calendar
    public void setActiveCycle(WorkoutCycle wc) {
        this.activeCycle = wc;
    }

    public WorkoutCycle getActiveCycle() {
        return activeCycle;
    }

    public Map<LocalDate, Double> getMyWeightLog() {
        return myWeightLog;
    }

    // creates the users default workouts
    private void createDefaultWorkouts() {
        ExerciseCatalog.loadExercises();
        Workout push = new Workout("push");
        push.addLift("barbell bench press", 8, 135, 3);
        push.addLift("incline barbell press", 6, 135, 3);
        push.addLift("pec deck", 12, 100, 3);
        push.addLift("dumbbell side raises", 12, 25, 3);
        push.addLift("cable tricep pushdown", 12, 40, 3);
        push.addLift("skullcrushers", 10, 25, 3);

        Workout pull = new Workout("pull");
        pull.addLift("pronated lat pulldown", 10, 120, 3);
        pull.addLift("supinated lat pulldown", 10, 100, 3);
        pull.addLift("machine high row", 8, 225, 3);
        pull.addLift("cable row", 10, 25, 3);
        pull.addLift("cable rear delt fly", 12, 40, 3);
        pull.addLift("cable face pull", 15, 80, 3);
        pull.addLift("preacher curls", 12, 25, 3);

        Workout legs = new Workout("legs");
        legs.addLift("squats", 6, 225, 3);
        legs.addLift("deadlift", 4, 315, 3);
        legs.addLift("leg extension", 8, 100, 3);
        legs.addLift("leg press", 10, 225, 3);
        legs.addLift("hamstring curl", 10, 45, 3);
        legs.addLift("calf raise", 25, 135, 3);

        Workout chest = new Workout("chest");
        chest.addLift("dumbbell bench press", 10, 60, 3);
        chest.addLift("incline barbell press", 6, 135, 3);
        chest.addLift("pec deck", 8, 150, 3);
        chest.addLift("push ups", 15, 150, 3);
        chest.addLift("crunch", 15, 150, 3);

        Workout back = new Workout("back");
        back.addLift("pronated lat pulldown", 10, 130, 3);
        back.addLift("supinated lat pulldown", 10, 130, 3);
        back.addLift("machine high row", 10, 225, 3);
        back.addLift("cable row", 10, 140, 3);
        back.addLift("pull ups", 15, 150, 3);
        back.addLift("crunch", 15, 150, 3);

        Workout arms = new Workout("arms");
        arms.addLift("dumbbell shoulder press", 12, 60, 3);
        arms.addLift("dumbbell side raises", 10, 25, 3);
        arms.addLift("cable tricep pushdown", 15, 40, 3);
        arms.addLift("skullcrushers", 10, 50, 3);
        arms.addLift("preacher curls", 10, 30, 3);
        arms.addLift("seated curls", 10, 30, 3);

        WorkoutCycle threeDay = new WorkoutCycle("Default: Three Day", 4);
        threeDay.addWorkout("monday", push);
        threeDay.addWorkout("wednesday", pull);
        threeDay.addWorkout("friday", legs);

        WorkoutCycle fourDay = new WorkoutCycle("Default: Four Day", 4);
        fourDay.addWorkout("monday", chest);
        fourDay.addWorkout("tuesday", back);
        fourDay.addWorkout("wednesday", legs);
        fourDay.addWorkout("thursday", arms);

        WorkoutCycle fiveDay = new WorkoutCycle("Default: Five Day", 4);
        fiveDay.addWorkout("monday", chest);
        fiveDay.addWorkout("tuesday", legs);
        fiveDay.addWorkout("wednesday", back);
        fiveDay.addWorkout("thursday", legs);
        fiveDay.addWorkout("friday", arms);

        addWorkoutCycle(threeDay);
        addWorkoutCycle(fourDay);
        addWorkoutCycle(fiveDay);

        for (WorkoutCycle workoutCycle : myWorkoutCycles) {
            for (DayOfWeek day : DayOfWeek.values()) {
                addWorkout(workoutCycle.getWorkoutByDay(day));
            }
        }
        setActiveCycle(threeDay);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FullLog fullLog = (FullLog) o;
        return Objects.equals(myWorkoutCycles, fullLog.myWorkoutCycles) && Objects.equals(myExercises, fullLog.myExercises) && Objects.equals(myWorkouts, fullLog.myWorkouts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(myWorkoutCycles, myExercises, myWorkouts);
    }
}
