package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.User;
import view.LoginView;
import view.*;

public class LoginController {
    private GUIView app;

    public LoginController(GUIView app) {
        this.app = app;
    }

    public void login(String username, String password) {
        User user = app.getUserDb().login(username, password);
        if (user != null) {
            app.showDashboard(app.getPrimaryStage(), user);
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Login Failed");
            alert.setContentText("Invalid username or password.");
            alert.showAndWait();
        }
    }

    public void goToCreateAccount() {
        app.showCreateAccount(app.getPrimaryStage());
    }
}