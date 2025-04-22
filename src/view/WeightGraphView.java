package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;

public class WeightGraphView {
    private final VBox view;
    private final User user;

    public WeightGraphView(GUIView app, Stage stage) {
        this.user = app.getCurrentUser();

        // Back Button
        Button backBtn = new Button("Back to Home");
        backBtn.setOnAction(e -> app.showHomePage(stage));

        // Show Graph Button
        Button showGraphBtn = new Button("Show Weight Graph");
        showGraphBtn.setOnAction(e -> showGraph());

        // VBox setup (like CalendarView)
        view = new VBox(10);
        view.setPadding(new Insets(20));
        view.getChildren().addAll(backBtn, showGraphBtn);
    }

    public VBox getView() {
        return view;
    }

    private void showGraph() {
        Stage graphStage = new Stage();
        graphStage.setTitle("Weight Progress");

        //  Change NumberAxis to CategoryAxis for the x-axis
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Date");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Weight (lbs)");

        // Update LineChart generic types accordingly
        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Daily Weight Log");
        lineChart.setLegendVisible(false);

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        Map<LocalDate, Double> weightLog = user.getMyFullLog().getMyWeightLog();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd"); // e.g., Apr 21

        for (Map.Entry<LocalDate, Double> entry : weightLog.entrySet()) {
            String dateLabel = formatter.format(entry.getKey());
            XYChart.Data<String, Number> dataPoint = new XYChart.Data<>(dateLabel, entry.getValue());

            series.getData().add(dataPoint);

            // Add tooltip after node creation
            dataPoint.nodeProperty().addListener((obs, oldNode, newNode) -> {
                if (newNode != null) {
                    Tooltip tooltip = new Tooltip(dateLabel + ": " + entry.getValue() + " lbs");
                    Tooltip.install(newNode, tooltip);
                }
            });
        }

        lineChart.getData().add(series);

        Scene scene = new Scene(lineChart, 800, 600);
        graphStage.setScene(scene);
        graphStage.show();
    }

}
