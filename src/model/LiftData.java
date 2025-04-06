// ExerciseAndReps.java
package model;

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
	public Exercise getExercise() {
		return this.ex;
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

	
}
