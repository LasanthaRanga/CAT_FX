/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import modle.Customer;
import org.hibernate.Session;
import pojo.Assessment;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class CustomerViewController implements Initializable {

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
    @FXML
    private JFXTextField txt_assess;
    @FXML
    private JFXTextField txt_street;
    @FXML
    private JFXTextField txt_ward;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<Assessment> assessments = modle.StaticBadu.getAssessmentList();
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();

        List.clear();
        try {

            Assessment as = assessments.get(0);
            txt_assess.setText(as.getAssessmentNo());
            txt_street.setText(as.getStreet().getStreetName());
            txt_ward.setText(as.getStreet().getWard().getWardName());

            for (Assessment assessment : assessments) {
                pojo.Customer c = assessment.getCustomer();
                Customer customer = new modle.Customer().searchCustomerByID(c.getIdCustomer());

                txt_fullname.setText(customer.getFullName());
                String addr = customer.getAddress1() + ",  " + customer.getAddress2() + ",  " + customer.getAddress3();
                List.add(new cus(customer.getIdCustomer(), customer.getNic(), addr, customer.getMobile(), customer.getEmail()));

            }
            loadTable();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    @FXML
    private void selectByTable(MouseEvent event) {
        cus selectedItem = tbl_cus.getSelectionModel().getSelectedItem();
        txt_idCus.setText(selectedItem.getIdcus() + "");

        modle.Customer byId = new modle.Customer().searchCustomerByID(selectedItem.getIdcus());
        modle.StaticBadu.setCustomerModle(byId);
        txt_fullname.setText(modle.StaticBadu.getCustomerModle().getFullName());
    }

    @FXML
    private void getThis(ActionEvent event) {
        modle.StaticBadu.getCustomerController().setDataBySelectedCustomer();
        btn_get.getScene().getWindow().hide();

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

        tbl_cus.setItems(List);

    }

}
