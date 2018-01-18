/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modle.Log_User;
import org.hibernate.Session;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField txt_uname;

    @FXML
    private JFXPasswordField txt_pass;

    @FXML
    private JFXButton btn_singin;

    @FXML
    private JFXButton btn_foget;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btn_singin.setOnAction((event) -> {
            login();
        });

        btn_foget.setOnAction((event) -> {
            foget();
        });

    }

    public void foget() {
        AnchorPane paymant;
        try {
            btn_foget.getParent().getScene().getWindow().hide();
            paymant = FXMLLoader.load(getClass().getResource("/view/Foget.fxml"));
            btn_singin.getParent().getScene();
            Scene scene = new Scene(paymant);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void login() {

        String uname = txt_uname.getText();
        String pass = txt_pass.getText();

        Log_User log_User = new modle.Log_User();

        int b = log_User.loginCheack(uname, pass);

        if (b == 1) {
            System.out.println("PAY");

//            try {
//                System.out.println("");;
//                btn_singin.getParent().getScene().getWindow().hide();
//                AnchorPane paymant = FXMLLoader.load(getClass().getResource("/view/AdminView.fxml"));
//                btn_singin.getParent().getScene();
//                Scene scene = new Scene(paymant);
//                Stage stage = new Stage();
//                stage.initStyle(StageStyle.TRANSPARENT);
//                stage.setScene(scene);
//                stage.show();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//                Logger.getLogger(PayController.class.getName()).log(Level.SEVERE, null, ex);
//            }

        } else if (b == 0) {

        } else if (b > 1) {
            try {
                System.out.println("");;
                btn_singin.getParent().getScene().getWindow().hide();
                AnchorPane paymant = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
                btn_singin.getParent().getScene();
                Scene scene = new Scene(paymant);
                Stage stage = new Stage();
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
                Logger.getLogger(PayController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
