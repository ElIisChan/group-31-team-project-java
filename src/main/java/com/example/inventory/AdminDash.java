package com.example.inventory;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


    public class AdminDash extends Application {

        @Override
        public void start(Stage primaryStage) {
            AnchorPane root = new AnchorPane();
            root.setPrefSize(600, 400);

            VBox menuBox = new VBox();
            menuBox.setPrefSize(184, 400);
            menuBox.setStyle("-fx-background-color: #272859;");

            ImageView logoImg = new ImageView(new Image("file:src/main/resources/templates/images/logo.png"));
            logoImg.setFitWidth(154);
            logoImg.setFitHeight(44);
            VBox.setMargin(logoImg, new Insets(15, 10, 0, 15));
            menuBox.getChildren().add(logoImg);

            Button btnDash = new Button("Dashboard");
            btnDash.setStyle("-fx-background-color: #74BCDB; -fx-border-style: LinearBorder15;");
            btnDash.setPrefSize(184, 30);
            btnDash.setFont(new Font(14));
            VBox.setMargin(btnDash, new Insets(30, 0, 0, 0));
            menuBox.getChildren().add(btnDash);

            Button btnNotif = new Button("Notifications");
            btnNotif.setStyle("-fx-background-color: #74BCDB; -fx-border-style: LinearBorder15;");
            btnNotif.setPrefSize(184, 30);
            btnNotif.setFont(new Font(14));
            VBox.setMargin(btnNotif, new Insets(10, 0, 0, 0));
            menuBox.getChildren().add(btnNotif);

            Button btnOrders = new Button("Orders");
            btnOrders.setStyle("-fx-background-color: #74BCDB; -fx-border-style: LinearBorder15;");
            btnOrders.setPrefSize(184, 30);
            btnOrders.setFont(new Font(14));
            VBox.setMargin(btnOrders, new Insets(10, 0, 0, 0));
            menuBox.getChildren().add(btnOrders);

            Button btnEdit = new Button("Edit Webpage");
            btnEdit.setStyle("-fx-background-color: #74BCDB; -fx-border-style: LinearBorder15;");
            btnEdit.setPrefSize(184, 30);
            btnEdit.setFont(new Font(14));
            VBox.setMargin(btnEdit, new Insets(10, 0, 0, 0));
            menuBox.getChildren().add(btnEdit);

            Button btnLogout = new Button("Logout");
            btnLogout.setStyle("-fx-background-color: #74BCDB; -fx-border-style: LinearBorder15;");
            btnLogout.setPrefSize(184, 30);
            btnLogout.setFont(new Font(14));
            VBox.setMargin(btnLogout, new Insets(10, 0, 0, 0));
            menuBox.getChildren().add(btnLogout);

            VBox contentBox = new VBox();
            contentBox.setPrefSize(416, 400);
            contentBox.setLayoutX(184);

            Pane pane = new Pane();
            pane.setPrefSize(366, 400);
            Label label = new Label("Admin Dashboard");
            label.setFont(Font.font("System Bold", 24));
            label.setPadding(new Insets(25));
            pane.getChildren().add(label);

            Label statsLabel = new Label("Stats");
            statsLabel.setTextFill(javafx.scene.paint.Color.web("#818181"));

        }
    }

