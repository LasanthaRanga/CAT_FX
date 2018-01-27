/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import pojo.Catagory;
import pojo.Department;
import pojo.User;
import pojo.UserHasCatagory;
import pojo.UserHasDepartment;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class Admin_user_updateController implements Initializable {

    @FXML
    private TableView<pojo.User> tbl_user;
    @FXML
    private TableColumn<pojo.User, String> tbl_clmn_nic;
    @FXML
    private TableColumn<pojo.User, String> tbl_clmn_name;
    @FXML
    private TableColumn<pojo.User, String> tbl_clmn_contact;

    private modle.Users user;
    @FXML
    private JFXTextField txt_fname;
    @FXML
    private JFXTextField txt_nic;
    @FXML
    private JFXTextField txt_mobile;
    @FXML
    private JFXTextField txt_regisdate;
    @FXML
    private JFXButton btn_deactive;
    @FXML
    private TableColumn<pojo.User, Integer> tbl_clmn_id;
    @FXML
    private JFXButton btn_rest;
    @FXML
    private JFXListView<pojo.Catagory> lv_category;
    @FXML
    private JFXButton btn_update;
    @FXML
    private TableColumn<pojo.User, String> tbl_clmn_status;
    @FXML
    private JFXListView<pojo.Department> lv_departments;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user = new modle.Users();
        tbl_clmn_id.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        tbl_clmn_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        tbl_clmn_nic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        tbl_clmn_contact.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        tbl_clmn_status.setCellValueFactory((TableColumn.CellDataFeatures<User, String> param) -> {
            if (null == param.getValue().getStatus()) {
                return new SimpleStringProperty("Unknown");
            } else {
                switch (param.getValue().getStatus()) {
                    case 1:
                        return new SimpleStringProperty("Active");
                    case 0:
                        return new SimpleStringProperty("Deactive");
                    default:
                        return new SimpleStringProperty("Unknown");
                }
            }
        });
        lv_category.setCellFactory(new Callback<ListView<Catagory>, ListCell<Catagory>>() {
            @Override
            public ListCell<Catagory> call(ListView<Catagory> param) {
                ListCell<Catagory> cell = new ListCell<Catagory>() {
                    @Override
                    protected void updateItem(Catagory t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getCatagoryName());
                        }
                    }
                };
                return cell;
            }
        });
        lv_departments.setCellFactory(new Callback<ListView<Department>, ListCell<Department>>() {
            @Override
            public ListCell<Department> call(ListView<Department> param) {
                ListCell<pojo.Department> cell = new ListCell<pojo.Department>(){
                    @Override
                    protected void updateItem(Department item, boolean empty) {
                        super.updateItem(item, empty); 
                        if (item != null) {
                            setText(item.getDepartment());
                        }
                    }
                };
                return cell;
            }
        });
        this.loadTable();
    }

    private void loadTable() {
        List<User> users = user.getList();
        tbl_user.getItems().clear();
        if (users != null) {
            ObservableList<pojo.User> list_active_users = FXCollections.observableArrayList(users);
            for (User list_active_user : list_active_users) {
                if(list_active_user.getStatus()==1)
                tbl_user.getItems().add(list_active_user);
            }
            for (User list_active_user : list_active_users) {
                if(list_active_user.getStatus()!=1)
                tbl_user.getItems().add(list_active_user);
            }
        }
    }

    @FXML
    private void loadUser(MouseEvent event) {
        Integer idUser = tbl_user.getSelectionModel().getSelectedItem().getIdUser();
        if (idUser != null) {
            User byIdUser = user.getByIdUserFull(idUser);
            if (byIdUser != null) {
                txt_fname.setText(byIdUser.getFullName());
                txt_mobile.setText(byIdUser.getMobile());
                txt_nic.setText(byIdUser.getNic());
                txt_regisdate.setText(new SimpleDateFormat("yyyy-MM-dd").format(byIdUser.getRegDate()));
                
                List<pojo.UserHasCatagory> userCategories = new modle.UserHasCategory().getByUser(byIdUser);
                if (userCategories != null) {
                    ObservableList<pojo.Catagory> list = FXCollections.observableArrayList();
                    for (UserHasCatagory userCategory : userCategories) {
                        list.add(userCategory.getCatagory());
                    }
                    lv_category.setItems(list);
                }
                List<UserHasDepartment> userdep = new modle.UserHasDepartments().getByUser(byIdUser);
                if(userdep!=null){
                    ObservableList<pojo.Department> list = FXCollections.observableArrayList();
                    for (pojo.UserHasDepartment dep : userdep) {
                        list.add(dep.getDepartment());
                    }
                    lv_departments.setItems(list);
                }
                
                if (byIdUser.getStatus() == 1) {
                    btn_deactive.setText("Deactive");
                    btn_deactive.setStyle("-fx-background-color: #f44336;");
                } else if (byIdUser.getStatus() == 0) {
                    btn_deactive.setText("Active");
                    btn_deactive.setStyle("-fx-background-color: #4caf50;");
                } else {
                    btn_deactive.setStyle("-fx-background-color: #e0e0e0;");
                    btn_deactive.setText("Unknown");
                }
            } else {
                Notifications.create()
                        .title("Warning")
                        .text("Not Found User.")
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.BOTTOM_RIGHT).showWarning();
            }
        } else {
            Notifications.create()
                    .title("Warning")
                    .text("Not Found Selected User.")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT).showWarning();
        }

    }

    @FXML
    private void deactiveUser(MouseEvent event) {
        pojo.User u = tbl_user.getSelectionModel().getSelectedItem();
        if (u != null) {
            if (btn_deactive.getText().equalsIgnoreCase("Deactivate")) {
                u.setStatus(0);
            } else {
                u.setStatus(1);
            }
            if (user.update(u)) {
                Notifications.create()
                        .title("Success")
                        .text("Update Success.")
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.BOTTOM_RIGHT).showInformation();
                this.loadTable();
            } else {
                Notifications.create()
                        .title("Warning")
                        .text("Update failed.")
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.BOTTOM_RIGHT).showWarning();
            }
        } else {
            Notifications.create()
                    .title("Warning")
                    .text("Not Found User.")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT).showWarning();
        }
    }

    @FXML
    private void reset(MouseEvent event) {
        this.loadTable();
        txt_fname.setText("");
        txt_mobile.setText("");
        txt_nic.setText("");
        txt_regisdate.setText("");
    }

    @FXML
    private void updateUser(MouseEvent event) {
        pojo.User u = tbl_user.getSelectionModel().getSelectedItem();
        if (u != null) {
            String fname = txt_fname.getText();
            if (!fname.isEmpty()) {
                u.setFullName(fname);
                u.setMobile(txt_mobile.getText());
                u.setNic(txt_nic.getText());
                if (user.update(u)) {
                    Notifications.create()
                            .title("Success")
                            .text("Update Success.")
                            .hideAfter(Duration.seconds(3))
                            .position(Pos.BOTTOM_RIGHT).showInformation();
                    this.loadTable();
                } else {
                    Notifications.create()
                            .title("Warning")
                            .text("Update failed.")
                            .hideAfter(Duration.seconds(3))
                            .position(Pos.BOTTOM_RIGHT).showWarning();
                }
            } else {
                Notifications.create()
                        .title("Warning")
                        .text("Enter Name.")
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.BOTTOM_RIGHT).showWarning();
            }
        } else {
            Notifications.create()
                    .title("Warning")
                    .text("Not Found User.")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT).showWarning();
        }
    }

}
