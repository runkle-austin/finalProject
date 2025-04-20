package view;

import controller.WorkoutController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.FullLog;
import model.Workout;
import model.WorkoutCycle;

import java.time.DayOfWeek;

public class GUIView extends Application {
    @Override
    public void start(Stage stage) {
        // Create the model
        FullLog model = new FullLog();

        // Create the controller and view
        WorkoutController controller = new WorkoutController(model);
        DashBoardView view = new DashBoardView(model);

        // Optional: add a test WorkoutCycle
        WorkoutCycle cycle = new WorkoutCycle("Push Pull Legs", 4);
        Workout push = new Workout("Push Day");
        push.addLift("Bench Press", 10, 135, 3);
        push.addLift("Overhead Press", 8, 95, 3);
        cycle.addWorkout("MONDAY", push);

        Workout pull = new Workout("Pull Day");
        pull.addLift("Deadlift", 5, 185, 3);
        cycle.addWorkout("WEDNESDAY", pull);

        Workout legs = new Workout("Leg Day");
        legs.addLift("Squats", 8, 155, 4);
        cycle.addWorkout("FRIDAY", legs);

        cycle.createFullCycle();
        controller.addWorkoutCycle(cycle);
        controller.setActiveCycle(cycle);

        // Set up scene
        Scene scene = new Scene(view.getView(), 800, 600);
        stage.setScene(scene);
        stage.setTitle("Workout Planner");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
