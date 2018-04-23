/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShopRent.controller;

import ShopRent.modle.GetModle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import modle.Strret;
import pojo.SrBuilding;
import pojo.SrFlow;
import pojo.Street;
import pojo.Ward;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class AddBuildingController implements Initializable {

    @FXML
    private JFXComboBox<String> com_ward;
    @FXML
    private JFXComboBox<String> com_street;
    @FXML
    private JFXTextField txt_building;
    @FXML
    private TableView<?> tbl_building;
    @FXML
    private TableColumn<?, ?> col_ward;
    @FXML
    private TableColumn<?, ?> col_street;
    @FXML
    private TableColumn<?, ?> col_building;
    @FXML
    private JFXButton btn_addBuilding;
    @FXML
    private JFXButton btn_updateBuilding;

    @FXML
    private TableView<?> tbl_Floor;
    @FXML
    private TableColumn<?, ?> col_floor;
    @FXML
    private TableColumn<?, ?> col_shop;
    @FXML
    private JFXTextField txt_floor;
    @FXML
    private JFXButton btn_addFloor;
    @FXML
    private JFXButton btn_updateFlor;

    public static java.util.logging.Logger logger;
    ShopRent.modle.GetModle getModle;
    pojo.SrBuilding selectedSrBuilding;
    pojo.SrFlow selectedFloor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        getModle = new ShopRent.modle.GetModle();
        loadWard();
        loadBuilidTable();
        logger = Logger.getLogger(AddShopController.class.getName());

    }

    public void loadWard() {
        List<Ward> list = getModle.getWard().getList();
        ObservableList List = FXCollections.observableArrayList();
        list.forEach((ward) -> {
            List.add(ward.getWardName());
        });
        com_ward.setItems(List);
    }

    @FXML
    private void addBuilding(ActionEvent event) {
        if (com_ward.getSelectionModel().getSelectedItem() != null && com_street.getSelectionModel().getSelectedItem() != null) {
            if (txt_building.getText().length() > 1) {
                if (!getModle.getBuilding().isExist(txt_building.getText())) {
                    SrBuilding building = new pojo.SrBuilding();
                    Strret strret = getModle.getStrret();
                    Ward ward = getModle.getWard().getWardByWardName(com_ward.getSelectionModel().getSelectedItem());
                    Street street = strret.getStreetsByStreetNameAndWard(com_street.getSelectionModel().getSelectedItem(), ward);
                    building.setStreet(street);
                    building.setBuildingName(txt_building.getText());
                    building.setStatus(1);
                    building.setSyn(1);
                    boolean save = getModle.getBuilding().save(building);
                    if (save) {
                        modle.Allert.notificationGood("Save", txt_building.getText());
                        loadBuilidTable();
                        // modle.MyLogger.loggerWrite(logger, Level.INFO, "Save Building -- " + txt_building.getText());
                    } else {
                        modle.Allert.notificationError("Something Wrong", txt_building.getText());
                    }
                } else {
                    modle.Allert.notificationInfo("Building Name Ollredy Exist", txt_building.getText());
                }
            } else {
                modle.Allert.notificationInfo("Empty Building Name", "Plese Enter Name Of Building");
            }
        } else {
            modle.Allert.notificationInfo("Select Ward And Street", "");
        }
    }

    @FXML
    private void updateBuilding(ActionEvent event) {
        if (selectedSrBuilding != null) {
            if (com_ward.getSelectionModel().getSelectedItem() != null && com_street.getSelectionModel().getSelectedItem() != null) {
                if (txt_building.getText().length() > 1) {
                    if (!getModle.getBuilding().isExist(txt_building.getText())) {
                        SrBuilding building = selectedSrBuilding;
                        Strret strret = getModle.getStrret();
                        Ward ward = getModle.getWard().getWardByWardName(com_ward.getSelectionModel().getSelectedItem());
                        Street street = strret.getStreetsByStreetNameAndWard(com_street.getSelectionModel().getSelectedItem(), ward);
                        building.setStreet(street);
                        building.setBuildingName(txt_building.getText());
                        building.setStatus(1);
                        building.setSyn(1);
                        boolean save = getModle.getBuilding().update(building);
                        if (save) {
                            modle.Allert.notificationGood("Save", txt_building.getText());
                            loadBuilidTable();
                        } else {
                            modle.Allert.notificationError("Something Wrong", txt_building.getText());
                        }
                    } else {
                        modle.Allert.notificationInfo("Building Name Ollredy Exist", txt_building.getText());
                    }
                } else {
                    modle.Allert.notificationInfo("Empty Building Name", "Plese Enter Name Of Building");
                }
            } else {
                modle.Allert.notificationInfo("Select Ward And Street", "");
            }
        } else {
            modle.Allert.notificationInfo("Empty Selected For Update", "Please Select Building");
        }

    }

    @FXML
    private void onActionWard(ActionEvent event) {
        modle.Ward ward = getModle.getWard();
        String selectedItem = com_ward.getSelectionModel().getSelectedItem();
        ward.setWardname(selectedItem);
        ObservableList list = ward.getStreetBySelectedWard();
        com_street.setItems(list);
    }

    @FXML
    private void selectBuilding(MouseEvent event) {
        BuildingTable building = (BuildingTable) tbl_building.getSelectionModel().getSelectedItem();
        String buildngName = building.getBuildngName();
        com_ward.getSelectionModel().select(building.getWardName());
        com_street.getSelectionModel().select(building.getStreetName());
        txt_building.setText(buildngName);
        selectedSrBuilding = getModle.getBuilding().getT_By_Id(building.getIdBuilding());
        loadFloorTable();
    }

    @FXML
    private void addFloor(ActionEvent event) {
        if (selectedSrBuilding != null) {
            if (txt_floor.getText().length() > 0) {
                if (!getModle.getFloor().isExist(txt_floor.getText(), selectedSrBuilding.getIdBuilding())) {
                    SrFlow srFlow = new pojo.SrFlow(selectedSrBuilding);
                    srFlow.setFlowName(txt_floor.getText());
                    srFlow.setSyn(1);
                    srFlow.setStatus(1);
                    if (getModle.getFloor().save(srFlow)) {
                        modle.Allert.notificationGood("Save", txt_floor.getText());
                        loadFloorTable();
                    } else {
                        modle.Allert.notificationError("Something Wrong", txt_floor.getText());
                    }
                } else {
                    modle.Allert.notificationError("Olredy Exist", txt_floor.getText());
                }
            } else {
                modle.Allert.notificationError("Please Enter Floor Name", "Floor Name Is Empty");
            }
        } else {
            modle.Allert.notificationError("NO Selected Buildign", "please Select The building");
        }
    }

    @FXML
    private void updateFloor(ActionEvent event) {
        if (selectedFloor != null) {
            if (selectedSrBuilding != null) {
                if (txt_floor.getText().length() > 0) {
                    if (!getModle.getFloor().isExist(txt_floor.getText(), selectedSrBuilding.getIdBuilding())) {
                        SrFlow srFlow = getModle.getFloor().getT_By_Id(selectedFloor.getIdFlow());
                        srFlow.setFlowName(txt_floor.getText());
                        srFlow.setSrBuilding(getModle.getBuilding().getT_By_Id(selectedSrBuilding.getIdBuilding()));
                        srFlow.setSyn(1);
                        srFlow.setStatus(1);
                        if (getModle.getFloor().update(srFlow)) {
                            modle.Allert.notificationGood("Update", txt_floor.getText());
                            loadFloorTable();
                        } else {
                            modle.Allert.notificationError("Something Wrong", txt_floor.getText());
                        }
                    } else {
                        modle.Allert.notificationError("Olredy Exist", txt_floor.getText());
                    }
                } else {
                    modle.Allert.notificationError("Please Enter Floor Name", "Floor Name Is Empty");
                }
            } else {
                modle.Allert.notificationError("NO Selected Buildign", "please Select The building");
            }
        } else {
            modle.Allert.notificationInfo("Not Selected Floor", "please Select Floor");
        }

    }

    @FXML
    private void selectFloor(MouseEvent event) {
        FloorTable floor = (FloorTable) tbl_Floor.getSelectionModel().getSelectedItem();
        txt_floor.setText(floor.getFlorName());
        selectedFloor = getModle.getFloor().getT_By_Id(floor.getIdFlor());
    }

    public class BuildingTable {

        private final SimpleStringProperty wardName;
        private final int idWared;
        private final SimpleStringProperty streetName;
        private final int idStreet;
        private final SimpleStringProperty buildngName;
        private final int idBuilding;

        /**
         * @return the wardName
         */
        public String getWardName() {
            return wardName.get();
        }

        /**
         * @return the idWared
         */
        public int getIdWared() {
            return idWared;
        }

        /**
         * @return the streetName
         */
        public String getStreetName() {
            return streetName.get();
        }

        /**
         * @return the idStreet
         */
        public int getIdStreet() {
            return idStreet;
        }

        /**
         * @return the buildngName
         */
        public String getBuildngName() {
            return buildngName.get();
        }

        /**
         * @return the idBuilding
         */
        public int getIdBuilding() {
            return idBuilding;
        }

        public BuildingTable(String wardName, int idWared, String streetName, int idStreet, String buildngName, int idBuilding) {
            this.wardName = new SimpleStringProperty(wardName);
            this.idWared = idWared;
            this.streetName = new SimpleStringProperty(streetName);
            this.idStreet = idStreet;
            this.buildngName = new SimpleStringProperty(buildngName);
            this.idBuilding = idBuilding;
        }

    }

    public void loadBuilidTable() {
        HashMap<TableColumn, String> hashMap = new HashMap<>();
        hashMap.put(col_ward, "wardName");
        hashMap.put(col_street, "streetName");
        hashMap.put(col_building, "buildngName");

        ObservableList<Object> obl = FXCollections.observableArrayList();
        List<SrBuilding> list = getModle.getBuilding().getList();
        for (SrBuilding srBuilding : list) {
            obl.add(new BuildingTable(
                    getModle.getBuilding().getWardByBuilidingID(srBuilding.getIdBuilding()),
                    getModle.getBuilding().getWardIdByBuilidingID(srBuilding.getIdBuilding()),
                    getModle.getBuilding().getStreetByBuilidingID(srBuilding.getIdBuilding()),
                    getModle.getBuilding().getStreetIDByBuilidingID(srBuilding.getIdBuilding()),
                    srBuilding.getBuildingName(),
                    srBuilding.getIdBuilding())
            );
        }
        new ShopRent.modle.TableLoad().load(obl, tbl_building, hashMap);
    }

    public class FloorTable {

        public FloorTable(int idFlor, String florName, int shopCount) {
            this.idFlor = idFlor;
            this.florName = new SimpleStringProperty(florName);
            this.shopCount = shopCount;
        }

        /**
         * @return the idFlor
         */
        public int getIdFlor() {
            return idFlor;
        }

        /**
         * @return the florName
         */
        public String getFlorName() {
            return florName.get();
        }

        /**
         * @return the shopCount
         */
        public int getShopCount() {
            return shopCount;
        }
        private final int idFlor;
        private final SimpleStringProperty florName;
        private final int shopCount;

    }

    public void loadFloorTable() {
        HashMap<TableColumn, String> hashMap = new HashMap<>();
        hashMap.put(col_floor, "florName");
        hashMap.put(col_shop, "shopCount");

        ObservableList<Object> obl = FXCollections.observableArrayList();
        List<SrFlow> list = getModle.getFloor().getList(selectedSrBuilding);
        for (SrFlow srFlow : list) {
            obl.add(new FloorTable(srFlow.getIdFlow(), srFlow.getFlowName(), getModle.getShopNo().shopCount(srFlow)));
        }
        new ShopRent.modle.TableLoad().load(obl, tbl_Floor, hashMap);
    }

}
