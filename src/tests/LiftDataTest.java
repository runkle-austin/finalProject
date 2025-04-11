// LiftDataTest.java
package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Exercise;
import model.Intensity;
import model.MuscleGroup;
import model.LiftData;

public class LiftDataTest {
	
	@Test
	public void testLiftData() {
		Exercise e = new Exercise("Pull up", MuscleGroup.BACK, Intensity.MEDIUM);
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
	
	@Test
	public void testLiftCopy() {
		Exercise e1 = new Exercise("Pull up", MuscleGroup.BACK, Intensity.MEDIUM);
		Exercise e2 = new Exercise("Push up", MuscleGroup.BACK, Intensity.MEDIUM);
		LiftData l1 = new LiftData(e1, 10, 0.0, 3);
		LiftData l2 = l1.copy();
		// test two identical lifts
		assertEquals(l1.toString(), l2.toString());
		assertEquals(l1,l2);
		// same exercise but different reps, weights, and sets
		LiftData l3 = new LiftData(e1, 11, 1.0, 4);
		assertEquals(l1,l3);
		// different exercise, same reps, weights, sets
		l3 = new LiftData(e2, 10, 0.0, 3);
		assertNotEquals(l1, l3);
		
	}
}
