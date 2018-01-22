/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modle.Customer;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class CustomerController implements Initializable {

    @FXML
    private JFXComboBox<String> com_ward;

    @FXML
    private JFXComboBox<String> com_street;

    @FXML
    private JFXTextField txt_assesment;

    @FXML
    private JFXTextField txt_nic;

    @FXML
    private JFXTextField txt_fname;

    @FXML
    private JFXTextField txt_phone;

    @FXML
    private JFXTextField txt_mobile;

    @FXML
    private JFXTextField txt_email;

    @FXML
    private JFXTextField txt_adress1;

    @FXML
    private JFXTextField txt_adress2;

    @FXML
    private JFXTextField txt_adress3;

    @FXML
    private JFXTextField txt_city;

    @FXML
    private JFXButton btn_add;

    @FXML
    private JFXButton btn_update;

    @FXML
    private JFXButton btn_delete;

    @FXML
    private StackPane stakpain;

    @FXML
    private JFXButton btn_aplication;

    modle.Ward ward = null;
    modle.Customer customer = null;
    modle.CustomerHasAssesment cha = null;
    static pojo.Customer selectPcus = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ward = new modle.Ward();
        customer = new Customer();
        cha = new modle.CustomerHasAssesment();
        loadWard();
        loadStrret();
        getSelectedWaredStrret();
        saveCustomer();
        searchCustomerByAssesment();

        Customer cus = new modle.Customer();
        ArrayList list = cus.getCustomerFnameList();

        TextFields.bindAutoCompletion(txt_fname, list);
        updateCustomer();
        deactivCustomer();

    }

    @FXML
    public void loadCusByFullname(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            System.out.println("ENTER GEHUWAA");
            String fname = txt_fname.getText();
            modle.StaticBadu.setCus_fullname(fname);

            List<Customer> searchCustomer = new modle.Customer().searchCustomer(fname);
            modle.StaticBadu.setCuslist(searchCustomer);

            if (searchCustomer.size() > 1) {
                if (searchCustomer != null) {
                    try {
                        AnchorPane paymant = javafx.fxml.FXMLLoader.load(getClass().getResource("/view/SearchCus.fxml"));
                        txt_fname.getParent().getScene();
                        Scene scene = new Scene(paymant);
                        Stage stage = new Stage();
                        stage.initStyle(StageStyle.TRANSPARENT);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        Logger.getLogger(PayController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }

        }
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

    public void saveCustomer() {
        btn_add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                customer = new Customer();
                String nic1 = txt_nic.getText();
                customer.setNic(nic1);

                Customer searchCustomerByNic = customer.searchCustomerByNic();
                if (searchCustomerByNic.getFullName() == null) {

                    customer.setSelectedWard(selectedWard);
                    customer.setSelectedStreet(selectedStreet);
                    customer.setAssesmentNO(txt_assesment.getText());

                    customer.setFullName(txt_fname.getText());
                    customer.setAddress1(txt_adress1.getText());
                    customer.setAddress2(txt_adress2.getText());
                    customer.setAddress3(txt_adress3.getText());
                    customer.setCity(txt_city.getText());

                    customer.setPhone(txt_phone.getText());
                    customer.setMobile(txt_mobile.getText());
                    customer.setEmail(txt_email.getText());

                    boolean saveCustomer = customer.saveCustomer();

                    if (saveCustomer) {
                        modle.Allert.notificationGood("Added", customer.getFullName());
                        cleareCus();
                    } else {
                        modle.Allert.notificationError("Error", null);
                    }

                } else {

                }
            }
        });
    }

    @FXML
    public void keyListener(KeyEvent event) {
        int length = txt_assesment.getLength();
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

    @FXML
    public void searchCustomerByNIC() {

        String text = txt_nic.getText();
        customer = new modle.Customer();
        customer.setNic(text);

        Customer cus = customer.searchCustomerByNic();
        upcus = cus;
        if (cus.getFullName() == null) {
            modle.Allert.messagInfo(stakpain, "Not Found", "404");
        } else {
            modle.Allert.messagSuccsess(stakpain, "Found Customer", text);
        }
        if (cus != null) {
            txt_fname.setText(cus.getFullName());
            txt_phone.setText(cus.getPhone());
            txt_mobile.setText(cus.getMobile());
            txt_email.setText(cus.getEmail());
            txt_adress1.setText(cus.getAddress1());
            txt_adress2.setText(cus.getAddress2());
            txt_adress3.setText(cus.getAddress3());

        }
    }

    public void searchCustomerByAssesment() {
        getSelectedWaredStrret();
        String asno = txt_assesment.getText();
        cha.setAssesment(asno);
        cha.setStreet(selectedStreet);
        cha.setWard(selectedWard);
        customer = cha.searchCustometByAssesmentAndWardStrret();
        setCustometData();

    }

    public void setCustometData() {
        if (customer != null) {
            upcus = customer;
            txt_nic.setText(customer.getNic());
            txt_fname.setText(customer.getFullName());
            txt_adress1.setText(customer.getAddress1());
            txt_adress2.setText(customer.getAddress2());
            txt_adress3.setText(customer.getAddress3());
            txt_mobile.setText(customer.getMobile());
            txt_phone.setText(customer.getPhone());
            txt_email.setText(customer.getEmail());

        } else {

            txt_nic.setText(null);
            txt_fname.setText(null);
            txt_phone.setText(null);
            txt_mobile.setText(null);
            txt_email.setText(null);
            txt_adress1.setText(null);
            txt_adress2.setText(null);
            txt_adress3.setText(null);

        }
    }

    public void cleareCus() {
        customer = null;
        // com_street.setSelectionModel(null);
        // com_ward.setSelectionModel(null);
        txt_nic.setText(null);
        txt_fname.setText(null);
        txt_phone.setText(null);
        txt_mobile.setText(null);
        txt_email.setText(null);
        txt_adress1.setText(null);
        txt_adress2.setText(null);
        txt_adress3.setText(null);
        txt_assesment.setText(null);

    }

    public void cheackNICCurrent() {
        String nic = txt_nic.getText();

    }

    modle.Customer upcus;

    public void updateCustomer() {

        btn_update.setOnAction((event) -> {
            if (selectPcus != null) {
                upcus.setIdCustomer(selectPcus.getIdCustomer());
            }
            upcus.setFullName(txt_fname.getText());
            upcus.setNic(txt_nic.getText());
            upcus.setAddress1(txt_adress1.getText());
            upcus.setAddress2(txt_adress2.getText());
            upcus.setAddress3(txt_adress3.getText());
            upcus.setCity(txt_city.getText());
            upcus.setPhone(txt_phone.getText());
            upcus.setMobile(txt_mobile.getText());
            upcus.setEmail(txt_email.getText());

            upcus.updateCustomer();
            modle.Allert.messagSuccsess(stakpain, "Updated", "success");
            cleareCus();
            upcus = null;

        });

    }

    public void addApplication() {
        btn_aplication.setOnAction((ActionEvent event) -> {
//            System.out.println("APPLICATION");

//            try {
//                AnchorPane aplication = FXMLLoader.load(getClass().getResource("/view/application.fxml"));
//                 MainController.container.getChildren().add(aplication);
//                
//               
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//            System.out.println("end");
        });

    }

    public void deactivCustomer() {
        btn_delete.setOnAction((event) -> {
            if (selectPcus != null) {
                upcus.setIdCustomer(selectPcus.getIdCustomer());
            }
            upcus.deactiveCustomer();
            upcus = null;
            cleareCus();

        });

    }

}
