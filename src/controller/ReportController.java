/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import pojo.Application;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class ReportController implements Initializable {

    @FXML
    private JFXButton btn_arrears;
    @FXML
    private JFXDatePicker form;
    @FXML
    private JFXDatePicker to;
    @FXML
    private JFXButton btn_ledger;
    @FXML
    private JFXButton btn_cancle;
    @FXML
    private JFXComboBox<String> com_ro;
    @FXML
    private JFXButton btn_withRO;
    @FXML
    private JFXComboBox<String> com_ro1;
    @FXML
    private JFXButton btn_arrearsByRO;
    @FXML
    private JFXDatePicker pay_end;
    @FXML
    private JFXComboBox<String> com_TradeType;
    @FXML
    private JFXListView<String> list_AppNo;
    @FXML
    private JFXTextField txt_appNo;
    @FXML
    private JFXButton btn_arrearsByRO1;
    @FXML
    private Label selected_No;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadROcombo();
        loadTradeTypeCombo();
    }

    public void loadROcombo() {
        ObservableList aList = FXCollections.observableArrayList();
        List<String> roList = new modle.RO().getRoList();
        for (String string : roList) {
            aList.add(string);
        }
        com_ro.setItems(aList);
        com_ro1.setItems(aList);
    }

    public void loadTradeTypeCombo() {
        List<pojo.TradeType> loadTreadType = new modle.TradeType().loadTreadType();
        ObservableList List = FXCollections.observableArrayList();
        loadTreadType.forEach((tradeType1) -> {
            List.add(tradeType1.getTypeName());
        });
        com_TradeType.setItems(List);
    }

    @FXML
    private void arrearsLatter(ActionEvent event) {
        Instant instant = Instant.from(pay_end.getValue().atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        String fromString = new SimpleDateFormat("yyyy-MM-dd").format(date);
        new modle.Report().ariasLatterWithTradeType(fromString, com_TradeType.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void ledger(ActionEvent event) {
        Instant instant = Instant.from(form.getValue().atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        String fromString = new SimpleDateFormat("yyyy-MM-dd").format(date);

        Instant instantto = Instant.from(to.getValue().atStartOfDay(ZoneId.systemDefault()));
        Date date2 = Date.from(instantto);
        String toString = new SimpleDateFormat("yyyy-MM-dd").format(date2);

        new modle.Report().ledger(fromString, toString);

    }

    @FXML
    private void cancelPaymant(ActionEvent event) {
        Instant instant = Instant.from(form.getValue().atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        String fromString = new SimpleDateFormat("yyyy-MM-dd").format(date);

        Instant instantto = Instant.from(to.getValue().atStartOfDay(ZoneId.systemDefault()));
        Date date2 = Date.from(instantto);
        String toString = new SimpleDateFormat("yyyy-MM-dd").format(date2);

        new modle.Report().cancle(fromString, toString);

    }

    @FXML
    private void withRO(ActionEvent event) {

        Instant instant = Instant.from(form.getValue().atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        String fromString = new SimpleDateFormat("yyyy-MM-dd").format(date);

        Instant instantto = Instant.from(to.getValue().atStartOfDay(ZoneId.systemDefault()));
        Date date2 = Date.from(instantto);
        String toString = new SimpleDateFormat("yyyy-MM-dd").format(date2);

        new modle.Report().paymant_with_RO(fromString, toString, com_ro.getSelectionModel().getSelectedItem());

    }

    @FXML
    private void arrearsLatterByRO(ActionEvent event) {

        Instant instant = Instant.from(pay_end.getValue().atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        String fromString = new SimpleDateFormat("yyyy-MM-dd").format(date);
        new modle.Report().ariasLatter(com_ro1.getSelectionModel().getSelectedItem(), fromString, com_TradeType.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void selectTradeType(ActionEvent event) {
        String selectedItem = com_TradeType.getSelectionModel().getSelectedItem();
        List<Application> applications = new modle.Aplication().getApplicationNoList(selectedItem);
        ObservableList<String> aplicationNoList = FXCollections.observableArrayList();
        aplicationNoList.clear();
        for (Application application : applications) {
            aplicationNoList.add(application.getApplicationNo());
        }
        list_AppNo.setItems(aplicationNoList);

    }

    @FXML
    private void getSelectedApp(MouseEvent event) {
        selected_No.setText(list_AppNo.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void searchAppNo(KeyEvent event) {

        String selectedItem = com_TradeType.getSelectionModel().getSelectedItem();
        List<Application> applications = new modle.Aplication().getApplicationNoList(selectedItem, txt_appNo.getText(), com_ro1.getSelectionModel().getSelectedItem());
        ObservableList<String> aplicationNoList = FXCollections.observableArrayList();
        aplicationNoList.clear();
        for (Application application : applications) {
            aplicationNoList.add(application.getApplicationNo());
        }
        list_AppNo.setItems(aplicationNoList);

    }

    @FXML
    private void arrearsLatterSelectedOne(ActionEvent event) {

        Instant instant = Instant.from(pay_end.getValue().atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        String fromString = new SimpleDateFormat("yyyy-MM-dd").format(date);
        new modle.Report().ariasLatterOne(selected_No.getText(), fromString);

    }



    @FXML
    private void searchAppNo2(ActionEvent event) {
        String selectedItem = com_TradeType.getSelectionModel().getSelectedItem();
        List<Application> applications = new modle.Aplication().getApplicationNoList(selectedItem, txt_appNo.getText(), com_ro1.getSelectionModel().getSelectedItem());
        ObservableList<String> aplicationNoList = FXCollections.observableArrayList();
        aplicationNoList.clear();
        for (Application application : applications) {
            aplicationNoList.add(application.getApplicationNo());
        }
        list_AppNo.setItems(aplicationNoList);
    }

}
