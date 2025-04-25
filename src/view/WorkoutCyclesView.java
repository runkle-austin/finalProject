package view;

import controller.WorkoutCyclesController;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.User;
import model.WorkoutCycle;
import observer.UserObserver;

import java.util.ArrayList;

public class WorkoutCyclesView extends VBox implements UserObserver {
    private final VBox cycleList = new VBox(10);
    private WorkoutCyclesController controller;

    public WorkoutCyclesView(User user) {
        setSpacing(20);
        setPadding(new Insets(20));
        user.addObserver(this);

        Button backBtn = new Button("Back to Home");
        backBtn.setOnAction(e -> controller.handleBack());

        Button addCycleBtn = new Button("Add Workout Cycle");
        addCycleBtn.setOnAction(e -> controller.handleAddCycle());

        getChildren().addAll(backBtn, addCycleBtn, cycleList);
    }

    public void setController(WorkoutCyclesController controller) {
        this.controller = controller;
    }

    public void showCycles(ArrayList<WorkoutCycle> cycles) {
        cycleList.getChildren().clear();

        for (WorkoutCycle cycle : cycles) {
            HBox row = new HBox(10);
            row.setPadding(new Insets(5));

            Label name = new Label("Cycle: " + cycle.getName());
            Label weeks = new Label("Weeks: " + cycle.getNumberWeeks());

            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);

            Button viewBtn = new Button("Edit");
            Button setBtn = new Button("Set");
            Button removeBtn = new Button("Remove");

            viewBtn.setOnAction(e -> controller.handleEditCycle(cycle));
            setBtn.setOnAction(e -> controller.handleSetCycle(cycle));
            removeBtn.setOnAction(e -> controller.handleRemoveCycle(cycle));

            row.getChildren().addAll(name, weeks, spacer, viewBtn, setBtn, removeBtn);
            cycleList.getChildren().add(row);
        }
    }

    @Override
    public void modelChanged() {
        controller.refreshCycles();
    }
}