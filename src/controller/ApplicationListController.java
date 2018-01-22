/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import modle.ApplicationStatus;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class ApplicationListController implements Initializable {

    @FXML
    private TableView<?> tbl_applicaion;

    @FXML
    private TableColumn<?, ?> c_idApp;

    @FXML
    private TableColumn<?, ?> c_type;

    @FXML
    private TableColumn<?, ?> c_nature;

    @FXML
    private TableColumn<?, ?> c_alocation;

    @FXML
    private TableColumn<?, ?> c_tax;

    @FXML
    private TableColumn<?, ?> c_tname;

    @FXML
    private TableColumn<?, ?> c_approve;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    
    
    public class App {

        /**
         * @return the id
         */
        public int getId() {
            return id;
        }

        /**
         * @return the statues
         */
        public String getStatues() {
            if (statues == 0) {
                return "Pending";
            } else if (statues == 1) {
                return "approve";
            } else if (statues == 2) {
                return "Non Approve";
            } else {
                return null;
            }

        }

        /**
         * @return the autho
         */
        public String getAutho() {
            return autho.get();
        }

        /**
         * @return the date
         */
        public String getDate() {
            return date.get();
        }
        private int id;
        private int statues;
        private SimpleStringProperty autho;
        private SimpleStringProperty date;

        

    }
    ObservableList natureList = FXCollections.observableArrayList();

    public void loadTable() {
//        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
//        col_approve_by.setCellValueFactory(new PropertyValueFactory<>("autho"));
//        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
//        col_statues.setCellValueFactory(new PropertyValueFactory<>("statues"));
//
//        ApplicationStatus applicationStatus = new modle.ApplicationStatus();
//        List<modle.Approve> list = applicationStatus.getListByApplication(app);
//
//        natureList.clear();
//        for (modle.Approve tnn : list) {
//            natureList.add(new approve(tnn.getIdApprove(), tnn.getStatues(), tnn.getOutherCatName(), tnn.getDate()));
//        }
//        tbl_approve.setItems(natureList);
    }

    
    
    

}
