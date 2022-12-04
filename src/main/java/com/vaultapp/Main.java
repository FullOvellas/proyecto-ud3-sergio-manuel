package com.vaultapp;

import atlantafx.base.theme.NordLight;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static Scene main;

    public static void main(String[] args) {
        Main.launch();
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
