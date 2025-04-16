package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;

public class GUIView extends Application {
    @Override
    public void start(Stage stage) {
        User dummyUser = new User("user", "pass"); // Optional: pre-fill test user
        LoginView loginView = new LoginView(stage, dummyUser);
        Scene scene = new Scene(loginView, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
