/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modle.AuthUser;
import modle.UserCat;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class SelectLoginTypeController implements Initializable {

    @FXML
    private JFXComboBox<String> com_type;

    @FXML
    private JFXButton btn_log;

    List<UserCat> userCats;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList List = FXCollections.observableArrayList();
        userCats = AuthUser.getUserCats();
        for (UserCat userCat : userCats) {
            List.add(userCat.getOthoname());
        }
        com_type.setItems(List);

        btn_log.setOnAction((event) -> {
            getSelected();
        });

    }

    public void getSelected() {
        String selectedItem = com_type.getSelectionModel().getSelectedItem();
        for (UserCat userCat : userCats) {
            if (userCat.getOthoname().equals(selectedItem)) {

                try {

                    AuthUser.setIdOc(userCat.getOthoid());

                    btn_log.getParent().getScene().getWindow().hide();
                    AnchorPane paymant = javafx.fxml.FXMLLoader.load(getClass().getResource("/view/authoritist.fxml"));
                    btn_log.getParent().getScene();
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

}
