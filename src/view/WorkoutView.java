package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import model.Workout;
import model.WorkoutCycle;
import java.util.ArrayList;

public class WorkoutView {
    private final VBox view;
    private final User user;
    private final Stage stage;
    private final GUIView app;

    public WorkoutView(GUIView app, Stage stage) {
        this.app   = app;
        this.stage = stage;
        this.user  = app.getCurrentUser();

        // Debug Statement to see if Workouts are Added Properly (Will Delete Later)
        System.out.println(">> WorkoutView: My Workouts Size = " + user.getMyFullLog().getMyWorkouts().size());
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
            row.getChildren().addAll(
                    new Label("Workout: " + w.getName()),
                    new Button("View") {{
                        setOnAction(e -> app.showWorkoutDetailsView(stage, w));
                    }}
            );
            cycleList.getChildren().add(row);
        }

        view = new VBox(20, backBtn, addWorkoutButton, cycleList);
        view.setPadding(new Insets(20));
    }

    public VBox getView() {
        return view;
    }
}