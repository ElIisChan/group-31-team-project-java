package com.example.inventory;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;

public class Application extends javafx.application.Application {
    private static final String DB_URL = "";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create a scene and show it
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("adminLogin.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}