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
	
	// 
	
	
}
