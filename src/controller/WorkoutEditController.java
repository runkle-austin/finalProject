package controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Exercise;
import model.ExerciseCatalog;
import model.LiftData;
import model.Workout;
import view.GUIView;
import view.WorkoutEditView;

public class WorkoutEditController {
    private final GUIView app;
    private final Stage stage;
    private final WorkoutEditView view;
    private final Workout workout;

    public WorkoutEditController(GUIView app, Stage stage, WorkoutEditView view, Workout workout) {
        this.app = app;
        this.stage = stage;
        this.view = view;
        this.workout = workout;
    }

    public void setupUI() {
        VBox content = view.getContent();
        content.getChildren().clear();

        Label title = new Label("Workout: " + workout.getName());
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-underline: true;");
        content.getChildren().add(title);

        for (LiftData lift : workout.getLifts()) {
            addLiftRow(content, lift.getName(), lift.getSets(), lift.getReps(), lift.getWeightInLbs());
        }

        // Input Fields
        ComboBox<String> exerciseCombo = new ComboBox<>();
        ExerciseCatalog.getAllExercises().stream()
                .map(Exercise::getName)
                .forEach(exerciseCombo.getItems()::add);
        exerciseCombo.setPromptText("Select exercise");

        TextField setsField = new TextField(); setsField.setPromptText("Sets"); setsField.setPrefWidth(60);
        TextField repsField = new TextField(); repsField.setPromptText("Reps"); repsField.setPrefWidth(60);
        TextField weightField = new TextField(); weightField.setPromptText("Weight"); weightField.setPrefWidth(80);

        Button addLiftBtn = new Button("Add Lift");
        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");

        HBox inputRow = new HBox(10, exerciseCombo, setsField, repsField, weightField);
        inputRow.setPadding(new Insets(10));
        inputRow.setAlignment(Pos.CENTER_LEFT);
        inputRow.setMaxWidth(Double.MAX_VALUE);
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        inputRow.getChildren().add(spacer);
        inputRow.getChildren().add(addLiftBtn);

        content.getChildren().addAll(inputRow, errorLabel);

        addLiftBtn.setOnAction(e -> {
            String name = exerciseCombo.getValue();
            String setsText = setsField.getText();
            String repsText = repsField.getText();
            String weightText = weightField.getText();

            if (name == null || setsText.isEmpty() || repsText.isEmpty() || weightText.isEmpty()) {
                errorLabel.setText("Please fill out all fields.");
                return;
            }

            try {
                int sets = Integer.parseInt(setsText);
                int reps = Integer.parseInt(repsText);
                double weight = Double.parseDouble(weightText);

                if (sets <= 0 || reps <= 0 || weight < 0) {
                    errorLabel.setText("Invalid input: all values must be positive.");
                    return;
                }

                workout.addLift(name, reps, weight, sets);
                view.modelChanged(); // optional immediate refresh, or wait for notifyObservers()

                // Reset input
                exerciseCombo.setValue(null);
                setsField.clear();
                repsField.clear();
                weightField.clear();
                errorLabel.setText("");

            } catch (NumberFormatException ex) {
                errorLabel.setText("Invalid numbers.");
            }
        });

        // Back button
        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> app.showWorkoutView(stage));
        content.getChildren().add(backBtn);
    }

    private void addLiftRow(VBox content, String name, int sets, int reps, double weight) {
        String text = String.format("%s — %d sets, %d reps, %.1f lbs", name, sets, reps, weight);
        Label liftLabel = new Label(text);
        Button removeBtn = new Button("Remove");

        HBox row = new HBox(15, liftLabel);
        row.setPadding(new Insets(5));
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        row.getChildren().addAll(spacer, removeBtn);

        removeBtn.setOnAction(e -> {
            workout.removeLift(name);
            view.modelChanged();
        });

        int insertIndex = content.getChildren().size() - 1; // before back button
        content.getChildren().add(insertIndex, row);
    }

    public void refresh() {
        setupUI(); // Rebuild UI from current workout state
    }
}