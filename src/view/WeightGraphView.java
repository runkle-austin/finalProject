package view;

import controller.WeightGraphController;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import observer.UserObserver;

public class WeightGraphView implements UserObserver {
    private final VBox root = new VBox(10);
    private final WeightGraphController controller;

    public WeightGraphView(GUIView app, Stage stage) {
        User user = app.getCurrentUser();
        user.addObserver(this);

        root.setPadding(new Insets(20));

        this.controller = new WeightGraphController(app, stage, this, user);
        controller.setupUI();
    }

    public Parent getView() {
        return root;
    }

    public VBox getContent() {
        return root;
    }

    @Override
    public void modelChanged() {
        controller.refresh();
    }
}