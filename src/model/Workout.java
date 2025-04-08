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
	
	// add a lift to the set of lifts
	public void addLift(String exName, int reps, double weight, int sets) {
		// retrieve exercise
		Exercise e = ExerciseCatalog.getExerciseByName(exName);
		// add data to exercise
		LiftData l = new LiftData(e, reps, weight, sets);
		// Lift data has overloaded equals method
		lifts.add(l);
	}
	
	// remove a lift form the set of lifts
	public boolean removeLift(String exName) {
		for (LiftData l: lifts) {
			if (l.getExercise().getName().equals(exName)) {
				lifts.remove(l);
				return true;
			}
		}
		return false;
	}
	
	
	// get the name of the work out
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		String str = "Workout "+ this.name + "\n";
		for (LiftData l: lifts) {
			str = str + l.getExercise().getName() + "\n";
		}
		return str;
	}

	public Workout copy() {
		Workout copy = new Workout(this.name);
		for (LiftData l: lifts) {
			copy.addLift(l.getExercise().getName(), l.getReps(), l.getWeight(), l.getSets());
		}
		return copy;
	}
	
	
}
