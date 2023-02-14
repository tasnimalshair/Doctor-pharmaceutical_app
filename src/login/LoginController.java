/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import finalproject.DataBase;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class LoginController implements Initializable {

    public static String userId;

    @FXML
    private TextField passwordField;
    @FXML
    private Button loginBtn;
    @FXML
    private TextField idField;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private Label errorLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> list = FXCollections.observableArrayList("doctor", "pharmacist");
        comboBox.setItems(list);
    }

    @FXML
    private void loginBtnAction(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        DataBase.connectDB();

        PreparedStatement statement = DataBase.connection.prepareStatement("SELECT * FROM login WHERE id="
                + idField.getText() + " AND password='" + passwordField.getText() + "' AND type='" + comboBox.getValue() + "'");
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            userId = idField.getText();
//            Parent root = FXMLLoader.load(getClass().getResource("../finalproject\\mainDoctor.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("../prescription\\makePrescription2.fxml"));

            Stage window = (Stage) comboBox.getScene().getWindow();
            window.setScene(new Scene(root));
            window.setTitle("add user");
        }else{
            errorLabel.setText("Invalid!!");
        }
        }

    }
