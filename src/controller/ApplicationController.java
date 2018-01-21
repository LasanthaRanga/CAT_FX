package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import modle.Customer;
import modle.SuccessMessage;
import modle.TradeType;
import modle.Ward;
import pojo.Application;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class ApplicationController implements Initializable {

    @FXML
    private JFXComboBox<String> com_ward;

    @FXML
    private JFXComboBox<String> com_street;

    @FXML
    private JFXTextField txt_assesmantNO;

    @FXML
    private JFXComboBox<String> com_trade_type;

    @FXML
    private JFXComboBox<String> com_nature;

    @FXML
    private JFXComboBox<String> com_subnature;

    @FXML
    private JFXTextField txt_cus_nic;

    @FXML
    private JFXTextField txt_cus_fname;

    @FXML
    private JFXTextField txt_ro;

    @FXML
    private JFXTextField txt_aplicaton_No;

    @FXML
    private JFXTextField txt_year;

    @FXML
    private JFXTextField txt_month;

    @FXML
    private JFXTextField txt_day;

    @FXML
    private JFXTextField txt_allocaton;

    @FXML
    private JFXTextField txt_trade_name;

    @FXML
    private JFXTextField txt_adl1;

    @FXML
    private JFXTextField txt_adl2;

    @FXML
    private JFXTextField txt_adl3;

    @FXML
    private JFXTextField txt_taxt_amount;

    @FXML
    private JFXTextArea txt_discription;

    @FXML
    private JFXButton btn_save_app;

    @FXML
    private StackPane stackPain;

    modle.Ward ward = null;
    modle.CustomerHasAssesment cha = null;
    modle.Customer customer = null;
    modle.TradeType tt = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ward = new modle.Ward();
        cha = new modle.CustomerHasAssesment();
        customer = new Customer();
        btn_save_app.setOnAction((event) -> {

        });

        loadWard();
        loadTradeCombo();
        getTradyType();

    }

    public void saveAplication() {
        Application ap = new pojo.Application();

    }

    public void loadWard() {
        List<pojo.Ward> loaWards = ward.loadWardTable();
        ObservableList List = FXCollections.observableArrayList();

        for (pojo.Ward wa : loaWards) {
            List.add(wa.getWardName());

        }

        com_ward.setItems(List);

        loadStrret();

    }

    public void loadStrret() {
        com_ward.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String selectedItem = com_ward.getSelectionModel().getSelectedItem();
                ward.setWardname(selectedItem);
                ObservableList list = ward.getStreetBySelectedWard();
                com_street.setItems(list);
            }
        });
    }

    String selectedStreet = null;
    String selectedWard = null;

    public void getSelectedWaredStrret() {
        selectedWard = com_ward.getSelectionModel().getSelectedItem();
        selectedStreet = com_street.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void keyListener(KeyEvent event) {
        int length = txt_assesmantNO.getLength();
        // System.out.println(length);

        if (event.getCode() == KeyCode.ENTER) {
            System.out.println("ENTER GEHUWAA");
            searchCustomerByAssesment();
        }

    }

    @FXML
    public void nicCheack(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            System.out.println("ENTER GEHUWAA");
            searchCustomerByNIC();
        }
    }

    public void searchCustomerByAssesment() {
        getSelectedWaredStrret();
        String asno = txt_assesmantNO.getText();
        cha.setAssesment(asno);
        cha.setStreet(selectedStreet);
        cha.setWard(selectedWard);

        customer = cha.searchCustometByAssesmentAndWardStrret();
        txt_cus_fname.setText(customer.getFullName());
        txt_cus_nic.setText(customer.getNic());
        //  setCustometData();

    }

    @FXML
    public void searchCustomerByNIC() {

        String text = txt_cus_nic.getText();
        customer = new modle.Customer();
        customer.setNic(text);

        Customer cus = customer.searchCustomerByNic();
        // upcus = cus;
        if (cus.getFullName() == null) {
            //   modle.Allert.messagInfo(stakpain, "Not Found", "404");
        } else {
            //  modle.Allert.messagSuccsess(stakpain, "Found Customer", text);
        }
        if (cus != null) {
            txt_cus_fname.setText(cus.getFullName());

        }
    }

    public void loadTradeCombo() {
        List<pojo.TradeType> loadTreadType = new modle.TradeType().loadTreadType();
        ObservableList List = FXCollections.observableArrayList();
        for (pojo.TradeType tradeType1 : loadTreadType) {
            List.add(tradeType1.getTypeName());
        }

        com_trade_type.setItems(List);
    }

    public void getTradyType() {
        com_trade_type.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String selectedItem = com_trade_type.getSelectionModel().getSelectedItem();
                pojo.TradeType ptt = new TradeType().loadTreadType(selectedItem);

            }
        });
    }

}
