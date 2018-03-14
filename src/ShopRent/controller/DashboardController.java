/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShopRent.controller;

import ShopRent.modle.Building;
import ShopRent.modle.Street;
import ShopRent.modle.TableLoad;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import pojo.SrBuilding;
import pojo.Ward;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class DashboardController implements Initializable {

    @FXML
    private JFXButton btn_test;
    @FXML
    public TableView<?> tbl1;
    @FXML
    public TableColumn<?, ?> col1;
    @FXML
    public TableColumn<?, ?> col2;
    @FXML
    private JFXButton btnload;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void testClick(ActionEvent event) {
        Building building = new ShopRent.modle.Building();
        pojo.Street street = building.searchPojoStreetByID(1);
        if (street != null) {
            SrBuilding sb = new pojo.SrBuilding();
            sb.setStreet(street);
            sb.setBuildingName("Name");
            sb.setStatus(1);
            sb.setSyn(1);
            boolean savePojoBuilding = building.savePojoBuilding(sb);
            if (savePojoBuilding) {
                modle.Allert.notificationGood("Buillding Save", "sucsessful");
            } else {
                modle.Allert.notificationError("Eroor", "not saved bulding");
            }
        } else {
            modle.Allert.notificationError("Cheack Street", "Street not found");
        }
    }

    public class WardTable {

        /**
         * @return the wid
         */
        public String getWid() {
            return wid.get();
        }

        /**
         * @return the wname
         */
        public String getWname() {
            return wname.get();
        }

        private SimpleStringProperty wid;
        private SimpleStringProperty wname;

        public WardTable(String wid, String wname) {
            this.wid = new SimpleStringProperty(wid);
            this.wname = new SimpleStringProperty(wname);
        }

    }

    @FXML
    private void loadtable(ActionEvent event) {

        HashMap<TableColumn, String> hashMap = new HashMap<TableColumn, String>();
        hashMap.put(col1, "wid");
        hashMap.put(col2, "wname");
        java.util.List<pojo.Ward> list = new modle.Ward().loadWardTable();

        ObservableList oList = FXCollections.observableArrayList();
        for (Ward w : list) {
            oList.add(new WardTable(w.getIdWard()+"", w.getWardName()));
        }

        TableLoad tableLoad = new ShopRent.modle.TableLoad();
        tableLoad.load(oList, tbl1, hashMap);

    }

    @FXML
    private void getSelected(MouseEvent event) {
        WardTable name = (DashboardController.WardTable) tbl1.getSelectionModel().getSelectedItem();
        modle.Allert.notificationGood(name.getWname(), "");
    }

}
