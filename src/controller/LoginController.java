package controller;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.User;
import view.GUIView;
import view.LoginView;

public class LoginController {
    private final GUIView app;
    private final Stage stage;

    public LoginController(GUIView app, Stage stage) {
        this.app = app;
        this.stage = stage;
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
        String strongPassword = app.getUserDb().isStrongPassword(password);
        if (strongPassword.equals("")) {
            // if account created successfully
            if (app.getUserDb().createAccount(username, password)) {
                User currUser = app.getUserDb().login(username, password);
                app.setCurrentUser(currUser);
                app.setCurrentUserName(username);
                // Successfully authenticated: show the home page.
                app.showHomePage(stage);
            }
            else{
                showError("Account Already Exists");
            }
        }
        else {
            showError(strongPassword);
        }
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}