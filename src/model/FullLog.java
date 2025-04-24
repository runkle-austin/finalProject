//FullLog.java
package model;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

// This class stores all of the users information about their workouts
public class FullLog implements Serializable {
	// INSTANCE VARIABLES
    private ArrayList<WorkoutCycle> myWorkoutCycles;
    private WorkoutCycle activeCycle;
    private ArrayList<Exercise> myExercises;    // this is only exercises that aren't in the catalog
    private ArrayList<Workout> myWorkouts;
    private Map<LocalDate, Double> myWeightLog = new TreeMap<>();

    // Constructor
    public FullLog() {
        this.myWorkoutCycles = new ArrayList<>();
        this.myExercises = new ArrayList<>();
        this.myWorkouts = new ArrayList<>();
        this.myWeightLog = new TreeMap<>();
        createDefaultWorkouts();
        // Also loaded catalog so user can pick from exercises
        // It was too complicated to add an exercise that we didn't have
        // Sorry Ryan
        ExerciseCatalog.loadExercises();
    }

    
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

    // set the list of myWorkouts
    public void setMyWorkouts(ArrayList<Workout> workouts){
        myWorkouts = workouts;
    }

    // add a workout to the workout cycle
    public void addWorkout(Workout workout){
        if (!myWorkouts.contains(workout)) {
            myWorkouts.add(workout);
        }
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

    // add a workout cycle to the list of workout cycles
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

    // remove a work out cycle from the list of work out cycles
    public boolean removeWorkoutCycle(WorkoutCycle workoutCycle) {
        if (myWorkoutCycles.contains(workoutCycle)) {
            myWorkoutCycles.remove(workoutCycle);
            return true;
        }
        return false;
    }

    // remove a work out from the my work out list
    public boolean removeWorkout(Workout workout) {
        boolean removed = false;
        for(WorkoutCycle cycle: myWorkoutCycles){
            removed = cycle.removeWorkoutFromOneWeek(workout);
            myWorkouts.remove(workout);
        }
        return removed;
    }

    // set the current work out cycle to display on the calendar
    public void setActiveCycle(WorkoutCycle wc) {
        this.activeCycle = wc;
    }

    // get the current work out cycle
    public WorkoutCycle getActiveCycle() {
        return this.activeCycle;
    }

    // return the weight log
    public Map<LocalDate, Double> getMyWeightLog() {
        return myWeightLog;
    }

    // creates all of the default work outs for the users
    private void createDefaultWorkouts() {
        ExerciseCatalog.loadExercises();
        
        // default push work out
        Workout push = new Workout("push");
        push.addLift("barbell bench press", 0, 0, 0);
        push.addLift("incline barbell press", 0, 0, 0);
        push.addLift("pec deck", 0, 0, 0);
        push.addLift("dumbbell side raises", 0, 0, 0);
        push.addLift("cable tricep pushdown", 0, 0, 0);
        push.addLift("skullcrushers", 0, 0, 0);

        // default pull work out
        Workout pull = new Workout("pull");
        pull.addLift("pronated lat pulldown", 0, 0, 0);
        pull.addLift("supinated lat pulldown", 0, 0, 0);
        pull.addLift("machine high row", 0, 0, 0);
        pull.addLift("cable row", 0, 0, 0);
        pull.addLift("cable rear delt fly", 0, 0, 0);
        pull.addLift("cable face pull", 0, 0, 0);
        pull.addLift("preacher curls", 0, 0, 0);

        // default leg work out
        Workout legs = new Workout("legs");
        legs.addLift("squats", 0, 0, 0);
        legs.addLift("deadlift", 0, 0, 0);
        legs.addLift("leg extension", 0, 0, 0);
        legs.addLift("leg press", 0, 0, 0);
        legs.addLift("hamstring curl", 0, 0, 0);
        legs.addLift("calf raise", 0, 0, 0);

        // default chest work out
        Workout chest = new Workout("chest");
        chest.addLift("dumbbell bench press", 0, 0, 0);
        chest.addLift("incline barbell press", 0, 0, 0);
        chest.addLift("pec deck", 0, 0, 0);
        chest.addLift("push ups", 0, 0, 0);
        chest.addLift("crunch", 0, 0, 0);

        // default back work out
        Workout back = new Workout("back");
        back.addLift("pronated lat pulldown", 0, 0, 0);
        back.addLift("supinated lat pulldown", 0, 0, 0);
        back.addLift("machine high row", 0, 0, 0);
        back.addLift("cable row", 0, 0, 0);
        back.addLift("pull ups", 0, 0, 0);
        back.addLift("crunch", 0, 0, 0);

        // default arm work out
        Workout arms = new Workout("arms");
        arms.addLift("dumbbell shoulder press", 0, 0, 0);
        arms.addLift("dumbbell side raises", 0, 0, 0);
        arms.addLift("cable tricep pushdown", 0, 0, 0);
        arms.addLift("skullcrushers", 0, 0, 0);
        arms.addLift("preacher curls", 0, 0, 0);
        arms.addLift("seated curls", 0, 0, 0);

        // default work out cycles
        // default three day cycle
        WorkoutCycle threeDay = new WorkoutCycle("Default: Three Day", 4);
        threeDay.addWorkout("monday", push);
        threeDay.addWorkout("wednesday", pull);
        threeDay.addWorkout("friday", legs);

        // default four day cycle
        WorkoutCycle fourDay = new WorkoutCycle("Default: Four Day", 4);
        fourDay.addWorkout("monday", chest);
        fourDay.addWorkout("tuesday", back);
        fourDay.addWorkout("wednesday", legs);
        fourDay.addWorkout("thursday", arms);

        // default five day cycle
        WorkoutCycle fiveDay = new WorkoutCycle("Default: Five Day", 4);
        fiveDay.addWorkout("monday", chest);
        fiveDay.addWorkout("tuesday", legs);
        fiveDay.addWorkout("wednesday", back);
        fiveDay.addWorkout("thursday", legs);
        fiveDay.addWorkout("friday", arms);

        // add the cycles to the list of cycles
        addWorkoutCycle(threeDay);
        addWorkoutCycle(fourDay);
        addWorkoutCycle(fiveDay);

        for (WorkoutCycle workoutCycle : myWorkoutCycles) {
            for (DayOfWeek day : DayOfWeek.values()) {
                addWorkout(workoutCycle.getWorkoutByDay(day));
            }
        }
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
