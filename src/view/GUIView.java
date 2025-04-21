/* MainApp.java */
package view;

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

    public void showHomePage(Stage stage, User user) {
        this.primaryStage = stage;
        FullLog model = user.getMyFullLog();
        HomePageView home = new HomePageView(model, this, stage);
        Scene scene = new Scene(home.getView(), 800, 600);
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