package controller;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.User;
import view.LoginView;
import model.*;
import view.*;

public class CreateAccountController {
    private GUIView app;

    public CreateAccountController(GUIView app) {
        this.app = app;
    }

    public void createAccount(String username, String password) {
        boolean success = app.getUserDb().createAccount(username, password);
        if (success) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Account Created");
            alert.setContentText("You can now log in with your new account.");
            alert.showAndWait();
            app.showLogin(app.getPrimaryStage());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Creation Failed");
            alert.setContentText("Username taken or password not strong enough.");
            alert.showAndWait();
        }
    }
}
