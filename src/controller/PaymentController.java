package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import pojo.AplicationPayment;
import pojo.Application;
import pojo.CashFlow;
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
    private JFXTextField txt_day;
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
    @FXML
    private JFXTextField txt_year_pay;
    @FXML
    private JFXTextField txt_month_pay;
    @FXML
    private JFXTextField txt_day_pay;
    @FXML
    private JFXCheckBox chb_vat_allow;
    @FXML
    private JFXCheckBox chb_nbt_allow;

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

        txt_tax_amount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
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
        });
        
        txt_vat_amount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
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
        });
        
        txt_nbt_amount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
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
        });
        
        txt_stamp_amount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
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
        });
    }

    public void SetDate() {
        Date date = new Date();
        SimpleDateFormat yeare = new SimpleDateFormat("yyyy");
        SimpleDateFormat month = new SimpleDateFormat("MM");
        SimpleDateFormat day = new SimpleDateFormat("dd");

        txt_year_pay.setText(yeare.format(date));
        txt_month_pay.setText(month.format(date));
        txt_day_pay.setText(day.format(date));
    }
    
    private void setTable() {
        List<Application> unpaiedApprovedApplications = new modle.Aplication().getUnpaiedApprovedApplications();
        if (unpaiedApprovedApplications != null) {
            ObservableList<Application> list = FXCollections.observableArrayList(unpaiedApprovedApplications);
            tbl_approved_list.setItems(list);
        }
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
                    CashFlow cashFlow = new pojo.CashFlow();
                    Vort vort = new modle.Vort().getById(application.getTradeType().getVort().getIdVort());
                    AplicationPayment aplicationPayment = new pojo.AplicationPayment();
                    aplicationPayment.setApplication(application);
                    aplicationPayment.setPayment(payment);

                    int nextReceiptNo = new modle.Payment().getNextReceiptNo();
                    int nextTradeLicenNo = new modle.TradeLicen().getNextTradeLicenNo();

                    // cash
                    if (cash) {
                        String cash_amount = txt_pay_amount_cash.getText();
                        if (!cash_amount.isEmpty()) {
                            cashFlow.setCash(Double.parseDouble(txt_pay_amount_cash.getText()));
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
                        String cheque_no = txt_cheque_no.getText();
                        if (!cheque_no.isEmpty()) {
                            LocalDate date = txt_cheque_date.getValue();
                            if (date != null) {
                                cashFlow.setChequeNo(txt_cheque_no.getText());
                                cashFlow.setCheque(Double.parseDouble(txt_pay_amount_cheque.getText()));
                            } else {
                                status = false;
                                Notifications.create()
                                        .title("Warning")
                                        .text("Please enter cheque date.")
                                        .hideAfter(Duration.seconds(3))
                                        .position(Pos.BOTTOM_RIGHT).showWarning();
                            }
                        } else {
                            status = false;
                            Notifications.create()
                                    .title("Warning")
                                    .text("Please enter cheque number.")
                                    .hideAfter(Duration.seconds(3))
                                    .position(Pos.BOTTOM_RIGHT).showWarning();
                        }
                    }

                    if (status) {
                        if (!txt_total_amount.getText().isEmpty()) {

                            // binding
                            application.getTradeLicenses().add(tradeLicense);
                            tradeLicense.setApplication(application);
                            tradeLicense.setPayment(payment);
                            payment.getTradeLicenses().add(tradeLicense);
                            customerHasTradeLicense.setCustomer(application.getCustomer());
                            customerHasTradeLicense.setTradeLicense(tradeLicense);
                            tradeLicense.getCustomerHasTradeLicenses().add(customerHasTradeLicense);
                            vort.getCashFlows().add(cashFlow);
                            cashFlow.setVort(vort);
                            vort.getPayments().add(payment);

                            // setup payment
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
                            payment.setReceiptNo(nextReceiptNo + "");

                            // setup trade licen
                            tradeLicense.setTradeLicenseDate(new Date());
                            tradeLicense.setLicenNo(nextTradeLicenNo + "");
                            tradeLicense.setStatus(1);
                            tradeLicense.setSyn(1);

                            // setup customer trade licen
                            customerHasTradeLicense.setSyn(1);

                            vort.setVoteCurrentBalance(
                                    vort.getVoteCurrentBalance() + (Double.parseDouble(txt_tax_amount.getText()))
                            );
                            payment.setVort(vort);

                            cashFlow.setApplicationNo(application.getIdApplication() + "");
                            cashFlow.setBankInfo(vort.getBankInfo());
                            cashFlow.setReciptNo(nextReceiptNo + "");
                            cashFlow.setTotale(Double.parseDouble(txt_total_amount.getText()));
                            cashFlow.setYear(application.getYear());
                            cashFlow.setMont(application.getMonth());
                            cashFlow.setCfDate(new Date());

                            aplicationPayment.setSyn(1);

                            if (new modle.Payment().saveOrUpdate(payment)) {
                                Notifications.create()
                                        .title("Success")
                                        .text("Payment success.")
                                        .hideAfter(Duration.seconds(3))
                                        .position(Pos.BOTTOM_RIGHT).showInformation();
                            } else {
                                Notifications.create()
                                        .title("Warning")
                                        .text("Payment failed.")
                                        .hideAfter(Duration.seconds(3))
                                        .position(Pos.BOTTOM_RIGHT).showWarning();
                            }

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
    private void chequeClicked(MouseEvent event) {
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

    @FXML
    private void allowVAT(MouseEvent event) {
        if(chb_nbt_allow.isSelected())
            txt_nbt_amount.setDisable(false);
        else
            txt_nbt_amount.setDisable(true);
    }

    @FXML
    private void allowNBT(MouseEvent event) {
        if(chb_vat_allow.isSelected())
            txt_vat_amount.setDisable(false);
        else
            txt_vat_amount.setDisable(true);
    }

}
