package view;

import controller.WorkoutEditController;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import model.Workout;
import observer.UserObserver;

public class WorkoutEditView implements UserObserver {
    private final VBox root = new VBox();
    private final VBox content = new VBox(15);
    private final WorkoutEditController controller;

    public WorkoutEditView(GUIView app, Stage stage, Workout workout) {
        content.setPadding(new Insets(20));
        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        root.getChildren().add(scrollPane);

        User user = app.getCurrentUser();
        user.addObserver(this);

        this.controller = new WorkoutEditController(app, stage, this, workout);
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
        controller.refresh(); // Rebuilds content
    }
}