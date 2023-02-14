/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package update;

import finalproject.DataBase;
import finalproject.Patient;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class UpdatePatientController implements Initializable {

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField problemField;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private TableColumn<Patient, Integer> id;
    @FXML
    private TableColumn<Patient, String> name;
    @FXML
    private TableColumn<Patient, Integer> age;
    @FXML
    private TableColumn<Patient, String> gender;
    @FXML
    private TableColumn<Patient, String> image;
    @FXML
    private TableColumn<Patient, String> problem;
    @FXML
    private TableColumn<Patient, Date> date;
    @FXML
    private Button backBtn;
    @FXML
    private Button saveBtn;
    @FXML
    private TableView<Patient> table;
    ToggleGroup tg = null;
    @FXML
    private TextField dateField;
    @FXML
    private TextField imageField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        id.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));
        age.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("age"));
        gender.setCellValueFactory(new PropertyValueFactory<Patient, String>("gender"));
        image.setCellValueFactory(new PropertyValueFactory<Patient, String>("image"));
        problem.setCellValueFactory(new PropertyValueFactory<Patient, String>("problem"));
        date.setCellValueFactory(new PropertyValueFactory<Patient, Date>("entranceDate"));

        try {
            table.setItems(getPatients());
        } catch (SQLException ex) {
            Logger.getLogger(UpdatePatientController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdatePatientController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tg = new ToggleGroup();
        male.setToggleGroup(tg);
        female.setToggleGroup(tg);
    }

    @FXML
    private void maleAction(ActionEvent event) {
    }

    @FXML
    private void femaleAction(ActionEvent event) {
    }

    @FXML
    private void backBtnAction(ActionEvent event) {
    }

    @FXML
    private void saveBtnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        Patient selPatient = table.getSelectionModel().getSelectedItem();
        if (selPatient != null) {
            String selGender = "";
            if (male.isSelected()) {
                selGender = "male";
            }
            if (female.isSelected()) {
                selGender = "female";
            }
                  if (!idField.getText().equals("") && !nameField.getText().equals("") && !ageField.getText().equals("")
                && !imageField.getText().equals("") && !problemField.getText().equals("") && !dateField.getText().equals("")) {

            Connection connection= DataBase.connectDB();
            PreparedStatement statement = connection.prepareStatement("UPDATE patient SET name='" + nameField.getText() + "' ,age="+Integer.parseInt(ageField.getText())+" , gender='" + selGender
                    + "' ,  image='" + imageField.getText() + "' , problem='" + problemField.getText() + "' ,entranceDate='"+dateField.getText() +"' WHERE id=" + idField.getText());
            int ss = statement.executeUpdate();
            table.setItems(getPatients());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Information Dialog");
            alert.setContentText("Patient updated successfully");
            alert.showAndWait();

        }}

    }
    @FXML
    private void onClickPatient(MouseEvent event) {
        Patient selected = table.getSelectionModel().getSelectedItem();
        idField.setText(selected.getId() + "");
        nameField.setText(selected.getName());
        ageField.setText(selected.getAge() + "");
        if (selected.getGender().equals("male")) {
            male.setSelected(true);
        }
        if (selected.getGender().equals("female")) {
            female.setSelected(true);
        }

        imageField.setText(selected.getName());
        problemField.setText(selected.getProblem());
        dateField.setText(selected.getEntranceDate() + "");

    }

    public ObservableList<Patient> getPatients() throws SQLException, ClassNotFoundException {
        ObservableList<Patient> olist = FXCollections.observableArrayList();
        Statement statement = null;
        Connection connection = DataBase.connectDB();
        statement = connection.createStatement();

        ResultSet rs = statement.executeQuery("select * from patient;");
        while (rs.next()) {
            Patient s = new Patient();
            s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
            s.setAge(rs.getInt("age"));
            s.setGender(rs.getString("gender"));
            s.setImage(rs.getString("image"));
            s.setProblem(rs.getString("problem"));
            s.setEntranceDate(rs.getDate("entranceDate"));

            olist.add(s);

        }
        return olist;
    }

}
