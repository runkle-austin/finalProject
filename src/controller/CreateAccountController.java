package controller;

import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;
import view.LoginView;

public class CreateAccountController {
    private Stage stage;

    public CreateAccountController(Stage stage) {
        this.stage = stage;
    }

    public void createAccount(String username, String password) {
        User newUser = new User(username, password);
        System.out.println("Account created!");
        goToLogin(newUser);
    }

    public void goToLogin(User user) {
        LoginView loginView = new LoginView(stage, user);
        stage.setScene(new Scene(loginView, 400, 300));
    }
}
