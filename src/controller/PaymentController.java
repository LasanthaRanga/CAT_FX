package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import pojo.Application;
import pojo.Customer;
import pojo.CustomerHasTradeLicense;
import pojo.Payment;
import pojo.TradeLicense;
import pojo.Vort;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class PaymentController implements Initializable {

    @FXML
    private JFXButton btn_paynow;
    @FXML
    private TableView<pojo.Application> tbl_approved_list;
    @FXML
    private TableColumn<pojo.Application, String> tbl_clmn_application_no;
    @FXML
    private TableColumn<pojo.Application, String> tbl_clmn_allocation;
    @FXML
    private TableColumn<pojo.Application, String> tbl_clmn_application_date;
    @FXML
    private JFXTextField txt_application_no;
    @FXML
    private JFXTextField txt_year;
    @FXML
    private JFXTextField txt_month;
    @FXML
    private JFXTextField txt_day;
    @FXML
    private JFXTextField txt_trade_licen_no;
    @FXML
    private JFXTextField txt_customer_name;
    @FXML
    private JFXTextField txt_customer_nic;
    @FXML
    private JFXTextField txt_allocation;
    @FXML
    private JFXTextField txt_tax;
    @FXML
    private JFXTextField txt_trade_name;
    @FXML
    private JFXTextField txt_trade_address1;
    @FXML
    private JFXTextField txt_trade_address2;
    @FXML
    private JFXTextField txt_trade_address3;
    @FXML
    private JFXButton btn_reset;
    @FXML
    private JFXTextField txt_tax_amount;
    @FXML
    private JFXTextField txt_vat_amount;
    @FXML
    private JFXTextField txt_nbt_amount;
    @FXML
    private JFXTextField txt_stamp_amount;
    @FXML
    private JFXTextField txt_total_amount;
    @FXML
    private JFXTextField txt_receipt_no;
    @FXML
    private CheckBox chb_cash;
    @FXML
    private CheckBox chb_check;
    @FXML
    private JFXTextField txt_cheque_no;
    @FXML
    private JFXDatePicker txt_cheque_date;
    @FXML
    private JFXTextField txt_pay_amount_cash;
    @FXML
    private JFXTextField txt_pay_amount_cheque;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        tbl_clmn_allocation.setCellValueFactory(new PropertyValueFactory<>("allocation"));
        tbl_clmn_application_no.setCellValueFactory(new PropertyValueFactory<>("idApplication"));
        tbl_clmn_application_date.setCellValueFactory(new PropertyValueFactory<>("applicationDate"));
        this.setTable();

        btn_paynow.setOnAction((event) -> {
            System.out.println("PAY");
            try {
                AnchorPane paymant = FXMLLoader.load(getClass().getResource("/view/pay.fxml"));
                btn_paynow.getParent().getScene();
                Scene scene = new Scene(paymant);
                Stage stage = new Stage();
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                ex.printStackTrace();
                Logger.getLogger(PayController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

    }

    private void setTable() {
        ObservableList<Application> list = FXCollections.observableArrayList(new modle.Aplication().getUnpaiedApprovedApplications());
        tbl_approved_list.setItems(list);
    }

    @FXML
    private void selectApplication(MouseEvent event) {
        Application application = tbl_approved_list.getSelectionModel().getSelectedItem();
        if (application != null) {
            Customer customer = new modle.Customer().getById(application.getCustomer().getIdCustomer());
            if (customer != null) {
                txt_application_no.setText(application.getIdApplication() + "");
                txt_allocation.setText(application.getAllocation() + "");
                txt_tax.setText(application.getTaxAmount() + "");
                txt_tax_amount.setText(application.getTaxAmount() + "");
                txt_year.setText(application.getYear() + "");
                txt_month.setText(application.getMonth() + "");
                txt_trade_address1.setText(application.getTradeAddress1());
                txt_trade_address2.setText(application.getTradeAddress2());
                txt_trade_address3.setText(application.getTradeAddress3());
                txt_trade_name.setText(application.getTradeName());
                txt_customer_name.setText(customer.getFullName());
                txt_customer_nic.setText(customer.getNic());
            } else {
                Notifications.create()
                        .title("Warning")
                        .text("Not Found Customer.")
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.BOTTOM_RIGHT).showWarning();
            }
        } else {
            Notifications.create()
                    .title("Warning")
                    .text("Not Found Application.")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT).showWarning();
        }
    }

    @FXML
    private void paynow(MouseEvent event) {
        Application application = tbl_approved_list.getSelectionModel().getSelectedItem();
        if (application != null) {
            boolean cash = chb_cash.isSelected();
            boolean cheque = chb_check.isSelected();
            if (cash || cheque) {
                try {
                    boolean status = true;
                    // declaring
                    Payment payment = new pojo.Payment();
                    TradeLicense tradeLicense = new pojo.TradeLicense();
                    CustomerHasTradeLicense customerHasTradeLicense = new pojo.CustomerHasTradeLicense();
                    List<Vort> list_vote = new modle.Vort().getList();
                    
                    // binding
                    application.getTradeLicenses().add(tradeLicense);
                    tradeLicense.setApplication(application);
                    tradeLicense.setPayment(payment);
                    payment.getTradeLicenses().add(tradeLicense);
                    customerHasTradeLicense.setCustomer(application.getCustomer());
                    customerHasTradeLicense.setTradeLicense(tradeLicense);
                    tradeLicense.getCustomerHasTradeLicenses().add(customerHasTradeLicense);

                    // cash
                    if (cash) {
                        String cash_amount = txt_pay_amount_cash.getText();
                        if (!cash_amount.isEmpty()) {

                        } else {
                            status = false;
                            Notifications.create()
                                    .title("Warning")
                                    .text("Please enter amount.")
                                    .hideAfter(Duration.seconds(3))
                                    .position(Pos.BOTTOM_RIGHT).showWarning();
                        }
                    }

                    if (cheque) {

                    }

                    if (status) {
                        if (!txt_total_amount.getText().isEmpty()) {
                            payment.setPaymentDate(new Date());
                            payment.setApplicationNo(application.getIdApplication());
                            payment.setYear(application.getYear());
                            payment.setMonth(application.getMonth());
                            payment.setTaxAmount(Double.parseDouble(txt_tax_amount.getText()));
                            // vat
                            if (!txt_vat_amount.getText().isEmpty()) {
                                payment.setVat(Double.parseDouble(txt_vat_amount.getText()));
                            } else {
                                payment.setVat(0.0);
                            }
                            // nbt
                            if (!txt_nbt_amount.getText().isEmpty()) {
                                payment.setNbt(Double.parseDouble(txt_nbt_amount.getText()));
                            } else {
                                payment.setNbt(0.0);
                            }
                            // stamp
                            if (!txt_stamp_amount.getText().isEmpty()) {
                                payment.setSpamp(Double.parseDouble(txt_stamp_amount.getText()));
                            } else {
                                payment.setSpamp(0.0);
                            }
                            payment.setTotaleAmount(Double.parseDouble(txt_total_amount.getText()));
                            payment.setStatus(1);
                            payment.setSyn(1);
                            payment.setUserLog(modle.Log_User.getLogUser());
                            
                            tradeLicense.setTradeLicenseDate(new Date());
                            tradeLicense.setStatus(1);
                            tradeLicense.setSyn(1);
                            
                            customerHasTradeLicense.setSyn(1);
                            Vort vort = list_vote.get(0);
                            vort.setVoteCurrentBalance(
                                    vort.getVoteCurrentBalance()+
                                            (Double.parseDouble(txt_tax_amount.getText()))
                            );
                            payment.setVort(vort);
                            
                            
                        } else {
                            Notifications.create()
                                    .title("Warning")
                                    .text("Not found total.")
                                    .hideAfter(Duration.seconds(3))
                                    .position(Pos.BOTTOM_RIGHT).showWarning();
                        }
                    }

                } catch (NumberFormatException e) {
                    Notifications.create()
                            .title("Warning")
                            .text("Please enter valid number.")
                            .hideAfter(Duration.seconds(3))
                            .position(Pos.BOTTOM_RIGHT).showWarning();
                }
            } else {
                Notifications.create()
                        .title("Warning")
                        .text("Please select payment type.")
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.BOTTOM_RIGHT).showWarning();
            }
        } else {
            Notifications.create()
                    .title("Warning")
                    .text("Not found application.")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT).showWarning();
        }
    }

    @FXML
    private void reset(MouseEvent event) {
        this.setTable();
        txt_application_no.setText("");
        txt_allocation.setText("");
        txt_tax.setText("");
        txt_year.setText("");
        txt_month.setText("");
        txt_trade_address1.setText("");
        txt_trade_address2.setText("");
        txt_trade_address3.setText("");
        txt_trade_name.setText("");
        txt_customer_name.setText("");
        txt_customer_nic.setText("");
        txt_tax_amount.setText("00.00");
        txt_vat_amount.setText("00.00");
        txt_nbt_amount.setText("00.00");
        txt_stamp_amount.setText("00.00");
        txt_total_amount.setText("");
    }

    private void setTotal() {
        try {
            double total = 0.0;
            total += Double.parseDouble(txt_tax_amount.getText());
            total += Double.parseDouble(txt_vat_amount.getText());
            total += Double.parseDouble(txt_nbt_amount.getText());
            total += Double.parseDouble(txt_stamp_amount.getText());
            txt_total_amount.setText(total + "");
        } catch (NumberFormatException numberFormatException) {
            Notifications.create()
                    .title("Warning")
                    .text("Please Enter Valid Number.")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT).showWarning();
        }
    }

    @FXML
    private void taxAmountChange(InputMethodEvent event) {
        this.setTotal();
    }

    @FXML
    private void vatAmountChange(InputMethodEvent event) {
        this.setTotal();
    }

    @FXML
    private void nbtAmountChange(InputMethodEvent event) {
        this.setTotal();
    }

    @FXML
    private void stampAmountChange(InputMethodEvent event) {
        this.setTotal();
    }

    @FXML
    private void cashClicked(MouseEvent event) {
        if (chb_cash.isSelected()) {
            txt_pay_amount_cash.setDisable(false);
        } else {
            txt_pay_amount_cash.setDisable(true);
        }
    }

    @FXML
    private void chequeClicked(ContextMenuEvent event) {
        if (chb_check.isSelected()) {
            txt_cheque_no.setDisable(false);
            txt_cheque_date.setDisable(false);
            txt_pay_amount_cheque.setDisable(false);
        } else {
            txt_pay_amount_cheque.setDisable(true);
            txt_cheque_no.setDisable(true);
            txt_cheque_date.setDisable(true);
        }
    }

}
