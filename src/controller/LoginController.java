package controller;

import model.User;
import view.CreateAccountView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController {
    private Stage stage;
    private User currentUser;

    public LoginController(Stage stage, User user) {
        this.stage = stage;
        this.currentUser = user;
    }

    public void login(String username, String password) {
        if (currentUser != null && currentUser.getUserName().equals(username) && currentUser.getPassword().equals(password)) {
            System.out.println("Login successful!");
            // TODO: Navigate to dashboard
        } else {
            System.out.println("Invalid credentials");
        }
    }

    public void goToCreateAccount() {
        CreateAccountView createView = new CreateAccountView(stage);
        stage.setScene(new Scene(createView, 400, 300));
    }
}
