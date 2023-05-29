/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import Model.Account;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class UpdateAccountPageController implements Initializable {

    private Account oldAccount;
    
    @FXML
    private TextField AccountNumberTF;
    @FXML
    private TextField currencyTF;
    @FXML
    private TextField balanceTF;
    @FXML
    private TextField useridTF;
    @FXML
    private Button updateBtn;
    @FXML
    private Button CancelCreateBtn;
    @FXML
    private TextField usernameTF;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.oldAccount = Controller.Admin.AccountsManagmentController.selectedAccountToUpdate;
        usernameTF.setText(oldAccount.getUsername());
        useridTF.setText(Integer.toString(oldAccount.getUserid()));
        AccountNumberTF.setText(Integer.toString(oldAccount.getAccountnumber()));
        currencyTF.setText(oldAccount.getCurrency());
        balanceTF.setText(Double.toString(oldAccount.getBalance()));

    }

    @FXML
    private void updateBtnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String username = usernameTF.getText();
        int userid = Integer.parseInt(useridTF.getText());
        int accountnumber = Integer.parseInt(AccountNumberTF.getText());
        String currency = currencyTF.getText();
        double balance = Double.parseDouble (balanceTF.getText()) ;

        
        Account newaccount = new Account(username, userid, accountnumber, currency, balance);

       
        newaccount.setId(oldAccount.getId());

        
        newaccount.update();

       
        Controller.Admin.AccountsManagmentController.updateStage.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Account updated");
        alert.setContentText("Account updated");
        alert.showAndWait();
    }

    @FXML
    private void CancelCreateBtnAction(ActionEvent event) {
        Controller.Admin.AccountsManagmentController.updateStage.close();
    }

}
