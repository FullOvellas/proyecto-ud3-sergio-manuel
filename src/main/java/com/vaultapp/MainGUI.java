package com.vaultapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class.
 * Contains the main method.
 * **Provisional class name.
 */
public class MainGUI extends Application {
    private static Scene main;


    public static void startApp() {
        Application.launch(MainGUI.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        main = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Vault App");
        primaryStage.setScene(main);
        primaryStage.show();
    }
}
