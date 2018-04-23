/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShopRent.controller;

import ShopRent.modle.ShopTable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pojo.SrBuilding;
import pojo.SrFlow;
import pojo.SrRentalType;
import pojo.SrShopNo;
import pojo.SrShopType;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class AddShopController implements Initializable {

    @FXML
    private JFXComboBox<String> com_building;
    @FXML
    private JFXComboBox<String> com_flow;
    @FXML
    private JFXTextField txt_shopNo;
    @FXML
    private JFXComboBox<String> com_type;
    @FXML
    private JFXComboBox<String> com_rentType;
    @FXML
    private JFXButton btn_add;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    ShopRent.modle.GetModle getModle;
    @FXML
    private JFXTextField txt_shopName;
    @FXML
    private TableView<ShopTable> tbl_shop;
    @FXML
    private TableColumn<ShopTable, String> col_building;
    @FXML
    private TableColumn<ShopTable, String> col_floor;
    @FXML
    private TableColumn<ShopTable, String> col_type;
    @FXML
    private TableColumn<ShopTable, String> col_rent;
    @FXML
    private TableColumn<ShopTable, String> col_number;
    @FXML
    private TableColumn<ShopTable, String> col_name;
    @FXML
    private TableColumn<ShopTable, String> col_customer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getModle = new ShopRent.modle.GetModle();
        loadBuilding();
        loadShopTypes();
        loadShopRentalTypes();
        loadTable();
    }

    @FXML
    private void clicAddButtom(ActionEvent event) {
        if (com_building.getSelectionModel().getSelectedItem() != null) {
            if (com_flow.getSelectionModel().getSelectedItem() != null) {
                if (com_type.getSelectionModel().getSelectedItem() != null) {
                    if (com_rentType.getSelectionModel().getSelectedItem() != null) {
                        if (txt_shopNo.getText().length() > 0) {

                            SrBuilding pBuilding = getModle.getBuilding().getT_By_name(com_building.getSelectionModel().getSelectedItem());
                            SrFlow pFloor = getModle.getFloor().getT_By_name(com_flow.getSelectionModel().getSelectedItem(), pBuilding);

                            if (!getModle.getShopNo().isExist(txt_shopNo.getText(), pFloor)) {
                                SrShopNo srShopNo = new pojo.SrShopNo();
                                SrRentalType pRentalType = getModle.getRentType().getT_By_name(com_rentType.getSelectionModel().getSelectedItem());
                                SrShopType pShopType = getModle.getShopType().getT_By_name(com_type.getSelectionModel().getSelectedItem());

                                srShopNo.setSrFlow(pFloor);
                                srShopNo.setSrRentalType(pRentalType);
                                srShopNo.setSrShopType(pShopType);

                                srShopNo.setShopNo(txt_shopNo.getText());
                                srShopNo.setTradeName(txt_shopName.getText());
                                srShopNo.setStatus(1);
                                srShopNo.setSyn(1);

                                if (getModle.getShopNo().save(srShopNo)) {
                                    modle.Allert.notificationGood("Added Shop", txt_shopNo.getText());
                                    loadTable();
                                } else {
                                    modle.Allert.notificationError("Something Wrong", txt_shopNo.getText());
                                }

                            } else {
                                modle.Allert.notificationError(txt_shopNo.getText() + " Shop Number already exist in " + com_flow.getSelectionModel().getSelectedItem(), "please Enter Othe Number");
                            }
                        } else {
                            modle.Allert.notificationError("Empty Shop Number", "please Enter Shop Number");
                        }
                    } else {
                        modle.Allert.notificationError("Empty Rent Type", "pleas Select Rent Type");
                    }
                } else {
                    modle.Allert.notificationError("Empty Shop Type", "pleas Select Shop Type");
                }
            } else {
                modle.Allert.notificationError("Empty Floor", "pleas Select Floor");
            }
        } else {
            modle.Allert.notificationError("Empty Building", "pleas Select Building");
        }

    }

    String building;
    String flor;
    String type;
    String rent;

    @FXML
    private void onActionBuilding(ActionEvent event) {
        List<SrFlow> list = getModle.getFloor().getList(getModle.getBuilding().getT_By_name(com_building.getSelectionModel().getSelectedItem()));
        ObservableList<String> oal = FXCollections.observableArrayList();
        for (SrFlow srFlow : list) {
            oal.add(srFlow.getFlowName());
        }
        com_flow.setItems(oal);
        building = com_building.getSelectionModel().getSelectedItem();
        loadTable();
    }

    public void loadBuilding() {
        List<SrBuilding> list = getModle.getBuilding().getList();
        ObservableList<String> oal = FXCollections.observableArrayList();
        list.forEach((srBuilding) -> {
            oal.add(srBuilding.getBuildingName());
        });
        com_building.setItems(oal);
    }

    public void loadShopTypes() {
        List<SrShopType> list = getModle.getShopType().getList();
        ObservableList<String> arrList = FXCollections.observableArrayList();
        for (SrShopType srShopType : list) {
            arrList.add(srShopType.getShopType());
        }
        com_type.setItems(arrList);
    }

    public void loadShopRentalTypes() {
        List<SrRentalType> list = getModle.getRentType().getList();
        ObservableList<String> arrList = FXCollections.observableArrayList();
        for (SrRentalType srRentalType : list) {
            arrList.add(srRentalType.getRType());
        }
        com_rentType.setItems(arrList);
    }

    @FXML
    private void onActionFloor(ActionEvent event) {
        flor = com_flow.getSelectionModel().getSelectedItem();
        loadTable();
    }

    @FXML
    private void onActionType(ActionEvent event) {
        type = com_type.getSelectionModel().getSelectedItem();
        loadTable();
    }

    @FXML
    private void onActionRentType(ActionEvent event) {
        rent = com_rentType.getSelectionModel().getSelectedItem();
        loadTable();
    }

    public void loadTable() {
        ObservableList<ShopTable> list = getModle.getShopNo().getList(building, flor, type, rent);
        HashMap<TableColumn, String> hashMap = new HashMap<>();
        hashMap.put(col_building, "buildingName");
        hashMap.put(col_floor, "floorName");
        hashMap.put(col_rent, "retType");
        hashMap.put(col_type, "shopType");
        hashMap.put(col_number, "shopNo");        
        hashMap.put(col_name, "shopName");
        hashMap.put(col_customer, "customer");        
        new ShopRent.modle.TableLoad().load(list, tbl_shop, hashMap);
    }

}
