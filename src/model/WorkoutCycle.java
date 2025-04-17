//file: WorkoutCycle.java
//desc: this class generates a muilti-week workout cycle based on one week of workouts

package model;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public final class WorkoutCycle {
	private String name;
	private int numberWeeks;
	private HashMap<DayOfWeek, Workout> oneWeek = new HashMap<DayOfWeek, Workout>();
<<<<<<< HEAD
	private ArrayList<HashMap<DayOfWeek, Workout>> fullCycle;
	
=======
>>>>>>> bc12d243156b0c0e0045e06f86fbce2e64fd688b
	
	public WorkoutCycle(String name, int numberWeeks) {
		this.name = name;
		this.numberWeeks = numberWeeks;
	}
	
	// METHOD
	public String getName() {
		return this.name;
	}
	
	// adds a single day of workouts to our week
	public void addWorkout(String d, Workout w) {
		DayOfWeek day = DayOfWeek.valueOf(d.toUpperCase());
		// remove any existing days
		oneWeek.remove(day);
		// add the work out to the full cycle
		oneWeek.put(day, w);
	}
	
	// remove a single day of workouts and return true if completed
	public boolean removeWorkoutByDay(String d) {
		DayOfWeek day = DayOfWeek.valueOf(d.toUpperCase());
		if (!oneWeek.containsKey(day)) {
			return false;
		}
		oneWeek.remove(day);
		return true;
	}

	//based on our input week of workouts, generates a full workout cycle of len numberOfWeeks
	public void createFullCycle() {
		ArrayList<HashMap<DayOfWeek, Workout>> fullCycle = new ArrayList<>(numberWeeks);
		// fill the array list with template weeks
		for (int i = 0; i < numberWeeks -1; i ++) {
			HashMap<DayOfWeek, Workout> thisWeek = updateWeek(oneWeek, i);
			fullCycle.add(thisWeek);
		}
		this.fullCycle = fullCycle;
		// calls deloadWeek function to add last week to cycle 
		deloadWeek(oneWeek);
	}
	
<<<<<<< HEAD
	// Implementation of deload week (last week gets 60% of weight and 3 less reps) 
	public void deloadWeek(HashMap<DayOfWeek, Workout> weekOne) {
		HashMap<DayOfWeek, Workout> thisWeek = new HashMap<>();
		for (DayOfWeek day: weekOne.keySet()) {
			Workout thisDay = weekOne.get(day).copy();
			for (LiftData l: thisDay.lifts()) {
				l.setWeightInLbs(Math.floor(l.getWeightInLbs() * .6));
				if (l.getReps() > 3) {
					l.setReps(l.getReps() - 3);
				}
				l.setReps(3);
			}
			thisWeek.put(day, thisDay);
		}
		fullCycle.add(thisWeek);
	}
	
	// TODO does this work if days are empty? mostly concerned w/ thisDay.getLifts()
	
	// helper function used in createFullCycle
	private HashMap<DayOfWeek, Workout> updateWeek(HashMap<DayOfWeek, Workout> inputWeek, int i) {
		HashMap<DayOfWeek, Workout> thisWeek = new HashMap<>();
		// loop through days in the week, and lifts in each days workout
		for (DayOfWeek day: inputWeek.keySet()) {
			// bc this is a copy, we don't worry about escaping references and get lifts directly
			Workout thisDay = inputWeek.get(day).copy();
			// gets set of lifts in a days workout
			for (LiftData l: thisDay.lifts()) {
				switch(l.getIntensity()) {
					case HIGH:
						l.setReps(l.getReps() + (1 * i));
						break;
					case MEDIUM:
						l.setReps(l.getReps() + (2 * i));
						break;
					case LOW:
						l.setReps(l.getReps() + (3 * i));
						break;
					
=======
	public ArrayList<HashMap<DayOfWeek, Workout>> createFullCycle() {
		ArrayList<HashMap<DayOfWeek, Workout>> fullCycle = new ArrayList<>(numberWeeks);
		// fill the array list with template weeks
		for (int i = 0; i < numberWeeks; i ++) {
			HashMap<DayOfWeek, Workout> thisWeek = updateWeek(oneWeek, i);
			fullCycle.add(thisWeek);
		}

		return fullCycle;
	}
	
	// return the updated week based on week number
	private HashMap<DayOfWeek, Workout> updateWeek(HashMap<DayOfWeek, Workout> inputWeek, int i) {
		HashMap<DayOfWeek, Workout> thisWeek = new HashMap<>();
		for (DayOfWeek day: inputWeek.keySet()) {
			Workout thisDay = inputWeek.get(day).copy();
			// gets set of lifts in a days workout
			for (LiftData l: thisDay.toArrayList()) {
				Intensity intes = l.getExercise().getIntensity();
				if (Intensity.HIGH.equals(intes)) {
					l.setReps(l.getReps() + 1);
				} else if (Intensity.MEDIUM.equals(intes)) {
					l.setReps(l.getReps() + 2);
				} else if (Intensity.LOW.equals(intes)) {
					l.setReps(l.getReps() + 2);
>>>>>>> bc12d243156b0c0e0045e06f86fbce2e64fd688b
				}
			}
			thisWeek.put(day, thisDay);
		}
		return thisWeek;
	}

<<<<<<< HEAD

	public String getFullCycle() {
		String str = "Full Cycle\n";
		int index = 1;
		for (HashMap<DayOfWeek, Workout> week: fullCycle) {
			str += "Week: " + index + "\n" + week.toString() + "\n=================\n";
			index ++;
		}
		return str;
	}


	//TODO do we want to replace the toString with getFullCycle()?
=======
>>>>>>> bc12d243156b0c0e0045e06f86fbce2e64fd688b
	@Override
	public String toString() {
		if (oneWeek.isEmpty()) {
			return "Workout cycle is empty \n";
		}
		String str = "Workout " + this.getName() + "\n";
		for (DayOfWeek day: DayOfWeek.values()) {
			if (oneWeek.get(day) == null) {
<<<<<<< HEAD
				str += "  ==== " + day + " ==== \nREST DAY\n";
			} else {
				str += " ==== " + day + " ---> " + oneWeek.get(day).toString();
=======
				str = str + "  ==== " + day + " ==== \nREST DAY\n";
			} else {
				str = str + " ==== " + day + " ---> " + oneWeek.get(day).toString();
>>>>>>> bc12d243156b0c0e0045e06f86fbce2e64fd688b
			}
		}
		return str;
	}
	
}
