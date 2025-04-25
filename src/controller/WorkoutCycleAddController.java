package controller;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import model.Workout;
import model.WorkoutCycle;
import view.GUIView;
import view.WorkoutCycleAddView;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

public class WorkoutCycleAddController {
    private final GUIView app;
    private final Stage stage;
    private final WorkoutCycleAddView view;
    private final User user;
    private final Map<DayOfWeek, Workout> stagedWorkouts = new HashMap<>();

    public WorkoutCycleAddController(GUIView app, Stage stage, WorkoutCycleAddView view, User user) {
        this.app = app;
        this.stage = stage;
        this.view = view;
        this.user = user;
    }

    public void setupUI() {
        VBox content = view.getContent();
        content.getChildren().clear();

        // Inputs
        Label titleLabel = new Label("Workout Cycle Title:");
        TextField titleField = new TextField();
        titleField.setPromptText("Enter cycle name");

        Label lengthLabel = new Label("Number of Weeks:");
        TextField lengthField = new TextField();
        lengthField.setPromptText("Enter number > 0");

        HBox titleRow = new HBox(10, titleLabel, titleField);
        HBox lengthRow = new HBox(10, lengthLabel, lengthField);
        content.getChildren().addAll(titleRow, lengthRow);

        // Day rows
        for (DayOfWeek day : DayOfWeek.values()) {
            HBox row = new HBox(10);
            row.setPadding(new Insets(5));

            Label dayLabel = new Label(day.toString().substring(0, 1) + day.toString().substring(1).toLowerCase());
            dayLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

            ComboBox<String> combo = new ComboBox<>();
            user.getMyFullLog().getMyWorkouts().stream()
                    .map(Workout::getName)
                    .forEach(combo.getItems()::add);

            combo.setPromptText("Select Workout");

            Button addBtn = new Button("Add Workout");

            row.getChildren().addAll(dayLabel, combo, addBtn);

            addBtn.setOnAction(e -> {
                String selected = combo.getValue();
                if (selected != null) {
                    Workout selectedWorkout = user.getMyFullLog().getMyWorkouts()
                            .stream()
                            .filter(w -> w.getName().equals(selected))
                            .findFirst()
                            .orElse(null);

                    if (selectedWorkout != null) {
                        stagedWorkouts.put(day, selectedWorkout);
                        Label workoutLabel = new Label(selectedWorkout.getName());
                        Button changeBtn = new Button("Change");

                        row.getChildren().setAll(dayLabel, workoutLabel, changeBtn);
                        changeBtn.setOnAction(ev -> row.getChildren().setAll(dayLabel, combo, addBtn));
                    }
                }
            });

            content.getChildren().add(row);
        }

        // Error and control buttons
        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");

        Button doneBtn = new Button("Done");
        doneBtn.setOnAction(e -> {
            String title = titleField.getText().trim();
            String lengthText = lengthField.getText().trim();

            if (title.isEmpty()) {
                errorLabel.setText("Title cannot be empty.");
                return;
            }

            int length;
            try {
                length = Integer.parseInt(lengthText);
                if (length <= 0) {
                    errorLabel.setText("Length must be greater than 0.");
                    return;
                }
            } catch (NumberFormatException ex) {
                errorLabel.setText("Length must be a valid number.");
                return;
            }

            WorkoutCycle newCycle = new WorkoutCycle(title, length);
            stagedWorkouts.forEach(newCycle::setWorkoutByDay);

            user.getMyFullLog().addWorkoutCycle(newCycle);
            user.notifyObservers(); // 🔥 trigger updates
            app.showWorkoutCyclesView(stage);
        });

        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> app.showWorkoutCyclesView(stage));

        content.getChildren().addAll(backBtn, errorLabel, doneBtn);
    }

    public void refresh() {
        setupUI(); // Simply rebuilds the layout
    }
}