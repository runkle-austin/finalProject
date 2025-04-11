// ExerciseAndReps.java
package model;

import java.util.Objects;

public class LiftData {
	// INSTANCE VARIABLES
	private Exercise ex;
	private int reps;
	private double weight;
	private int sets;
	
	// CONSTRUCTOR
	public LiftData(Exercise ex, int reps, double weight, int sets) {
		this.ex = ex;
		this.reps = reps;
		this.weight = weight;
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
	
	public double getWeight() {
		return this.weight;
	}
	
	public int getSets() {
		return this.sets;
	}
	
	// SETTERS
	public void setReps(int reps) {
		this.reps = reps;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
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
		return ex.getName() + ", reps = " + reps + ", weight = " + weight + ", sets = " + sets + "\n";
	}

	public LiftData copy() {
		return new LiftData(ex, reps, weight, sets);
	}
	
}
