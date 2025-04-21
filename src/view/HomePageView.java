package view;

import controller.HomeController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.FullLog;

public class HomePageView {
    private BorderPane root;

    public HomePageView(FullLog log, GUIView app, Stage stage) {
        root = new BorderPane();
        VBox content = new VBox(10);
        content.setStyle("-fx-padding: 20;");

        Label welcomeLabel = new Label("Welcome To My Fitness App");
        Label workoutCount = new Label("Total Workouts: " + log.getMyWorkouts().size());

        Button logoutBtn = new Button("Log Out");
        HomeController controller = new HomeController(app, stage);
        logoutBtn.setOnAction(e -> controller.logout());

        content.getChildren().addAll(welcomeLabel, workoutCount, logoutBtn);
        root.setCenter(content);
    }

    public BorderPane getView() {
        return root;
    }
}

