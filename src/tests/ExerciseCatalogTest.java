package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import model.ExerciseCatalog;

public class ExerciseCatalogTest {
	@Test
	void testLoadExcersices() {
		ExerciseCatalog.loadExercises();
		assertEquals("Push Ups, CHEST, LOW\n", ExerciseCatalog.getExerciseByName("Push Ups").toString());
	}
}
