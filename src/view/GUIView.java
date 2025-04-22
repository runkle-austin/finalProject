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
    private User currentUser;
    private String username;

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

    public void showHomePage(Stage stage) {
        this.primaryStage = stage;
        HomePageView home = new HomePageView(this, stage);
        Scene scene = new Scene(home.getView(), 800, 600);
        stage.setScene(scene);
        stage.setTitle("Home Page - " + username);
        stage.show();
    }

    public void showCalendarView(Stage stage) {
        CalendarView calendarView = new CalendarView(this, stage);
        Scene scene = new Scene(calendarView.getView(), 800, 600);
        stage.setTitle("Workout Calendar");
        stage.setScene(scene);
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

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public void setCurrentUserName(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}