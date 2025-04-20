package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.FullLog;
import model.Workout;
import model.WorkoutCycle;
import observer.WorkoutObserver;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;

public class DashBoardView implements WorkoutObserver {
    private final GridPane calendar;
    private final YearMonth currentMonth;
    private final FullLog model;

    public DashBoardView(FullLog model) {
        this.model = model;
        this.model.addObserver(this);
        calendar = new GridPane();
        calendar.setPadding(new Insets(20));
        calendar.setHgap(10);
        calendar.setVgap(10);
        currentMonth = YearMonth.now();
        populateCalendar();
    }

    public GridPane getView() {
        return calendar;
    }

    private void populateCalendar() {
        calendar.getChildren().clear();

        int daysInMonth = currentMonth.lengthOfMonth();
        LocalDate firstDay = currentMonth.atDay(1);
        int dayOfWeek = firstDay.getDayOfWeek().getValue(); // 1=Monday, 7=Sunday

        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        for (int i = 0; i < days.length; i++) {
            Label lbl = new Label(days[i]);
            lbl.setFont(Font.font(16));
            calendar.add(lbl, i, 0);
        }

        int row = 1;
        int col = dayOfWeek - 1;

        WorkoutCycle cycle = model.getActiveCycle();
        HashMap<Integer, Workout> workoutsForMonth = new HashMap<>();

        if (cycle != null) {
            int currentDayOfMonth = 1;
            int totalDays = currentMonth.lengthOfMonth();
            var fullCycle = cycle.getFullCycleData();
            for (var week : fullCycle) {
                for (int i = 0; i < 7 && currentDayOfMonth <= totalDays; i++) {
                    DayOfWeek day = DayOfWeek.of((i + 1));
                    if (week.containsKey(day)) {
                        workoutsForMonth.put(currentDayOfMonth, week.get(day));
                    }
                    currentDayOfMonth++;
                }
            }
        }

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
                Label workoutLabel = new Label(todayWorkout.getName());
                workoutLabel.setFont(Font.font(12));
                workoutLabel.setWrapText(true);

                dayButton.setOnAction(e -> openWorkoutDetails(todayWorkout));
                dayBox.getChildren().addAll(dayButton, workoutLabel);
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
        WorkoutDetailsView view = new WorkoutDetailsView(workout);
        Scene scene = new Scene(view.getView(), 400, 300);
        detailsStage.setTitle("Workout Details");
        detailsStage.setScene(scene);
        detailsStage.show();
    }

    @Override
    public void modelChanged() {
        populateCalendar();
    }
}