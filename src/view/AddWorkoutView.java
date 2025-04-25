package view;

import controller.AddWorkoutController;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import observer.UserObserver;

public class AddWorkoutView implements UserObserver {
    private final VBox root = new VBox(10);
    private final AddWorkoutController controller;
    private final TextField nameField = new TextField();
    private final Label errorLabel = new Label();

    public AddWorkoutView(GUIView app, Stage stage) {
        root.setPadding(new Insets(20));
        User user = app.getCurrentUser();
        user.addObserver(this);

        this.controller = new AddWorkoutController(app, stage, this, user);
        controller.setupUI();
    }

    public Parent getView() {
        return root;
    }

    public VBox getRoot() {
        return root;
    }

    public TextField getNameField() {
        return nameField;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    @Override
    public void modelChanged() {
        controller.refresh();
    }
}