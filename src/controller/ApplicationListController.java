/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.sun.management.jmx.Trace;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modle.Aplication;
import modle.ApplicationStatus;
import org.hibernate.Session;
import pojo.Application;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class ApplicationListController implements Initializable {

    @FXML
    private TableView<AppTbl> tbl_applicaion;

    @FXML
    private TableColumn<AppTbl, Integer> c_idApp;

    @FXML
    private TableColumn<AppTbl, String> c_type;

    @FXML
    private TableColumn<AppTbl, String> c_nature;

    @FXML
    private TableColumn<AppTbl, Double> c_alocation;

    @FXML
    private TableColumn<AppTbl, Double> c_tax;

    @FXML
    private TableColumn<AppTbl, String> c_approve;

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

    @FXML
    private JFXTextField txt_idApp;
    @FXML
    private JFXTextField txt_idApp1;
    @FXML
    private TableColumn<AppTbl, String> c_tname;
    @FXML
    private ToggleGroup statues;
    @FXML
    private JFXRadioButton ra_pending;
    @FXML
    private JFXRadioButton ra_approve;
    @FXML
    private JFXRadioButton ra_nonaprove;
    @FXML
    private ToggleGroup paid;

    @FXML
    private JFXRadioButton ra_approve_pay;
    @FXML
    private JFXRadioButton ra_pendig_pay;
    @FXML
    private JFXRadioButton ra_paid;
    @FXML
    private JFXButton btn_sendtoApprove;
    @FXML
    private JFXButton btn_approve_toPay;
    @FXML
    private JFXButton btn_All;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadTable();
        // clickTable();

        tbl_applicaion.setOnMouseReleased((event) -> {

            int appno = tbl_applicaion.getSelectionModel().getSelectedItem().getAppno();

            app = new modle.Aplication().getApllicationPojoByID(appno);

            loadTableApprove();

            txt_idApp.setText(app.getIdApplication() + "");

        });

    }

    @FXML
    private void reload(ActionEvent event) {

        loadTable();

    }

    @FXML
    private void sendToApprove(ActionEvent event) {

        try {
            modle.StaticBadu.setApp(app);
            AnchorPane paymant = javafx.fxml.FXMLLoader.load(getClass().getResource("/view/SendToApprove.fxml"));
            btn_sendtoApprove.getParent().getScene();
            Scene scene = new Scene(paymant);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(PayController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void sendToPay(ActionEvent event) {

        Integer approveToPaymant = app.getApproveToPaymant();

        if (approveToPaymant == 0) {
            app.setApproveToPaymant(1);
            Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
            try {
                session.update(app);
                session.beginTransaction().commit();
                modle.Allert.notificationGood("Approve To Paymant", app.getIdApplication() + "");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                session.close();
            }
        } else if (approveToPaymant == 1) {
            modle.Allert.notificationInfo("Allrady Approve", app.getIdApplication() + "");
        } else {
            modle.Allert.notificationInfo("Paymant Done", app.getIdApplication() + "");
        }
    }

    @FXML
    private void getAllDetails(ActionEvent event) {

    }

    public class AppTbl {

        public AppTbl(int appno, String Type, String nature, Double alocation, Double txt, int payapp, String Tname) {
            this.appno = appno;
            this.Type = new SimpleStringProperty(Type);
            this.nature = new SimpleStringProperty(nature);
            this.alocation = alocation;
            this.txt = txt;
            this.payapp = payapp;
            this.Tname = new SimpleStringProperty(Tname);
        }

        /**
         * @return the appno
         */
        public int getAppno() {
            return appno;
        }

        /**
         * @param appno the appno to set
         */
        public void setAppno(int appno) {
            this.appno = appno;
        }

        /**
         * @return the Type
         */
        public String getType() {
            return Type.get();
        }

        /**
         * @param Type the Type to set
         */
        public void setType(SimpleStringProperty Type) {
            this.Type = Type;
        }

        /**
         * @return the nature
         */
        public String getNature() {
            return nature.get();
        }

        /**
         * @param nature the nature to set
         */
        public void setNature(SimpleStringProperty nature) {
            this.nature = nature;
        }

        /**
         * @return the alocation
         */
        public Double getAlocation() {
            return alocation;
        }

        /**
         * @param alocation the alocation to set
         */
        public void setAlocation(Double alocation) {
            this.alocation = alocation;
        }

        /**
         * @return the txt
         */
        public Double getTxt() {
            return txt;
        }

        /**
         * @param txt the txt to set
         */
        public void setTxt(Double txt) {
            this.txt = txt;
        }

        /**
         * @return the payapp
         */
        public String getPayapp() {
            if (payapp == 1) {
                return "Approve";
            } else if (payapp == 0) {
                return "Pending";
            } else {
                return "Paied";
            }
        }

        /**
         * @param payapp the payapp to set
         */
        public void setPayapp(int payapp) {
            this.payapp = payapp;
        }

        private int appno;
        private SimpleStringProperty Type;
        private SimpleStringProperty nature;
        private SimpleStringProperty Tname;
        private Double alocation;
        private Double txt;
        private int payapp;

        /**
         * @return the Tname
         */
        public String getTname() {
            return Tname.get();
        }

    }

    ObservableList appList = FXCollections.observableArrayList();

    public void loadTable() {
        c_idApp.setCellValueFactory(new PropertyValueFactory<>("appno"));
        c_type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        c_nature.setCellValueFactory(new PropertyValueFactory<>("nature"));
        c_alocation.setCellValueFactory(new PropertyValueFactory<>("alocation"));
        c_tax.setCellValueFactory(new PropertyValueFactory<>("txt"));
        c_approve.setCellValueFactory(new PropertyValueFactory<>("payapp"));
        c_tname.setCellValueFactory(new PropertyValueFactory<>("Tname"));

        Aplication aplication = new modle.Aplication();

        int approve = 0;
        int paid = 0;

        if (ra_approve.isSelected()) {
            approve = 1;
        }
        if (ra_pending.isSelected()) {
            approve = 0;
        }
        if (ra_nonaprove.isSelected()) {
            approve = 2;
        }
////////////////////
        if (ra_pendig_pay.isSelected()) {
            paid = 0;
        }
        if (ra_approve_pay.isSelected()) {
            paid = 1;
        }
        if (ra_paid.isSelected()) {
            paid = 2;
        }

        List<modle.AppTbl> appTbls = aplication.getAppListToTable(approve, paid);
        appList.clear();
        for (modle.AppTbl a : appTbls) {
            appList.add(new AppTbl(a.getAppno(), a.getType(), a.getNature(), a.getAlocation(), a.getTxt(), a.getPayapp(), a.getTname()));
        }
        tbl_applicaion.setItems(appList);

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

    pojo.Application app;

    ObservableList natureList = FXCollections.observableArrayList();

    public void loadTableApprove() {
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

//    public void clickTable(){
//    tbl_applicaion.setOnMouseReleased((event) -> {
//        
//        int appno = tbl_applicaion.getSelectionModel().getSelectedItem().getAppno();
//        loadTableApprove();
//    });
//    
//    }
}
//asdfasfadfasf asf
