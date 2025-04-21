package controller;

import javafx.stage.Stage;
import view.GUIView;

public class HomeController {
    private GUIView app;
    private Stage stage;

    public HomeController(GUIView app, Stage stage) {
        this.app = app;
        this.stage = stage;
    }

    public void logout() {
        app.getUserDb().logout();
        app.showLogin(stage); // ✅ go back to login screen
    }
}

