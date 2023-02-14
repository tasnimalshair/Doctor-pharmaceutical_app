/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package add;

import finalproject.DataBase;
import static finalproject.DataBase.connection;
import java.net.URL;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AddPatientController implements Initializable {

    @FXML
    private RadioButton male;
    @FXML
    private Button backBtn;
    @FXML
    private Button addBtn;
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField ageField;
    @FXML
    private TextArea problemField;
    @FXML
    private RadioButton female;
    @FXML
    private TextField imageField;
    @FXML
    private TextField dateField;
    private ToggleGroup tg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tg = new ToggleGroup();
        male.setToggleGroup(tg);
        female.setToggleGroup(tg);
    }

    @FXML
    private void maleAction(ActionEvent event) {
    }

    @FXML
    private void backBtnAction(ActionEvent event) {
    }

    @FXML
    @SuppressWarnings("empty-statement")
    private void addBtnAction(ActionEvent event) throws SQLException, ParseException {
        if (!idField.getText().equals("") && !nameField.getText().equals("") && !ageField.getText().equals("")
                && !imageField.getText().equals("") && !problemField.getText().equals("") && !dateField.getText().equals("")) {

            String selectedGender = "";
            if (male.isSelected()) {
                selectedGender = "male";
            } else if (female.isSelected()) {
                selectedGender = "female";
            }
            int pId = Integer.parseInt(idField.getText());
            String pName = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String pGender = selectedGender;
            String pImage = imageField.getText();
            String pProplem = problemField.getText();

            java.sql.Date date = java.sql.Date.valueOf(dateField.getText());

            DataBase.connectDB();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO patient VALUES ("
                    + pId + ", '" + pName + "' , " + age + " , '" + pGender + "' , '" + pImage + "', '"
                    + pProplem + "' ,  '" + date + "' ," + login.LoginController.userId + ");");
//            login.LoginController.userId
            int ss = statement.executeUpdate();

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Information Dialog");
            alert.setContentText("Patient added successfully");
            alert.showAndWait();

            idField.setText("");
            nameField.setText("");
            ageField.setText("");
            male.setSelected(false);
            female.setSelected(false);
            imageField.setText("");
            problemField.setText("");
            dateField.setText("");

        } else {
        }
    }

}
