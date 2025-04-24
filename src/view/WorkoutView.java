package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import model.Workout;

public class WorkoutView {
    private final VBox view;
    private final User user;
    private final Stage stage;
    private final GUIView app;

    public WorkoutView(GUIView app, Stage stage) {
        this.app   = app;
        this.stage = stage;
        this.user  = app.getCurrentUser();

        // Back to Home
        Button backBtn = new Button("Back to Home");
        backBtn.setOnAction(e -> app.showHomePage(stage));

        // Your Add-Workout button
        Button addWorkoutButton = new Button("Add Workout");
        addWorkoutButton.setOnAction(e -> app.showAddWorkoutPage(stage));

        // Container for workouts
        VBox cycleList = new VBox(10);
        cycleList.setPadding(new Insets(10));
        // populate *right now* from the log
        for (Workout w : user.getMyFullLog().getMyWorkouts()) {
            HBox row = new HBox(15);
            row.setPadding(new Insets(5));

            Label workoutLabel = new Label("Workout: " + w.getName());

            Button viewButton = new Button("Edit");
            viewButton.setOnAction(e -> app.showWorkoutDetailsView(stage, w));

            Button removeButton = new Button("Remove");
            removeButton.setOnAction(e -> {
                user.getMyFullLog().removeWorkout(w);
                cycleList.getChildren().remove(row);
            });

            Region spacer = new Region();
            HBox.setHgrow(spacer, javafx.scene.layout.Priority.ALWAYS);

            row.getChildren().addAll(workoutLabel, spacer, viewButton, removeButton);
            cycleList.getChildren().add(row);
        }

        view = new VBox(20, backBtn, addWorkoutButton, cycleList);
        view.setPadding(new Insets(20));
    }

    public VBox getView() {
        return view;
    }
}