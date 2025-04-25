package controller;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.User;
import model.WorkoutCycle;
import view.GUIView;
import view.WorkoutCyclesView;

public class WorkoutCyclesController {
    private final GUIView app;
    private final Stage stage;
    private final User user;
    private final WorkoutCyclesView view;

    public WorkoutCyclesController(GUIView app, Stage stage, WorkoutCyclesView view) {
        this.app = app;
        this.stage = stage;
        this.user = app.getCurrentUser(); // correctly set
        this.view = view;
        this.view.setController(this);
        refreshCycles();
    }

    public void refreshCycles() {
        view.showCycles(user.getMyFullLog().getMyWorkoutCycles());
    }

    public void handleBack() {
        app.showHomePage(stage);
    }

    public void handleAddCycle() {
        app.showAddWorkoutCyclePage(stage);
    }

    public void handleEditCycle(WorkoutCycle cycle) {
        app.showWorkoutCycleEditView(stage, cycle);
    }

    public void handleSetCycle(WorkoutCycle cycle) {
        user.getMyFullLog().setActiveCycle(cycle);
        user.notifyObservers();
        app.showCalendarView(stage);
    }

    public void handleRemoveCycle(WorkoutCycle cycle) {
        if (cycle.equals(user.getMyFullLog().getActiveCycle())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cannot Remove Active Cycle");
            alert.setHeaderText(null);
            alert.setContentText("Set another cycle as active before removing this one.");
            alert.showAndWait();
        } else {
            user.getMyFullLog().removeWorkoutCycle(cycle);
            user.notifyObservers();
        }
    }
}