package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class Admin_Assign_usersController implements Initializable {

    @FXML
    private TableView<?> tbl_user;

    @FXML
    private TableColumn<?, ?> col_idUser;

    @FXML
    private TableColumn<?, ?> col_fullname;

    @FXML
    private TableColumn<?, ?> col_nic;

    @FXML
    private TableColumn<?, ?> col_mobile;

    @FXML
    private TableColumn<?, ?> col_date;

    @FXML
    private TableColumn<?, ?> col_statues;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
