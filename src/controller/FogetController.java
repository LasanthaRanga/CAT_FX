/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import pojo.Login;

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
    @FXML
    private JFXTextField txt_newpassword;
    @FXML
    private JFXButton btn_update;
    @FXML
    private MaterialDesignIconView lbl_close;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void check(MouseEvent event) {
        String ans = txt_ans.getText();
        if (!ans.isEmpty()) {
            if (userlogin.getUser().getSqa().equals(ans)) {
                txt_newpassword.setDisable(false);
                btn_update.setDisable(false);
            } else {
                Notifications.create()
                        .title("Fail")
                        .text("Wrong answer. Try again.")
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
    }

    private pojo.Login userlogin;
    private modle.Login login;

    @FXML
    private void get(MouseEvent event) {
        String text = txt_username.getText();
        if (!text.isEmpty()) {
            login = new modle.Login();
            userlogin = login.getByUsernameWithUser(text);
            if (userlogin != null) {
                txta_sq.setText(userlogin.getUser().getSq());
            } else {
                Notifications.create()
                        .title("Warning")
                        .text("Not Found User")
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
    }

    @FXML
    private void update(MouseEvent event) {
        String newpassword = txt_newpassword.getText();
        if (!newpassword.isEmpty()) {
            userlogin.setPword(newpassword);
            if (login.update(userlogin)) {
                Notifications.create()
                        .title("Warning")
                        .text("Update Success.")
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.BOTTOM_RIGHT).showInformation();
            } else {
                Notifications.create()
                        .title("Warning")
                        .text("Update Fail.")
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.BOTTOM_RIGHT).showError();
            }
        } else {
            Notifications.create()
                    .title("Warning")
                    .text("Please Enter New Password.")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT).showWarning();
        }
    }

    @FXML
    private void close(MouseEvent event) {
        Platform.exit();
    }

}
