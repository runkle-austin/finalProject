package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Stores information about all of the available exercises
public final class ExerciseCatalog {
	 /* INSTANCE VARIABLES
	 * each exercise is guaranteed to be unique 
	 */ 
	static ArrayList<Exercise> exercises = new ArrayList<>();
	
	// CONSTRUCTOR
	public static void loadExercises() {
		File file = new File("exerciseCatalog.txt");
		Scanner scanner;
		
		try {
			scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				exercises.add(createExercise(line.split(", ")));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static Exercise createExercise(String[] line) {
		String name  = line[0].strip();
		String group = line[1].strip();
		String intes = line[2].strip();
		
		MuscleGroup muscleGroup = muscleGroupEnum(group);
		Intensity intensity = intensityEnum(intes);
		return new Exercise(name, muscleGroup, intensity);
	}
	

	public static Intensity intensityEnum(String intes) {
		switch(intes.toUpperCase()) {
			case "LOW":
				return Intensity.LOW;
			case "MED":
				return Intensity.MEDIUM;
			default:
				return Intensity.HIGH;
		}
	}
	
	public static MuscleGroup muscleGroupEnum(String muscle) {
		switch(muscle) {
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
	
	// This method writes the Exercises back to ExerciseCatalog.txt
	public static void uploadExcercises() {
		try(FileWriter fw = new FileWriter("ExerciseCatalog.txt", true)){
			for (Exercise e: exercises) {
				fw.write(e.toString());
			}
		} catch (IOException e) {
			System.out.println("Error writing to file");
		}
	}
	
	public static Exercise getExerciseByName(String name) {
		for(Exercise ex: exercises) {
			if (ex.getName().equals(name)) {
				return ex;
			}
		}
		return null;
	}
	
	// helper method for testing
	public static void empty() {
		exercises = new ArrayList<>();
	}
}
