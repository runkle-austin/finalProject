package controller;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Exercise;
import model.ExerciseCatalog;
import model.Workout;
import view.GUIView;
import view.LiftEditView;

public class LiftEditController {
    private final GUIView app;
    private final Stage stage;
    private final LiftEditView view;
    private final Workout workout;

    public LiftEditController(GUIView app, Stage stage, LiftEditView view, Workout workout) {
        this.app = app;
        this.stage = stage;
        this.view = view;
        this.workout = workout;
    }

    public void setupUI() {
        VBox content = view.getContent();
        content.getChildren().clear();

        Label header = new Label("Editing Workout: " + workout.getName());

        ComboBox<String> exerciseCombo = new ComboBox<>();
        ExerciseCatalog.getAllExercises().stream()
                .map(Exercise::getName)
                .forEach(exerciseCombo.getItems()::add);
        exerciseCombo.setPromptText("Select exercise");

        TextField repsField = new TextField(); repsField.setPromptText("Reps");
        TextField weightField = new TextField(); weightField.setPromptText("Weight");
        TextField setsField = new TextField(); setsField.setPromptText("Sets");

        Button addLiftBtn = new Button("Add Lift");
        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");

        HBox inputBox = new HBox(10, exerciseCombo, repsField, weightField, setsField, addLiftBtn);
        inputBox.setPadding(new Insets(10));

        ListView<String> listView = view.getLiftListView();
        refreshLiftList(listView);

        Button backBtn = new Button("Back to Workouts");
        backBtn.setOnAction(e -> app.showWorkoutView(stage));

        Button removeBtn = new Button("Remove Selected");
        removeBtn.setOnAction(e -> {
            int idx = listView.getSelectionModel().getSelectedIndex();
            if (idx >= 0) {
                String exName = workout.getLifts().get(idx).getExercise().getName();
                workout.removeLift(exName);
                refreshLiftList(listView);
            }
        });

        Button doneBtn = new Button("Done");
        doneBtn.setOnAction(e -> app.showWorkoutView(stage));

        addLiftBtn.setOnAction(e -> {
            String exName = exerciseCombo.getValue();
            String repsTxt = repsField.getText().trim();
            String wtTxt = weightField.getText().trim();
            String setsTxt = setsField.getText().trim();
            errorLabel.setText("");

            if (exName == null || repsTxt.isEmpty() || wtTxt.isEmpty() || setsTxt.isEmpty()) {
                errorLabel.setText("Fill in all fields");
                return;
            }

            try {
                int reps = Integer.parseInt(repsTxt);
                double wt = Double.parseDouble(wtTxt);
                int sets = Integer.parseInt(setsTxt);

                if (sets <= 0 || reps <= 0 || wt < 0) {
                    errorLabel.setText("Sets and reps must be positive; weight must be 0 or greater.");
                    return;
                }

                boolean added = workout.addLift(exName, reps, wt, sets);
                if (!added) {
                    errorLabel.setText("Could not add (duplicate or invalid exercise)");
                }

                refreshLiftList(listView);
                // Optionally clear inputs here
                exerciseCombo.setValue(null);
                repsField.clear();
                weightField.clear();
                setsField.clear();

            } catch (NumberFormatException ex) {
                errorLabel.setText("Invalid numbers.");
            }
        });

        VBox contentBox = view.getContent();
        contentBox.getChildren().addAll(
                new HBox(10, backBtn, removeBtn, doneBtn),
                header,
                inputBox,
                errorLabel,
                new Label("Current Lifts:"),
                listView
        );

        view.getRoot().getChildren().setAll(contentBox); // ✅ works if getRoot() returns VBox
    }

    private void refreshLiftList(ListView<String> listView) {
        listView.getItems().clear();
        workout.getLifts().forEach(lift -> {
            String line = String.format("%s — %dx%d @ %.1f lbs",
                    lift.getExercise().getName(), lift.getReps(), lift.getSets(), lift.getWeightInLbs());
            listView.getItems().add(line);
        });
    }

    public void refresh() {
        refreshLiftList(view.getLiftListView());
    }
}