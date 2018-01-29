package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import pojo.User;
import pojo.UserHasCatagory;
import pojo.UserHasDepartment;
import pojo.UserHasOtheritiscat;

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

    @FXML
    private JFXButton btn_dip_assign;

    @FXML
    private JFXButton btn_cat_assign;

    @FXML
    private JFXButton btn_outh_assign;

    @FXML
    private TableView<TableCat> tbl_dip;

    @FXML
    private TableColumn<TableCat, Integer> col_dip_id;

    @FXML
    private TableColumn<TableCat, String> col_dip_dipartment;



    @FXML
    private TableView<TblCat> tbl_cat;

    @FXML
    private TableColumn<TblCat, Integer> col_c_id;

    @FXML
    private TableColumn<TblCat, String> col_c_cat;

    @FXML
    private TableView<TblAuther> tbl_author;

    @FXML
    private TableColumn<TblAuther, String> col_a_id;

    @FXML
    private TableColumn<TblAuther, String> col_a_name;

    modle.Users usersmod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        usersmod = new modle.Users();
        loadUserTbl();
        loadDepartmentCombo();
        loadCatagoryCombo();
        loadOutherCombo();

        selectUserFromTable();
        selectDepartmentFromCombo();
        assignDipartment();

        selectCatagoryFromCombo();
        selectOutherFromCombo();

        assignCatagory();
        assignAuthority();

//        btn_reload.setOnAction((event) -> {
//
//            loadUserTbl();
//            loadDepartmentCombo();
//            loadCatagoryCombo();
//            loadOutherCombo();
//
//            com_authorities.getSelectionModel().clearSelection();
//            com_catagory.getSelectionModel().clearSelection();
//            com_department.getSelectionModel().clearSelection();
//
//        });
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

    public void loadCatagoryCombo() {

        List<pojo.Catagory> list = new modle.Catagory().getList();
        ObservableList cList = FXCollections.observableArrayList();
        try {
            list.forEach((dip) -> {
                cList.add(dip.getCatagoryName());

            });
            com_catagory.setItems(cList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadOutherCombo() {
        List<pojo.Otheritiscat> list = new modle.Authority().getList();
        ObservableList aList = FXCollections.observableArrayList();
        try {
            list.forEach((dip) -> {
                aList.add(dip.getCatname());

            });
            com_authorities.setItems(aList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    pojo.User user;

    public void selectUserFromTable() {
        tbl_user.setOnMouseReleased((MouseEvent event) -> {
            int idUser = tbl_user.getSelectionModel().getSelectedItem().getIdUser();
            user = usersmod.getByIdUser(idUser);
            loadDepartmentByUser(usersmod);
            loadCatByUser(usersmod);
            loadAutherByUser(usersmod);
        });
    }

    pojo.Department dip;
    pojo.Catagory cat;
    pojo.Otheritiscat other;

    public void selectDepartmentFromCombo() {
        com_department.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String selectionModel = com_department.getSelectionModel().getSelectedItem();
                System.out.println(selectionModel);
                dip = new modle.Department().getDepartmentByDepartmentName(selectionModel);

            }
        });
    }

    public void selectCatagoryFromCombo() {
        com_catagory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String selectionModel = com_catagory.getSelectionModel().getSelectedItem();
                System.out.println(selectionModel);
                cat = new modle.Catagory().getCatagoryByCatagoryName(selectionModel);
            }
        });
    }

    public void selectOutherFromCombo() {
        com_authorities.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String selectionModel = com_authorities.getSelectionModel().getSelectedItem();
                System.out.println(selectionModel);
                other = new modle.Authority().getAuthorityByAuthorityName(selectionModel);
            }
        });
    }

    public void assignDipartment() {
        btn_dip_assign.setOnAction((event) -> {
            if (user != null && dip != null) {
                UserHasDepartment uhd = new pojo.UserHasDepartment(dip, user, 1);
                //    UserHasDepartment uhd = new pojo.UserHasDepartment(dip, user, 1, 1);
                if (new modle.UserHas_Dip().save(uhd)) {
                    modle.Allert.notificationGood("Assign Compleet", user.getFullName());
                    loadDepartmentByUser(usersmod);
                } else {
                    modle.Allert.notificationError("Error", "");
                }
            }

        });
    }

    public void assignCatagory() {
        btn_cat_assign.setOnAction((event) -> {
            if (user != null && cat != null) {
                UserHasCatagory uhc = new pojo.UserHasCatagory(cat, user, 1, 1);

                if (new modle.UserHas_Catagory().save(uhc)) {
                    modle.Allert.notificationGood("Assign Compleet", user.getFullName());
                    loadCatByUser(usersmod);
                } else {
                    modle.Allert.notificationError("Error", "");
                }
            }

        });
    }

    public void assignAuthority() {
        btn_outh_assign.setOnAction((event) -> {
            if (user != null && other != null) {
                UserHasOtheritiscat uha = new pojo.UserHasOtheritiscat(other, user, 1);
                

                if (new modle.UserHas_Authorities().save(uha)) {
                    modle.Allert.notificationGood("Assign Compleet", user.getFullName());
                    loadAutherByUser(usersmod);
                } else {
                    modle.Allert.notificationError("Error", "");
                }
            }
        });
    }

    ObservableList dipList = FXCollections.observableArrayList();

    public void loadDepartmentByUser(modle.Users usermod) {

        col_dip_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_dip_dipartment.setCellValueFactory(new PropertyValueFactory<>("dip"));

        HashMap<Integer, String> departments = usermod.getDepartments(user.getIdUser());
        Set keySet = departments.keySet();
        dipList.clear();
        for (Integer key : departments.keySet()) {
            dipList.add(new TableCat(key, departments.get(key)));
        }
        tbl_dip.setItems(dipList);

    }

    public class TableCat {

        /**
         * @return the id
         */
        public int getId() {
            return id;
        }

        /**
         * @return the dip
         */
        public String getDip() {
            return dip.get();
        }

        private int id;
        private SimpleStringProperty dip;

        public TableCat(int id, String dip) {
            this.id = id;
            this.dip = new SimpleStringProperty(dip);
        }

    }

    ObservableList cList = FXCollections.observableArrayList();

    public void loadCatByUser(modle.Users usermod) {

        col_c_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_c_cat.setCellValueFactory(new PropertyValueFactory<>("dip"));

        HashMap<Integer, String> departments = usermod.getCatagory(user.getIdUser());
        Set keySet = departments.keySet();
        cList.clear();
        for (Integer key : departments.keySet()) {
            cList.add(new TblCat(key, departments.get(key)));
        }
        tbl_cat.setItems(cList);

    }

    public class TblCat {

        /**
         * @return the id
         */
        public int getId() {
            return id;
        }

        /**
         * @return the dip
         */
        public String getDip() {
            return dip.get();
        }

        private int id;
        private SimpleStringProperty dip;

        public TblCat(int id, String dip) {
            this.id = id;
            this.dip = new SimpleStringProperty(dip);
        }

    }

    ObservableList aList = FXCollections.observableArrayList();

    public void loadAutherByUser(modle.Users usermod) {

        col_a_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_a_name.setCellValueFactory(new PropertyValueFactory<>("dip"));

        HashMap<Integer, String> departments = usermod.getAuthoriti(user.getIdUser());
        Set keySet = departments.keySet();
        aList.clear();
        for (Integer key : departments.keySet()) {
            aList.add(new TblAuther(key, departments.get(key)));
        }
       
        tbl_author.setItems(aList);

    }

    public class TblAuther {

        /**
         * @return the id
         */
        public int getId() {
            return id;
        }

        /**
         * @return the dip
         */
        public String getDip() {
            return dip.get();
        }

        private int id;
        private SimpleStringProperty dip;

        public TblAuther(int id, String dip) {
            this.id = id;
            this.dip = new SimpleStringProperty(dip);
        }

    }

}
