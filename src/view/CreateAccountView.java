package view;

import controller.CreateAccountController;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateAccountView extends VBox {
    public CreateAccountView(Stage stage) {
        CreateAccountController controller = new CreateAccountController(stage);

        TextField username = new TextField();
        username.setPromptText("Choose a username");

        PasswordField password = new PasswordField();
        password.setPromptText("Choose a password");

        Button submitBtn = new Button("Create Account");
        submitBtn.setOnAction(e -> controller.createAccount(username.getText(), password.getText()));

        Button backBtn = new Button("Back to Login");
        backBtn.setOnAction(e -> controller.goToLogin(null)); // null = no user created

        getChildren().addAll(new Label("Create Account"), username, password, submitBtn, backBtn);
        setSpacing(10);
    }
}
