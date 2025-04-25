package view;

import controller.CalendarWorkoutController;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.User;
import model.Workout;
import observer.UserObserver;

public class CalendarWorkoutView implements UserObserver {
    private final VBox root = new VBox(10);
    private final CalendarWorkoutController controller;

    public CalendarWorkoutView(User user, Workout workout, boolean showInLbs) {
        root.setPadding(new Insets(15));
        user.addObserver(this); // listen for updates

        this.controller = new CalendarWorkoutController(this, workout, showInLbs);
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