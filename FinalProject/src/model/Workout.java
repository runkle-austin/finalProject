//Workout.java
package model;

import java.util.HashSet;
import java.util.Set;

// Stores information about a single work out
public class Workout {
	// INSTANCE VARAIBLES
	String name;
	Set<LiftData> lifts = new HashSet<>();
	
	// CONSTRUCTOR
	public Workout(String name) {
		this.name = name;
	}
	
	// METHODS
	
	// add a life to the list of lifts
	public void addLift(String exName, int reps, double weight, int sets) {
		// retrieve exercise
		Exercise e = 
		// add data to exercise
		LiftData l = new LiftData(e, reps, weight, sets);
		// Lift data has overloaded equals method
		lifts.add(l);
	}
	
	
	// get the name of the work out
	public String getName() {
		return this.name;
	}

	
}
