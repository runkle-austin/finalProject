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
import java.util.HashMap;
import java.util.Map;

public class WorkoutCycleAddView {
    private final VBox root;

    public WorkoutCycleAddView(GUIView app, Stage stage) {
        User user = app.getCurrentUser();
        VBox content = new VBox(15);
        content.setPadding(new Insets(20));

        // Input fields for title and length
        Label titleLabel = new Label("Workout Cycle Title:");
        TextField titleField = new TextField();
        titleField.setPromptText("Enter cycle name");

        Label lengthLabel = new Label("Number of Weeks:");
        TextField lengthField = new TextField();
        lengthField.setPromptText("Enter number > 0");

        // HBox rows for title and length
        HBox titleRow = new HBox(10, titleLabel, titleField);
        HBox lengthRow = new HBox(10, lengthLabel, lengthField);

        content.getChildren().addAll(titleRow, lengthRow);

        // Dummy cycle for layout purposes — you'll replace this after form is submitted
        WorkoutCycle cycle = new WorkoutCycle("Temp", 1);

        // For each day of week: show existing workout or allow add/remove
        Map<DayOfWeek, Workout> stagedWorkouts = new HashMap<>();

        for (DayOfWeek day : DayOfWeek.values()) {
            HBox row = new HBox(10);
            row.setPadding(new Insets(5));

            Label dayLabel = new Label(day.toString().substring(0,1) + day.toString().substring(1).toLowerCase());
            dayLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

            ComboBox<String> combo = new ComboBox<>();
            user.getMyFullLog().getMyWorkouts()
                    .stream()
                    .map(Workout::getName)
                    .forEach(combo.getItems()::add);
            combo.setPromptText("Select Workout");

            Button addBtn = new Button("Add Workout");

            // We'll replace this later
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

                        // Replace combo + addBtn with label + changeBtn
                        Label workoutLabel = new Label(selectedWorkout.getName());
                        Button changeBtn = new Button("Change");
                        row.getChildren().setAll(dayLabel, workoutLabel, changeBtn);

                        changeBtn.setOnAction(changeEvent -> {
                            // Put the combo and add button back
                            row.getChildren().setAll(dayLabel, combo, addBtn);
                        });
                    }
                }
            });

            content.getChildren().add(row);
        }

        // Back button
        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> app.showWorkoutCyclesView(stage));
        content.getChildren().add(backBtn);

        // Error message label
        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");

// Done button
        Button doneBtn = new Button("Done");
        doneBtn.setOnAction(e -> {
            String title = titleField.getText().trim();
            String lengthText = lengthField.getText().trim();

            // Validation
            if (title.isEmpty()) {
                errorLabel.setText("Title cannot be empty.");
                return;
            }

            int length;
            try {
                length = Integer.parseInt(lengthText);
                if (length <= 0) {
                    errorLabel.setText("Length must be a number greater than 0.");
                    return;
                }
            } catch (NumberFormatException ex) {
                errorLabel.setText("Length must be a valid number.");
                return;
            }

            // Create and populate cycle (just oneWeek)
            WorkoutCycle newCycle = new WorkoutCycle(title, length);
            stagedWorkouts.forEach(newCycle::setWorkoutByDay);

            // Save to user log
            user.getMyFullLog().addWorkoutCycle(newCycle);
            app.showWorkoutCyclesView(stage);
        });

// Add to view
        content.getChildren().addAll(errorLabel, doneBtn);

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);

        root = new VBox(scrollPane);
    }

    public Parent getView() {
        return root;
    }
}