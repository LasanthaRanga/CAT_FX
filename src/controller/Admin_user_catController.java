/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pojo.Catagory;
import pojo.Otheritiscat;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class Admin_user_catController implements Initializable {

    @FXML
    private JFXTextField txt_catagory;

    @FXML
    private JFXButton btn_add_cat;

    @FXML
    private TableView<Cat> tbl_catagory;

    @FXML
    private TableColumn<Cat, Integer> col_id_cat;

    @FXML
    private TableColumn<Cat, String> col_cat;

    @FXML
    private JFXButton btn_add_department;

    @FXML
    private TableView<Dep> tbl_department;

    @FXML
    private TableColumn<Dep, Integer> col_id_dep;

    @FXML
    private TableColumn<Dep, String> col_dep;

    @FXML
    private JFXTextField txt_department;

    @FXML
    private JFXTextField txt_authorities;

    @FXML
    private JFXButton btn_add_authorities;

    @FXML
    private TableView<?> tbl_authorities;

    @FXML
    private TableColumn<?, ?> col_id_authorities;

    @FXML
    private TableColumn<?, ?> col_authorities;

    modle.Catagory cat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cat = new modle.Catagory();

        btn_add_cat.setOnAction((event) -> {
            if (txt_catagory.getText().length() > 1) {
                if (cat.save(txt_catagory.getText())) {
                    modle.Allert.notificationGood("Saved", txt_catagory.getText());
                    loadCatTbl();
                    txt_catagory.setText("");
                } else {
                    modle.Allert.notificationError("Fail", txt_catagory.getText());
                }
            } else {
                modle.Allert.notificationInfo("Empty Catagory", "Fil The Catagory Name");
            }
        });
        loadCatTbl();

        btn_add_department.setOnAction((event) -> {

            if (txt_department.getText().length() > 1) {

                pojo.Department department = new pojo.Department(txt_department.getText(), null, 1, 1, null);
                if (new modle.Department().save(department)) {
                    modle.Allert.notificationGood("Added", department.getDepartment());
                    loadDepTbl();
                    txt_department.setText("");
                } else {
                    modle.Allert.notificationError("Error", txt_department.getText());
                }
            } else {
                modle.Allert.notificationInfo("Empty Departmant", "Fil The Dipartmant Name");
            }

        });
        loadDepTbl();

        btn_add_authorities.setOnAction((event) -> {

            if (txt_authorities.getText().length() > 1) {
                Otheritiscat otheritiscat = new pojo.Otheritiscat(txt_authorities.getText(), "", 1, 1, null);
                if (new modle.Authority().save(otheritiscat)) {
                    modle.Allert.notificationGood("Added", otheritiscat.getCatname());
                    txt_authorities.setText("");
                    loadOtherTable();
                } else {
                    modle.Allert.notificationError("Error", txt_authorities.getText());
                }
            }else{
                 modle.Allert.notificationInfo("Empty Autorities", "Fil TheAutorities Name");
            }
        });
        loadOtherTable();

    }

    @FXML
    public void addCatClick() {

    }

    public class Cat {

        /**
         * @return the id
         */
        public int getId() {
            return id;
        }

        /**
         * @return the cat
         */
        public String getCat() {
            return cat.get();
        }

        /**
         * @return the statues
         */
        public int getStatues() {
            return statues;
        }

        private int id;
        private SimpleStringProperty cat;
        private int statues;

        public Cat(int id, String cat, int statues) {
            this.id = id;
            this.cat = new SimpleStringProperty(cat);
            this.statues = statues;
        }

    }
    ObservableList catList = FXCollections.observableArrayList();

    public void loadCatTbl() {
        col_id_cat.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_cat.setCellValueFactory(new PropertyValueFactory<>("cat"));
        List<pojo.Catagory> loadCatListForTable = cat.loadCatListForTable();
        catList.clear();
        for (Catagory catagory : loadCatListForTable) {
            Cat cat1 = new Cat(catagory.getIdCatagory(), catagory.getCatagoryName(), catagory.getStatus());
            catList.add(cat1);
        }
        tbl_catagory.setItems(catList);

    }

    public class Dep {

        /**
         * @return the id
         */
        public int getId() {
            return id;
        }

        /**
         * @return the dip
         */
        public String getDip() {
            return dip.get();
        }

        private int id;
        private SimpleStringProperty dip;

        public Dep(int id, String dip) {
            this.id = id;
            this.dip = new SimpleStringProperty(dip);
        }

    }

    ObservableList depList = FXCollections.observableArrayList();

    public void loadDepTbl() {
        col_id_dep.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_dep.setCellValueFactory(new PropertyValueFactory<>("dip"));
        List<pojo.Department> loadCatListForTable = new modle.Department().getList();

        depList.clear();
        for (pojo.Department dip : loadCatListForTable) {
            Dep d = new Dep(dip.getIdDepartment(), dip.getDepartment());
            depList.add(d);
        }
        tbl_department.setItems(depList);

    }

    public class Other {

        /**
         * @return the id
         */
        public int getId() {
            return id;
        }

        /**
         * @return the Othercat
         */
        public String getOthercat() {
            return Othercat.get();
        }

        private int id;
        private SimpleStringProperty Othercat;

        public Other(int id, String Othercat) {
            this.id = id;
            this.Othercat = new SimpleStringProperty(Othercat);
        }

    }
    ObservableList olist = FXCollections.observableArrayList();

    public void loadOtherTable() {
        col_id_authorities.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_authorities.setCellValueFactory(new PropertyValueFactory<>("Othercat"));

        List<Otheritiscat> list = new modle.Authority().getList();
        olist.clear();
        for (Otheritiscat otheritiscat : list) {
            olist.add(new Other(otheritiscat.getIdOtheritisCat(), otheritiscat.getCatname()));
        }
        tbl_authorities.setItems(olist);
    }

}
