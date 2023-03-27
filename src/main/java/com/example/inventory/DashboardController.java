package com.example.inventory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;

public class DashboardController {

    @FXML private Button btn_logout;
    @FXML private Button btn_orders;
    @FXML private VBox orderPane;
    @FXML private VBox dashPane;
    @FXML private VBox notifPane;


    public void initialize() {
        showDashboard();
        btn_logout.setOnAction(e -> handleLogout());
        btn_orders.setOnAction(e -> showOrders());
    }

    // Handle dashboard button
    public void showDashboard() {
        // Hide other panes
        orderPane.setVisible(false);
        notifPane.setVisible(false);
        // Show dashboard pane
        dashPane.setVisible(true);

        // Setup db locally, then connect to db and get data
    }

    // Handle logout button
    public void handleLogout() {
        // Close application
        Stage stage = (Stage) btn_logout.getScene().getWindow();
        stage.close();
    }

    // Handle orders button
    public void showOrders() {
        // Instead of changing scenes, hide and show parts of the dashboard
        // Hide other panes
        dashPane.setVisible(false);
        notifPane.setVisible(false);
        // Show order pane
        orderPane.setVisible(true);
        // Connect to database and get orders


        // If we get orders from the database, add them to the VBox
        // orderPane.getChildren().add();

    }

}
