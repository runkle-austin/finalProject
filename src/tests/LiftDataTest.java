// LiftDataTest.java
package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.Exercise;
import model.Intensity;
import model.MuscleGroup;
import model.LiftData;

public class LiftDataTest {
	
	@Test
	public void testLiftData() {
		Exercise e = new Exercise(MuscleGroup.BACK, "Pull up", Intensity.MEDIUM);
		LiftData l = new LiftData(e, 10, 0.0, 3);
		assertEquals(l.getExercise(), e);
		assertEquals(l.getReps(), 10);
		// This was done because assertEquals does not work with doubles
		assertEquals(l.getWeight() + "", 0.0 + "");
		assertEquals(l.getSets(), 3);
		
		// adjust values
		l.setReps(12);
		l.setWeight(2.5);
		l.setSets(4);
		
		assertEquals(l.getReps(), 12);
		// This was done because assertEquals does not work with doubles
		assertEquals(l.getWeight() + "", 2.5 + "");
		assertEquals(l.getSets(), 4);
	}
	
}
