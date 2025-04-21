// Workout.java
package model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

// Stores information about a single work out
public class Workout implements Serializable {
	// INSTANCE VARIABLES
	private String name;
	private ArrayList<LiftData> lifts;
	private static final long serialVersionUID = 1L;

	// CONSTRUCTOR
	public Workout(String name) {
		this.lifts = new ArrayList<>();
		this.name = name;
	}

	// METHODS

	// add a lift to the set of lifts, returns true if successful, false if not
	public boolean addLift(String exName, int reps, double weight, int sets) {
		Exercise e = ExerciseCatalog.getExerciseByName(exName);
		if (e == null) {
			return false;
		}
		LiftData l = new LiftData(e, reps, weight, sets);
		if (!lifts.contains(l)) {
			addByIntensity(l);
			return true;
		}
		return false;
	}


	//

	// @pre lift must be unique
	// adds lift in descending order from highest intensity to lowest
	private void addByIntensity(LiftData lift) {
		for (int i = 0; i < lifts.size(); i++) {
			if (lift.getIntensity().compareTo(lifts.get(i).getIntensity()) > 0) {
				lifts.add(i, lift);
				return;
			}
		}
		lifts.add(lift);
	}

	// remove a lift from the set of lifts
	public boolean removeLift(String exName) {
		for (LiftData l : lifts) {
			if (l.getExercise().getName().equals(exName)) {
				lifts.remove(l);
				return true;
			}
		}
		return false;
	}

	// get deep-copied lifts in the workout
	public ArrayList<LiftData> getLifts() {
		ArrayList<LiftData> copy = new ArrayList<>();
		for (LiftData l : lifts) {
			copy.add(l.copy());
		}
		return copy;
	}

	public Workout getCopy(){
		Workout copy = new Workout(name);
		// sets lifts to deep copy
		copy.setLifts(this.getLifts());
		return copy;
	}

	// shallow copy version, used by one branch
	public ArrayList<LiftData> toArrayList() {
		ArrayList<LiftData> copy = new ArrayList<>();
		for (LiftData l : lifts) {
			copy.add(l);
		}
		return copy;
	}

	// getter for raw internal list
	public ArrayList<LiftData> lifts() {
		return lifts;
	}

	// get the name of the workout
	public String getName() {
		return this.name;
	}

	public void setLifts(ArrayList<LiftData> lifts) {
		this.lifts = lifts;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("Workout " + this.name + "\n");
		for (LiftData l : lifts) {
			str.append(l.toString());
		}
		return str.toString();
	}

	// creates deep copy of workout
	public Workout copy() {
		Workout copy = new Workout(this.name);
		for (LiftData l : lifts) {
			copy.addLift(l.getExercise().getName(), l.getReps(), l.getWeightInLbs(), l.getSets());
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
		if (obj == null || getClass() != obj.getClass())
			return false;
		Workout other = (Workout) obj;
		return Objects.equals(lifts, other.lifts) && Objects.equals(name, other.name);
	}
}
