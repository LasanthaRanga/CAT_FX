package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modle.Aplication;
import modle.Customer;
import modle.Nature;
import org.controlsfx.control.textfield.TextFields;
import org.hibernate.Session;
import pojo.Application;
import pojo.Assessment;
import pojo.SubNature;
import pojo.TradeNature;
import pojo.Ward;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class ApplicationController implements Initializable {

    @FXML
    private JFXComboBox<String> com_ward;

    @FXML
    private JFXComboBox<String> com_street;

    @FXML
    private JFXTextField txt_assesmantNO;

    @FXML
    private JFXComboBox<String> com_trade_type;

    @FXML
    private JFXComboBox<String> com_nature;

    @FXML
    private JFXComboBox<String> com_subnature;

    @FXML
    private JFXTextField txt_cus_nic;

    @FXML
    private JFXTextField txt_cus_fname;

    @FXML
    private JFXTextField txt_ro;

    @FXML
    private JFXTextField txt_aplicaton_No;

    @FXML
    private JFXTextField txt_year;

    @FXML
    private JFXTextField txt_month;

    @FXML
    private JFXTextField txt_day;

    @FXML
    private JFXTextField txt_allocaton;

    @FXML
    private JFXTextField txt_trade_name;

    @FXML
    private JFXTextField txt_adl1;

    @FXML
    private JFXTextField txt_adl2;

    @FXML
    private JFXTextField txt_adl3;

    @FXML
    private JFXTextField txt_taxt_amount;

    @FXML
    private JFXTextArea txt_discription;

    @FXML
    private JFXButton btn_save_app;

    @FXML
    private JFXButton btn_send_approve;

    @FXML
    public static Label lbl_idCus;

    @FXML
    private JFXButton btn_approve;

    pojo.Ward pward = null;

    @FXML
    private JFXButton btn_clear;

    @FXML
    private JFXTextField txt_appno;

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
    private TableColumn<AppTbl, String> c_tname;
    @FXML
    private JFXToggleButton toggle;
    @FXML
    private JFXButton btn_update_app;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadWardCombo();
        loadStrretCombo();
        loadTreadTypeCombo();
        getTradyType();
        getSelectedNature();

        loadRo();

        SetDate();
        setApplicationid();
        saveApplication();
        sendToApprove();
        Customer cus = new modle.Customer();
        ArrayList list = cus.getCustomerFnameList();
        TextFields.bindAutoCompletion(txt_cus_fname, list);

        btn_approve.setOnAction((event) -> {
            ApproveToPay();
        });

        tbl_applicaion.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                AppTbl selectedApp = tbl_applicaion.getSelectionModel().getSelectedItem();

                if (selectedApp != null) {
                    String appNOString = selectedApp.getAppNOString();
                    txt_appno.setText(appNOString);
                    searchApplicationNO();
                }
            }
        });

    }

    @FXML
    private void searchAppNo(KeyEvent event) {

        event.consume();

//        String character = event.getCharacter();
//       
//
        if (event.getCode() == KeyCode.ENTER) {
            searchApplicationNO();
        }
    }

    public boolean searchApplicationNO() {
        boolean b = false;
        String text = txt_appno.getText();
        a = null;
        a = new modle.Aplication().getApllicationPojoByApplicationNo(text);

        if (a != null) {
            b = true;
            Session session = conn.NewHibernateUtil.getSessionFactory().openSession();

            try {
                a = (pojo.Application) session.load(pojo.Application.class, a.getIdApplication());

                modle.Allert.notificationInfo("Find Applicaiton", text);
                pCustomer = a.getCustomer();
                txt_allocaton.setText(a.getAllocation() + "");
                txt_taxt_amount.setText(a.getTaxAmount() + "");
                txt_trade_name.setText(a.getTradeName());
                txt_adl1.setText(a.getTradeAddress1());
                txt_adl2.setText(a.getTradeAddress2());
                txt_adl3.setText(a.getTradeAddress3());
                txt_discription.setText(a.getDiscription());

                txt_assesmantNO.setText(a.getAssessment().getAssessmentNo());
                txt_cus_fname.setText(a.getCustomer().getFullName());
                txt_cus_nic.setText(a.getCustomer().getNic());

                com_ward.getSelectionModel().select(a.getAssessment().getStreet().getWard().getWardName());
                com_street.getSelectionModel().select(a.getAssessment().getStreet().getStreetName());
                com_trade_type.getSelectionModel().select(a.getTradeType().getTypeName());
                com_nature.getSelectionModel().select(a.getTradeNature().getNature());

                if (a.getYear() != null) {
                    txt_year.setText(a.getYear() + "");
                }
                if (a.getMonth() != null) {
                    txt_month.setText(a.getMonth() + "");
                }
                if (a.getApplicationDate() != null) {
                    txt_day.setText(new SimpleDateFormat("dd").format(a.getApplicationDate()));
                }
                if (a.getApproveToPaymant() == 2) {
                    btn_update_app.setDisable(true);
                } else {
                    btn_update_app.setDisable(false);
                }
                if (a.getUser() != null) {
                    txt_ro.setText(a.getUser().getFullName());
                }
                SubNature subNature = a.getSubNature();
                if (subNature != null) {
                    com_subnature.getSelectionModel().select(subNature.getSubNature());
                }
            } catch (Exception e) {
                e.printStackTrace();
                session.close();
            } finally {
                session.close();
            }

        } else {
            modle.Allert.notificationInfo("No Application no", text);
            clearApplication();
            txt_appno.setText(text);
        }
        return b;
    }

    int x = 0;
    modle.Customer upcus;

    @FXML
    public void loadCusByFullname(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {
            System.out.println("ENTER GEHUWAA");
            String fname = txt_cus_fname.getText();

            if (x == 0) {
                modle.StaticBadu.setCus_fullname(fname);
                List<Customer> searchCustomer = new modle.Customer().searchCustomer(fname);
                modle.StaticBadu.setCuslist(searchCustomer);
                x = 1;

                {

                    if (searchCustomer.size() > 1) {
                        if (searchCustomer != null) {
                            try {
                                AnchorPane paymant = javafx.fxml.FXMLLoader.load(getClass().getResource("/view/SearchCus.fxml"));
                                txt_cus_fname.getParent().getScene();
                                Scene scene = new Scene(paymant);
                                Stage stage = new Stage();
                                stage.initStyle(StageStyle.TRANSPARENT);
                                stage.setScene(scene);
                                stage.show();

                                x = 2;

                            } catch (IOException ex) {
                                ex.printStackTrace();
                                Logger.getLogger(PayController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    } else {
                        upcus = searchCustomer.get(0);
                        txt_cus_nic.setText(upcus.getNic());

                        Session openSession = conn.NewHibernateUtil.getSessionFactory().openSession();
                        try {

                            Integer idCustomer = upcus.getIdCustomer();
                            pojo.Customer load = (pojo.Customer) openSession.load(pojo.Customer.class, idCustomer);
                            loadTable();
                            Set<Assessment> assessments = load.getAssessments();

                            for (Assessment assessment : assessments) {

                                String assessmentNo = assessment.getAssessmentNo();
                                String streetName = assessment.getStreet().getStreetName();
                                String wardName = assessment.getStreet().getWard().getWardName();

                                System.out.println(assessmentNo + "  Ases");

                                txt_assesmantNO.setText(assessmentNo);
                                com_ward.getSelectionModel().select(wardName);
                                com_street.getSelectionModel().select(streetName);

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            openSession.close();
                        }

                        x = 0;

                    }

                }

            }//x = 0
            else if (x == 2) {
                System.out.println(x);
                System.out.println(modle.StaticBadu.getpCustomer().getFullName() + "=== Static Badu");
                if (modle.StaticBadu.getpCustomer() != null) {
                    upcus = new modle.Customer().searchCustomerByID(modle.StaticBadu.getpCustomer().getIdCustomer());

                    txt_cus_nic.setText(upcus.getNic());
                    Session openSession = conn.NewHibernateUtil.getSessionFactory().openSession();
                    try {

                        txt_assesmantNO.setText(modle.StaticBadu.getAssessment().getAssessmentNo());
                        com_ward.getSelectionModel().select(modle.StaticBadu.getWard().getWardName());
                        com_street.getSelectionModel().select(modle.StaticBadu.getStreet().getStreetName());

//                        Integer idCustomer = upcus.getIdCustomer();
//                        pojo.Customer load = (pojo.Customer) openSession.load(pojo.Customer.class, idCustomer);
//                        loadTable();
//                        Set<Assessment> assessments = load.getAssessments();
//
//                        for (Assessment assessment : assessments) {
//
//                            String assessmentNo = assessment.getAssessmentNo();
//                            String streetName = assessment.getStreet().getStreetName();
//                            String wardName = assessment.getStreet().getWard().getWardName();
//
//                            System.out.println(assessmentNo + "  Ases");
//
//                            txt_assesmantNO.setText(assessmentNo);
//                            com_ward.getSelectionModel().select(wardName);
//                            com_street.getSelectionModel().select(streetName);
//
//                        }
//                        Assessment assessment = (pojo.Assessment) openSession.createCriteria(pojo.Assessment.class).add(Restrictions.eq("idAssessment", upcus.getAsses().getIdAssessment())).uniqueResult();
//
//                        String assessmentNo = assessment.getAssessmentNo();
//                        String streetName = assessment.getStreet().getStreetName();
//                        String wardName = assessment.getStreet().getWard().getWardName();
//
//                        System.out.println(assessmentNo + "  Ases");
//
//                        txt_assesmantNO.setText(assessmentNo);
//                        com_street.getSelectionModel().select(streetName);
//                        com_ward.getSelectionModel().select(wardName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        openSession.close();
                    }

                    x = 0;

                }
            }// x==2

        }

    }

    public void loadWardCombo() {
        List<Ward> list = new modle.Ward().getList();
        ObservableList List = FXCollections.observableArrayList();
        for (Ward ward : list) {
            List.add(ward.getWardName());
        }
        com_ward.setItems(List);

    }

    public void loadStrretCombo() {

        com_ward.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                modle.Ward ward = new modle.Ward();
                String selectedItem = com_ward.getSelectionModel().getSelectedItem();
                ward.setWardname(selectedItem);
                ObservableList list = ward.getStreetBySelectedWard();
                com_street.setItems(list);
            }
        });

    }

    @FXML
    public void keyListener(KeyEvent event) {
        int length = txt_assesmantNO.getLength();
        // System.out.println(length);

        if (event.getCode() == KeyCode.ENTER) {

            modle.Customer customer = null;
            getSelectedWaredStrret();
            String asno = txt_assesmantNO.getText();

            customer = modle.AssesmantNo.searchByAssesmantNO(selectedWard, selectedStreet, asno);

            if (customer == null) {
                txt_cus_nic.setText(null);
                txt_cus_fname.setText(null);
                upcus = null;
                appList.clear();
                tbl_applicaion.setItems(appList);
                customer = null;

            } else {
                upcus = customer;
                loadTable();
                txt_cus_nic.setText(customer.getNic());
                txt_cus_fname.setText(customer.getFullName());
            }
        }

    }

    String selectedStreet = null;
    String selectedWard = null;

    public void getSelectedWaredStrret() {
        selectedWard = com_ward.getSelectionModel().getSelectedItem();
        selectedStreet = com_street.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void nicCheack(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            System.out.println("ENTER GEHUWAA");
            searchCustomerByNIC();
        }
    }

    int y = 0;

    public void searchCustomerByNIC() {

        String text = txt_cus_nic.getText();
        modle.Customer customer = new modle.Customer();
        customer.setNic(text);

        Customer cus = customer.searchCustomerByNic();
        // upcus = cus;
        if (cus.getFullName() == null) {

        } else {

        }
        if (cus != null) {
            txt_cus_fname.setText(cus.getFullName());
            upcus = cus;
            loadTable();
            Session openSession = conn.NewHibernateUtil.getSessionFactory().openSession();
            try {

                Integer idCustomer = cus.getIdCustomer();
                pojo.Customer load = (pojo.Customer) openSession.load(pojo.Customer.class, idCustomer);

                Set<Assessment> assessments = load.getAssessments();

                if (assessments.size() == 1) {
                    for (Assessment assessment : assessments) {

                        String assessmentNo = assessment.getAssessmentNo();
                        String streetName = assessment.getStreet().getStreetName();
                        String wardName = assessment.getStreet().getWard().getWardName();

                        System.out.println(assessmentNo + "  Ases");

                        txt_assesmantNO.setText(assessmentNo);
                        com_ward.getSelectionModel().select(wardName);
                        com_street.getSelectionModel().select(streetName);

                    }

                } else {
                    if (y == 0) {
                        modle.StaticBadu.setpCustomer(load);
                        AnchorPane paymant = javafx.fxml.FXMLLoader.load(getClass().getResource("/view/AssessmantCheack.fxml"));
                        txt_cus_fname.getParent().getScene();
                        Scene scene = new Scene(paymant);
                        Stage stage = new Stage();

                        stage.initStyle(StageStyle.TRANSPARENT);
                        stage.setScene(scene);
                        stage.show();
                        y = 1;
                    } else if (y == 1) {
                        txt_assesmantNO.setText(modle.StaticBadu.getAssessment().getAssessmentNo());
                        com_ward.getSelectionModel().select(modle.StaticBadu.getWard().getWardName());
                        com_street.getSelectionModel().select(modle.StaticBadu.getStreet().getStreetName());
                        y = 0;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                openSession.close();
            }

        }

    }

    public void loadTreadTypeCombo() {
        List<pojo.TradeType> loadTreadType = new modle.TradeType().loadTreadType();
        ObservableList List = FXCollections.observableArrayList();
        for (pojo.TradeType tradeType1 : loadTreadType) {
            List.add(tradeType1.getTypeName());
        }

        com_trade_type.setItems(List);
    }

    int tt = 0;

    public void getTradyType() {
        com_trade_type.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String selectedItem = com_trade_type.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    tt = new modle.TradeType().loadTreadType(selectedItem).getIdTradeType();
                    loadNature();
                }
            }
        });

    }

    public void CalTaxAmmount() {

        double taxValue = modle.TaxCal.getTaxValue(Double.parseDouble(txt_allocaton.getText()), com_trade_type.getSelectionModel().getSelectedItem());

        double name = Math.round(taxValue * 100.00) / 100.00;

        txt_taxt_amount.setText(name + "");
    }

    public void loadNature() {
        ObservableList natureList = FXCollections.observableArrayList();
        Nature tn = new modle.Nature();
        tn.setTradeType(new modle.TradeType().loadTreadType(tt));
        List<TradeNature> nlist = tn.getNaturesList();
        natureList.clear();
        for (pojo.TradeNature tnn : nlist) {
            natureList.add(tnn.getNature());
        }
        com_nature.setItems(natureList);
        TextFields.bindAutoCompletion(com_nature.getEditor(), com_nature.getItems());

    }

    String tnature;

    public void getSelectedNature() {
        com_nature.setOnAction((event) -> {
            String natureString = com_nature.getSelectionModel().getSelectedItem();
            tnature = natureString;
            System.out.println(natureString);
            loadSubNature();
        });

    }

    public void loadSubNature() {

        ObservableList subList = FXCollections.observableArrayList();
        modle.SubNature sn = new modle.SubNature(0, new modle.Nature().getSubNatureByNature(tnature), null, null, 0, 0);
        List<pojo.SubNature> loadNature = sn.loadNature();

        subList.clear();
        for (pojo.SubNature tnn : loadNature) {
            subList.add(tnn.getSubNature());
        }
        com_subnature.setItems(subList);

    }

    public void loadRo() {
        List<String> roList = new modle.RO().getRoList();

//        ArrayList<String> list = new ArrayList();
//        for (User user : roList) {
//            list.add(user.getFullName());
//        }
        TextFields.bindAutoCompletion(txt_ro, roList);

    }

    public void SetDate() {
        Date date = new Date();
        SimpleDateFormat yeare = new SimpleDateFormat("yyyy");
        SimpleDateFormat month = new SimpleDateFormat("MM");
        SimpleDateFormat day = new SimpleDateFormat("dd");

        txt_year.setText(yeare.format(date));
        txt_month.setText(month.format(date));
        txt_day.setText(day.format(date));
    }

    public void setApplicationid() {
        int ano = new modle.Aplication().getLastApllicationID();
        System.out.println(ano);
        txt_aplicaton_No.setText(ano + "");
        // txt_aplicaton_No.setDisable(true);

    }

    double alocation;
    double txtAmount;
    String appno;
    String tradeNaem;
    String adl1;
    String adl2;
    String adl3;
    String discription;
    String year;
    String month;
    String day;
    String ro;
    String assesno;
    String cus_name;
    String cus_nic;
    Date apdat;

    String wardname;
    String streetname;
    String tradeType;
    String nature;
    String subnature;

    pojo.Ward pWard;
    pojo.Street pStreet;
    pojo.TradeType pTradeType;
    pojo.TradeNature pNature;
    pojo.SubNature pSubNature;
    pojo.User pro;
    pojo.Customer pCustomer;

    public ApplicationController() {
    }

    public void collectData() {

        try {
            alocation = Double.parseDouble(txt_allocaton.getText());
        } catch (NumberFormatException e) {
            modle.Allert.notificationError("Alocation Value Cant Be String", txt_allocaton.getText());
        }

        try {
            txtAmount = Double.parseDouble(txt_taxt_amount.getText());
        } catch (Exception e) {
            modle.Allert.notificationError("Alocation Value Cant Be String", txt_taxt_amount.getText());
        }

        tradeNaem = txt_trade_name.getText();

        adl1 = txt_adl1.getText();
        adl2 = txt_adl2.getText();
        adl3 = txt_adl3.getText();
        discription = txt_discription.getText();
        year = txt_year.getText();
        month = txt_month.getText();
        day = txt_day.getText();

        try {
            apdat = new SimpleDateFormat("yyyy-MM-dd").parse(year + "-" + month + "-" + day);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            ro = txt_ro.getText();
            assesno = txt_assesmantNO.getText();
            cus_name = txt_cus_fname.getText();
            cus_nic = txt_cus_nic.getText();
            appno = txt_appno.getText();

            wardname = com_ward.getSelectionModel().getSelectedItem();
            streetname = com_street.getSelectionModel().getSelectedItem();
            tradeType = com_trade_type.getSelectionModel().getSelectedItem();
            nature = com_nature.getSelectionModel().getSelectedItem();
            subnature = com_subnature.getSelectionModel().getSelectedItem();

            modle.Ward ward = new modle.Ward();
            ward.setWardname(wardname);
            pward = ward.getWardByWardName(wardname);
            pTradeType = new modle.TradeType().getTreadTypeByTypeName(tradeType);
            Nature natu = new modle.Nature();
            pNature = natu.getNatureByNature(nature);
            pStreet = new modle.Strret().getStreetsByStreetNameAndWard(streetname, pward);
            pSubNature = new modle.SubNature().getNatureBySubNatureName(subnature);
            pro = new modle.RO().getRobyRoname(ro);

            // pCustomer = new modle.Customer().searchCustomer(cus_nic, cus_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveApplication() {
        btn_save_app.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                collectData();
                Aplication mAplication = new modle.Aplication();

                boolean hasApplication = mAplication.hasApplication(appno);

                if (hasApplication) {

                    modle.Allert.notificationInfo("Application Number is already exist\n Please Cheack It ", appno);

                } else {

                    String year = txt_year.getText();
                    String format = new SimpleDateFormat("yyyy").format(new java.util.Date());

                    if (year.equals(format)) {

                        Application app = new pojo.Application();
                        app.setApproveToPaymant(0);
                        app.setCustomer(pCustomer);
                        app.setSubNature(pSubNature);
                        app.setTradeNature(pNature);
                        app.setApplicationNo(appno);
                        Assessment assesmantPojo = new modle.Customer().getAssesmantPojo(wardname, streetname, assesno);

                        if (assesmantPojo != null) {
                            System.out.println(assesmantPojo.getAssessmentNo());
                            app.setAssessment(assesmantPojo);
                            app.setTradeType(pTradeType);
                            app.setUser(pro);
                            //log wela inna user
                            app.setUserLog(modle.Log_User.getLogUser());

                            app.setApplicationDate(apdat);
                            app.setYear(Integer.parseInt(year));
                            app.setMonth(Integer.parseInt(month));
                            app.setAllocation(alocation);

                            app.setTradeName(tradeNaem);
                            app.setTradeAddress1(adl1);
                            app.setTradeAddress2(adl2);
                            app.setTradeAddress3(adl3);

                            app.setTaxAmount(txtAmount);
                            app.setDiscription(discription);
                            app.setApproveToPaymant(0);
                            app.setStatues(1);
                            app.setSyn(1);

                            boolean save = mAplication.save(app);

                            if (save) {
                                modle.StaticBadu.setApp(app);
                                modle.Allert.notificationGood("Saved Application", app.getIdApplication() + "");
                                Runtime.getRuntime().gc();
                            } else {
                                modle.Allert.notificationError("Error", txt_aplicaton_No.getText());
                            }
                        } else {
                            modle.Allert.notificationInfo("Cheack Customer", txt_assesmantNO.getText());
                        }

                    } else {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation Dialog");
                        alert.setHeaderText("Pleace Cheack Year.... ");
                        alert.setContentText(txt_year.getText());
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {

                        }
                    }
                }

            }
        }
        );
    }

    public void saveUpdate() {

        collectData();
        //  System.out.println(pCustomer.getFullName());
        Application app = a;
        app.setApproveToPaymant(0);
        // app.setCustomer(pCustomer);
        app.setSubNature(pSubNature);
        app.setTradeNature(pNature);
        //  app.setAp

        Assessment assesmantPojo = new modle.Customer().getAssesmantPojo(wardname, streetname, assesno);

        if (assesmantPojo != null) {

            System.out.println(assesmantPojo.getAssessmentNo());
            app.setAssessment(assesmantPojo);
            app.setTradeType(pTradeType);
            app.setUser(pro);
            //log wela inna user
            app.setUserLog(modle.Log_User.getLogUser());

            app.setApplicationDate(apdat);
            app.setYear(Integer.parseInt(year));
            app.setMonth(Integer.parseInt(month));
            app.setAllocation(alocation);

            app.setTradeName(tradeNaem);
            app.setTradeAddress1(adl1);
            app.setTradeAddress2(adl2);
            app.setTradeAddress3(adl3);

            app.setTaxAmount(txtAmount);
            app.setDiscription(discription);
            app.setApproveToPaymant(0);
            app.setStatues(1);
            app.setSyn(1);

            boolean update = new modle.Aplication().updateApp(app);

            if (update) {
                modle.StaticBadu.setApp(app);
                modle.Allert.notificationGood("Updated Application", txt_aplicaton_No.getText());
                Runtime.getRuntime().gc();
            } else {
                modle.Allert.notificationError("Error", txt_aplicaton_No.getText());
            }
        } else {
            modle.Allert.notificationInfo("Cheack Customer", txt_assesmantNO.getText());
        }

    }

    public void sendToApprove() {
        btn_send_approve.setOnAction((event) -> {
            System.out.println("Approve");
            try {
                AnchorPane paymant = FXMLLoader.load(getClass().getResource("/view/SendToApprove.fxml"));
                btn_send_approve.getParent().getScene();
                Scene scene = new Scene(paymant);
                Stage stage = new Stage();
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
                clearApplication();

            } catch (IOException ex) {
                ex.printStackTrace();
                Logger.getLogger(PayController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

    public void ApproveToPay() {

        pojo.Application app = modle.StaticBadu.getApp();

        if (app != null) {

            app.setApproveToPaymant(1);
            boolean update = new modle.Aplication().update(app);

            if (update) {
                modle.Allert.notificationGood("Approve TO Paymant", app.getIdApplication() + "");
                clearApplication();
            } else {
                modle.Allert.notificationError("Error", "Cannot Approve To Paymant");

            }

        }

    }

    public void clearApplication() {
        txt_allocaton.setText(null);
        txt_taxt_amount.setText(null);
        txt_trade_name.setText(null);
        txt_adl1.setText(null);
        txt_adl2.setText(null);
        txt_adl3.setText(null);
        txt_discription.setText(null);

        SetDate();
        txt_aplicaton_No.setText(null);
        txt_appno.setText(null);

        appList.clear();
        tbl_applicaion.setItems(appList);

//        txt_year.setText(null);
//        txt_month.setText(null);
//        txt_day.setText(null);
        //  txt_ro.setText(null);
        txt_assesmantNO.setText(null);
        txt_cus_fname.setText(null);
        txt_cus_nic.setText(null);

        com_ward.getSelectionModel().clearSelection();
        com_street.getSelectionModel().clearSelection();
        com_trade_type.getSelectionModel().clearSelection();
        com_nature.getSelectionModel().clearSelection();
        com_subnature.getSelectionModel().clearSelection();
        btn_save_app.setDisable(false);
        btn_save_app.setText("Save");
        setApplicationid();
        a = null;
        Runtime.getRuntime().gc();
    }

    @FXML
    private void calTaxAmount(KeyEvent event) {
        if (KeyCode.ENTER == event.getCode()) {
            CalTaxAmmount();
        }
    }

    @FXML
    private void clearApp(ActionEvent event) {
        clearApplication();
    }

    pojo.Application a;

    @FXML
    private void searchApp(KeyEvent event) {

        String an = txt_aplicaton_No.getText();

        if (event.getCode() == KeyCode.ENTER) {

            Session session = conn.NewHibernateUtil.getSessionFactory().openSession();

            try {
                a = (pojo.Application) session.load(pojo.Application.class, Integer.parseInt(an));

                if (a != null) {
                    try {

                        txt_allocaton.setText(a.getAllocation() + "");
                        txt_taxt_amount.setText(a.getTaxAmount() + "");
                        txt_trade_name.setText(a.getTradeName());
                        txt_adl1.setText(a.getTradeAddress1());
                        txt_adl2.setText(a.getTradeAddress2());
                        txt_adl3.setText(a.getTradeAddress3());
                        txt_discription.setText(a.getDiscription());

                        txt_assesmantNO.setText(a.getAssessment().getAssessmentNo());
                        txt_cus_fname.setText(a.getCustomer().getFullName());
                        txt_cus_nic.setText(a.getCustomer().getNic());

                        com_ward.getSelectionModel().select(a.getAssessment().getStreet().getWard().getWardName());
                        com_street.getSelectionModel().select(a.getAssessment().getStreet().getStreetName());
                        com_trade_type.getSelectionModel().select(a.getTradeType().getTypeName());
                        com_nature.getSelectionModel().select(a.getTradeNature().getNature());

                        txt_year.setText(a.getYear() + "");

                        txt_month.setText(a.getMonth() + "");

                        txt_day.setText(new SimpleDateFormat("dd").format(a.getApplicationDate()));

                        if (a.getUser() != null) {
                            txt_ro.setText(a.getUser().getFullName());
                        }

                        com_subnature.getSelectionModel().select(a.getSubNature().getSubNature());
                    } catch (Exception e) {
                        clearApplication();
                    }
                } else {
                    clearApplication();
                }
                //  txt_ro.setText(null);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                session.close();
            }
        }
    }

    @FXML
    private void getToggle(ActionEvent event) {
        loadTable();
    }

    @FXML
    private void updateApplication(ActionEvent event) {
        saveUpdate();
    }

    public class AppTbl {

        public AppTbl(int appno, String appNOString, String Type, String nature, String Tname, Double alocation, Double txt, int payapp) {
            this.appno = appno;
            this.appNOString = new SimpleStringProperty(appNOString);
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
        private SimpleStringProperty appNOString;
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

        /**
         * @return the appNOString
         */
        public String getAppNOString() {
            return appNOString.get();
        }

        /**
         * @param appNOString the appNOString to set
         */
        public void setAppNOString(SimpleStringProperty appNOString) {
            this.appNOString = appNOString;
        }

    }

    ObservableList appList = FXCollections.observableArrayList();

    public void loadTable() {

        c_idApp.setCellValueFactory(new PropertyValueFactory<>("appno"));
        c_idApp.setCellValueFactory(new PropertyValueFactory<>("appNOString"));
        c_type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        c_nature.setCellValueFactory(new PropertyValueFactory<>("nature"));
        c_alocation.setCellValueFactory(new PropertyValueFactory<>("alocation"));
        c_tax.setCellValueFactory(new PropertyValueFactory<>("txt"));
        c_approve.setCellValueFactory(new PropertyValueFactory<>("payapp"));
        c_tname.setCellValueFactory(new PropertyValueFactory<>("Tname"));

        Aplication aplication = new modle.Aplication();

        if (upcus != null) {
            Integer idCustomer = upcus.getIdCustomer();

            List<modle.AppTbl> appTbls = null;

            if (toggle.isSelected()) {
                appTbls = aplication.getAppListByCustomerThisYear(idCustomer);
            } else {
                appTbls = aplication.getAppListByCustomer(idCustomer);
            }

            if (appTbls != null) {

                appList.clear();
                for (modle.AppTbl a : appTbls) {
                    appList.add(new AppTbl(a.getAppno(), a.getAppNOString(), a.getType(), a.getNature(), a.getTname(), a.getAlocation(), a.getTxt(), a.getPayapp()));
                }
                tbl_applicaion.setItems(appList);
            }
        }
    }

}
