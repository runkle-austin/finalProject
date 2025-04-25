package view;

import controller.HomeController;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomePageView {
    private final BorderPane root = new BorderPane();
    private final VBox content = new VBox(10);
    private final HomeController controller;
    private final TextField weightField = new TextField();
    private final Label confirmationLabel = new Label();

    public HomePageView(GUIView app, Stage stage) {
        content.setStyle("-fx-padding: 20;");
        this.controller = new HomeController(app, stage, this);

        Label welcomeLabel = new Label("Welcome To My Fitness App");

        Button workoutCyclesBtn = new Button("My Workout Cycles");
        workoutCyclesBtn.setOnAction(e -> controller.goToWorkoutCycles());

        Button myWorkoutsBtn = new Button("My Workouts");
        myWorkoutsBtn.setOnAction(e -> controller.goToWorkouts());

        Button calendarBtn = new Button("View Calendar");
        calendarBtn.setOnAction(e -> controller.goToCalendar());

        weightField.setPromptText("Enter your weight (lbs)");

        Button submitButton = new Button("Save Weight");
        submitButton.setOnAction(e -> controller.saveWeight(weightField.getText()));

        Button logoutBtn = new Button("Log Out");
        logoutBtn.setOnAction(e -> controller.logout());

        content.getChildren().addAll(
                welcomeLabel,
                workoutCyclesBtn,
                myWorkoutsBtn,
                calendarBtn,
                weightField,
                submitButton,
                confirmationLabel,
                logoutBtn
        );

        root.setCenter(content);
    }

    public Parent getView() {
        return root;
    }

    public void setConfirmationText(String message) {
        confirmationLabel.setText(message);
    }

    public void clearWeightInput() {
        weightField.clear();
    }
}