// Exercise.java
package model;

// store information about each song
public final class Exercise {
	// INSTANCE VARIABLES
	private final MuscleGroup muscle;
	private final Intensity inten;
	private final String name;
	
	// CONSTRUCTOR
	public Exercise(MuscleGroup muscle, String name, Intensity inten) {
		this.muscle = muscle;	
		this.name = name;
		this.inten = inten;
	}

	// METHODS
	public String getName() {
		return name;
	}
	
	public Intensity getIntensity() {
		return inten;
	}

	public MuscleGroup getMuscle() {
		return muscle;
	}
}
