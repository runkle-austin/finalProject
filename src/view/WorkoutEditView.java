package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.LiftData;
import model.Workout;

public class WorkoutEditView {
    private final VBox root;  // this becomes the thing returned by getView()

    public WorkoutEditView(GUIView app, Stage stage, Workout workout) {
        VBox content = new VBox(15);
        content.setPadding(new Insets(20));

        // Title of Workout
        Label title = new Label("Workout: " + workout.getName());
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-underline: true;");

        // Show each exercise and lift data
        for (LiftData liftData : workout.getLifts()) {
            String text = String.format("%s — %d sets, %d reps, %.1f lbs",
                    liftData.getName(), liftData.getSets(), liftData.getReps(), liftData.getWeightInLbs());

            Label liftLabel = new Label(text);
            Button removeButton = new Button("Remove");

            HBox row = new HBox(15); // ← matches your style
            row.setPadding(new Insets(5));
            row.getChildren().addAll(liftLabel, removeButton);

            removeButton.setOnAction(e -> {
                workout.removeLift(liftData.getName());
                content.getChildren().remove(row);
            });

            content.getChildren().add(row);
        }
        content.getChildren().add(new Text("\n"));

        // Back button
        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> app.showWorkoutView(stage));
        content.getChildren().add(backBtn);

        // Wrap in a scroll pane
        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);

        // Final root VBox that holds the scroll pane
        root = new VBox(scrollPane);
    }

    public VBox getView() {
        return root;
    }
}