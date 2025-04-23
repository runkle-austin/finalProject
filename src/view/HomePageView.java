package view;

import controller.HomeController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.FullLog;
import model.User;

import java.time.LocalDate;

public class HomePageView {
    private BorderPane root;

    public HomePageView(GUIView app, Stage stage) {
        root = new BorderPane();
        VBox content = new VBox(10);
        content.setStyle("-fx-padding: 20;");

        Label welcomeLabel = new Label("Welcome To My Fitness App");

        Button logoutBtn = new Button("Log Out");
        HomeController controller = new HomeController(app, stage);
        logoutBtn.setOnAction(e -> controller.logout());

        // New buttons
        Button workoutCyclesBtn = new Button("My Workout Cycles");
        workoutCyclesBtn.setOnAction(e -> app.showWorkoutCyclesView(stage));

        Button myWorkoutsBtn = new Button("My Workouts");
        myWorkoutsBtn.setOnAction(e -> app.showWorkoutView(stage));

        Button calendarBtn = new Button("View Calendar");
        calendarBtn.setOnAction(e -> app.showCalendarView(stage)); // Assuming this method exists

        TextField weightField = new TextField();
        weightField.setPromptText("Enter your weight (lbs)");

        Button submitButton = new Button("Save Weight");
        Label confirmationLabel = new Label();

        submitButton.setOnAction(e -> {
            try {
                double weight = Double.parseDouble(weightField.getText());
                LocalDate date = LocalDate.now();  // or get from a DatePicker
                app.getCurrentUser().getMyFullLog().getMyWeightLog().put(date, weight);
                confirmationLabel.setText("Saved weight for " + date.toString() + ": " + weight + " lbs");
                weightField.clear();
                app.showWeightGraphView(stage);
            } catch (NumberFormatException ex) {
                confirmationLabel.setText("Please enter a valid number.");
            }
        });

        content.getChildren().addAll(
                welcomeLabel,
                workoutCyclesBtn,
                myWorkoutsBtn,
                calendarBtn,
                weightField,
                submitButton,
                logoutBtn
        );

        root.setCenter(content);
    }

    public BorderPane getView() {
        return root;
    }
}
