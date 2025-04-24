package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import model.WorkoutCycle;
import java.util.ArrayList;

public class WorkoutCyclesView {
    private final VBox view;
    private final User user;

    public WorkoutCyclesView(GUIView app, Stage stage) {
        this.user = app.getCurrentUser();

        // Back Button
        Button backBtn = new Button("Back to Home");
        backBtn.setOnAction(e -> app.showHomePage(stage));

        // VBox to hold all workout cycles
        VBox cycleList = new VBox(10);
        cycleList.setPadding(new Insets(10));

        ArrayList<WorkoutCycle> cycles = user.getMyFullLog().getMyWorkoutCycles();

        for (WorkoutCycle cycle : cycles) {
            HBox row = new HBox(15);
            Label name = new Label("Cycle: " + cycle.getName());
            Label weeks = new Label("Weeks: " + cycle.getNumberWeeks());

            // View the Workout Cycle
            Button viewBtn = new Button("View");
            viewBtn.setOnAction(e -> app.showWorkoutCycleDetailView(stage, cycle));

            // Set a Workout Cycle as Active Cycle
            Button setCycleBtn = new Button("Set Workout Cycle");
            setCycleBtn.setOnAction(e -> {
                user.getMyFullLog().setActiveCycle(cycle);
                user.notifyObservers();
                app.showCalendarView(stage);});

            row.getChildren().addAll(name, weeks, viewBtn,  setCycleBtn);
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