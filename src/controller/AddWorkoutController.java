package controller;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import model.Workout;
import view.AddWorkoutView;
import view.GUIView;

public class AddWorkoutController {
    private final GUIView app;
    private final Stage stage;
    private final AddWorkoutView view;
    private final User user;

    public AddWorkoutController(GUIView app, Stage stage, AddWorkoutView view, User user) {
        this.app = app;
        this.stage = stage;
        this.view = view;
        this.user = user;
    }

    public void setupUI() {
        VBox root = view.getRoot();
        root.getChildren().clear();

        Button backBtn = new Button("Back to Workouts");
        backBtn.setOnAction(e -> app.showWorkoutView(stage));

        Label nameLabel = new Label("Set Workout Name:");
        TextField nameField = view.getNameField();
        nameField.setPromptText("Enter workout name");

        Label errorLabel = view.getErrorLabel();
        errorLabel.setStyle("-fx-text-fill: red;");
        errorLabel.setText(""); // clear on load

        Button createBtn = new Button("Create Workout");
        createBtn.setOnAction(e -> {
            String workoutName = nameField.getText().trim();
            if (workoutName.isEmpty()) {
                errorLabel.setText("Please enter a workout name.");
                return;
            }

            Workout newWorkout = new Workout(workoutName);
            if (!user.getMyFullLog().checkDupWorkoutName(workoutName) && user.getMyFullLog().addWorkout(newWorkout)) {
                user.notifyObservers(); // Notify view updates
                app.showEditLiftsView(stage, newWorkout);
            } else {
                errorLabel.setText("Workout name already exists. Choose a different name.");
            }
        });

        root.getChildren().addAll(backBtn, nameLabel, nameField, createBtn, errorLabel);
    }

    public void refresh() {
        view.getErrorLabel().setText(""); // Clear errors on refresh
    }
}