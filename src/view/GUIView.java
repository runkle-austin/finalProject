/* MainApp.java */
package view;

import controller.WorkoutController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;
import model.UserDatabase;
import model.*;

public class GUIView extends Application {
    private UserDatabase userDb = new UserDatabase();
    private Stage primaryStage;

    @Override
    public void start(Stage stage) {
        // Load persisted users
        userDb.loadUsers();
        showLogin(stage);
    }

    public void showLogin(Stage stage) {
        LoginView loginView = new LoginView(this, stage);
        Scene scene = new Scene(loginView, 350, 250);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }

    public void showCreateAccount(Stage stage) {
        CreateAccountView createView = new CreateAccountView(this, stage);
        Scene scene = new Scene(createView, 350, 300);
        stage.setScene(scene);
        stage.setTitle("Create Account");
        stage.show();
    }

    public void showDashboard(Stage stage, User user) {
        // Initialize your main application view with the authenticated user
        FullLog model = user.getMyFullLog();
        WorkoutController controller = new WorkoutController(model);
        DashBoardView dashboard = new DashBoardView(model);
        // build and show the dashboard
        Scene scene = new Scene(dashboard.getView(), 800, 600);
        stage.setScene(scene);
        stage.setTitle("Dashboard - " + user.getUserName());
        stage.show();
    }

    public UserDatabase getUserDb() {
        return userDb;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void stop() {
        // Save users on exit
        userDb.logout();
    }

    public static void main(String[] args) {
        launch(args);
    }
}