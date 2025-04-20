// Exercise.java
package model;

import java.io.Serializable;
import java.util.Objects;

// store information about each song
// note that this class is immutable
public final class Exercise implements Serializable {
	// INSTANCE VARIABLES
	private model.MuscleGroup muscle;
	private model.Intensity inten;
	private String name;
	private static final long serialVersionUID = 1L;
	
	// CONSTRUCTOR
	public Exercise(String name, model.MuscleGroup muscle, model.Intensity inten) {
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

	public model.MuscleGroup getMuscle() {
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
