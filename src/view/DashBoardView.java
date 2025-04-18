package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;

public class DashBoardView {
    private final GridPane calendar;
    private final YearMonth currentMonth;
    private final HashMap<Integer, String> workouts;

    public DashBoardView() {
        calendar = new GridPane();
        calendar.setPadding(new Insets(20));
        calendar.setHgap(10);
        calendar.setVgap(10);
        currentMonth = YearMonth.now();

        // Example workout data
        workouts = new HashMap<>();
        workouts.put(2, "Leg Day\nSquats + Lunges");
        workouts.put(5, "Push\nBench + Overhead Press");
        workouts.put(9, "Pull\nRows + Deadlifts");
        workouts.put(15, "Rest");
        workouts.put(18, "Cardio\n5k run");

        populateCalendar();
    }

    public GridPane getView() {
        return calendar;
    }

    private void populateCalendar() {
        int daysInMonth = currentMonth.lengthOfMonth();
        LocalDate firstDay = currentMonth.atDay(1);
        int dayOfWeek = firstDay.getDayOfWeek().getValue(); // 1=Monday, 7=Sunday

        // Add weekday headers
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        for (int i = 0; i < days.length; i++) {
            Label lbl = new Label(days[i]);
            lbl.setFont(Font.font(16));
            calendar.add(lbl, i, 0);
        }

        // Add day cells
        int row = 1;
        int col = dayOfWeek - 1;

        for (int day = 1; day <= daysInMonth; day++) {
            VBox dayBox = new VBox();
            dayBox.setPadding(new Insets(5));
            dayBox.setPrefSize(100, 80);
            dayBox.setStyle("-fx-border-color: gray; -fx-background-color: #f9f9f9;");

            Label dayNum = new Label(String.valueOf(day));
            dayNum.setFont(Font.font(14));
            dayBox.getChildren().add(dayNum);

            if (workouts.containsKey(day)) {
                Label workoutLabel = new Label(workouts.get(day));
                workoutLabel.setFont(Font.font(12));
                workoutLabel.setWrapText(true);
                dayBox.getChildren().add(workoutLabel);
            }

            calendar.add(dayBox, col, row);
            col++;
            if (col == 7) {
                col = 0;
                row++;
            }
        }
    }
}
