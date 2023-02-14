/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prescription;

import finalproject.DataBase;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class HistoryController implements Initializable {
    
    @FXML
    private TextField pIdField;
    @FXML
    private Button showBtn;
    @FXML
    private TableView<Prescription> table;
    @FXML
    private TableColumn<Prescription, Integer> pId;
    @FXML
    private TableColumn<Prescription, Integer> mId;
    @FXML
    private TableColumn<Prescription, Integer> dId;
    @FXML
    private TableColumn<Prescription, Date> date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pId.setCellValueFactory(new PropertyValueFactory<Prescription, Integer>("pId"));
        mId.setCellValueFactory(new PropertyValueFactory<Prescription, Integer>("mId"));
        dId.setCellValueFactory(new PropertyValueFactory<Prescription, Integer>("dId"));
        date.setCellValueFactory(new PropertyValueFactory<Prescription, Date>("date"));
    }
    
    @FXML
    private void showBtnAction(ActionEvent event) throws SQLException {
        Connection connection = DataBase.connectDB();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM prescription WHERE pat_id=" + Integer.parseInt(pIdField.getText()));
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Prescription p = new Prescription();
            p.setpId(rs.getInt("pat_id"));
            p.setmId(rs.getInt("medicine_id"));
            p.setdId(rs.getInt("doctors_id"));
            p.setDate(rs.getDate("publishedDate"));
        }
        table.setItems(Prescription.list);
        
    }
    
}
