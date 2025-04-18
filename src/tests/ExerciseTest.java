// ExerciseTest.java
package tests;

import static org.junit.Assert.*;

import model.LiftData;
import org.junit.jupiter.api.Test;

import model.Exercise;
import model.Intensity;
import model.MuscleGroup;

public class ExerciseTest {
 
	@Test
	public void testExerciseGetters() {
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
	
	@Test
	public void testEquals() {
		Exercise e1 = new Exercise("Pull up", MuscleGroup.BACK, Intensity.MEDIUM);
		Exercise e2 = new Exercise("Pull up", MuscleGroup.BACK, Intensity.MEDIUM);
		Exercise e3 = new Exercise("Squat", MuscleGroup.LEGS, Intensity.HIGH);
		assertEquals(e1,e2);
		assertNotEquals(e1,e3);
		// test hashCode
		assertEquals(e1.hashCode(),e2.hashCode());
		assertNotEquals(e1.hashCode(),e3.hashCode());
	}

	@Test
	public void testNullExercise() {
		Exercise e1 = new Exercise("Pull up", MuscleGroup.BACK, Intensity.MEDIUM);
		// test null objects
		assertNotEquals(e1,null);
		assertNotEquals(e1.hashCode(),null);
		// test different class
		LiftData l1 = new LiftData(e1, 1,1,1);
		assertNotEquals(e1, l1);
		assertNotEquals(e1.hashCode(),l1);
	}


}
