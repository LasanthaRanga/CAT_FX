/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.Label;
import modle.UserCat;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modle.Aplication;
import modle.ApplicationStatus;
import pojo.Apprualstatues;
import pojo.User;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class AuthoritistController implements Initializable {

    @FXML
    private Label lbl_atype;

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
    private JFXTextArea txt_myComment;

    @FXML
    private JFXButton btn_non;

   

    @FXML
    private JFXTextArea txt_coment;

    @FXML
    private JFXButton btn_approve;

    @FXML
    private Label lbl_user;

    @FXML
    private JFXButton btn_reload;

    @FXML
    private ToggleGroup status;

    @FXML
    private JFXRadioButton radio_panding;

    @FXML
    private JFXRadioButton radio_approve;

    @FXML
    private JFXRadioButton radio_none;

    /**
     * Initializes the controller class.
     */
    UserCat uc;
    pojo.Application app;
    pojo.Apprualstatues apprualstatues;
    modle.Aplication modApp;
    modle.ApplicationStatus modState;
    @FXML
    private JFXButton btn_more;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        modApp = new modle.Aplication();
        modState = new modle.ApplicationStatus();
        List<UserCat> userCats = modle.AuthUser.getUserCats();
        uc = userCats.get(0);
        lbl_atype.setText(uc.getOthoname());
        loadTable();
        LoadApprovTable();
        LoadApproveData();
        approve();
        nonApprove();
        radio();
      ///  sendToApprove();
        close();

    }

    public void reload() {
        btn_reload.setOnAction((event) -> {
            modApp = new modle.Aplication();
            modState = new modle.ApplicationStatus();
            List<UserCat> userCats = modle.AuthUser.getUserCats();
            uc = userCats.get(0);
            lbl_atype.setText(uc.getOthoname());
            loadTable();
            LoadApprovTable();
            LoadApproveData();
            approve();
            nonApprove();
        });
    }

    public void LoadApproveData() {
        tbl_approve.setOnMouseReleased((event) -> {
            int id = tbl_approve.getSelectionModel().getSelectedItem().getId();
            apprualstatues = modApp.getApproveStatus(id);
            txt_coment.setText(apprualstatues.getDescription());
            User user = new modle.Users().getByIdUser(apprualstatues.getUser().getIdUser());
            lbl_user.setText(user.getFullName());
        });
    }

    public void approve() {

        btn_approve.setOnAction((event) -> {
            if (apprualstatues != null) {
                Integer idOtheritisCat = apprualstatues.getIdOtheritisCat();
                int idOc = modle.AuthUser.getIdOc();
                if (idOc == idOtheritisCat) {
                    String mycoment = txt_myComment.getText();
                    apprualstatues.setApproveDate(new Date());
                    apprualstatues.setDescription(mycoment);
                    apprualstatues.setUser(modle.AuthUser.getUser());
                    apprualstatues.setStatues(1);
                    apprualstatues.setSyn(1);
                    boolean update = modState.update(apprualstatues);
                    if (update) {
                        modle.Allert.notificationGood("Approved", mycoment);
                    } else {
                        modle.Allert.notificationError("Error", "Some Thing Was Wrong");
                    }
                } else {
                    modle.Allert.notificationInfo("Select Your Authoritist", lbl_atype.getText());
                }
            }

        });

    }

    public void nonApprove() {

        btn_non.setOnAction((event) -> {
            if (apprualstatues != null) {
                Integer idOtheritisCat = apprualstatues.getIdOtheritisCat();
                int idOc = modle.AuthUser.getIdOc();
                if (idOc == idOtheritisCat) {
                    String mycoment = txt_myComment.getText();
                    apprualstatues.setApproveDate(new Date());
                    apprualstatues.setDescription(mycoment);
                    apprualstatues.setUser(modle.AuthUser.getUser());
                    apprualstatues.setStatues(2);
                    apprualstatues.setSyn(1);
                    boolean update = modState.update(apprualstatues);
                    if (update) {
                        modle.Allert.notificationGood("NON approved", mycoment);
                    } else {
                        modle.Allert.notificationError("Error", "Some Thing Was Wrong");
                    }
                } else {
                    modle.Allert.notificationInfo("Select Your Authoritist", lbl_atype.getText());
                }
            }

        });

    }

    public void LoadApprovTable() {

        tbl_applicaion.setOnMouseReleased((event) -> {

            int appno = tbl_applicaion.getSelectionModel().getSelectedItem().getAppno();

            app = modApp.getApllicationPojoByID(appno);

            loadTableApprove();
            loadAppDetails();

            //  txt_idApp.setText(app.getIdApplication()+"");
        });

    }

   

    @FXML
    private void moreDetaisl(ActionEvent event) {

        System.out.println("MORE");
        try {
            modle.StaticBadu.setApp(app);
            AnchorPane paymant = javafx.fxml.FXMLLoader.load(getClass().getResource("/view/Ditails.fxml"));
            btn_more.getParent().getScene();
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

    public class AppTbl {

        public AppTbl(int appno, String Type, String nature, Double alocation, Double txt, int payapp) {
            this.appno = appno;
            this.Type = new SimpleStringProperty(Type);
            this.nature = new SimpleStringProperty(nature);
            this.alocation = alocation;
            this.txt = txt;
            this.payapp = payapp;
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
            } else {
                return "Non Approve";
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
        private Double alocation;
        private Double txt;
        private int payapp;

    }

    ObservableList appList = FXCollections.observableArrayList();

    public void loadTable() {
        c_idApp.setCellValueFactory(new PropertyValueFactory<>("appno"));
        c_type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        c_nature.setCellValueFactory(new PropertyValueFactory<>("nature"));
        c_alocation.setCellValueFactory(new PropertyValueFactory<>("alocation"));
        c_tax.setCellValueFactory(new PropertyValueFactory<>("txt"));
        c_approve.setCellValueFactory(new PropertyValueFactory<>("payapp"));

        Aplication aplication = modApp;
        List<modle.AppTbl> appTbls = aplication.getAppListToTableForOtho();
        appList.clear();
        for (modle.AppTbl a : appTbls) {
            appList.add(new AppTbl(a.getAppno(), a.getType(), a.getNature(), a.getAlocation(), a.getTxt(), a.getPayapp()));
        }
        tbl_applicaion.setItems(appList);

    }

    public void loadApproveTable() {
        c_idApp.setCellValueFactory(new PropertyValueFactory<>("appno"));
        c_type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        c_nature.setCellValueFactory(new PropertyValueFactory<>("nature"));
        c_alocation.setCellValueFactory(new PropertyValueFactory<>("alocation"));
        c_tax.setCellValueFactory(new PropertyValueFactory<>("txt"));
        c_approve.setCellValueFactory(new PropertyValueFactory<>("payapp"));

        Aplication aplication = modApp;
        List<modle.AppTbl> appTbls = aplication.getAppListToTableForOtho_Approve();
        appList.clear();
        for (modle.AppTbl a : appTbls) {
            appList.add(new AppTbl(a.getAppno(), a.getType(), a.getNature(), a.getAlocation(), a.getTxt(), a.getPayapp()));
        }
        tbl_applicaion.setItems(appList);

    }

    public void loadApproveTable_None() {
        c_idApp.setCellValueFactory(new PropertyValueFactory<>("appno"));
        c_type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        c_nature.setCellValueFactory(new PropertyValueFactory<>("nature"));
        c_alocation.setCellValueFactory(new PropertyValueFactory<>("alocation"));
        c_tax.setCellValueFactory(new PropertyValueFactory<>("txt"));
        c_approve.setCellValueFactory(new PropertyValueFactory<>("payapp"));

        Aplication aplication = modApp;
        List<modle.AppTbl> appTbls = aplication.getAppListToTableForOtho_None();
        appList.clear();
        for (modle.AppTbl a : appTbls) {
            appList.add(new AppTbl(a.getAppno(), a.getType(), a.getNature(), a.getAlocation(), a.getTxt(), a.getPayapp()));
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

    public void loadAppDetails() {

        Integer idUser = app.getUser().getIdUser();

        if (apprualstatues != null) {
            User user = new modle.Users().getByIdUser(apprualstatues.getUser().getIdUser());

            //  lbl_username.setText(user.getFullName());
            //   lbl_idApp.setText(app.getIdApplication() + "");
        }
    }

    public void radio() {
        radio_panding.setOnAction((event) -> {
            loadTable();
        });
        radio_approve.setOnAction((event) -> {
            loadApproveTable();
        });

        radio_none.setOnAction((event) -> {
            loadApproveTable_None();
        });
    }

//    public void sendToApprove() {
//        btn_send.setOnAction((event) -> {
//
//            modle.StaticBadu.setApp(app);
//
//            System.out.println("Approve");
//            try {
//                AnchorPane paymant = javafx.fxml.FXMLLoader.load(getClass().getResource("/view/SendToApprove.fxml"));
//                btn_send.getParent().getScene();
//                Scene scene = new Scene(paymant);
//                Stage stage = new Stage();
//                stage.initStyle(StageStyle.TRANSPARENT);
//                stage.setScene(scene);
//                stage.show();
//
//            } catch (IOException ex) {
//                ex.printStackTrace();
//                Logger.getLogger(PayController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        });
//    }

    public void close() {

        btn_reload.setOnAction((event) -> {
            btn_reload.getParent().getScene().getWindow().hide();
        });
    }

}
