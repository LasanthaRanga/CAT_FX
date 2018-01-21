package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import modle.Authority;
import pojo.Application;
import pojo.Apprualstatues;
import pojo.Otheritiscat;
import pojo.User;

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
    String au;
    pojo.Application app;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        authority = new modle.Authority();
        // TODO
        app = modle.StaticBadu.getApp();
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
        au = com_autho.getSelectionModel().getSelectedItem();
        outo = authority.getAuthorityByAuthorityName(au);
    }

    public void save() {
        btn_send.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getSelected();

                Apprualstatues apprualstatues = new pojo.Apprualstatues();
                apprualstatues.setApplication(app);
                apprualstatues.setDate(new Date());
                apprualstatues.setIdOtheritisCat(outo.getIdOtheritisCat());
                apprualstatues.setStatues(0);
                apprualstatues.setSyn(1);

                boolean save = new modle.ApplicationStatus().save(apprualstatues);

                if (save) {
                    modle.Allert.notificationGood("Send To", au);
                } else {
                    modle.Allert.notificationError("error", au);
                }
            }
        });
    }

}
