package com.example.inventory;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    @FXML private VBox editProduct;


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
        TableView<String[]> table = new TableView<>();
        TableColumn<String[], String> column1 = new TableColumn<>("Product Name");
        column1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[0]));

        TableColumn<String[], String> column2 = new TableColumn<>("Stock Level");
        column2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[1]));

        TableColumn<String[], String> column3 = new TableColumn<>("Price (£)");
        column3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[2]));

        table.getColumns().addAll(column1, column2, column3);
        ResultSet resultSet;
        try (PreparedStatement statement = dbConnection.prepareStatement(query)) {
            statement.executeQuery();
            resultSet = statement.getResultSet();
            // If we get data from the database, add them to a table
            while (resultSet.next()) {
                // Get data from resultset
                String name = resultSet.getString("product_name");
                String quantity = String.valueOf(resultSet.getInt("quantity"));
                String price = String.valueOf(resultSet.getDouble("price"));
                String[] data = {name, quantity, price};
                // Add data to table within dashPane
                table.getItems().add(data);
            }
        }
        // If we don't get data from the database, show an error
        if (table.getItems().isEmpty()) {
            Label error = new Label("No data found");
            dashPane.getChildren().add(error);
        } else {
            // Add table to dashPane
            Label message = new Label("Click row to edit product");
            dashPane.getChildren().add(message);
            dashPane.getChildren().add(table);
            // Add button below table to add product
            Button addProduct = new Button("Add Product");
            dashPane.getChildren().add(addProduct);
            // When add product is clicked, show edit product pane
            addProduct.setOnAction(e -> {
                dashPane.setVisible(false);
                // Clear edit product before showing
                editProduct.getChildren().clear();
                editProduct.setVisible(true);
                editProduct.getChildren().add(new Label("Add Product"));
                // Create labels and text fields
                Label nameLabel = new Label("Product Name");
                Label quantityLabel = new Label("Stock Level");
                Label priceLabel = new Label("Price (£)");
                Label productDescription = new Label("Product Description");
                TextField nameField = new TextField();
                TextField quantityField = new TextField();
                TextField priceField = new TextField();
                TextField productDescriptionField = new TextField();
                editProduct.getChildren().add(nameLabel);
                editProduct.getChildren().add(nameField);
                editProduct.getChildren().add(quantityLabel);
                editProduct.getChildren().add(quantityField);
                editProduct.getChildren().add(priceLabel);
                editProduct.getChildren().add(priceField);
                editProduct.getChildren().add(productDescription);
                editProduct.getChildren().add(productDescriptionField);
                // Create save button
                Button saveButton = new Button("Save");
                editProduct.getChildren().add(saveButton);
                // On save button click, save data to db
                saveButton.setOnAction(event -> {
                    try {
                        // Setup db locally, then connect to db and get data
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection createConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/aston_31?user=a31&password=a31");
                        // Get data from product table
                        String createQuery = "INSERT INTO products (product_name, quantity, price, product_description, photo) VALUES (?, ?, ?, ?, ?)";
                        try (PreparedStatement statement = createConnection.prepareStatement(createQuery)) {
                            statement.setString(1, nameField.getText());
                            statement.setInt(2, Integer.parseInt(quantityField.getText()));
                            statement.setDouble(3, Double.parseDouble(priceField.getText()));
                            statement.setString(4, productDescriptionField.getText());
                            statement.setString(5, "https://www.ikea.com/gb/en/images/products/ektorp-armchair-bomstad-black__0712009_PE729202_S5.JPG");
                            statement.executeUpdate();
                        }
                        // Clear edit product pane
                        editProduct.getChildren().clear();
                        // Clear dashpane
                        dashPane.getChildren().clear();
                        // Show dashboard
                        showDashboard();
                    } catch (ClassNotFoundException e1) {
                        throw new RuntimeException(e1);
                    } catch (SQLException e1) {
                        throw new RuntimeException(e1);
                    }
                });
            });
            // When a row is clicked, show edit product pane
            table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    try {
                        showEditProduct( table.getSelectionModel().getSelectedIndex() );
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

    public void showEditProduct(int arrayIndex) throws ClassNotFoundException, SQLException {
        dashPane.setVisible(false);
        // Clear edit product before showing
        editProduct.getChildren().clear();
        editProduct.setVisible(true);
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
            Label nameLabel = new Label("Product Name");
            Label quantityLabel = new Label("Stock Level");
            Label priceLabel = new Label("Price (£)");
            TextField nameField = new TextField();
            TextField quantityField = new TextField();
            TextField priceField = new TextField();
            while (resultSet.next()) {
                // If arrayIndex matches the index of the row, get data from that row
                if (arrayIndex == resultSet.getRow()-1) {
                    // Get data from resultset
                    String name = resultSet.getString("product_name");
                    int quantity = resultSet.getInt("quantity");
                    double price = resultSet.getDouble("price");
                    // Add fields to edit these
                    // Add data to fields
                    nameField.setText(name);
                    quantityField.setText(String.valueOf(quantity));
                    priceField.setText(String.valueOf(price));
                    // stop loop here
                    break;
                }
            }
            editProduct.getChildren().add(nameLabel);
            editProduct.getChildren().add(nameField);
            editProduct.getChildren().add(quantityLabel);
            editProduct.getChildren().add(quantityField);
            editProduct.getChildren().add(priceLabel);
            editProduct.getChildren().add(priceField);
            // Create save button
            Button saveButton = new Button("Save");
            Button deleteButton = new Button("Delete");
            editProduct.getChildren().add(saveButton);
            editProduct.getChildren().add(deleteButton);
            // When button clicked, send data to database
            saveButton.setOnAction(e -> {
                // Get field data
                String newName = nameField.getText();
                int newQuantity = Integer.parseInt(quantityField.getText());
                double newPrice = Double.parseDouble(priceField.getText());
                // Connect to database and update product
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection updateConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/aston_31?user=a31&password=a31");
                    String updateQuery = "UPDATE products SET product_name = ?, quantity = ?, price = ? WHERE id = ?";
                    try (PreparedStatement updateStatement = updateConnection.prepareStatement(updateQuery)) {
                        updateStatement.setString(1, newName);
                        updateStatement.setInt(2, newQuantity);
                        updateStatement.setDouble(3, newPrice);
                        updateStatement.setInt(4, arrayIndex+1);
                        updateStatement.executeUpdate();
                    }
                    // Close connection
                    updateConnection.close();
                } catch (ClassNotFoundException e2) {
                    throw new RuntimeException(e2);
                } catch (SQLException e2) {
                    throw new RuntimeException(e2);
                }
                // Close editProduct pane
                editProduct.setVisible(false);
                // Show dashboard
                try {
                    // Clear old table from dashboard
                    dashPane.getChildren().clear();
                    showDashboard();
                } catch (ClassNotFoundException e2) {
                    throw new RuntimeException(e2);
                } catch (SQLException e2) {
                    throw new RuntimeException(e2);
                }
            });
            // When delete button clicked, delete product from database
            deleteButton.setOnAction(e -> {
                // Connect to database and delete product
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection deleteConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/aston_31?user=a31&password=a31");
                    String deleteQuery = "DELETE FROM products WHERE id = ?";
                    try (PreparedStatement deleteStatement = deleteConnection.prepareStatement(deleteQuery)) {
                        deleteStatement.setInt(1, arrayIndex+1);
                        deleteStatement.executeUpdate();
                    }
                    // Close connection
                    deleteConnection.close();
                } catch (ClassNotFoundException e2) {
                    throw new RuntimeException(e2);
                } catch (SQLException e2) {
                    throw new RuntimeException(e2);
                }
                // Close editProduct pane
                editProduct.setVisible(false);
                // Show dashboard
                try {
                    // Clear old table from dashboard
                    dashPane.getChildren().clear();
                    showDashboard();
                } catch (ClassNotFoundException e2) {
                    throw new RuntimeException(e2);
                } catch (SQLException e2) {
                    throw new RuntimeException(e2);
                }
            });

        }
        // If nothing is found, show an error
        if (editProduct.getChildren().isEmpty()) {
            Label error = new Label("No data found");
            editProduct.getChildren().add(error);
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
