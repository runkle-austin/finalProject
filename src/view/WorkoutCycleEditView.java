package view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.User;
import model.Workout;
import model.WorkoutCycle;

import java.time.DayOfWeek;

public class WorkoutCycleEditView {
    private final VBox root;

    public WorkoutCycleEditView(GUIView app, Stage stage, WorkoutCycle cycle) {
        User user = app.getCurrentUser();
        VBox content = new VBox(15);
        content.setPadding(new Insets(20));

        // Title and cycle length
        Label title = new Label("Workout Cycle: " + cycle.getName());
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-underline: true;");

        Label length = new Label("Length: " + cycle.getNumberWeeks() + " weeks");
        length.setStyle("-fx-font-size: 18px; -fx-font-style: italic;");

        content.getChildren().addAll(title, length);

        // For each day of week: show existing workout or allow add/remove
        for (DayOfWeek day : DayOfWeek.values()) {
            HBox row = new HBox(10);
            row.setPadding(new Insets(5));

            Label dayLabel = new Label(
                    day.toString().substring(0,1) +
                            day.toString().substring(1).toLowerCase()
            );
            dayLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

            Workout existing = cycle.getOneWeek().get(day);
            if (existing != null) {
                // Display and allow removal
                Label workoutName = new Label(existing.getName());
                workoutName.setStyle("-fx-font-size: 16px;");
                Button removeBtn = new Button("Remove Workout");
                removeBtn.setOnAction(e -> {
                    cycle.removeWorkout(day);
                    // refresh the view
                    app.showWorkoutCycleEditView(stage, cycle);
                });
                row.getChildren().addAll(dayLabel, workoutName, removeBtn);

            } else {
                // Allow adding a workout
                ComboBox<String> combo = new ComboBox<>();
                user.getMyFullLog().getMyWorkouts()
                        .stream()
                        .map(Workout::getName)
                        .forEach(combo.getItems()::add);
                combo.setPromptText("Select Workout");

                Button addBtn = new Button("Add Workout");
                addBtn.setOnAction(e -> {
                    String selected = combo.getValue();
                    if (selected != null) {
                        Workout toAdd = user.getMyFullLog().getMyWorkouts()
                                .stream()
                                .filter(w -> w.getName().equals(selected))
                                .findFirst()
                                .orElse(null);
                        if (toAdd != null) {
                            cycle.setWorkoutByDay(day, toAdd);
                            app.showWorkoutCycleEditView(stage, cycle);
                        }
                    }
                });

                row.getChildren().addAll(dayLabel, combo, addBtn);
            }

            content.getChildren().add(row);
        }

        // Back button
        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> app.showWorkoutCyclesView(stage));
        content.getChildren().add(backBtn);

        // Wrap in scroll pane
        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);

        root = new VBox(scrollPane);
    }

    public Parent getView() {
        return root;
    }
}
