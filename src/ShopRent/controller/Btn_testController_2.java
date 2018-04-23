package ShopRent.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Btn_testController_2 implements Initializable {

    @FXML
    private JFXButton btn_dashboard;
    @FXML
    private ImageView img_dashboard;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void clickOnAction(ActionEvent event) {

        if (event.getSource() == btn_dashboard) {      // #03A9F4
            btn_dashboard.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #03A9F4;");
            img_dashboard.setImage(new Image("dashboard_2_blue.png"));
        } else {

        }
    }

    public void buttonUp() {
        btn_dashboard.setStyle("");
        img_dashboard.setImage(new Image("dashboard_2.png"));
        btn_dashboard.setStyle("-fx-background-color: #03A9F4; -fx-text-fill: #FFFFFF;");
    }

}
