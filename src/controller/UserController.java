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
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import modle.Users;

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
    private JFXComboBox<String> com_cat;

    @FXML
    private JFXButton btn_saveuser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadCat();
        loadSQ();

        btn_saveuser.setOnAction((ActionEvent event) -> {
            saveUsers();
        });

    }

    public modle.Users collectUserInfo() {
        Users users = new modle.Users();
        users.setCatagory(com_cat.getSelectionModel().getSelectedItem());
        users.setSQ(com_sq.getSelectionModel().getSelectedItem());
        users.setFullName(txt_fname.getText());
        users.setNic(txt_nic.getText());
        users.setMobile(txt_mobile.getText());
        users.setSqa(txt_answer.getText());
        users.setUname(txt_uname.getText());
        users.setPword(txt_pword.getText());

        System.out.println(users.getPword());
        return users;

    }
    
    public void clear(){
        txt_fname.setText(null);
        txt_mobile.setText(null);
        txt_answer.setText(null);
        txt_nic.setText(null);
        txt_pword.setText(null);
        txt_pword.setText(null);        
    }

    public void saveUsers() {
        Users users = collectUserInfo();
        users.saveUser();
        clear();
    }

    public void loadCat() {
        ObservableList loadCatList = new modle.Catagory().loadCatList();
        com_cat.setItems(loadCatList);
    }

    public void loadSQ() {
        ObservableList sq = new modle.SQ().getSQ();
        com_sq.setItems(sq);
    }

}
