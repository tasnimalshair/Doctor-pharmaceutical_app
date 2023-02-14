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
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class MakePrescriptionController implements Initializable {

    @FXML
    private TextField pId;
    @FXML
    private TextField mId;
    private TextField dId;
    @FXML
    private TextField date;
    @FXML
    private Button saveBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void saveBtnAction(ActionEvent event) throws SQLException, ParseException {
        Connection connection = DataBase.connectDB();
//        Date date_ = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(date.getText());
                    java.sql.Date datee = java.sql.Date.valueOf(date.getText());

        PreparedStatement statement = connection.prepareStatement("INSERT INTO prescription VALUES("
                + Integer.parseInt(pId.getText()) + ","
                + Integer.parseInt(mId.getText()) + ","
                + login.LoginController.userId + ","
                + "'" + datee + "');");

        int ss = statement.executeUpdate();

    }

}
