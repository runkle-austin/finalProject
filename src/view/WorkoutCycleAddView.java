package view;

import controller.WorkoutCycleAddController;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import observer.UserObserver;

public class WorkoutCycleAddView implements UserObserver {
    private final VBox root = new VBox();
    private final VBox content = new VBox(15); // spacing between children
    private final WorkoutCycleAddController controller;

    public WorkoutCycleAddView(GUIView app, Stage stage) {
        content.setPadding(new Insets(20)); // ✅ add this back for visual padding

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        root.getChildren().add(scrollPane);

        User user = app.getCurrentUser();
        user.addObserver(this); // Register as observer

        this.controller = new WorkoutCycleAddController(app, stage, this, user);
        controller.setupUI();
    }

    public Parent getView() {
        return root;
    }

    public VBox getContent() {
        return content;
    }

    @Override
    public void modelChanged() {
        controller.refresh(); // Redraw UI if model updates
    }
}