package view;

import controller.LoginController;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ExerciseCatalog;

public class LoginView {
    private final VBox root = new VBox(10);
    private final LoginController controller;

    public LoginView(GUIView app, Stage stage) {
        ExerciseCatalog.loadExercises();
        root.setPadding(new Insets(20));

        Label title = new Label("Please Log In");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginBtn = new Button("Login");
        Button createBtn = new Button("Create Account");

        this.controller = new LoginController(app, stage);
        loginBtn.setOnAction(e -> controller.login(usernameField.getText(), passwordField.getText()));
        createBtn.setOnAction(e -> controller.createAccount(usernameField.getText(), passwordField.getText()));

        root.getChildren().addAll(title, usernameField, passwordField, loginBtn, createBtn);
    }

    public Parent getView() {
        return root;
    }
}