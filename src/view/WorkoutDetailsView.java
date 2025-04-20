package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.LiftData;
import model.Workout;

public class WorkoutDetailsView {
    private final VBox layout;

    public WorkoutDetailsView(Workout workout) {
        layout = new VBox(10);
        layout.setPadding(new Insets(15));

        Label title = new Label("Workout: " + workout.getName());
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        layout.getChildren().add(title);

        for (LiftData lift : workout.getLifts()) {
            String text = String.format(
                    "%s — %d sets of %d reps @ %.1f lbs",
                    lift.getExercise().getName(),
                    lift.getSets(),
                    lift.getReps(),
                    lift.getWeightInLbs()
            );
            layout.getChildren().add(new Label(text));
        }
    }

    public VBox getView() {
        return layout;
    }
}
