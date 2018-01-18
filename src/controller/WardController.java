/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import modle.Strret;

import modle.Ward;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class WardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    //ward+++++++=
    @FXML
    private JFXTextField txt_wardname;

    @FXML
    private JFXButton btn_ward_add;

    @FXML
    private AnchorPane anc_ward;

    @FXML
    private JFXButton btn_ward_update;

    @FXML
    private JFXButton btn_ward_delete;

    @FXML
    private TableView<WardTable> table_ward;

    @FXML
    private TableColumn<WardTable, String> idward;

    @FXML
    private TableColumn<WardTable, String> wardname;

    // Strret============
    @FXML
    private JFXButton btn_add_Street;

    @FXML
    private JFXTextField txt_strret;

    @FXML
    private JFXButton btn_street_add;

    @FXML
    private JFXButton btn_street_update;

    @FXML
    private JFXButton btn_street_delete;

    @FXML
    private TableView<StrretTable> tbl_strret;

    @FXML
    private TableColumn<StrretTable, String> col_idStreet;

    @FXML
    private TableColumn<StrretTable, String> col_streetName;

    //--------------------
    int idWard = 0;
    int idStrret = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addWard();
        load();
        getSelectedWoard();
        update();
        delete();
        txt_strret.setDisable(true);
        btn_street_add.setDisable(true);
        btn_ward_update.setDisable(true);
        btn_street_delete.setDisable(true);
        btn_ward_delete.setDisable(true);
        btn_street_update.setDisable(true);
        btn_ward_add.setDisable(true);
        saveStreet();
        selectStreet();
        updateStrrt();
        strretDeactive();
        disableStrrt();

    }

    public void disableStrrt() {
        txt_strret.setDisable(true);
        btn_street_add.setDisable(true);
        btn_street_update.setDisable(true);
        btn_street_delete.setDisable(true);
    }

    public void annableStrrt() {
        txt_strret.setDisable(false);
        btn_street_add.setDisable(false);
        //  btn_street_update.setDisable(false);
        // btn_street_delete.setDisable(false);
    }

    public void addWard() {
        btn_ward_add.setOnAction((ActionEvent event) -> {
            String text = txt_wardname.getText();
            if (text.length() > 2) {
                Ward ward = new modle.Ward();
                ward.setWardname(text);
                int saveWaard = ward.saveWaard();
                if (saveWaard > 0) {
                    modle.Allert.messageSuccess();
                    load();
                    txt_wardname.setText(null);
                }
            } else {
                System.out.println("Null Ewa Epoo");
            }
        });
        disableStrrt();
    }

    public void getSelectedWoard() {
        table_ward.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int selectedIndex = table_ward.getSelectionModel().getSelectedIndex();
                String wid = table_ward.getSelectionModel().getSelectedItem().getWid();
                String wnme = table_ward.getSelectionModel().getSelectedItem().getWname();
                idWard = Integer.parseInt(wid);
                txt_wardname.setText(wnme);
                txt_strret.setText(null);
                annableStrrt();
                btn_ward_update.setDisable(false);
                btn_ward_delete.setDisable(false);
                idStrret = 0;
                loadStrrt();
            }
        });
    }

    public void message() {

    }

    public void update() {
        btn_ward_update.setOnAction((ActionEvent event) -> {
            Ward ward = new modle.Ward();
            if (idWard != 0) {
                ward.setIdWard(idWard);
                ward.setWardname(txt_wardname.getText());
                ward.updateWard();
                idWard = 0;
                load();
                txt_wardname.setText(null);
                disableStrrt();
            }
        });
    }

    public void delete() {
        btn_ward_delete.setOnAction((ActionEvent event) -> {
            Ward ward = new modle.Ward();
            if (idWard != 0) {
                ward.setIdWard(idWard);
                ward.deactivWard();
                idWard = 0;
                load();
                txt_wardname.setText(null);
                disableStrrt();
            }
        });
    }

    public void saveStreet() {
        btn_street_add.setOnAction((ActionEvent event) -> {
            if (idWard != 0) {
                Strret strret = new modle.Strret();
                strret.setIdWard(idWard);
                strret.setStreetName(txt_strret.getText());
                strret.saveStreet();
                loadStrrt();
                idStrret = 0;
                txt_strret.setText(null);
            }
        });

    }

    //=====================================
    ObservableList List = FXCollections.observableArrayList();

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

    public void load() {
        idward.setCellValueFactory(new PropertyValueFactory<>("wid"));
        wardname.setCellValueFactory(new PropertyValueFactory<>("wname"));

        Ward ward = new modle.Ward();
        java.util.List<pojo.Ward> list = ward.loadWardTable();
        List.clear();
        for (pojo.Ward w : list) {
            List.add(new WardTable(w.getIdWard() + "", w.getWardName()));
        }
        table_ward.setItems(List);
        loadStrrt();
    }

//+++++++++++++++++++++++++++++++++++++++++++++++++++
//==========================Strret Table Load
    ObservableList StList = FXCollections.observableArrayList();

    public class StrretTable {

        private SimpleStringProperty sid;
        private SimpleStringProperty sname;

        /**
         * @return the sid
         */
        public String getSid() {
            return sid.get();
        }

        /**
         * @return the sname
         */
        public String getSname() {
            return sname.get();
        }

        public StrretTable(String sid, String sname) {
            this.sid = new SimpleStringProperty(sid);
            this.sname = new SimpleStringProperty(sname);
        }

    }

    public void loadStrrt() {
        col_idStreet.setCellValueFactory(new PropertyValueFactory<>("sid"));
        col_streetName.setCellValueFactory(new PropertyValueFactory<>("sname"));
        modle.Strret street = new modle.Strret();
        street.setIdWard(idWard);
        java.util.List<pojo.Street> list = street.loadStrretTable();
        StList.clear();
        for (pojo.Street w : list) {
            StList.add(new StrretTable(w.getIdStreet() + "", w.getStreetName()));
        }
        tbl_strret.setItems(StList);

    }

    public void selectStreet() {
        tbl_strret.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int selectedIndex = tbl_strret.getSelectionModel().getSelectedIndex();
                String sid = tbl_strret.getSelectionModel().getSelectedItem().getSid();
                String snme = tbl_strret.getSelectionModel().getSelectedItem().getSname();
                idStrret = Integer.parseInt(sid);
                txt_strret.setText(snme);
                btn_street_update.setDisable(false);
                btn_street_delete.setDisable(false);
            }
        });

    }

//==============================
    public void updateStrrt() {
        btn_street_update.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Strret strret = new modle.Strret();
                strret.setIdStrret(idStrret);
                strret.setStreetName(txt_strret.getText());
                strret.updateStrret();
                loadStrrt();
                idStrret = 0;
                txt_strret.setText(null);

                btn_street_update.setDisable(true);
                btn_street_delete.setDisable(true);
            }
        });

    }

    public void strretDeactive() {
        btn_street_delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Strret strret = new modle.Strret();
                strret.setIdStrret(idStrret);
                strret.setStreetName(txt_strret.getText());
                strret.updateDeactive();
                loadStrrt();
                idStrret = 0;
                txt_strret.setText(null);
                btn_street_update.setDisable(true);
                btn_street_delete.setDisable(true);
            }
        });

    }

    @FXML
    public void keyListener(KeyEvent event) {

        int length = txt_wardname.getLength();

        System.out.println(length);

        if (length <= 3) {
            btn_ward_add.setDisable(true);
            btn_street_add.setDisable(true);
        } else {
            btn_ward_add.setDisable(false);
        }

    }

}
