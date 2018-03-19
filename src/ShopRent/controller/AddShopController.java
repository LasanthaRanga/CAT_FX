/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShopRent.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import modle.Strret;
import pojo.SrBuilding;
import pojo.Street;
import pojo.Ward;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class AddShopController implements Initializable {

    @FXML
    private JFXComboBox<String> com_ward;
    @FXML
    private JFXComboBox<String> com_street;
    @FXML
    private JFXComboBox<String> com_building;
    @FXML
    private JFXComboBox<String> com_flow;
    @FXML
    private JFXTextField txt_shopNo;
    @FXML
    private JFXComboBox<?> com_type;
    @FXML
    private JFXComboBox<?> com_rentType;
    @FXML
    private JFXButton btn_add;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadWardCombo();
    }

    @FXML
    private void clicAddButtom(ActionEvent event) {
    }

    public void loadWardCombo() {
        List<Ward> list = new modle.Ward().getList();
        ObservableList List = FXCollections.observableArrayList();
        list.forEach((ward) -> {
            List.add(ward.getWardName());
        });
        com_ward.setItems(List);
    }

    @FXML
    private void wardComboOnAction(ActionEvent event) {
        modle.Ward ward = new modle.Ward();
        String selectedItem = com_ward.getSelectionModel().getSelectedItem();
        ward.setWardname(selectedItem);
        ObservableList list = ward.getStreetBySelectedWard();
        com_street.setItems(list);
    }

    @FXML
    private void streetComboOnAction(ActionEvent event) {
        Street pojoStreet = new modle.Strret().getStreetsByStreetNameAndWard(com_street.getSelectionModel().getSelectedItem(), new modle.Ward().getWardByWardName(com_ward.getSelectionModel().getSelectedItem()));
        List<SrBuilding> buildings = new ShopRent.modle.Building().getBuildingListBy_StreetID(pojoStreet.getIdStreet());
        ObservableList<String> list = FXCollections.observableArrayList();
        list.clear();
        for (SrBuilding building : buildings) {
            list.add(building.getBuildingName());
        }
        com_building.setItems(list);
    }
}
