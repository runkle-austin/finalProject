package controller;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import model.Workout;
import view.GUIView;
import view.WorkoutView;

public class WorkoutController {
    private final GUIView app;
    private final Stage stage;
    private final WorkoutView view;
    private final User user;

    public WorkoutController(GUIView app, Stage stage, WorkoutView view, User user) {
        this.app = app;
        this.stage = stage;
        this.view = view;
        this.user = user;
    }

    public void setupUI() {
        VBox content = view.getContent();
        content.getChildren().clear();

        // Navigation Buttons
        Button backBtn = new Button("Back to Home");
        backBtn.setOnAction(e -> app.showHomePage(stage));

        Button addWorkoutBtn = new Button("Add Workout");
        addWorkoutBtn.setOnAction(e -> app.showAddWorkoutPage(stage));

        VBox workoutList = new VBox(10);
        workoutList.setPadding(new Insets(10));

        for (Workout w : user.getMyFullLog().getMyWorkouts()) {
            HBox row = new HBox(15);
            row.setPadding(new Insets(5));

            Label label = new Label("Workout: " + w.getName());

            Button editBtn = new Button("Edit");
            editBtn.setOnAction(e -> app.showWorkoutDetailsView(stage, w));

            Button removeBtn = new Button("Remove");
            removeBtn.setOnAction(e -> {
                user.getMyFullLog().removeWorkout(w);
                user.notifyObservers(); // Notify the view
            });

            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);

            row.getChildren().addAll(label, spacer, editBtn, removeBtn);
            workoutList.getChildren().add(row);
        }

        content.getChildren().addAll(backBtn, addWorkoutBtn, workoutList);
    }

    public void refresh() {
        setupUI(); // Redraw the list
    }
}