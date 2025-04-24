package view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Exercise;
import model.ExerciseCatalog;
import model.Workout;

import java.util.Collections;

public class EditLiftsView {
    private final VBox view;
    private final GUIView app;
    private final Stage stage;
    private final Workout workout;

    public EditLiftsView(GUIView app, Stage stage, Workout workout) {
        this.app     = app;
        this.stage   = stage;
        this.workout = workout;

        // Back button
        Button backBtn = new Button("Back to Workouts");
        backBtn.setOnAction(e -> app.showWorkoutView(stage));

        // Header
        Label header = new Label("Editing Workout: " + workout.getName());

        // Exercise selector (ComboBox)
        ComboBox<String> exerciseCombo = new ComboBox<>();
        // populate from catalog (add this getter in ExerciseCatalog)
        ExerciseCatalog.getAllExercises()
                .stream()
                .map(Exercise::getName)
                .forEach(exerciseCombo.getItems()::add);
        exerciseCombo.setPromptText("Select exercise");

        // Reps, Weight, Sets inputs
        TextField repsField   = new TextField(); repsField.setPromptText("Reps");
        TextField weightField = new TextField(); weightField.setPromptText("Weight");
        TextField setsField   = new TextField(); setsField.setPromptText("Sets");

        // Add Lift button & error label
        Button addLiftBtn = new Button("Add Lift");
        Label errorLabel  = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");

        // ListView to display current lifts
        ListView<String> liftsListView = new ListView<>();
        liftsListView.setPrefHeight(200);
        refreshLiftList(liftsListView);

        // Remove selected lift button
        Button removeBtn = new Button("Remove Selected");
        removeBtn.setOnAction(e -> {
            int idx = liftsListView.getSelectionModel().getSelectedIndex();
            if (idx >= 0) {
                String exName = workout.getLifts().get(idx).getExercise().getName();
                workout.removeLift(exName);
                refreshLiftList(liftsListView);
            }
        });

        // Add Lift logic
        addLiftBtn.setOnAction(e -> {
            String exName = exerciseCombo.getValue();
            String repsTxt = repsField.getText().trim();
            String wtTxt   = weightField.getText().trim();
            String setsTxt = setsField.getText().trim();
            errorLabel.setText("");

            if (exName == null || repsTxt.isEmpty() || wtTxt.isEmpty() || setsTxt.isEmpty()) {
                errorLabel.setText("Fill in all fields");
                return;
            }
            try {
                int reps   = Integer.parseInt(repsTxt);
                double wt  = Double.parseDouble(wtTxt);
                int sets   = Integer.parseInt(setsTxt);
                boolean added = workout.addLift(exName, reps, wt, sets);
                if (!added) errorLabel.setText("Could not add (duplicate or invalid exercise)");
                refreshLiftList(liftsListView);
            } catch (NumberFormatException nfe) {
                errorLabel.setText("Reps, weight, and sets must be numbers");
            }
        });

        // Done button
        Button doneBtn = new Button("Done");
        doneBtn.setOnAction(e -> app.showWorkoutView(stage));

        // Layout
        HBox inputBox   = new HBox(10, exerciseCombo, repsField, weightField, setsField, addLiftBtn);
        HBox actionBox  = new HBox(10, backBtn, removeBtn, doneBtn);
        view = new VBox(15,
                actionBox,
                header,
                inputBox,
                errorLabel,
                new Label("Current Lifts:"),
                liftsListView
        );
        view.setPadding(new Insets(20));
    }

    private void refreshLiftList(ListView<String> listView) {
        listView.getItems().clear();
        workout.getLifts().forEach(l -> {
            String line = String.format("%s — %dx%d @ %.1f lbs",
                    l.getExercise().getName(), l.getReps(), l.getSets(), l.getWeightInLbs());
            listView.getItems().add(line);
        });
    }

    public Parent getView() {
        return view;
    }
}
