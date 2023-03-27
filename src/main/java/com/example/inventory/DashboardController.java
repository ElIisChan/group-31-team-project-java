package com.example.inventory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;
import java.sql.*;

public class DashboardController {

    @FXML private Button btn_logout;
    @FXML private Button btn_orders;
    @FXML private VBox orderPane;
    @FXML private VBox dashPane;
    @FXML private VBox notifPane;


    public void initialize() throws ClassNotFoundException, SQLException {
        showDashboard();
        btn_logout.setOnAction(e -> handleLogout());
        btn_orders.setOnAction(e -> showOrders());
    }

    // Handle dashboard button
    public void showDashboard() throws ClassNotFoundException, SQLException {
        // Hide other panes
        orderPane.setVisible(false);
        notifPane.setVisible(false);
        // Show dashboard pane
        dashPane.setVisible(true);
        // Setup db locally, then connect to db and get data
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/aston_31?user=a31&password=a31");
        // Get data from product table
        String query = "SELECT * FROM products";
        ResultSet resultSet;
        try (PreparedStatement statement = dbConnection.prepareStatement(query)) {
            statement.executeQuery();
            resultSet = statement.getResultSet();
            // If we get data from the database, add them to a table
            while (resultSet.next()) {
                // Get data from resultset
                int id = resultSet.getInt("id");
                String name = resultSet.getString("product_name");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                // Create a label for each piece of data
                Label idLabel = new Label(Integer.toString(id));
                Label nameLabel = new Label(name);
                Label quantityLabel = new Label(Integer.toString(quantity));
                Label priceLabel = new Label(Double.toString(price));
                // Add labels to VBox
                dashPane.getChildren().add(idLabel);
                dashPane.getChildren().add(nameLabel);
                dashPane.getChildren().add(quantityLabel);
                dashPane.getChildren().add(priceLabel);
            }
        }

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
