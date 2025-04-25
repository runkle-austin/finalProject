package view;

import controller.LiftEditController;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.User;
import model.Workout;
import observer.UserObserver;

public class LiftEditView implements UserObserver {
    private final VBox root = new VBox(15);
    private final VBox content = new VBox(10);
    private final LiftEditController controller;
    private final ListView<String> liftListView = new ListView<>();

    public LiftEditView(GUIView app, Stage stage, Workout workout) {
        root.setPadding(new Insets(20));
        liftListView.setPrefHeight(200);

        User user = app.getCurrentUser();
        user.addObserver(this);

        this.controller = new LiftEditController(app, stage, this, workout);
        controller.setupUI();
    }

    public Parent getView() {
        return root;
    }

    public VBox getContent() {
        return content;
    }

    public ListView<String> getLiftListView() {
        return liftListView;
    }

    @Override
    public void modelChanged() {
        controller.refresh();
    }

    public VBox getRoot() {
        return root;
    }
}