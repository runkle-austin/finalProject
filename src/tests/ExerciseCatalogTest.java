package tests;

import org.junit.Test;

import model.ExerciseCatalog;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExerciseCatalogTest {
	@Test
    public void testLoadExcersices() {
		ExerciseCatalog.loadExercises();
		assertEquals("Push Ups, CHEST, LOW\n", ExerciseCatalog.getExerciseByName("Push Ups").toString());
	}
}
