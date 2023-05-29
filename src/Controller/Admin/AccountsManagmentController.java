/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import static Controller.Admin.UsersManagmentController.selectedUserToUpdate;
import static Controller.Admin.UsersManagmentController.updateStage;
import Model.Account;
import Model.User;
import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yahya
 */
public class AccountsManagmentController implements Initializable {

    public static Account selectedAccountToUpdate;
    public static Stage updateStage;
    @FXML
    private Button usersManagmentPageBtn;
    @FXML
    private Button accountsPageBtn;
    @FXML
    private Button operationsPageBtn;
    @FXML
    private Button createNewAccountrBtn;
    @FXML
    private Button showAllAccountsBtn;
    @FXML
    private Button updateSelectedAccountBtn;
    @FXML
    private Button deleteSelectedAccountBtn;
    @FXML
    private Button searchAccountBtn;
    @FXML
    private TextField accontSearchTF;
    @FXML
    private TableView<Account> AccountTable;
    @FXML
    private TableColumn<Account, Integer> idcol;
    @FXML
    private TableColumn<Account, String> Accountnumbercol;
    @FXML
    private TableColumn<Account, String> UserNamecol;
    @FXML
    private TableColumn<Account, String> Currencycol;
    @FXML
    private TableColumn<Account, String> Balancecol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        idcol.setCellValueFactory(new PropertyValueFactory("id"));
        UserNamecol.setCellValueFactory(new PropertyValueFactory("username"));
        Accountnumbercol.setCellValueFactory(new PropertyValueFactory("accountnumber"));
        Currencycol.setCellValueFactory(new PropertyValueFactory("currency"));
        Balancecol.setCellValueFactory(new PropertyValueFactory("balance"));

    }

    @FXML
    private void showUsersManagmentPage(ActionEvent event) {
        ViewManager.adminPage.changeSceneToUsersManagment();
    }

    @FXML
    private void showAccountsPage(ActionEvent event) {
    }

    @FXML
    private void showOperationsPage(ActionEvent event) {
    }

    @FXML
    private void showAccountCreationPage(ActionEvent event) {
        ViewManager.adminPage.changeScenecreateAccount();
    }

    @FXML
    private void showAllAccounts(ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<Account> accountList
                = FXCollections.observableArrayList(Account.getAllACCOUNTS());

        AccountTable.setItems(accountList);
    }

    @FXML
    private void deleteSelectedAccount(ActionEvent event) {
        if (AccountTable.getSelectionModel().getSelectedItem() != null) {
            //store the selected Account from the TableView in new Account object
            Account selectedUser = AccountTable.getSelectionModel().getSelectedItem();

            //show an confirmation alert and make the deletion on confirm event
            Alert deleteConfirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirmAlert.setTitle("Account delete");
            deleteConfirmAlert.setContentText("Are you sure to delete this Account ?");
            deleteConfirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        //delete the selected user from database table using delete method in our Account model
                        selectedUser.delete();
                    } catch (SQLException ex) {
                        Logger.getLogger(UsersManagmentController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(UsersManagmentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Alert deletedSuccessAlert = new Alert(Alert.AlertType.INFORMATION);
                    deletedSuccessAlert.setTitle("Account deleted");
                    deletedSuccessAlert.setContentText("Account deleted");
                    deletedSuccessAlert.show();
                }
            });

        } else {
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
            warnAlert.setTitle("Select an Account");
            warnAlert.setContentText("Please select an Account from the table view");
            warnAlert.show();
        }
    }

    @FXML
    private void searchForAnAccount(ActionEvent event) {
    }

    @FXML
    private void updateSelectedAccount(ActionEvent event) throws IOException {
        //check if there is an Account selected from the TableView
       if (AccountTable.getSelectionModel().getSelectedItem() != null) {   
            selectedAccountToUpdate = AccountTable.getSelectionModel().getSelectedItem();
            FXMLLoader loaderAccountUpdate = new FXMLLoader(getClass().getResource("/View/AdminFXML/UpdateAccountPage.fxml"));
            Parent rootUpdateAccount = loaderAccountUpdate.load();
            Scene updateAccountScene = new Scene(rootUpdateAccount);
            updateStage = new Stage();
            updateStage.setScene(updateAccountScene);
            updateStage.setTitle("Update Account " + selectedAccountToUpdate.getUsername());
            updateStage.show();
        }

    }
}
