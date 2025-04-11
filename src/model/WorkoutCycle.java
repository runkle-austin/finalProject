package model;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public final class WorkoutCycle {
	private String name;
	private int numberWeeks;
	private HashMap<DayOfWeek, Workout> oneWeek = new HashMap<DayOfWeek, Workout>();
	
	public WorkoutCycle(String name, int numberWeeks) {
		this.name = name;
		this.numberWeeks = numberWeeks;
	}
	
	// METHOD
	public String getName() {
		return this.name;
	}
	
	// add a Workout to the workout cycle
	public void addWorkout(String d, Workout w) {
		DayOfWeek day = DayOfWeek.valueOf(d.toUpperCase());
		// remove any existing days
		oneWeek.remove(day);
		// add the work out to the full cycle
		oneWeek.put(day, w);
	}
	
	// remove a work out and return true if completed
	public boolean removeWorkoutByDay(String d) {
		DayOfWeek day = DayOfWeek.valueOf(d.toUpperCase());
		if (!oneWeek.containsKey(day)) {
			return false;
		}
		oneWeek.remove(day);
		return true;
	}
	
	public ArrayList<HashMap<DayOfWeek, Workout>> createFullCycle() {
		ArrayList<HashMap<DayOfWeek, Workout>> fullCycle = new ArrayList<>(numberWeeks);
		// fill the array list with template weeks
		for (int i = 0; i < numberWeeks; i ++) {
			HashMap<DayOfWeek, Workout> thisWeek = updateWeek(oneWeek, i);
			fullCycle.add(thisWeek);
		}

		return fullCycle;
	}
	
	// TODO does this work if days are empty? mostly concerned w/ thisDay.getLifts()
	
	// return the updated week based on week number
	private HashMap<DayOfWeek, Workout> updateWeek(HashMap<DayOfWeek, Workout> inputWeek, int i) {
		HashMap<DayOfWeek, Workout> thisWeek = new HashMap<>();
		// loop through days in the week, and lifts in each days workout
		for (DayOfWeek day: inputWeek.keySet()) {
			Workout thisDay = inputWeek.get(day).copy();
			// gets set of lifts in a days workout
			for (LiftData l: thisDay.getLifts()) {
				switch(l.getIntensity()) {
					case HIGH:
						l.setReps(l.getReps() + (1 * i));
					case MEDIUM:
						l.setReps(l.getReps() + (2 * i));
					default:
						l.setReps(l.getReps() + (3 * i));
					
				}
			}
			thisWeek.put(day, thisDay);
		}
		return thisWeek;
	}

	@Override
	public String toString() {
		if (oneWeek.isEmpty()) {
			return "Workout cycle is empty \n";
		}
		String str = "Workout " + this.getName() + "\n";
		for (DayOfWeek day: DayOfWeek.values()) {
			if (oneWeek.get(day) == null) {
				str = str + "  ==== " + day + " ==== \nREST DAY\n";
			} else {
				str = str + " ==== " + day + " ---> " + oneWeek.get(day).toString();
			}
		}
		return str;
	}
	
}
