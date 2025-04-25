package controller;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.User;
import model.UserDatabase;
import view.GUIView;
import view.LoginView;

public class LoginController {
    private final GUIView app;
    private final Stage stage;
    private final LoginView view;

    public LoginController(GUIView app, Stage stage, LoginView view) {
        this.app = app;
        this.stage = stage;
        this.view = view;
    }

    public void login(String username, String password) {
        User user = app.getUserDb().login(username, password);
        if (user != null) {
            app.setCurrentUser(user);
            app.setCurrentUserName(username);
            app.showHomePage(stage);
        } else {
            showError("Login failed. Invalid credentials.");
        }
    }

    public void createAccount(String username, String password) {
        boolean success = app.getUserDb().createAccount(username, password);
        if (success) {
            login(username, password);
        } else {
            showError("Account creation failed. Username already exists.");
        }
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}