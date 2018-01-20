/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import pojo.User;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class Admin_user_updateController implements Initializable {

    @FXML
    private TableView<pojo.User> tbl_user;
    @FXML
    private TableColumn<pojo.User, String> tbl_clmn_nic;
    @FXML
    private TableColumn<pojo.User, String> tbl_clmn_name;
    @FXML
    private TableColumn<pojo.User, String> tbl_clmn_contact;
    
    private modle.Users user;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user=new modle.Users();
        ObservableList<User> list_active_users = (ObservableList<User>) user.getActiveUsers();
        tbl_clmn_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        tbl_clmn_nic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        tbl_clmn_contact.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        tbl_user.setItems(list_active_users);
    }

    @FXML
    private void loadUser(MouseEvent event) {
        Integer idUser = tbl_user.getSelectionModel().getSelectedItem().getIdUser();
       
    }
    
}
