package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import pojo.Application;
import pojo.Customer;

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
            double total=0.0;
            total+=Double.parseDouble(txt_tax_amount.getText());
            total+=Double.parseDouble(txt_vat_amount.getText());
            total+=Double.parseDouble(txt_nbt_amount.getText());
            total+=Double.parseDouble(txt_stamp_amount.getText());
            txt_total_amount.setText(total+"");
        } catch (NumberFormatException numberFormatException) {
            Notifications.create()
                    .title("Warning")
                    .text("Please Enter Valid Number.")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT).showWarning();
        }
    }

    @FXML
    private void txtChange(InputMethodEvent event) {
        System.out.println("asdasd");
    }

}
