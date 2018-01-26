package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modle.Customer;
import modle.Ward;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class SearchCusController implements Initializable {

    @FXML
    private JFXTextField txt_fullname;

    @FXML
    private TableView<cus> tbl_cus;

    @FXML
    private TableColumn<cus, String> c_id;

    @FXML
    private TableColumn<cus, String> c_nic;

    @FXML
    private TableColumn<cus, String> c_address;

    @FXML
    private TableColumn<cus, String> c_mobile;

    @FXML
    private TableColumn<cus, String> c_email;

    @FXML
    private JFXTextField txt_idCus;

    @FXML
    private JFXButton btn_get;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTable();
        selectCustomer();
        btn_get.setOnAction((event) -> {
            setCustomerID();
        });

    }

    public class cus {

        public cus(int idcus, String nic, String address, String mobile, String email) {
            this.idcus = idcus;
            this.nic = new SimpleStringProperty(nic);
            this.address = new SimpleStringProperty(address);
            this.mobile = new SimpleStringProperty(mobile);
            this.email = new SimpleStringProperty(email);
        }

        /**
         * @return the idcus
         */
        public int getIdcus() {
            return idcus;
        }

        /**
         * @return the nic
         */
        public String getNic() {
            return nic.get();
        }

        /**
         * @return the address
         */
        public String getAddress() {
            return address.get();
        }

        /**
         * @return the mobile
         */
        public String getMobile() {
            return mobile.get();
        }

        /**
         * @return the email
         */
        public String getEmail() {
            return email.get();
        }
        private int idcus;
        private SimpleStringProperty nic;
        private SimpleStringProperty address;
        private SimpleStringProperty mobile;
        private SimpleStringProperty email;

    }
    ObservableList List = FXCollections.observableArrayList();

    public void loadTable() {
        c_id.setCellValueFactory(new PropertyValueFactory<>("idcus"));
        c_nic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        c_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        c_mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        c_email.setCellValueFactory(new PropertyValueFactory<>("email"));

        List<Customer> cuslist = modle.StaticBadu.getCuslist();
        List.clear();
        for (Customer customer : cuslist) {
            txt_fullname.setText(customer.getFullName());
            String addr = customer.getAddress1() + ",  " + customer.getAddress2() + ",  " + customer.getAddress3();
            List.add(new cus(customer.getIdCustomer(), customer.getNic(), addr, customer.getMobile(), customer.getEmail()));
        }

        tbl_cus.setItems(List);

    }

    int idCus;

    public void selectCustomer() {
        tbl_cus.setOnMouseReleased((event) -> {
            idCus = tbl_cus.getSelectionModel().getSelectedItem().getIdcus();
            txt_idCus.setText(idCus + "");

        });
    }

    public void setCustomerID() {
        
        Customer customer = new modle.Customer();
        customer.setIdCustomer(idCus);

        modle.StaticBadu.setpCustomer(customer.getById(idCus));
        
        System.out.println(modle.StaticBadu.getpCustomer().getIdCustomer());
        System.out.println(modle.StaticBadu.getpCustomer().getFullName());
        
      //  controller.CustomerController.selectPcus = customer.getById(idCus); 
      
        btn_get.getScene().getWindow().hide();

    }

}
