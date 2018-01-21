package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import modle.Customer;
import modle.CustomerHasAssesment;
import modle.Nature;
import org.controlsfx.control.textfield.TextFields;
import pojo.TradeNature;
import pojo.Ward;

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

    pojo.Ward pward = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadWardCombo();
        loadStrretCombo();
        loadTreadTypeCombo();
        getTradyType();
        getSelectedNature();

    }

    public void loadWardCombo() {
        List<Ward> list = new modle.Ward().getList();
        ObservableList List = FXCollections.observableArrayList();
        for (Ward ward : list) {
            List.add(ward.getWardName());
        }
        com_ward.setItems(List);

    }

    public void loadStrretCombo() {

        com_ward.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                modle.Ward ward = new modle.Ward();
                String selectedItem = com_ward.getSelectionModel().getSelectedItem();
                ward.setWardname(selectedItem);
                ObservableList list = ward.getStreetBySelectedWard();
                com_street.setItems(list);
            }
        });

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

    String selectedStreet = null;
    String selectedWard = null;

    public void getSelectedWaredStrret() {
        selectedWard = com_ward.getSelectionModel().getSelectedItem();
        selectedStreet = com_street.getSelectionModel().getSelectedItem();
    }

    public void searchCustomerByAssesment() {
        getSelectedWaredStrret();
        String asno = txt_assesmantNO.getText();
        CustomerHasAssesment cha = new modle.CustomerHasAssesment();
        cha.setAssesment(asno);
        cha.setStreet(selectedStreet);
        cha.setWard(selectedWard);

        modle.Customer customer = cha.searchCustometByAssesmentAndWardStrret();

    }

    @FXML
    public void nicCheack(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            System.out.println("ENTER GEHUWAA");
            searchCustomerByNIC();
        }
    }

    @FXML
    public void searchCustomerByNIC() {

        String text = txt_cus_nic.getText();
        modle.Customer customer = new modle.Customer();
        customer.setNic(text);

        Customer cus = customer.searchCustomerByNic();
        // upcus = cus;
        if (cus.getFullName() == null) {

        } else {

        }
        if (cus != null) {
            txt_cus_fname.setText(cus.getFullName());

        }
    }

    public void loadTreadTypeCombo() {
        List<pojo.TradeType> loadTreadType = new modle.TradeType().loadTreadType();
        ObservableList List = FXCollections.observableArrayList();
        for (pojo.TradeType tradeType1 : loadTreadType) {
            List.add(tradeType1.getTypeName());
        }

        com_trade_type.setItems(List);
    }

    int tt = 0;

    public void getTradyType() {
        com_trade_type.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String selectedItem = com_trade_type.getSelectionModel().getSelectedItem();
                tt = new modle.TradeType().loadTreadType(selectedItem).getIdTradeType();
                loadNature();
            }
        });

    }

    public void loadNature() {
        ObservableList natureList = FXCollections.observableArrayList();
        Nature tn = new modle.Nature();
        tn.setTradeType(new modle.TradeType().loadTreadType(tt));
        List<TradeNature> nlist = tn.getNaturesList();
        natureList.clear();
        for (pojo.TradeNature tnn : nlist) {
            natureList.add(tnn.getNature());
        }
        com_nature.setItems(natureList);

    }

    String tnature;

    public void getSelectedNature() {
        com_nature.setOnAction((event) -> {
            String natureString = com_nature.getSelectionModel().getSelectedItem();
            tnature = natureString;
            System.out.println(natureString);
            loadSubNature();
        });
    }

    public void loadSubNature() {

        ObservableList subList = FXCollections.observableArrayList();
        modle.SubNature sn = new modle.SubNature(0, new modle.Nature().getSubNatureByNature(tnature), null, null, 0, 0);
        List<pojo.SubNature> loadNature = sn.loadNature();

        subList.clear();
        for (pojo.SubNature tnn : loadNature) {
            subList.add(tnn.getSubNature());
        }
        com_subnature.setItems(subList);

    }

    public void loadRo() { 
     //   ArrayList list = cus.getCustomerFnameList();
      //  TextFields.bindAutoCompletion(txt_ro, list);
        
        
        
    }

    public void loadNatureCombo() {
    }

    public void loadSubCombo() {
    }

    public pojo.Ward getWardPojo() {

        return null;
    }

    public pojo.Street getStrretPojo() {
        return null;
    }

    public pojo.Ward getTradTypePojo() {
        return null;
    }

    public pojo.Ward getNaturePojo() {
        return null;
    }

    public pojo.Ward getSubNaturePojo() {
        return null;
    }

}
