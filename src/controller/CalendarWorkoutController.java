package controller;

import javafx.scene.control.Label;
import model.LiftData;
import model.Workout;
import view.CalendarWorkoutView;

public class CalendarWorkoutController {
    private final CalendarWorkoutView view;
    private final Workout workout;
    private final boolean showInLbs;

    public CalendarWorkoutController(CalendarWorkoutView view, Workout workout, boolean showInLbs) {
        this.view = view;
        this.workout = workout;
        this.showInLbs = showInLbs;
    }

    public void setupUI() {
        var root = view.getRoot();
        root.getChildren().clear();

        Label title = new Label("Workout: " + workout.getName());
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        root.getChildren().add(title);

        String unit = showInLbs ? "lbs" : "kg";
        for (LiftData lift : workout.getLifts()) {
            String text = String.format(
                    "%s — %d sets of %d reps @ %.1f %s",
                    lift.getExercise().getName(),
                    lift.getSets(),
                    lift.getReps(),
                    lift.getWeight(showInLbs),
                    unit
            );
            root.getChildren().add(new Label(text));
        }
    }

    public void refresh() {
        setupUI(); // Rebuild UI on model update
    }
}