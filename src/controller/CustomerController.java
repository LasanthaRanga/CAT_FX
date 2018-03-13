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
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modle.Customer;
import modle.WSA;
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

    // private JFXButton btn_delete;
//    @FXML
//    private JFXButton btn_aplication;
    modle.Ward ward = null;
    modle.Customer customer = null;
    modle.CustomerHasAssesment cha = null;
    static pojo.Customer selectPcus = null;
    @FXML
    private Label lbl_idCustomer;
    @FXML
    private TableView<WSA> tbl_asess;
    @FXML
    private TableColumn<WSA, String> col_ward;
    @FXML
    private TableColumn<WSA, String> col_street;
    @FXML
    private TableColumn<WSA, String> col_ases;
    @FXML
    private JFXButton btn_genarate;
    @FXML
    private JFXButton btn_clear;

    Scene scene;

    Customer cus;
    @FXML
    private JFXButton btn_add1;

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

        cus = new modle.Customer();
        ArrayList list = cus.getCustomerFnameList();
        TextFields.bindAutoCompletion(txt_fname, list);
        updateCustomer();

        btn_update.setDisable(true);

        tbl_asess.setOnMouseReleased((event) -> {

            WSA selectedItem = tbl_asess.getSelectionModel().getSelectedItem();
            upcus.setIdAssessmant(selectedItem.getIdAssesmant());
            com_ward.getSelectionModel().select(selectedItem.getWard());
            com_street.getSelectionModel().select(selectedItem.getStreet());
            txt_assesment.setText(selectedItem.getAssesmant());

        });

    }

    int x = 0;

    @FXML
    public void loadCusByFullname(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {
            System.out.println("ENTER GEHUWAA");
            String fname = txt_fname.getText();

            if (x == 0) {
                modle.StaticBadu.setCus_fullname(fname);
                List<Customer> searchCustomer = new modle.Customer().searchCustomer(fname);
                modle.StaticBadu.setCuslist(searchCustomer);
                x = 1;

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

                            x = 2;

                        } catch (IOException ex) {
                            ex.printStackTrace();
                            Logger.getLogger(PayController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                } else {
                    upcus = searchCustomer.get(0);
                    txt_fname.setText(upcus.getFullName());
                    txt_phone.setText(upcus.getPhone());
                    txt_mobile.setText(upcus.getMobile());
                    txt_email.setText(upcus.getEmail());
                    txt_adress1.setText(upcus.getAddress1());
                    txt_adress2.setText(upcus.getAddress2());
                    txt_adress3.setText(upcus.getAddress3());
                    txt_nic.setText(upcus.getNic());
                    x = 0;
                    // btn_add.setDisable(true);
                    btn_update.setDisable(false);

                    setWardStrretAssesmant();
                }

            }//x = 0
            else if (x == 2) {
                System.out.println(x);
                System.out.println(modle.StaticBadu.getpCustomer().getFullName() + "=== Static Badu");
                if (modle.StaticBadu.getpCustomer() != null) {
                    upcus = new modle.Customer().searchCustomerByID(modle.StaticBadu.getpCustomer().getIdCustomer());
                    System.out.println(upcus.getFullName() + "=== up cus");

                    txt_fname.setText(upcus.getFullName());
                    txt_phone.setText(upcus.getPhone());
                    txt_mobile.setText(upcus.getMobile());
                    txt_email.setText(upcus.getEmail());
                    txt_adress1.setText(upcus.getAddress1());
                    txt_adress2.setText(upcus.getAddress2());
                    txt_adress3.setText(upcus.getAddress3());
                    txt_nic.setText(upcus.getNic());
                    x = 0;
                    //btn_add.setDisable(true);
                    btn_update.setDisable(false);

                    setWardStrretAssesmant();

                }
            }// x==2

        }
    }

    public void setWardStrretAssesmant() {
        System.out.println("Asessmant Gannawa");
        ObservableList WASlist = modle.Customer.getWASlist();

        if (WASlist != null) {

            col_ward.setCellValueFactory(new PropertyValueFactory<>("Ward"));
            col_street.setCellValueFactory(new PropertyValueFactory<>("Street"));
            col_ases.setCellValueFactory(new PropertyValueFactory<>("Assesmant"));

            tbl_asess.setItems(WASlist);

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

    ObservableList slist;

    public void loadStrret() {
        com_ward.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String selectedItem = com_ward.getSelectionModel().getSelectedItem();
                ward.setWardname(selectedItem);
                slist = ward.getStreetBySelectedWard();
                com_street.setItems(slist);
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

                    getSelectedWaredStrret();

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
                        clearAll(event);

                    } else {
                        modle.Allert.notificationError("Error", null);
                    }

                } else {

                }
                ArrayList li = cus.getCustomerFnameList();
                TextFields.bindAutoCompletion(txt_fname, li);
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

    public void searchCustomerByNIC() {

        String text = txt_nic.getText();
        customer = new modle.Customer();
        customer.setNic(text);

        Customer cus = customer.searchCustomerByNic();
        upcus = cus;
        if (cus.getFullName() == null) {

            modle.Allert.notificationInfo("Not Found", text);
        } else {

            modle.Allert.notificationGood("Found Customer", text);
        }
        if (cus != null) {
            txt_fname.setText(cus.getFullName());
            txt_phone.setText(cus.getPhone());
            txt_mobile.setText(cus.getMobile());
            txt_email.setText(cus.getEmail());
            txt_adress1.setText(cus.getAddress1());
            txt_adress2.setText(cus.getAddress2());
            txt_adress3.setText(cus.getAddress3());

            setWardStrretAssesmant();
            // btn_add.setDisable(true);
            btn_update.setDisable(false);
        } else {
            upcus = null;
            Runtime.getRuntime().gc();
        }

    }

    public void SearchCustomerAndSetByFullName() {

        Customer cus = customer.searchCustomerFullName();
        upcus = cus;

        if (cus != null) {
            txt_fname.setText(cus.getFullName());
            txt_phone.setText(cus.getPhone());
            txt_mobile.setText(cus.getMobile());
            txt_email.setText(cus.getEmail());
            txt_adress1.setText(cus.getAddress1());
            txt_adress2.setText(cus.getAddress2());
            txt_adress3.setText(cus.getAddress3());
        } else {
            upcus = null;
            Runtime.getRuntime().gc();
        }
    }

    public void searchCustomerByAssesment() {

        getSelectedWaredStrret();
        String asno = txt_assesment.getText();
        cha.setAssesment(asno);
        cha.setStreet(selectedStreet);
        cha.setWard(selectedWard);

        customer = modle.AssesmantNo.searchByAssesmantNO(selectedWard, selectedStreet, asno);

        //   customer = cha.searchCustometByAssesmentAndWardStrret();
        setCustometData();
        //  setWardStrretAssesmant();
        //  btn_add.setDisable(true);
        btn_update.setDisable(false);

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
            btn_update.setDisable(false);
        } else {

            txt_nic.setText(null);
            txt_fname.setText(null);
            txt_phone.setText(null);
            txt_mobile.setText(null);
            txt_email.setText(null);
            txt_adress1.setText(null);
            txt_adress2.setText(null);
            txt_adress3.setText(null);
            ObservableList WASlist = FXCollections.observableArrayList();
            tbl_asess.setItems(WASlist);
            upcus = null;
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

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Are You Sure To Update This ? ");
            alert.setContentText("Are you ok with this?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

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
                upcus.setAssesmentNO(txt_assesment.getText());
                upcus.setSelectedWard(com_ward.getSelectionModel().getSelectedItem());
                upcus.setSelectedStreet(com_street.getSelectionModel().getSelectedItem());

                upcus.updateCustomer();
                modle.Allert.notificationGood("Updated", "success");
                clearAll(event);
                upcus = null;
                Runtime.getRuntime().gc();
                // setWardStrretAssesmant();
                // ... user chose OK
            } else {
                // ... user chose CANCEL or closed the dialog
            }
            ArrayList li = cus.getCustomerFnameList();
            TextFields.bindAutoCompletion(txt_fname, li);
        });
    }

//    public void addApplication() {
//        btn_aplication.setOnAction((ActionEvent event) -> {
////            System.out.println("APPLICATION");
//
////            try {
////                AnchorPane aplication = FXMLLoader.load(getClass().getResource("/view/application.fxml"));
////                 MainController.container.getChildren().add(aplication);
////                
////               
////            } catch (IOException ex) {
////                ex.printStackTrace();
////            }
////            System.out.println("end");
//        });
//
//    }
//    public void deactivCustomer() {
//        btn_delete.setOnAction((event) -> {
//            if (selectPcus != null) {
//                upcus.setIdCustomer(selectPcus.getIdCustomer());
//            }
//            upcus.deactiveCustomer();
//            upcus = null;
//            cleareCus();
//
//        });
//    }
    @FXML
    private void genarateAsessmantNO(ActionEvent event) {
        String ass;
        if (customer != null) {
            ass = customer.genarateAssesmant();
        } else {
            customer = new modle.Customer();
            ass = customer.genarateAssesmant();
        }
        txt_assesment.setText(ass);

    }

    @FXML
    private void clearAll(ActionEvent event) {

        ArrayList list = cus.getCustomerFnameList();
        TextFields.bindAutoCompletion(txt_fname, list);

        txt_nic.setText(null);
        txt_fname.setText(null);
        txt_phone.setText(null);
        txt_mobile.setText(null);
        txt_email.setText(null);
        txt_adress1.setText(null);
        txt_adress2.setText(null);
        txt_adress3.setText(null);
        ObservableList WASlist = FXCollections.observableArrayList();
        WASlist = null;
        tbl_asess.setItems(WASlist);
        com_ward.getSelectionModel().clearSelection();
        com_street.getSelectionModel().clearSelection();
        loadWard();
        txt_assesment.setText(null);
        upcus = null;
        btn_update.setDisable(true);
        btn_add.setDisable(false);
        upcus = null;
        Runtime.getRuntime().gc();

    }

    @FXML
    private void addAssessmant(ActionEvent event) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Are You Sure To Add New Assessmant For This Customer ?\n  " + txt_fname.getText());
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            customer = upcus;
            System.out.println(upcus.getIdCustomer());
            String nic1 = txt_nic.getText();
            customer.setNic(nic1);

            getSelectedWaredStrret();

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

            boolean saveCustomer = customer.saveNewAssessmant();

            if (saveCustomer) {
                modle.Allert.notificationGood("Added", customer.getFullName());
                clearAll(event);

                // setWardStrretAssesmant();
            } else {
                modle.Allert.notificationError("Error", null);
            }

        } else {
        }

        ArrayList li = cus.getCustomerFnameList();
        TextFields.bindAutoCompletion(txt_fname, li);

    }

}
