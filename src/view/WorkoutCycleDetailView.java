package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.LiftData;
import model.Workout;
import model.WorkoutCycle;

import java.time.DayOfWeek;

public class WorkoutCycleDetailView {
    private final VBox root;  // this becomes the thing returned by getView()

    public WorkoutCycleDetailView(GUIView app, Stage stage, WorkoutCycle cycle) {
        VBox content = new VBox(15);
        content.setPadding(new Insets(20));

        // Title and cycle length
        Label title = new Label("Workout Cycle: " + cycle.getName());
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-underline: true;");

        Label length = new Label("Length: " + cycle.getNumberWeeks() + " weeks");
        length.setStyle("-fx-font-size: 18px; -fx-font-style: italic;");
        content.getChildren().addAll(title, length);

        // Show each day and workout
        for (DayOfWeek day : DayOfWeek.values()) {
            Workout workout = cycle.getOneWeek().get(day);

            Label dayHeader = new Label(day.toString().substring(0, 1) + day.toString().substring(1).toLowerCase());
            dayHeader.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
            content.getChildren().add(dayHeader);

            if (workout == null) {
                content.getChildren().add(new Label("Rest Day"));
                continue;
            }

            Label workoutName = new Label(workout.getName());
            workoutName.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
            content.getChildren().add(workoutName);

            for (LiftData lift : workout.getLifts()) {
                String text = String.format("%s — %d sets, %d reps, %.1f lbs",
                        lift.getName(), lift.getSets(), lift.getReps(), lift.getWeightInLbs());
                Text liftText = new Text(text);
                content.getChildren().add(liftText);
            }

            content.getChildren().add(new Text("\n"));
        }

        // Back button
        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> app.showWorkoutCyclesView(stage));
        content.getChildren().add(backBtn);

        // Wrap in a scroll pane
        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);

        // Final root VBox that holds the scroll pane
        root = new VBox(scrollPane);
    }

    public VBox getView() {
        return root;
    }
}