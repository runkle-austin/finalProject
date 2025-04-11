//Workout.java
package model;

import java.util.ArrayList;
import java.util.Objects;

// Stores information about a single work out
public class Workout {
	// INSTANCE VARAIBLES
	String name;
	
	ArrayList<LiftData> lifts = new ArrayList<>();
	
	// CONSTRUCTOR
	public Workout(String name) {
		this.name = name;
	}
	
	// METHODS
	
	// add a lift to the set of lifts, returns true if successful, false if not
	public boolean addLift(String exName, int reps, double weight, int sets) {
		// retrieve exercise
		Exercise e = ExerciseCatalog.getExerciseByName(exName);
		// add data to exercise
		LiftData l = new LiftData(e, reps, weight, sets);
		
		// Lift data has overloaded equals method
		// add if not in list
		if (! lifts.contains(l)) {
			addByIntensity(l);
			return true;
		}
		return false;
	}

	// @pre lift must be unique 
	// adds lift in descending order from highest intensity to lowest
	private void addByIntensity(LiftData lift) {
		for (int i = 0; i < lifts.size(); i ++) {
			// TODO all are being set to HIGH intensity
			System.out.println(lift.getIntensity());
			// compares lift to currLift, if lift has greater intensity, add at that index
			if (lift.getIntensity().compareTo(lifts.get(i).getIntensity()) > 0) {
				lifts.add(i, lift);
				return;
			}
		}
		// if first item or least intensity, add to end
		lifts.add(lift);
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
	
	// get the lifts in the work out
	public ArrayList<LiftData> getLifts() {
		ArrayList<LiftData> copy = new ArrayList<>();
		for (LiftData l: lifts) {
			copy.add(l.copy());
		}
		return copy;
	}
	
	
	// get the name of the work out
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		String str = "Workout "+ this.name + "\n";
		for (LiftData l: lifts) {
			str = str + l.toString();
		}
		return str;
	}

	// creates deep copy of lifts
	public Workout copy() {
		Workout copy = new Workout(this.name);
		for (LiftData l: lifts) {
			copy.addLift(l.getName(), l.getReps(), l.getWeight(), l.getSets());
		}
		return copy;
	}

	@Override
	public int hashCode() {
		return Objects.hash(lifts, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Workout other = (Workout) obj;
		// auto generated, should use arraylist equals which will compare elements
		return Objects.equals(lifts, other.lifts) && Objects.equals(name, other.name);
	}
	
	
	
	
}
