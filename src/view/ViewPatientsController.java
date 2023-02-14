/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import finalproject.DataBase;
import finalproject.Patient;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import update.UpdatePatientController;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class ViewPatientsController implements Initializable {

    @FXML
    private TableView<Patient> table;
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
    private Button showBtn;

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
        date.setCellValueFactory(new PropertyValueFactory<Patient, Date>("date"));

        try {
            table.setItems(getPatients());
        } catch (SQLException ex) {
            Logger.getLogger(ViewPatientsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewPatientsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void onClickPatient(MouseEvent event) {
    }

    @FXML
    private void backBtnAction(ActionEvent event) {
    }

    @FXML
    private void showBtnAction(ActionEvent event) {

    }

    public ObservableList<Patient> getPatients() throws SQLException, ClassNotFoundException {
        ObservableList<Patient> olist = FXCollections.observableArrayList();
        Statement statement = null;
        Connection connection = DataBase.connectDB();
        statement = connection.createStatement();

        ResultSet rs = statement.executeQuery("select * from patient WHERE doctorId=" + login.LoginController.userId);
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
