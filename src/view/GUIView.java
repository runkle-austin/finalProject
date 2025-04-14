package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GUIView extends Application {

    @Override
    public void start(Stage stage) {
        Label label = new Label("🎉 JavaFX is working!");
        StackPane root = new StackPane(label);
        Scene scene = new Scene(root, 300, 200);

        stage.setTitle("GUIView Test Window");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
