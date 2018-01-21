package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import modle.Authority;
import pojo.Application;
import pojo.Otheritiscat;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class SendToApproveController implements Initializable {

    @FXML
    private JFXComboBox<String> com_autho;

    @FXML
    private Label lbl_idApplication;

    @FXML
    private JFXButton btn_send;

    @FXML
    private JFXButton btn_done;

    Authority authority;
    pojo.Otheritiscat outo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        authority = new modle.Authority();
        // TODO
        Application app = modle.StaticBadu.getApp();
        //System.out.println(app.getIdApplication());
        loadApprovCombo();
        save();
    }

    public void loadApprovCombo() {

        List<Otheritiscat> list = authority.getList();
        ObservableList auto = FXCollections.observableArrayList();

        for (Otheritiscat otheritiscat : list) {
            auto.add(otheritiscat.getCatname());
        }
        com_autho.setItems(auto);

    }

    public void getSelected() {
        String au = com_autho.getSelectionModel().getSelectedItem();
        outo = authority.getAuthorityByAuthorityName(au);
    }

    public void save() {
        btn_send.setOnAction((event) -> {

            getSelected();
            System.out.println(outo.getCatname());
        });
    }

}