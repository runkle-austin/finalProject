package view;

import controller.LoginController;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

public class LoginView extends VBox {
    public LoginView(Stage stage, User user) {
        LoginController controller = new LoginController(stage, user);

        TextField username = new TextField();
        username.setPromptText("Username");

        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        Button loginBtn = new Button("Login");
        loginBtn.setOnAction(e -> controller.login(username.getText(), password.getText()));

        Button createBtn = new Button("Create Account");
        createBtn.setOnAction(e -> controller.goToCreateAccount());

        getChildren().addAll(new Label("Login"), username, password, loginBtn, createBtn);
        setSpacing(10);
    }
}
