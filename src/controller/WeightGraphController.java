package controller;

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
import view.GUIView;
import view.WeightGraphView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class WeightGraphController {
    private final GUIView app;
    private final Stage stage;
    private final WeightGraphView view;
    private final User user;

    public WeightGraphController(GUIView app, Stage stage, WeightGraphView view, User user) {
        this.app = app;
        this.stage = stage;
        this.view = view;
        this.user = user;
    }

    public void setupUI() {
        VBox root = view.getContent();
        root.getChildren().clear();

        Button backBtn = new Button("Back to Home");
        backBtn.setOnAction(e -> app.showHomePage(stage));

        Button showGraphBtn = new Button("Show Weight Graph");
        showGraphBtn.setOnAction(e -> showGraph());

        root.getChildren().addAll(backBtn, showGraphBtn);
    }

    public void showGraph() {
        Stage graphStage = new Stage();
        graphStage.setTitle("Weight Progress");

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Date");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Weight (lbs)");

        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Daily Weight Log");
        lineChart.setLegendVisible(false);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        Map<LocalDate, Double> weightLog = user.getMyFullLog().getMyWeightLog();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd");

        for (Map.Entry<LocalDate, Double> entry : weightLog.entrySet()) {
            String dateLabel = formatter.format(entry.getKey());
            XYChart.Data<String, Number> dataPoint = new XYChart.Data<>(dateLabel, entry.getValue());
            series.getData().add(dataPoint);

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

    public void refresh() {
        setupUI(); // Just rebuilds buttons — graph is opened in a new stage
    }
}