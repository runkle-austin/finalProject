package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.User;
import view.*;

public class LoginController {
    private GUIView app;
    private Stage stage;

    public LoginController(GUIView app, Stage stage) {
        this.app = app;
        this.stage = stage;
    }

    public void login(String username, String password) {
        User currUser = app.getUserDb().login(username, password);
        if (currUser != null) {
            // Successfully authenticated: show the home page.
            app.setCurrentUser(currUser);
            app.setCurrentUserName(username);
            app.showHomePage(stage);
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Login Failed");
            alert.setContentText("Invalid username or password.");
            alert.showAndWait();
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
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText("Account Already Exists");
                alert.showAndWait();
            }
        }
        else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Account Creation Failed");
            alert.setContentText(strongPassword);
            alert.showAndWait();
        }
    }
}