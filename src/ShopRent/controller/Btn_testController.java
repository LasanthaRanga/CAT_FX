/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShopRent.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class Btn_testController extends Button_click implements Initializable {

    @FXML
    private JFXButton btn_dashboard;
    @FXML
    private ImageView img_dashboard;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void clickOnAction(ActionEvent event) {
        
        
        if (event.getSource() == btn_dashboard) {      // #03A9F4
            btn_dashboard.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #03A9F4;");
            img_dashboard.setImage(new Image("dashboard_2_blue.png"));
        } else {
            btn_dashboard.setStyle("");
            img_dashboard.setImage(new Image("dashboard_2.png"));
            btn_dashboard.setStyle("-fx-background-color: #03A9F4; -fx-text-fill: #FFFFFF;");
        }
    }
    

    

}
