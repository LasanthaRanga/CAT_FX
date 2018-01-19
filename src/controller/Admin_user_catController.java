/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class Admin_user_catController implements Initializable {

   @FXML
    private JFXTextField txt_catagory;

    @FXML
    private JFXButton btn_add_cat;

    @FXML
    private TableView<?> tbl_catagory;

    @FXML
    private TableColumn<?, ?> col_id_cat;

    @FXML
    private TableColumn<?, ?> col_cat;

    @FXML
    private JFXButton btn_add_autorities;

    @FXML
    private TableView<?> tbl_authorities;

    @FXML
    private TableColumn<?, ?> col_id_autho;

    @FXML
    private TableColumn<?, ?> col_autho;

    @FXML
    private JFXTextField txt_authorities;

    @FXML
    private JFXButton btn_add_department;

    @FXML
    private TableView<?> tbl_department;

    @FXML
    private TableColumn<?, ?> col_id_dep;

    @FXML
    private TableColumn<?, ?> col_dep;

    @FXML
    private JFXTextField txt_department;

    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        btn_add_cat.setOnAction((event) -> {
            System.out.println("Sout");
        });
        
        
    }    
    
    
    
    
    
    
    
    
    
    
    
}
