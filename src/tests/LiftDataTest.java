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
	public void testEquals() {
		Exercise e = new Exercise("Pull up", MuscleGroup.BACK, Intensity.MEDIUM);
		LiftData l1 = new LiftData(e, 10, 1, 3);
		LiftData l2 = new LiftData(e, 10, 1, 3);
		assertNotEquals(l1, null);
		assertNotEquals(l1, e);
		assertEquals(l1, l2);
	}

	@Test
	public void testGetters() {
		Exercise e = new Exercise("Pull up", MuscleGroup.BACK, Intensity.MEDIUM);
		LiftData l = new LiftData(e, 10, 1, 3);

		assertEquals(l.getName(), "Pull up");
		assertEquals(l.getMuscleGroup(), MuscleGroup.BACK);

		assertEquals(Double.compare(l.getWeightInKg(), 1/2.20462), 0);

		// test get weight
		assertEquals(Double.compare(l.getWeight(true), l.getWeightInLbs()),0);
		assertEquals(Double.compare(l.getWeight(false), l.getWeightInKg()),0);
	}

	@Test
	public void testLiftData() {
		Exercise e = new Exercise("Pull up", MuscleGroup.BACK, Intensity.MEDIUM);
		LiftData l = new LiftData(e, 10, 0.0, 3);
		assertEquals(l.getExercise(), e);
		assertEquals(10, l.getReps());
		// This was done because assertEquals does not work with doubles
		assertEquals(0.0 + "", l.getWeightInLbs() + "");
		assertEquals(3, l.getSets());
		
		// adjust values
		l.setReps(12);
		l.setWeightInLbs(2.5);
		l.setSets(4);
		
		assertEquals(12, l.getReps());
		// This was done because assertEquals does not work with doubles
		assertEquals(2.5 + "", l.getWeightInLbs() + "");
		assertEquals(4, l.getSets());
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
