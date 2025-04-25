package view;

import controller.CalendarController;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import model.User;
import observer.UserObserver;

public class CalendarView implements UserObserver {
    private final VBox root = new VBox(10);
    private final CalendarController controller;

    public CalendarView(GUIView app, javafx.stage.Stage stage) {
        root.setPadding(new Insets(20));
        User user = app.getCurrentUser();
        user.addObserver(this);

        this.controller = new CalendarController(app, stage, this, user);
        controller.setupUI();
    }

    public Parent getView() {
        return root;
    }

    public VBox getRoot() {
        return root;
    }

    @Override
    public void modelChanged() {
        controller.refresh();
    }
}