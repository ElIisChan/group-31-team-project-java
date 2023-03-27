package com.example.inventory;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;

public class LoginController {
    private Connection dbConnection;
    @FXML private TextField email;
    @FXML private PasswordField pass;
    @FXML private Button loginButton;
    @FXML private Label loginError;

    public void initialize() {
        loginError.setVisible(false);
        loginButton.setOnAction(e -> {
            try {
                handleLogin();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    @FXML
    private void handleLogin() throws SQLException, ClassNotFoundException {
        String username = email.getText();
        String password = pass.getText();
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/aston_31?user=a31&password=a31");
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        ResultSet resultSet;
        try (PreparedStatement statement = dbConnection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeQuery();
            resultSet = statement.getResultSet();
            if (resultSet.next()) {
                // redirect to admin dashboard
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("adminDash.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                loginError.setText("Invalid username or password.");
                loginError.setPrefWidth(Double.MAX_VALUE);
                loginError.setVisible(true);
            }
            // empty result set
            resultSet.close();
        }
    }
}