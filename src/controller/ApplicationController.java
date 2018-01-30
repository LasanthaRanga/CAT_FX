package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modle.Customer;
import modle.CustomerHasAssesment;
import modle.Nature;
import modle.TaxCal;
import org.controlsfx.control.textfield.TextFields;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pojo.Application;
import pojo.Assessment;
import pojo.Street;
import pojo.SubNature;
import pojo.TradeNature;
import pojo.TradeType;
import pojo.User;
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

                        Integer idCustomer = upcus.getIdCustomer();
                        pojo.Customer load = (pojo.Customer) openSession.load(pojo.Customer.class, idCustomer);

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
            } else {
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

            Session openSession = conn.NewHibernateUtil.getSessionFactory().openSession();
            try {

                Integer idCustomer = cus.getIdCustomer();
                pojo.Customer load = (pojo.Customer) openSession.load(pojo.Customer.class, idCustomer);

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
                tt = new modle.TradeType().loadTreadType(selectedItem).getIdTradeType();
                loadNature();
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
    String noInpaper;
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

        ro = txt_ro.getText();
        assesno = txt_assesmantNO.getText();
        cus_name = txt_cus_fname.getText();
        cus_nic = txt_cus_nic.getText();

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
        pStreet = new modle.Strret().getStreetsByStreetName(streetname);
        pSubNature = new modle.SubNature().getNatureBySubNatureName(subnature);
        pro = new modle.RO().getRobyRoname(ro);
        pCustomer = new modle.Customer().searchCustomer(cus_nic, cus_name);

    }

    public void saveApplication() {
        btn_save_app.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String btntxt = btn_save_app.getText();

                if (btntxt.equals("Save")) {

                    collectData();
                    //  System.out.println(pCustomer.getFullName());
                    Application app = new pojo.Application();
                    app.setApproveToPaymant(0);
                    app.setCustomer(pCustomer);
                    app.setSubNature(pSubNature);
                    app.setTradeNature(pNature);

                    Assessment assesmantPojo = new modle.Customer().getAssesmantPojo(wardname, streetname, assesno);
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
                    app.setStatues(0);
                    app.setSyn(1);

                    boolean save = new modle.Aplication().save(app);

                    if (save) {
                        modle.StaticBadu.setApp(app);
                        modle.Allert.notificationGood("Saved Application", app.getIdApplication()+"");
                    } else {
                        modle.Allert.notificationGood("Error", txt_aplicaton_No.getText());
                    }
                } else {
                    saveUpdate();
                }
            }
        });
    }

    public void saveUpdate() {

        collectData();
        //  System.out.println(pCustomer.getFullName());
        Application app = a;
        app.setApproveToPaymant(0);
        app.setCustomer(pCustomer);
        app.setSubNature(pSubNature);
        app.setTradeNature(pNature);
      //  app.setAp

        Assessment assesmantPojo = new modle.Customer().getAssesmantPojo(wardname, streetname, assesno);
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
        app.setStatues(0);
        app.setSyn(1);

        boolean save = new modle.Aplication().updateApp(app);

        if (save) {
            modle.StaticBadu.setApp(app);
            modle.Allert.notificationGood("Updated Application", txt_aplicaton_No.getText());
        } else {
            modle.Allert.notificationGood("Error", txt_aplicaton_No.getText());
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
        btn_save_app.setText("Save");
        setApplicationid();

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

                        btn_save_app.setText("UPDATE");

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

}
