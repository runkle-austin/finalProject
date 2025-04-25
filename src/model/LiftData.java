// ExerciseAndReps.java
package model;

import java.io.Serializable;
import java.util.Objects;

public class LiftData implements Serializable {
	// INSTANCE VARIABLES
	private Exercise ex;
	private int reps;
	private double weightInLbs;
	private int sets;
	private static final long serialVersionUID = 1L;
	
	// CONSTRUCTOR
	public LiftData(Exercise ex, int reps, double weightInLbs, int sets) {
		this.ex = ex;
		this.reps = reps;
		this.weightInLbs = weightInLbs;
		this.sets = sets;
	}

	// GETTERS
	public double getWeight(boolean showInLbs) {
		if (showInLbs) {
			return weightInLbs;
		}
		return getWeightInKg();
	}
	
	public String getName() {
		return this.ex.getName();
	}

	public Exercise getExercise() {
		return this.ex;
	}
	
	public Intensity getIntensity() {
		return this.ex.getIntensity();
	}
	
	public MuscleGroup getMuscleGroup() {
		return this.ex.getMuscle();
	}
	
	public int getReps () {
		return this.reps;
	}
	
	public double getWeightInLbs() {
		return this.weightInLbs;
	}
	
	public double getWeightInKg() {
		return this.weightInLbs / 2.20462;
	}
	
	public int getSets() {
		return this.sets;
	}
	
	// SETTERS
	public void setReps(int reps) {
		this.reps = reps;
	}
	
	public void setWeightInLbs(double lbs) {
		this.weightInLbs = lbs;
	}

	
	public void setSets(int sets) {
		this.sets = sets;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(ex);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LiftData other = (LiftData) obj;
		return Objects.equals(ex, other.ex);
	}

	@Override
	public String toString() {
		return ex.getName() + ", reps = " + reps + ", weight = " + weightInLbs + ", sets = " + sets + "\n";
	}

	public LiftData copy() {
		return new LiftData(ex, reps, weightInLbs, sets);
	}

}
