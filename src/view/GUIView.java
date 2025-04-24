/* MainApp.java */
package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
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

    public void showWeightGraphView(Stage stage) {
        WeightGraphView weightGraphView = new WeightGraphView(this, stage);
        Scene scene = new Scene(weightGraphView.getView(), 800, 600);
        stage.setTitle("Weight Graph");
        stage.setScene(scene);
        stage.show();
    }

    public void showWorkoutCyclesView(Stage stage) {
        WorkoutCyclesView workoutCyclesView = new WorkoutCyclesView(this, stage);
        Scene scene = new Scene(workoutCyclesView.getView(), 800, 600);
        stage.setTitle("My Workout Cycles");
        stage.setScene(scene);
        stage.show();
    }

    public void showWorkoutCycleEditView(Stage stage, WorkoutCycle cycle) {
        WorkoutCycleEditView detailView = new WorkoutCycleEditView(this, stage, cycle);
        Scene scene = new Scene(detailView.getView(), 800, 600);
        stage.setTitle("Cycle: " + cycle.getName());
        stage.setScene(scene);
        stage.show();
    }

    public void showWorkoutView(Stage stage) {
        WorkoutView workoutView = new WorkoutView(this, stage);
        Scene scene = new Scene(workoutView.getView(), 800, 600);
        stage.setTitle("My Workouts");
        stage.setScene(scene);
        stage.show();
    }

    public void showWorkoutDetailsView(Stage stage, Workout workout) {
        WorkoutEditView workoutEditView = new WorkoutEditView(this, stage, workout);
        Scene scene = new Scene(workoutEditView.getView(), 800, 600);
        stage.setTitle("Workout Details");
        stage.setScene(scene);
        stage.show();
    }

    // Helper Method to Show Add Workout Page
    public void showAddWorkoutPage(Stage stage) {
        AddWorkoutView addWorkoutView = new AddWorkoutView(this, stage);
        Scene scene = new Scene(addWorkoutView.getView(), 800, 600);
        stage.setTitle("Add Workout");
        stage.setScene(scene);
        stage.show();
    }

    /*public void showAddWorkoutCyclePage(Stage stage) {
        AddWorkoutCycleView addWorkoutCycleView = new AddWorkoutCycleView(this, stage);
        Scene scene = new Scene(addWorkoutCycleView.getView(), 800, 600);
        stage.setTitle("Add Workout Cycle");
        stage.setScene(scene);
        stage.show();
    }
     */

    // Helper Method to Show Edit Lifts View Page
    public void showEditLiftsView(Stage stage, Workout workout) {
        EditLiftsView editLiftsView = new EditLiftsView(this, stage, workout);
        Scene scene = new Scene(editLiftsView.getView(), 800, 600);
        stage.setTitle("Edit Lifts");
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