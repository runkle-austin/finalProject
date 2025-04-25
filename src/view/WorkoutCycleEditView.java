package view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.User;
import model.WorkoutCycle;
import controller.WorkoutCycleEditController;

import java.time.DayOfWeek;

public class WorkoutCycleEditView {
    private final VBox root = new VBox();
    private final VBox content = new VBox(15);
    private final WorkoutCycleEditController controller;

    public WorkoutCycleEditView(GUIView app, Stage stage, WorkoutCycle cycle) {
        this.controller = new WorkoutCycleEditController(app, stage, this, cycle);

        content.setPadding(new Insets(20));
        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        root.getChildren().add(scrollPane);

        controller.setupUI(); // Delegate UI population
    }

    public Parent getView() {
        return root;
    }

    public VBox getContent() {
        return content;
    }
}