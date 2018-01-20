package controller;

import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pojo.Department;
import pojo.User;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class Admin_Assign_usersController implements Initializable {

    @FXML
    private TableView<UserTbl> tbl_user;

    @FXML
    private TableColumn<UserTbl, Integer> col_idUser;

    @FXML
    private TableColumn<UserTbl, String> col_fullname;

    @FXML
    private TableColumn<UserTbl, String> col_nic;

    @FXML
    private TableColumn<UserTbl, String> col_mobile;

    @FXML
    private TableColumn<UserTbl, String> col_date;

    @FXML
    private TableColumn<UserTbl, String> col_statues;

     @FXML
    private JFXComboBox<String> com_department;

    @FXML
    private JFXComboBox<String> com_catagory;

    @FXML
    private JFXComboBox<String> com_authorities;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadUserTbl();
        loadDepartmentCombo();

    }

    public class UserTbl {

        public UserTbl(int idUser, String fullname, String nic, String mobile, Date date, int statues) {
            this.idUser = idUser;
            this.fullname = new SimpleStringProperty(fullname);
            this.nic = new SimpleStringProperty(nic);
            this.mobile = new SimpleStringProperty(mobile);
            this.date = new SimpleStringProperty(new SimpleDateFormat("yyyy-MM-dd ").format(date));
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
            if (statues == 1) {
                return "Active";
            } else {
                return "Deactive";
            }
        }

        private int idUser;
        private SimpleStringProperty fullname;
        private SimpleStringProperty nic;
        private SimpleStringProperty mobile;
        private SimpleStringProperty date;
        private int statues;

    }

    ObservableList userList = FXCollections.observableArrayList();

    public void loadUserTbl() {
        col_idUser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        col_fullname.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        col_nic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        col_mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_statues.setCellValueFactory(new PropertyValueFactory<>("statues"));
        List<User> list = new modle.Users().getList();
        userList.clear();
        for (User user : list) {
            userList.add(new UserTbl(user.getIdUser(), user.getFullName(), user.getNic(), user.getMobile(), user.getRegDate(), user.getStatus()));
        }
        tbl_user.setItems(userList);

    }

    public void loadDepartmentCombo() {

        List<pojo.Department> list = new modle.Department().getList();
       
        ObservableList dList = FXCollections.observableArrayList();
        try {

            list.forEach((dip) -> {
                dList.add(dip.getDepartment());
               
            });

            com_department.setItems(dList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
