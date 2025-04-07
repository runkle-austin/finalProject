package model;

import java.time.DayOfWeek;
import java.util.HashMap;

public final class WorkoutCycle {
	private String name;
	private int numberWeeks;
	private HashMap<DayOfWeek, Workout> fullCycle = new HashMap<DayOfWeek, Workout>();
	
	public WorkoutCycle(String name, int numberWeeks) {
		this.name = name;
		this.numberWeeks = numberWeeks;
	}
	
	// METHOD
	public String getName() {
		return this.name;
	}
	
	// add a Work out to the workout cycle
	public void addWorkout(String d, Workout w) {
		DayOfWeek day = DayOfWeek.valueOf(d);
		// remove any existing days
		fullCycle.remove(day);
		// add the work out to the full cycle
		fullCycle.put(day, w);
	}
	
	// remove a work out and return true if completed
	public void removeWorkoutByDay(DayOfWeek day) {
		fullCycle.remove(day);
	}
	
	@Override
	public String toString() {
		String str = "Workout " + this.getName() + "\n";
		for (DayOfWeek day: DayOfWeek.values()) {
			str = str + " " + day + " " + fullCycle.get(day).toString();
		}
		return str;
	}
	
	
}
