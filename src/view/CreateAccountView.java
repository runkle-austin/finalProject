package view;

import controller.CreateAccountController;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateAccountView extends VBox {
    public CreateAccountView(GUIView app, Stage stage) {
        setPadding(new Insets(20));
        setSpacing(10);

        Label title = new Label("Create New Account");
        TextField username = new TextField();
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        Button createBtn = new Button("Create Account");
        CreateAccountController controller = new CreateAccountController(app);
        createBtn.setOnAction(e -> controller.createAccount(username.getText(), password.getText()));

        Button cancelBtn = new Button("Back to Login");
        cancelBtn.setOnAction(e -> app.showLogin(stage));

        getChildren().addAll(title, username, password, createBtn, cancelBtn);
    }
}
