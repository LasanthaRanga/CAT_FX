/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShopRent.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class BoxController implements Initializable {

    @FXML
    private VBox vbox1;
    @FXML
    private JFXButton btn_dashboard;
    @FXML
    private JFXButton btn_bulding;
    @FXML
    private JFXButton btn_shop;
    @FXML
    private ImageView img_building;
    @FXML
    private ImageView img_shop;
    @FXML
    private ImageView img_dashboard;
    @FXML
    private VBox vbox2;

    private JFXButton btn_test;
    private JFXButton btn_test2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            btn_test = FXMLLoader.load(getClass().getResource("/ShopRent/view/btn_test.fxml"));
            btn_test2 = FXMLLoader.load(getClass().getResource("/ShopRent/view/btn_test_2.fxml"));
            vbox2.getChildren().add(btn_test);           
            vbox2.getChildren().add(btn_test2);           
        } catch (IOException ex) {
            ex.printStackTrace();            
        }
        
       

    }

    @FXML
    private void clickOnAction(ActionEvent event) {

        switch (event.getSource().toString()) {
            case "btn_dashboard.toString()":
                System.out.println("");
                break;
        }

        if (event.getSource() == btn_test) {      // #03A9F4
            btn_test.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #03A9F4;");
            img_dashboard.setImage(new Image("dashboard_2_blue.png"));
        } else {
            btn_test.setStyle("");
            img_dashboard.setImage(new Image("dashboard_2.png"));
            btn_test.setStyle("-fx-background-color: #03A9F4; -fx-text-fill: #FFFFFF;");
        }

        if (event.getSource() == btn_dashboard) {      // #03A9F4
            btn_dashboard.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #03A9F4;");
            img_dashboard.setImage(new Image("dashboard_2_blue.png"));
        } else {
            btn_dashboard.setStyle("");
            img_dashboard.setImage(new Image("dashboard_2.png"));
            btn_dashboard.setStyle("-fx-background-color: #03A9F4; -fx-text-fill: #FFFFFF;");
        }

        if (event.getSource() == btn_bulding) {
            btn_bulding.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #03A9F4;");
            img_building.setImage(new Image("building_blue.png"));
        } else {
            btn_bulding.setStyle("");
            img_building.setImage(new Image("building.png"));
            btn_bulding.setStyle("-fx-background-color: #03A9F4; -fx-text-fill: #FFFFFF;");
        }

        if (event.getSource() == btn_shop) {
            btn_shop.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #03A9F4;");
            img_shop.setImage(new Image("building_blue.png"));
        } else {
            btn_shop.setStyle("");
            btn_shop.setStyle("-fx-background-color: #03A9F4; -fx-text-fill: #FFFFFF;");
            img_shop.setImage(new Image("building.png"));
        }

    }

}
