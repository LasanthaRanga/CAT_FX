package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import modle.Customer;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class SearchCusController implements Initializable {

    @FXML
    private JFXTextField txt_fullname;

    @FXML
    private TableView<?> tbl_cus;

    @FXML
    private TableColumn<?, ?> c_id;

    @FXML
    private TableColumn<?, ?> c_nic;

    @FXML
    private TableColumn<?, ?> c_address;

    @FXML
    private TableColumn<?, ?> c_mobile;

    @FXML
    private TableColumn<?, ?> c_email;

    @FXML
    private JFXTextField txt_idCus;

    @FXML
    private JFXButton btn_get;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Customer> cuslist = modle.StaticBadu.getCuslist();
        for (Customer customer : cuslist) {
            System.out.println(customer.getIdCustomer());
        }
    }

}
