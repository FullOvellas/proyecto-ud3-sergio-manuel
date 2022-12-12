package com.vaultapp;

import atlantafx.base.theme.NordDark;
import atlantafx.base.theme.NordLight;
import com.vaultapp.controller.MainGUIController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Launches the application in graphic mode.
 * **Provisional class name.
 */
public class MainGUI extends Application {
    private static Scene main;

    public static void startApp() {
        Application.launch(MainGUI.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Application.setUserAgentStylesheet(new NordLight().getUserAgentStylesheet());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        main = new Scene(fxmlLoader.load(), 1200, 800);
        primaryStage.setTitle("Vaulted");
        primaryStage.setScene(main);
        primaryStage.show();
    }
}
