package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.LiftData;
import model.Workout;

public class CalendarWorkoutView {
    private final VBox layout;

    public CalendarWorkoutView(Workout workout, boolean showInLbs) {
        layout = new VBox(10);
        layout.setPadding(new Insets(15));

        Label title = new Label("Workout: " + workout.getName());
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        layout.getChildren().add(title);

        // toggle between kg and lbs
        String kgVsLbs;
        if (showInLbs) { kgVsLbs = "lbs";}
        else { kgVsLbs = "kg";}

        for (LiftData lift : workout.getLifts()) {
            String text = String.format(
                    "%s — %d sets of %d reps @ %.1f " + kgVsLbs,
                    lift.getExercise().getName(),
                    lift.getSets(),
                    lift.getReps(),
                    lift.getWeight(showInLbs)
            );
            layout.getChildren().add(new Label(text));
        }
    }

    public VBox getView() {
        return layout;
    }
}
