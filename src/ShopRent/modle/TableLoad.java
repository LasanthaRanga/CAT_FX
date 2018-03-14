/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShopRent.modle;

import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pojo.Ward;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class TableLoad {

    ObservableList List = FXCollections.observableArrayList();
    List<pojo.Ward> wList;

    public void load(List list, ShopRent.controller.DashboardController dashboardController) {
        wList = (List<Ward>) list;
        dashboardController.col1.setCellValueFactory(new PropertyValueFactory<>("wid"));
        dashboardController.col2.setCellValueFactory(new PropertyValueFactory<>("wname"));

        List.clear();
        for (pojo.Ward w : wList) {
            List.add(new WardTable(w.getIdWard() + "", w.getWardName()));
        }
        dashboardController.tbl1.setItems(List);

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

}
