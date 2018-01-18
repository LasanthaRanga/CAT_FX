/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class FogetController implements Initializable {

    @FXML
    private JFXTextArea txta_sq;
    @FXML
    private JFXTextField txt_ans;
    @FXML
    private JFXButton btn_check;
    @FXML
    private JFXTextField txt_username;
    @FXML
    private JFXButton btn_get;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void check(MouseEvent event) {
        
    }

    @FXML
    private void get(MouseEvent event) {
        String text = txt_username.getText();
        if(!text.isEmpty()){
            
        }
    }
    
}
