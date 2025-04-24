package model;

import model.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


// Stores information about all of the available exercises
public final class ExerciseCatalog {
	/* INSTANCE VARIABLES
	 * each exercise is guaranteed to be unique
	 */
	private static final ArrayList<Exercise> exercises = new ArrayList<>();

	// CONSTRUCTOR
	public static void loadExercises() {
		File file = new File("exerciseCatalog.txt");
		Scanner scanner;

		try {
			exercises.clear();
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				exercises.add(createExercise(line.split(", ")));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static List<Exercise> getAllExercises() {
		return Collections.unmodifiableList(exercises);
	}

	// creates the exercise from the given 
	private static Exercise createExercise(String[] line) {
		String name = line[0].strip();
		String group = line[1].strip();
		String intes = line[2].strip();

		MuscleGroup muscleGroup = muscleGroupEnum(group);
		Intensity intensity = intensityEnum(intes);
		return new Exercise(name, muscleGroup, intensity);
	}


	// set return the intensity enum of the exercise
	public static Intensity intensityEnum(String intes) {
		switch (intes.toUpperCase()) {
			case "LOW":
				return Intensity.LOW;
			case "MED":
				return Intensity.MEDIUM;
			default:
				return Intensity.HIGH;
		}
	}

	// set the returns the enum muscle group
	public static MuscleGroup muscleGroupEnum(String muscle) {
		switch (muscle) {
			case "Chest":
				return MuscleGroup.CHEST;
			case "Back":
				return MuscleGroup.BACK;
			case "Shoulders":
				return MuscleGroup.SHOULDERS;
			case "Arms":
				return MuscleGroup.ARMS;
			case "Core":
				return MuscleGroup.CORE;
			case "Legs":
				return MuscleGroup.LEGS;
			default:
				return MuscleGroup.MISC;
		}
	}

	// get the exercise by name
	public static Exercise getExerciseByName(String name) {
		for (Exercise ex : exercises) {
			if ((ex.getName().strip()).equalsIgnoreCase(name.strip())) {
				return ex;
			}
		}
		return null;
	}
}