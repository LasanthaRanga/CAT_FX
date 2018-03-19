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
        }

        if (event.getSource() == btn_bulding) {
            btn_bulding.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #03A9F4;");
            img_building.setImage(new Image("building_blue.png"));
        } else {
            btn_bulding.setStyle("");
            img_building.setImage(new Image("building.png"));
        }

        if (event.getSource() == btn_shop) {
            btn_shop.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #03A9F4;");
            img_shop.setImage(new Image("Shop_blue.png"));
        } else {
            btn_shop.setStyle("");
            img_shop.setImage(new Image("Shop.png"));
        }

    }

}
