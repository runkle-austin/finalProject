// ExerciseTest.java
package tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import model.Exercise;
import model.Intensity;
import model.MuscleGroup;

public class ExerciseTest {

	@Test
	public void testGetExercise() {
		Exercise e = new Exercise("Pull up", MuscleGroup.BACK, Intensity.MEDIUM);
		assertEquals(e.getIntensity(), Intensity.MEDIUM);
		assertEquals(e.getMuscle(), MuscleGroup.BACK);
		assertEquals(e.getName(), "Pull up");
		
		Exercise e1 = new Exercise("Curl", MuscleGroup.ARMS, Intensity.LOW);
		assertEquals(e1.getIntensity(), Intensity.LOW);
		assertEquals(e1.getMuscle(), MuscleGroup.ARMS);
		assertEquals(e1.getName(), "Curl");
		
		Exercise e2 = new Exercise("Squat", MuscleGroup.LEGS, Intensity.HIGH);
		assertEquals(e2.getIntensity(), Intensity.HIGH);
		assertEquals(e2.getMuscle(), MuscleGroup.LEGS);
		assertEquals(e2.getName(), "Squat");
	}
}
