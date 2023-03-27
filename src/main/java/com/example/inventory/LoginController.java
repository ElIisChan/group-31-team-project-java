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
import java.text.BreakIterator;

public class LoginController {
    @FXML private TextField email;
    @FXML private PasswordField pass;
    @FXML private Label messageLabel;
    @FXML private VBox loginPane;
    @FXML private Button loginButton;
    @FXML private Label loginError;

    public void initialize() {
        loginError.setVisible(false);
        loginButton.setOnAction(e -> handleLogin());
    }

    @FXML
    private void handleLogin() {
        String username = email.getText();
        String password = pass.getText();
        // User user = userDB.getUser(username);
        if (username.equals("admin") && password.equals("admin")) {
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
    }
}