package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modle.ApplicationStatus;
import modle.Authority;
import pojo.Application;
import pojo.Apprualstatues;
import pojo.Otheritiscat;
import pojo.User;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class SendToApproveController implements Initializable {

    @FXML
    private JFXComboBox<String> com_autho;

    @FXML
    private Label lbl_idApplication;

    @FXML
    private JFXButton btn_send;

    @FXML
    private JFXButton btn_done;

    @FXML
    private TableView<approve> tbl_approve;

    @FXML
    private TableColumn<approve, Integer> col_id;

    @FXML
    private TableColumn<approve, String> col_approve_by;

    @FXML
    private TableColumn<approve, String> col_statues;

    @FXML
    private TableColumn<approve, String> col_date;

    Authority authority;
    pojo.Otheritiscat outo;
    String au;
    pojo.Application app;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        authority = new modle.Authority();
        // TODO
        app = modle.StaticBadu.getApp();
        //System.out.println(app.getIdApplication());
        lbl_idApplication.setText(app.getIdApplication().toString());
        loadApprovCombo();
        save();
        loadTable();

        btn_done.setOnAction((event) -> {
            btn_done.getParent().getScene().getWindow().hide();
        });

    }

    public void loadApprovCombo() {

        List<Otheritiscat> list = authority.getList();
        ObservableList auto = FXCollections.observableArrayList();

        for (Otheritiscat otheritiscat : list) {
            auto.add(otheritiscat.getCatname());
        }
        com_autho.setItems(auto);

    }

    public void getSelected() {
        au = com_autho.getSelectionModel().getSelectedItem();
        outo = authority.getAuthorityByAuthorityName(au);
    }

    public void save() {
        btn_send.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getSelected();
                
                Apprualstatues apprualstatues = new pojo.Apprualstatues();
                apprualstatues.setApplication(app);
                apprualstatues.setDate(new Date());
                apprualstatues.setIdOtheritisCat(outo.getIdOtheritisCat());
                apprualstatues.setStatues(0);
                apprualstatues.setSyn(1);
                apprualstatues.setUser(modle.AuthUser.getUser());

                boolean save = new modle.ApplicationStatus().save(apprualstatues);

                if (save) {
                    modle.Allert.notificationGood("Send To", au);
                    loadTable();
                } else {
                    modle.Allert.notificationError("error", au);
                }
            }
        });
    }

    public class approve {

        /**
         * @return the id
         */
        public int getId() {
            return id;
        }

        /**
         * @return the statues
         */
        public String getStatues() {
            if (statues == 0) {
                return "Pending";
            } else if (statues == 1) {
                return "approve";
            } else if (statues == 2) {
                return "Non Approve";
            } else {
                return null;
            }

        }

        /**
         * @return the autho
         */
        public String getAutho() {
            return autho.get();
        }

        /**
         * @return the date
         */
        public String getDate() {
            return date.get();
        }
        private int id;
        private int statues;
        private SimpleStringProperty autho;
        private SimpleStringProperty date;

        public approve(int id, int statues, String autho, Date date) {
            this.id = id;
            this.statues = statues;
            this.autho = new SimpleStringProperty(autho);
            this.date = new SimpleStringProperty(new SimpleDateFormat("yyyy.MM.dd").format(date));
        }

    }
    ObservableList natureList = FXCollections.observableArrayList();

    public void loadTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_approve_by.setCellValueFactory(new PropertyValueFactory<>("autho"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_statues.setCellValueFactory(new PropertyValueFactory<>("statues"));

        ApplicationStatus applicationStatus = new modle.ApplicationStatus();
        List<modle.Approve> list = applicationStatus.getListByApplication(app);

        natureList.clear();
        for (modle.Approve tnn : list) {
            natureList.add(new approve(tnn.getIdApprove(), tnn.getStatues(), tnn.getOutherCatName(), tnn.getDate()));
        }
        tbl_approve.setItems(natureList);
    }

}
