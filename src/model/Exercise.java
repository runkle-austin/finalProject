// Exercise.java
package model;

import java.util.Objects;

// store information about each song
public final class Exercise {
	// INSTANCE VARIABLES
	private model.MuscleGroup muscle;
	private model.Intensity inten;
	private String name;
	
	// CONSTRUCTOR
	public Exercise(String name, model.MuscleGroup muscle, model.Intensity inten) {
		this.muscle = muscle;	
		this.name = name;
		this.inten = inten;
	}

	public Exercise() {
	}

	// METHODS
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Intensity getIntensity() {
		return inten;
	}

	public void setIntensity(Intensity inten) {
		this.inten = inten;
	}

	public model.MuscleGroup getMuscle() {
		return muscle;
	}

	public void setMuscle(MuscleGroup muscle) {
		this.muscle = muscle;
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
