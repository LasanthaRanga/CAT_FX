/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
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
    @FXML
    private JFXTextField txt_fname;
    @FXML
    private JFXTextField txt_nic;
    @FXML
    private JFXTextField txt_mobile;
    @FXML
    private JFXTextField txt_regisdate;
    @FXML
    private JFXButton btn_deactive;
    @FXML
    private TableColumn<pojo.User, Integer> tbl_clmn_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user = new modle.Users();
        this.loadTable();
    }

    private void loadTable(){
        ObservableList<pojo.User> list_active_users = FXCollections.observableArrayList(user.getActiveUsers());
        tbl_clmn_id.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        tbl_clmn_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        tbl_clmn_nic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        tbl_clmn_contact.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        tbl_user.setItems(list_active_users);
    }
    
    @FXML
    private void loadUser(MouseEvent event) {
        Integer idUser = tbl_user.getSelectionModel().getSelectedItem().getIdUser();
        User byIdUser = user.getByIdUser(0);
        if (byIdUser != null) {
            txt_fname.setText(byIdUser.getFullName());
            txt_mobile.setText(byIdUser.getMobile());
            txt_nic.setText(byIdUser.getNic());
            txt_regisdate.setText(new SimpleDateFormat("yyyy-MM-dd").format(byIdUser.getRegDate()));
        } else {
            Notifications.create()
                    .title("Warning")
                    .text("Not Found User.")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT).showWarning();
        }

    }

    @FXML
    private void deactiveUser(MouseEvent event) {
        Integer idUser = tbl_user.getSelectionModel().getSelectedItem().getIdUser();
        User byIdUser = user.getByIdUser(0);
        if (byIdUser != null) {
            byIdUser.setStatus(0);
            if(user.update(byIdUser)){
                Notifications.create()
                    .title("Success")
                    .text("Deactivation Success.")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT).showInformation();
                this.loadTable();
            }else{
                Notifications.create()
                    .title("Warning")
                    .text("Deactivation failed.")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT).showWarning();
            }
        } else {
            Notifications.create()
                    .title("Warning")
                    .text("Not Found User.")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT).showWarning();
        }
    }

}
