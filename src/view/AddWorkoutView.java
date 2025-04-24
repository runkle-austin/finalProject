package view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import model.Workout;

public class AddWorkoutView {
    private final VBox view;
    private final User user;
    private final GUIView app;
    private final Stage stage;

    public AddWorkoutView(GUIView app, Stage stage) {
        this.app   = app;
        this.stage = stage;
        this.user  = app.getCurrentUser();

        // Back button
        Button backBtn = new Button("Back to Workouts");
        backBtn.setOnAction(e -> app.showWorkoutView(stage));

        // Workout name input
        Label nameLabel = new Label("Set Workout Name:");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter workout name");

        // Error message
        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");

        // Create button
        Button createBtn = new Button("Create Workout");
        createBtn.setOnAction(e -> {
            String workoutName = nameField.getText().trim();
            if (workoutName.isEmpty()) {
                errorLabel.setText("Please enter a workout name.");
            } else {
                Workout newWorkout = new Workout(workoutName);

                // Only continue if adding the workout was successful
                if (user.getMyFullLog().addWorkout(newWorkout)) {
                    app.showEditLiftsView(stage, newWorkout);
                } else {
                    errorLabel.setText("Workout name already exists. Choose a different name.");
                }
            }
        });

        view = new VBox(10,
                backBtn,
                nameLabel,
                nameField,
                createBtn,
                errorLabel
        );
        view.setPadding(new Insets(20));
    }


    public Parent getView() {
        return view;
    }
}
