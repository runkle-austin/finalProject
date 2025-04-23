package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.LiftData;
import model.Workout;
import model.WorkoutCycle;

import java.time.DayOfWeek;

public class WorkoutCycleDetailView {
    private final VBox view;

    public WorkoutCycleDetailView(GUIView app, Stage stage, WorkoutCycle cycle) {
        view = new VBox(15);
        view.setPadding(new Insets(20));

        // Title and cycle length
        Label title = new Label("Workout Cycle: " + cycle.getName());
        Label length = new Label("Length: " + cycle.getNumberWeeks() + " weeks");
        view.getChildren().addAll(title, length);

        // Show each day and workout
        for (DayOfWeek day : DayOfWeek.values()) {
            Workout workout = cycle.getOneWeek().get(day);

            // <h1> Monday
            Label dayHeader = new Label(day.toString().substring(0, 1) + day.toString().substring(1).toLowerCase());
            dayHeader.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
            view.getChildren().add(dayHeader);

            if (workout == null) {
                view.getChildren().add(new Label("Rest Day"));
                continue;
            }

            // <h2> Workout name
            Label workoutName = new Label(workout.getName());
            workoutName.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
            view.getChildren().add(workoutName);

            // <p> Exercises
            for (LiftData lift : workout.lifts()) {
                String text = String.format("%s — %d sets, %d reps, %.1f lbs",
                        lift.getName(), lift.getSets(), lift.getReps(), lift.getWeightInLbs());
                Text liftText = new Text(text);
                view.getChildren().add(liftText);
            }

            view.getChildren().add(new Text("\n"));
        }

        // Back button
        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> app.showWorkoutCyclesView(stage));
        view.getChildren().add(backBtn);
    }

    public VBox getView() {
        return view;
    }
}