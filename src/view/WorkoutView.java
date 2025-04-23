package view;

import javafx.geometry.Insets;
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

    public WorkoutView(GUIView app, Stage stage) {
        this.user = app.getCurrentUser();

        // Back Button
        Button backBtn = new Button("Back to Home");
        backBtn.setOnAction(e -> app.showHomePage(stage));



        // VBox to hold all workout cycles
        VBox cycleList = new VBox(10);
        cycleList.setPadding(new Insets(10));

        ArrayList<Workout> myWorkouts = user.getMyFullLog().getMyWorkouts();

        for (Workout workout : myWorkouts) {
            HBox row = new HBox(15);
            Label name = new Label("Workout: " + workout.getName());
            Button viewBtn = new Button("View");

            // hook up later: maybe open a detail view
            //viewBtn.setOnAction(e -> app.showWorkoutDetailsView(stage, workout));

            row.getChildren().addAll(name, viewBtn);
            cycleList.getChildren().add(row);
        }

        view = new VBox(20);
        view.setPadding(new Insets(20));
        view.getChildren().addAll(backBtn, cycleList);
    }

    public VBox getView() {
        return view;
    }
}
