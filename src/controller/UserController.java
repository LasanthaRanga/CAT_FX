/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import modle.Users;
import org.controlsfx.control.Notifications;
import pojo.Login;
import pojo.User;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class UserController implements Initializable {

    @FXML
    private JFXTextField txt_fname;

    @FXML
    private JFXTextField txt_nic;

    @FXML
    private JFXTextField txt_mobile;

    @FXML
    private JFXTextField txt_uname;

    @FXML
    private JFXPasswordField txt_pword;

    @FXML
    private JFXComboBox<String> com_sq;

    @FXML
    private JFXTextField txt_answer;

    @FXML
    private JFXButton btn_saveuser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Load Security questions
        loadSQ();
    }

//    public modle.Users collectUserInfo() {
////        Users users = new modle.Users();
////        users.setCatagory(com_cat.getSelectionModel().getSelectedItem());
////        users.setSQ(com_sq.getSelectionModel().getSelectedItem());
////        users.setFullName(txt_fname.getText());
////        users.setNic(txt_nic.getText());
////        users.setMobile(txt_mobile.getText());
////        users.setSqa(txt_answer.getText());
////        users.setUname(txt_uname.getText());
////        users.setPword(txt_pword.getText());
////
////        System.out.println(users.getPword());
////        return users;
//
//    }
    public void clear() {
        txt_fname.setText(null);
        txt_mobile.setText(null);
        txt_answer.setText(null);
        txt_nic.setText(null);
        txt_pword.setText(null);
        txt_pword.setText(null);
    }

//    public void saveUsers() {
//        Users users = collectUserInfo();
//        users.saveUser();
//        clear();
//    }
//    public void loadCat() {
//        ObservableList loadCatList = new modle.Catagory().loadCatList();
//        com_cat.setItems(loadCatList);
//    }
    public void loadSQ() {
        ObservableList sq = new modle.SQ().getSQ();
        com_sq.setItems(sq);
    }

    @FXML
    private void saveUser(MouseEvent event) {
        String fname = txt_fname.getText();
        if (!fname.isEmpty()) {
            String nic = txt_nic.getText();
            if (!nic.isEmpty()) {
                String mobile = txt_mobile.getText();
                if (!mobile.isEmpty()) {
                    String sq = com_sq.getValue();
                    if (!sq.isEmpty()) {
                        String ans = txt_answer.getText();
                        if (!ans.isEmpty()) {
                            String uname = txt_uname.getText();
                            if (!uname.isEmpty()) {
                                String pword = txt_pword.getText();
                                if (!pword.isEmpty()) {
                                    // set data to user
                                    User user = new pojo.User();
                                    user.setSq(com_sq.getSelectionModel().getSelectedItem());
                                    user.setFullName(txt_fname.getText());
                                    user.setNic(txt_nic.getText());
                                    user.setMobile(txt_mobile.getText());
                                    user.setSqa(txt_answer.getText());
                                    user.setRegDate(new Date());
                                    user.setStatus(1);
                                    user.setSyn(1);
                                    // set data to login
                                    Login login = new pojo.Login();
                                    login.setUname(txt_uname.getText());
                                    login.setPword(txt_pword.getText());
                                    login.setStatus(1);
                                    login.setSyn(1);
                                    // init to database
                                    Users modle_user = new modle.Users();
                                    
                                    if (modle_user.save(user)) {
                                        Notifications.create()
                                                .title("Success")
                                                .text("User added success.")
                                                .hideAfter(Duration.seconds(3))
                                                .position(Pos.BOTTOM_RIGHT).showInformation();
                                    } else {
                                        Notifications.create()
                                                .title("Fail")
                                                .text("User adding failed.")
                                                .hideAfter(Duration.seconds(3))
                                                .position(Pos.BOTTOM_RIGHT).showError();
                                    }
                                } else {
                                    Notifications.create()
                                            .title("Warning")
                                            .text("Please Enter Password.")
                                            .hideAfter(Duration.seconds(3))
                                            .position(Pos.BOTTOM_RIGHT).showWarning();
                                }
                            } else {
                                Notifications.create()
                                        .title("Warning")
                                        .text("Please Enter Username.")
                                        .hideAfter(Duration.seconds(3))
                                        .position(Pos.BOTTOM_RIGHT).showWarning();
                            }
                        } else {
                            Notifications.create()
                                    .title("Warning")
                                    .text("Please Enter Answer.")
                                    .hideAfter(Duration.seconds(3))
                                    .position(Pos.BOTTOM_RIGHT).showWarning();
                        }
                    } else {
                        Notifications.create()
                                .title("Warning")
                                .text("Please Select Security Question.")
                                .hideAfter(Duration.seconds(3))
                                .position(Pos.BOTTOM_RIGHT).showWarning();
                    }
                } else {
                    Notifications.create()
                            .title("Warning")
                            .text("Please Enter Mobile Number.")
                            .hideAfter(Duration.seconds(3))
                            .position(Pos.BOTTOM_RIGHT).showWarning();
                }
            } else {
                Notifications.create()
                        .title("Warning")
                        .text("Please Enter NIC.")
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.BOTTOM_RIGHT).showWarning();
            }
        } else {
            Notifications.create()
                    .title("Warning")
                    .text("Please Enter Name.")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT).showWarning();
        }
    }

}
