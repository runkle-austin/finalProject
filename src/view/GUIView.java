package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUIView extends Application {
    @Override
    public void start(Stage stage) {
        DashBoardView dash = new DashBoardView();
        Scene scene = new Scene(dash.getView(), 800, 600);
        stage.setScene(scene);
        stage.setTitle("Workout Dashboard");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
