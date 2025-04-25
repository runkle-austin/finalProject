package controller;

import javafx.stage.Stage;
import model.User;
import view.GUIView;
import view.HomePageView;

import java.time.LocalDate;

public class HomeController {
    private final GUIView app;
    private final Stage stage;
    private final HomePageView view;

    public HomeController(GUIView app, Stage stage, HomePageView view) {
        this.app = app;
        this.stage = stage;
        this.view = view;
    }

    public void logout() {
        app.getUserDb().logout();
        app.showLogin(stage);
    }

    public void goToWorkoutCycles() {
        app.showWorkoutCyclesView(stage);
    }

    public void goToWorkouts() {
        app.showWorkoutView(stage);
    }

    public void goToCalendar() {
        app.showCalendarView(stage);
    }

    public void saveWeight(String weightInput) {
        try {
            double weight = Double.parseDouble(weightInput);
            LocalDate date = LocalDate.now();

            User currentUser = app.getCurrentUser();
            currentUser.getMyFullLog().getMyWeightLog().put(date, weight);
            currentUser.notifyObservers(); // optional

            view.setConfirmationText("Saved weight for " + date + ": " + weight + " lbs");
            view.clearWeightInput();
            app.showWeightGraphView(stage); // optional navigation
        } catch (NumberFormatException e) {
            view.setConfirmationText("Please enter a valid number.");
        }
    }
}