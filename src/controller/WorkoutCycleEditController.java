package controller;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import model.Workout;
import model.WorkoutCycle;
import view.GUIView;
import view.WorkoutCycleEditView;

import java.time.DayOfWeek;

public class WorkoutCycleEditController {
    private final GUIView app;
    private final Stage stage;
    private final WorkoutCycleEditView view;
    private final WorkoutCycle cycle;
    private final User user;

    public WorkoutCycleEditController(GUIView app, Stage stage, WorkoutCycleEditView view, WorkoutCycle cycle) {
        this.app = app;
        this.stage = stage;
        this.view = view;
        this.cycle = cycle;
        this.user = app.getCurrentUser();
    }

    public void setupUI() {
        VBox content = view.getContent();

        Label title = new Label("Workout Cycle: " + cycle.getName());
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-underline: true;");

        Label length = new Label("Length: " + cycle.getNumberWeeks() + " weeks");
        length.setStyle("-fx-font-size: 18px; -fx-font-style: italic;");
        content.getChildren().addAll(title, length);

        for (DayOfWeek day : DayOfWeek.values()) {
            HBox row = new HBox(10);
            row.setPadding(new Insets(5));

            Label dayLabel = new Label(day.toString().substring(0, 1) +
                    day.toString().substring(1).toLowerCase());
            dayLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);

            Workout existing = cycle.getOneWeek().get(day);
            if (existing != null) {
                Label workoutName = new Label(existing.getName());
                workoutName.setStyle("-fx-font-size: 16px;");

                Button removeBtn = new Button("Remove Workout");
                removeBtn.setOnAction(e -> {
                    cycle.removeWorkout(day);
                    app.showWorkoutCycleEditView(stage, cycle); // refresh
                });

                row.getChildren().addAll(dayLabel, workoutName, spacer, removeBtn);
            } else {
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

                row.getChildren().addAll(dayLabel, combo, spacer, addBtn);
            }

            content.getChildren().add(row);
        }

        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> app.showWorkoutCyclesView(stage));
        content.getChildren().add(backBtn);
    }
}