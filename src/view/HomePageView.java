package view;

import controller.HomeController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.FullLog;
import model.User;

public class HomePageView {
    private BorderPane root;

    public HomePageView(GUIView app, Stage stage) {
        root = new BorderPane();
        VBox content = new VBox(10);
        content.setStyle("-fx-padding: 20;");

        Label welcomeLabel = new Label("Welcome To My Fitness App");
        Label workoutCount = new Label("Total Workouts: " + app.getCurrentUser().getMyFullLog().getMyWorkouts().size());

        Button logoutBtn = new Button("Log Out");
        HomeController controller = new HomeController(app, stage);
        logoutBtn.setOnAction(e -> controller.logout());

        // New buttons
        Button workoutCyclesBtn = new Button("My Workout Cycles");
        workoutCyclesBtn.setOnAction(e -> controller.showWorkoutCycles());

        Button myWorkoutsBtn = new Button("My Workouts");
        myWorkoutsBtn.setOnAction(e -> controller.showMyWorkouts());

        Button calendarBtn = new Button("View Calendar");
        calendarBtn.setOnAction(e -> app.showCalendarView(stage)); // Assuming this method exists

        content.getChildren().addAll(
                welcomeLabel,
                workoutCount,
                workoutCyclesBtn,
                myWorkoutsBtn,
                calendarBtn,
                logoutBtn
        );

        root.setCenter(content);
    }

    public BorderPane getView() {
        return root;
    }
}
