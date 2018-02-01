/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.Duration;
import modle.Report;
import org.controlsfx.control.Notifications;
import pojo.Application;
import pojo.Payment;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class PaymantViewController implements Initializable {

    @FXML
    private TableColumn<pojo.Payment, String> tbl_clmn_receiptno;
    @FXML
    private TableColumn<pojo.Payment, String> tbl_clmn_cusname;
    @FXML
    private TableColumn<pojo.Payment, Double> tbl_clmn_amount;
    @FXML
    private CheckBox chb_accept;
    @FXML
    private CheckBox chb_cancel;
    @FXML
    private JFXTextField txt_by_receiptno;
    @FXML
    private JFXDatePicker txt_by_date;
    @FXML
    private JFXTextField txt_by_year;
    @FXML
    private Label lbl_customername;
    @FXML
    private Label lbl_tradename;
    @FXML
    private Label lbl_tradenature;
    @FXML
    private Label lbl_tradesubnature;
    @FXML
    private Label lbl_tradetype;
    @FXML
    private Label lbl_application_year;
    @FXML
    private Label lbl_application_date;
    @FXML
    private Label lbl_allocation;
    @FXML
    private Label lbl_application_taxamount;
    @FXML
    private Label lbl_roname;
    @FXML
    private Label lbl_receipt_no;
    @FXML
    private Label lbl_vort;
    @FXML
    private Label lbl_payment_year;
    @FXML
    private Label lbl_payment_date;
    @FXML
    private Label lbl_payment_taxamount;
    @FXML
    private Label lbl_vat;
    @FXML
    private Label lbl_payment_total;
    @FXML
    private TableView<pojo.Payment> tbl_payment;
    @FXML
    private Label lbl_nbt;
    @FXML
    private Label lbl_stamp;
    @FXML
    private Label lbl_payment_method;
    @FXML
    private JFXButton btn_reset;
    @FXML
    private JFXButton btn_printview;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tbl_clmn_receiptno.setCellValueFactory(new PropertyValueFactory<>("receiptNo"));
        tbl_clmn_cusname.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Payment, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Payment, String> param) {
                Application app = new modle.Aplication().getByIdFull(param.getValue().getApplicationNo());
                return new SimpleStringProperty((app!=null)?app.getCustomer().getFullName():"Not Found");
            }
        });
        tbl_clmn_amount.setCellValueFactory(new PropertyValueFactory<>("totaleAmount"));

        txt_by_receiptno.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            List<Payment> receiptNumberLike = null;
            if (chb_accept.isSelected() && chb_cancel.isSelected()) {
                receiptNumberLike = new modle.Payment().getReceiptNumberLike(newValue, -1);
            } else if (chb_accept.isSelected()) {
                receiptNumberLike = new modle.Payment().getReceiptNumberLike(newValue, 1);
            } else if (chb_cancel.isSelected()) {
                receiptNumberLike = new modle.Payment().getReceiptNumberLike(newValue, 0);
            }
            if (receiptNumberLike != null) {
                setToTable(FXCollections.observableArrayList(receiptNumberLike));
            }
        });
        txt_by_year.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            try {
                List<Payment> receiptNumberLike = null;
                if (chb_accept.isSelected() && chb_cancel.isSelected()) {
                    receiptNumberLike = new modle.Payment().getByYear(Integer.parseInt(newValue), -1);
                } else if (chb_accept.isSelected()) {
                    receiptNumberLike = new modle.Payment().getByYear(Integer.parseInt(newValue), 1);
                } else if (chb_cancel.isSelected()) {
                    receiptNumberLike = new modle.Payment().getByYear(Integer.parseInt(newValue), 0);
                }
                if (receiptNumberLike != null) {
                    setToTable(FXCollections.observableArrayList(receiptNumberLike));
                }
            } catch (NumberFormatException e) {
                Notifications.create()
                        .title("Warning")
                        .text("Please Enter Valid Number.")
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.BOTTOM_RIGHT).showWarning();
            }
        });

        txt_by_date.valueProperty().addListener((ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) -> {
            List<Payment> receiptNumberLike = null;
            if (chb_accept.isSelected() && chb_cancel.isSelected()) {
                receiptNumberLike = new modle.Payment().getByDate(Date.from(newValue.atStartOfDay().atZone(ZoneId.of("Asia/Colombo")).toInstant()), -1);
            } else if (chb_accept.isSelected()) {
                receiptNumberLike = new modle.Payment().getByDate(Date.from(newValue.atStartOfDay().atZone(ZoneId.of("Asia/Colombo")).toInstant()), 1);
            } else if (chb_cancel.isSelected()) {
                receiptNumberLike = new modle.Payment().getByDate(Date.from(newValue.atStartOfDay().atZone(ZoneId.of("Asia/Colombo")).toInstant()), 0);
            }
            if (receiptNumberLike != null) {
                setToTable(FXCollections.observableArrayList(receiptNumberLike));
            }
        });
        
        this.loadTable();
        
    }

    @FXML
    private void loadPaymentDetails(MouseEvent event) {
        Payment payment = tbl_payment.getSelectionModel().getSelectedItem();
        if(payment!=null){
        payment = new modle.Payment().getByIdFull(payment.getIdPayment());
        Application application = new modle.Aplication().getByIdFull(payment.getApplicationNo());
        if(application!=null){
        // set application details
        lbl_customername.setText(application.getCustomer().getFullName());
        lbl_tradename.setText(application.getTradeName());
        lbl_tradenature.setText(application.getTradeNature().getNature());
        lbl_tradesubnature.setText(application.getSubNature().getSubNature());
        lbl_tradetype.setText(application.getTradeType().getTypeName());
        lbl_application_year.setText(application.getYear() + "");
        lbl_application_date.setText(new SimpleDateFormat("yyyy-MM-dd").format(application.getApplicationDate()));
        lbl_allocation.setText("Rs. "+application.getAllocation() + "");
        lbl_application_taxamount.setText("Rs. "+Math.round(application.getTaxAmount() * 100.00) / 100.00 + "");
        lbl_roname.setText(application.getUser().getFullName());

        // set payment details
        lbl_receipt_no.setText(payment.getReceiptNo());
        lbl_vort.setText(payment.getVort().getVoteName());
        lbl_payment_year.setText(payment.getYear() + "");
        lbl_payment_date.setText(new SimpleDateFormat("yyyy-MM-dd").format(payment.getPaymentDate()));
        lbl_payment_taxamount.setText(Math.round(payment.getTaxAmount() * 100.00) / 100.00 + "");
        lbl_vat.setText("Rs. "+payment.getVat() + "");
        lbl_nbt.setText("Rs. "+payment.getNbt() + "");
        lbl_stamp.setText("Rs. "+payment.getSpamp() + "");
        lbl_payment_total.setText("Rs. "+payment.getTotaleAmount() + "");
        lbl_payment_method.setText((payment.getCashCheack()!=null&&payment.getCashCheack().equalsIgnoreCase("ca")?"Cash":
                (payment.getCashCheack()!=null&&payment.getCashCheack().equalsIgnoreCase("ch")?"Cheque":
                        (payment.getCashCheack()!=null&&payment.getCashCheack().equalsIgnoreCase("cc")?"Cash & Cheque":
                                "Unknown"))));
        }else{
            Notifications.create()
                        .title("Warning")
                        .text("Not Found Application.")
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.BOTTOM_RIGHT).showWarning();
        }
        }else{
            Notifications.create()
                        .title("Warning")
                        .text("Not Found Payment.")
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.BOTTOM_RIGHT).showWarning();
        }
    }

    @FXML
    private void byReceiptNumber(ActionEvent event) {
        
    }
    
    public void loadTable(){
        List<Payment> list = new modle.Payment().getByDate(new Date(), 1);
        if (list != null) 
                setToTable(FXCollections.observableArrayList(list));
    }
    
    public void reset(){
        loadTable();
        //reset application
        lbl_customername.setText("");
        lbl_tradename.setText("");
        lbl_tradenature.setText("");
        lbl_tradesubnature.setText("");
        lbl_tradetype.setText("");
        lbl_application_year.setText("");
        lbl_application_date.setText("");
        lbl_allocation.setText("");
        lbl_application_taxamount.setText("");
        lbl_roname.setText("");

        // reset payment details
        lbl_receipt_no.setText("");
        lbl_vort.setText("");
        lbl_payment_year.setText("");
        lbl_payment_date.setText("");
        lbl_payment_taxamount.setText("");
        lbl_vat.setText("");
        lbl_nbt.setText("");
        lbl_stamp.setText("");
        lbl_payment_total.setText("");
        lbl_payment_method.setText("");
        
        txt_by_receiptno.setText("");
        txt_by_year.setText("");
    }
    
    public void setToTable(ObservableList list) {
        tbl_payment.setItems(list);
    }

    @FXML
    private void resetAll(MouseEvent event) {
        this.reset();
    }
    Report report;
    @FXML
    private void showPrintView(MouseEvent event) {
        Payment selectedItem = tbl_payment.getSelectionModel().getSelectedItem();
        if(selectedItem!=null){
            if(report==null)report=new Report();
            report.paymentReceipt(selectedItem.getIdPayment(), true);
        }else{
            Notifications.create()
                    .title("Warning")
                    .position(Pos.BOTTOM_LEFT)
                    .hideAfter(Duration.seconds(6))
                    .text("Please select payment")
                    .showWarning();
        }
    }
}
