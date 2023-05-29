/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import Model.Account;
import Model.User;
import View.ViewManager;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class CreateAccountController implements Initializable {

    @FXML
    private TextField accountnumberfelde;
    @FXML
    private TextField usernamefilde;
    @FXML
    private TextField currencyfild;
    @FXML
    private TextField balancefide;
    @FXML
    private TextField useridfilde;
    @FXML
    private Button createbtn;
    @FXML
    private Button cancelBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void actioncreatebtn(ActionEvent event) throws SQLException, ClassNotFoundException {

        int userid = Integer.parseInt(useridfilde.getText());
        int accountnumber =Integer.parseInt(accountnumberfelde.getText()) ;
        String username = usernamefilde.getText();
        String currency = currencyfild.getText();
        double balance =  Double.parseDouble (balancefide.getText());
        // make an user object having this data

        Account account = new Account(username, userid, accountnumber, currency, balance);
        // save the user in database by save method
        account.save();

        //after saving should return to the back scene and show an alert
        ViewManager.adminPage.changeSceneToAccountsManagment();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Account inserted");
        alert.setContentText("Account inserted");
        alert.showAndWait();
    }

    @FXML
    private void actioncancelbtn(ActionEvent event) {
        ViewManager.adminPage.changeSceneToAccountsManagment();
    }

}
