// Exercise.java
package model;

import java.util.Objects;

// store information about each song
public final class Exercise {
	// INSTANCE VARIABLES
	private final MuscleGroup muscle;
	private final Intensity inten;
	private final String name;
	
	// CONSTRUCTOR
	public Exercise(String name, MuscleGroup muscle, Intensity inten) {
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
	
	@Override
	public int hashCode() {
		return Objects.hash(inten, muscle, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exercise other = (Exercise) obj;
		return inten == other.inten && muscle == other.muscle && Objects.equals(name, other.name);
	}
	
	@Override
	public String toString() {
		return this.getName() + ", " + this.getMuscle() + ", " + this.getIntensity() + "\n";
	}
	
}
