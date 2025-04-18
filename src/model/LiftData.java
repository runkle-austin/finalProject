// ExerciseAndReps.java
package model;

import model.Exercise;

import java.util.Objects;

public class LiftData {
	// INSTANCE VARIABLES
	private Exercise ex;
	private int reps;
	private double weightInLbs;
	private int sets;
	
	// CONSTRUCTOR
	public LiftData(Exercise ex, int reps, double weightInLbs, int sets) {
		this.ex = ex;
		this.reps = reps;
		this.weightInLbs = weightInLbs;
		this.sets = sets;
	}

	// GETTERS
	
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
	
	public double getWeightInKg(double lbs) {
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
	
	public void setWeightInKg(double kg) {
		this.weightInLbs = kg * 2.20462;
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
