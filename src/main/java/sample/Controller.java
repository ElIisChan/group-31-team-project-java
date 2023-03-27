package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    private Button btn_dash;
    @FXML
    private Button btn_notif;
    @FXML
    private Button btn_orders;
    @FXML
    private Button btn_edit;
    @FXML
    private Button btn_logout;
    @FXML
    private Button btn_additem;
    @FXML
    private Button btn_edititem;
    @FXML
    private Button image_upload;



    @FXML
    protected void sampleAdd() throws IOException{
        Stage firstStage = (Stage) addbutton.getScene().getWindow();
        firstStage. close ();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("adminDash.fxml"));
        stage.setTitle("Add Contact");
        stage.setScene(new Scene(root,500,500)) ;
        stage.show();

    }
}