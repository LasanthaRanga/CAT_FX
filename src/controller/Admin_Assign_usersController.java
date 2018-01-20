package controller;

import java.net.URL;
import java.util.ResourceBundle;
<<<<<<< HEAD
=======
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
>>>>>>> 5617d9006a5690211e97aa54d457959fdf2aad49
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
<<<<<<< HEAD
=======
import javafx.scene.control.cell.PropertyValueFactory;
>>>>>>> 5617d9006a5690211e97aa54d457959fdf2aad49

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

<<<<<<< HEAD
=======
    public class UserTbl {

        public UserTbl(int idUser, SimpleStringProperty fullname, SimpleStringProperty nic, SimpleStringProperty mobile, SimpleStringProperty date, SimpleStringProperty statues) {
            this.idUser = idUser;
            this.fullname = fullname;
            this.nic = nic;
            this.mobile = mobile;
            this.date = date;
            this.statues = statues;
        }

        /**
         * @return the idUser
         */
        public int getIdUser() {
            return idUser;
        }

        /**
         * @return the fullname
         */
        public String getFullname() {
            return fullname.get();
        }

        /**
         * @return the nic
         */
        public String getNic() {
            return nic.get();
        }

        /**
         * @return the mobile
         */
        public String getMobile() {
            return mobile.get();
        }

        /**
         * @return the date
         */
        public String getDate() {
            return date.get();
        }

        /**
         * @return the statues
         */
        public String getStatues() {
            return statues.get();
        }

        private int idUser;
        private SimpleStringProperty fullname;
        private SimpleStringProperty nic;
        private SimpleStringProperty mobile;
        private SimpleStringProperty date;
        private SimpleStringProperty statues;

    }
    
    ObservableList userList = FXCollections.observableArrayList();
    public void loadUserTbl(){
        
        col_idUser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        col_idUser.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        col_idUser.setCellValueFactory(new PropertyValueFactory<>("nic"));
        col_idUser.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        col_idUser.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_idUser.setCellValueFactory(new PropertyValueFactory<>("statues"));
    
    
    }

>>>>>>> 5617d9006a5690211e97aa54d457959fdf2aad49
}
