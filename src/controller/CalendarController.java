package controller;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.*;
import view.CalendarView;
import view.CalendarWorkoutView;
import view.GUIView;

import java.time.*;
import java.util.HashMap;

public class CalendarController {
    private final GUIView app;
    private final Stage stage;
    private final CalendarView view;
    private final User user;
    private final GridPane calendar = new GridPane();
    private final YearMonth currentMonth = YearMonth.now();
    private boolean showInLbs = true;

    public CalendarController(GUIView app, Stage stage, CalendarView view, User user) {
        this.app = app;
        this.stage = stage;
        this.view = view;
        this.user = user;
    }

    public void setupUI() {
        VBox root = view.getRoot();
        root.getChildren().clear();

        calendar.setPadding(new Insets(20));
        calendar.setHgap(10);
        calendar.setVgap(10);

        Button backBtn = new Button("Back to Home");
        backBtn.setOnAction(e -> app.showHomePage(stage));

        Button toggleUnitBtn = new Button("Show weight in KG");
        toggleUnitBtn.setOnAction(e -> {
            showInLbs = !showInLbs;
            toggleUnitBtn.setText(showInLbs ? "Show weight in KG" : "Show weight in LBS");
            refresh();
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox topBar = new HBox(10, backBtn, spacer, toggleUnitBtn);
        topBar.setPadding(new Insets(0, 0, 10, 0));
        topBar.setStyle("-fx-alignment: top-right;");

        root.getChildren().addAll(topBar, calendar);
        populateCalendar();
    }

    public void refresh() {
        populateCalendar();
    }

    private void populateCalendar() {
        calendar.getChildren().clear();

        int daysInMonth = currentMonth.lengthOfMonth();
        LocalDate firstDay = currentMonth.atDay(1);
        int dayOfWeek = firstDay.getDayOfWeek().getValue(); // 1=Mon, 7=Sun

        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        for (int i = 0; i < days.length; i++) {
            Label lbl = new Label(days[i]);
            lbl.setFont(Font.font(16));
            calendar.add(lbl, i, 0);
        }

        WorkoutCycle cycle = user.getMyFullLog().getActiveCycle();
        HashMap<Integer, Workout> workoutsForMonth = new HashMap<>();

        if (cycle != null) {
            cycle.createFullCycle();
            var fullCycle = cycle.getFullCycleData();
            int currentDay = 1;

            for (var week : fullCycle) {
                for (int i = 0; i < 7 && currentDay <= daysInMonth; i++) {
                    DayOfWeek day = DayOfWeek.of(i + 1);
                    if (week.containsKey(day)) {
                        workoutsForMonth.put(currentDay, week.get(day));
                    }
                    currentDay++;
                }
            }
        }

        int row = 1;
        int col = dayOfWeek - 1;

        for (int day = 1; day <= daysInMonth; day++) {
            VBox dayBox = new VBox();
            dayBox.setPadding(new Insets(5));
            dayBox.setPrefSize(100, 80);
            dayBox.setStyle("-fx-border-color: gray; -fx-background-color: #f9f9f9;");

            Button dayButton = new Button(String.valueOf(day));
            dayButton.setFont(Font.font(14));
            dayButton.setMaxWidth(Double.MAX_VALUE);

            if (workoutsForMonth.containsKey(day)) {
                Workout todayWorkout = workoutsForMonth.get(day);
                Label label = new Label(todayWorkout.getName());
                label.setFont(Font.font(12));

                dayButton.setOnAction(e -> openWorkoutDetails(todayWorkout));
                dayBox.getChildren().addAll(dayButton, label);
            } else {
                dayBox.getChildren().add(dayButton);
            }

            calendar.add(dayBox, col, row);
            col++;
            if (col == 7) {
                col = 0;
                row++;
            }
        }
    }

    private void openWorkoutDetails(Workout workout) {
        Stage detailsStage = new Stage();
        CalendarWorkoutView workoutView = new CalendarWorkoutView(user, workout, showInLbs);
        Scene scene = new Scene(workoutView.getView(), 400, 300);
        detailsStage.setTitle("Workout Details");
        detailsStage.setScene(scene);
        detailsStage.show();
    }
}