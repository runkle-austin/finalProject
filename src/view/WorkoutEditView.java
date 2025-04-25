package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Exercise;
import model.ExerciseCatalog;
import model.LiftData;
import model.Workout;

public class WorkoutEditView {
    private final VBox root;

    public WorkoutEditView(GUIView app, Stage stage, Workout workout) {
        VBox content = new VBox(15);
        content.setPadding(new Insets(20));

        // Title
        Label title = new Label("Workout: " + workout.getName());
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-underline: true;");
        content.getChildren().add(title);

        // Existing lifts
        for (LiftData liftData : workout.getLifts()) {
            String text = String.format("%s — %d sets, %d reps, %.1f lbs",
                    liftData.getName(), liftData.getSets(), liftData.getReps(), liftData.getWeightInLbs());

            Label liftLabel = new Label(text);
            Button removeButton = new Button("Remove");

            HBox row = new HBox(15);
            row.setPadding(new Insets(5));

            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);

            row.getChildren().addAll(liftLabel, spacer, removeButton);

            removeButton.setOnAction(e -> {
                workout.removeLift(liftData.getName());
                content.getChildren().remove(row);
            });

            content.getChildren().add(row);
        }

        // Input controls
        ComboBox<String> exerciseCombo = new ComboBox<>();
        ExerciseCatalog.getAllExercises().stream()
                .map(Exercise::getName)
                .forEach(exerciseCombo.getItems()::add);
        exerciseCombo.setPromptText("Select exercise");
        exerciseCombo.setPrefWidth(150);

        TextField setsField = new TextField(); setsField.setPromptText("Sets"); setsField.setPrefWidth(60);
        TextField repsField = new TextField(); repsField.setPromptText("Reps"); repsField.setPrefWidth(60);
        TextField weightField = new TextField(); weightField.setPromptText("Weight"); weightField.setPrefWidth(80);

        Button addLiftBtn = new Button("Add Lift");
        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");

        // Input row (HBox)
        HBox inputRow = new HBox(10);
        inputRow.setPadding(new Insets(10));
        inputRow.setAlignment(Pos.CENTER_LEFT);
        inputRow.setMaxWidth(Double.MAX_VALUE);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        inputRow.getChildren().addAll(exerciseCombo, setsField, repsField, weightField, spacer, addLiftBtn);
        content.getChildren().addAll(inputRow, errorLabel);

        // Add-lift button logic
        addLiftBtn.setOnAction(e -> {
            String exerciseName = exerciseCombo.getValue();
            String setsText = setsField.getText();
            String repsText = repsField.getText();
            String weightText = weightField.getText();

            if (exerciseName == null || setsText.isEmpty() || repsText.isEmpty() || weightText.isEmpty()) {
                errorLabel.setText("Please fill out all fields.");
                return;
            }

            try {
                int sets = Integer.parseInt(setsText);
                int reps = Integer.parseInt(repsText);
                double weight = Double.parseDouble(weightText);

                if (sets <= 0 || reps <= 0 || weight < 0) {
                    errorLabel.setText("Sets and reps must be positive integers: weight must be 0 or greater.");
                    return;
                }

                // Add to model
                workout.addLift(exerciseName, reps, weight, sets);

                // Add to UI
                String display = String.format("%s — %d sets, %d reps, %.1f lbs", exerciseName, sets, reps, weight);
                Label liftLabel = new Label(display);
                Button removeBtn = new Button("Remove");

                HBox liftRow = new HBox(15);
                liftRow.setPadding(new Insets(5));

                Region rowSpacer = new Region();
                HBox.setHgrow(rowSpacer, Priority.ALWAYS);

                liftRow.getChildren().addAll(liftLabel, rowSpacer, removeBtn);

                removeBtn.setOnAction(ev -> {
                    workout.removeLift(exerciseName);
                    content.getChildren().remove(liftRow);
                });

                int insertIndex = content.getChildren().indexOf(inputRow);
                content.getChildren().add(insertIndex, liftRow);

                // Clear inputs
                exerciseCombo.setValue(null);
                setsField.clear();
                repsField.clear();
                weightField.clear();
                errorLabel.setText("");

            } catch (NumberFormatException ex) {
                errorLabel.setText("Sets and reps must be positive integers: weight must be 0 or greater.");
            }
        });

        // Back button
        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> app.showWorkoutView(stage));
        content.getChildren().add(backBtn);

        // Scroll wrapper
        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);

        root = new VBox(scrollPane);
    }

    public VBox getView() {
        return root;
    }
}