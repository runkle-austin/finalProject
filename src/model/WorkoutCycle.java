// file: WorkoutCycle.java
// desc: this class generates a multi-week workout cycle based on one week of workouts

package model;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Objects;

// this class models a workoutCycle which is a collection of workouts that can be manipulated to match
// the users needs
public final class WorkoutCycle implements Serializable {
	private String name;
	private int numberWeeks;
	private HashMap<DayOfWeek, Workout> oneWeek = new HashMap<>();
	private ArrayList<HashMap<DayOfWeek, Workout>> fullCycle;
	private static final long serialVersionUID = 1L;

	public WorkoutCycle(String name, int numberWeeks) {
		this.name = name;
		this.numberWeeks = numberWeeks;
	}

	// METHOD
	public String getName() {
		return this.name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public int getNumberWeeks() {
		return this.numberWeeks;
	}

	public HashMap<DayOfWeek, Workout> getOneWeek() {
		return this.oneWeek;
	}

	// adds a single day of workouts to our week
	public void addWorkout(String d, Workout w) {
		DayOfWeek day = DayOfWeek.valueOf(d.toUpperCase());
		oneWeek.remove(day);
		oneWeek.put(day, w);
	}

	public Workout getWorkoutByDay(DayOfWeek day) {
		return oneWeek.get(day);
	}

	// remove a single day of workouts and return true if completed
	public boolean removeWorkoutByDay(String d) {
		DayOfWeek day = DayOfWeek.valueOf(d.toUpperCase());
		if (!oneWeek.containsKey(day)) {
			return false;
		}
		oneWeek.remove(day);
		return true;
	}

	public void removeWorkoutFromOneWeek(Workout w) {
        oneWeek.keySet().removeIf(day -> oneWeek.get(day).equals(w));
	}


	// Helper Method to Ad Workout By Day Object not String
	public void setWorkoutByDay(DayOfWeek day, Workout w) {
		oneWeek.put(day, w);
	}

	// Helper Method for GUI to Remove Workout By Day
	public void removeWorkout(DayOfWeek day) {
		oneWeek.remove(day);
	}

	// based on our input week of workouts, generates a full workout cycle of len numberOfWeeks
	public void createFullCycle() {
		fullCycle = new ArrayList<>(numberWeeks);
		for (int i = 0; i < numberWeeks - 1; i++) {
			HashMap<DayOfWeek, Workout> thisWeek = updateWeek(oneWeek, i);
			fullCycle.add(thisWeek);
		}
		deloadWeek(oneWeek);
	}

	// Implementation of deload week (last week gets 60% of weight and 3 less reps)
	public void deloadWeek(HashMap<DayOfWeek, Workout> weekOne) {
		HashMap<DayOfWeek, Workout> thisWeek = new HashMap<>();
		for (DayOfWeek day : weekOne.keySet()) {
			Workout thisDay = weekOne.get(day).copy();
			for (LiftData l : thisDay.lifts()) {
				l.setWeightInLbs(Math.floor(l.getWeightInLbs() * 0.6));
				if (l.getReps() > 3) {
					l.setReps(l.getReps() - 3);
				} else {
					l.setReps(3);
				}
			}
			thisWeek.put(day, thisDay);
		}
		fullCycle.add(thisWeek);
	}

	// helper function used in createFullCycle
	private HashMap<DayOfWeek, Workout> updateWeek(HashMap<DayOfWeek, Workout> inputWeek, int i) {
		HashMap<DayOfWeek, Workout> thisWeek = new HashMap<>();
		for (DayOfWeek day : inputWeek.keySet()) {
			Workout thisDay = inputWeek.get(day).copy();
			for (LiftData l : thisDay.lifts()) {
				switch (l.getIntensity()) {
					case HIGH:
						l.setReps(l.getReps() + (1 * i));
						break;
					case MEDIUM:
						l.setReps(l.getReps() + (2 * i));
						break;
					case LOW:
						l.setReps(l.getReps() + (3 * i));
						break;
				}
			}
			thisWeek.put(day, thisDay);
		}
		return thisWeek;
	}

	public String getFullCycle() {
		String str = "Full Cycle\n";
		int index = 1;
		for (HashMap<DayOfWeek, Workout> week : fullCycle) {
			str += "Week: " + index + "\n" + week.toString() + "\n=================\n";
			index++;
		}
		return str;
	}


	@Override
	public String toString() {
		if (oneWeek.isEmpty()) {
			return "Workout cycle is empty \n";
		}
		String str = "Workout " + this.getName() + "\n";
		for (DayOfWeek day : DayOfWeek.values()) {
			if (oneWeek.get(day) == null) {
				str += "  ==== " + day + " ==== \nREST DAY\n";
			} else {
				str += " ==== " + day + " ---> " + oneWeek.get(day).toString();
			}
		}
		return str;
	}

	public ArrayList<HashMap<DayOfWeek, Workout>> getFullCycleData() {
		return this.fullCycle;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		WorkoutCycle that = (WorkoutCycle) o;
		return numberWeeks == that.numberWeeks && Objects.equals(name, that.name) && Objects.equals(fullCycle, that.fullCycle);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, numberWeeks, fullCycle);
	}
}
