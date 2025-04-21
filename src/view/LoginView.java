package view;

import controller.LoginController;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

import java.awt.*;

public class LoginView extends VBox {
    public LoginView(GUIView app, Stage stage) {
        setPadding(new Insets(20));
        setSpacing(10);

        Label title = new Label("Please Log In");
        TextField username = new TextField();
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        Button loginBtn = new Button("Login");
        LoginController controller = new LoginController(app);
        loginBtn.setOnAction(e -> controller.login(username.getText(), password.getText()));

        Button createBtn = new Button("Create Account");
        createBtn.setOnAction(e -> controller.goToCreateAccount());

        getChildren().addAll(title, username, password, loginBtn, createBtn);
    }
}
