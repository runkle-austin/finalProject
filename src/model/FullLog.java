package model;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

public class FullLog implements Serializable {
    private ArrayList<WorkoutCycle> myWorkoutCycles;
    private WorkoutCycle activeCycle;
    private ArrayList<Exercise> myExercises;    // this is only exercises that aren't in the catalog
    private ArrayList<Workout> myWorkouts;
    private Map<LocalDate, Double> myWeightLog = new TreeMap<>();

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

    public void setMyWorkouts(ArrayList<Workout> workouts){
        myWorkouts = workouts;
    }

    // Change: 4/23 Carlos: boolean to void return type (fix for GUI)
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

    // TODO escaping ref
    public ArrayList<WorkoutCycle> getMyWorkoutCycles() {

        return myWorkoutCycles;
    }

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

    public boolean removeWorkoutCycle(WorkoutCycle workoutCycle) {
        if (myWorkoutCycles.contains(workoutCycle)) {
            myWorkoutCycles.remove(workoutCycle);
            return true;
        }
        return false;
    }

    public void removeWorkout(Workout workout) {
        for(WorkoutCycle cycle: myWorkoutCycles){
            cycle.removeWorkoutFromOneWeek(workout);
            myWorkouts.remove(workout);
        }
    }

    public void setActiveCycle(WorkoutCycle wc) {
        this.activeCycle = wc;
    }

    public WorkoutCycle getActiveCycle() {
        return activeCycle;
    }

    public Map<LocalDate, Double> getMyWeightLog() {
        return myWeightLog;
    }

    private void createDefaultWorkouts() {
        ExerciseCatalog.loadExercises();
        Workout push = new Workout("push");
        push.addLift("barbell bench press", 0, 0, 0);
        push.addLift("incline barbell press", 0, 0, 0);
        push.addLift("pec deck", 0, 0, 0);
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
        chest.addLift("pec deck", 0, 0, 0);
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
