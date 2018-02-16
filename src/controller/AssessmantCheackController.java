/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import static modle.Customer.getWASlist;
import modle.WSA;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pojo.Assessment;
import pojo.Customer;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class AssessmantCheackController implements Initializable {

    @FXML
    private TableView<WSA> tbl_asess;
    @FXML
    private TableColumn<WSA, String> col_ward;
    @FXML
    private TableColumn<WSA, String> col_street;
    @FXML
    private TableColumn<WSA, String> col_ases;
    @FXML
    private JFXButton btn_get;
    @FXML
    private JFXTextField txt_assess;
    @FXML
    private JFXTextField txt_street;
    @FXML
    private JFXTextField txt_ward;
    
    private controller.ApplicationController applicationController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setWardStrretAssesmant();
    }

    public void setWardStrretAssesmant() {
        System.out.println("Asessmant Gannawa");
        ObservableList WASlist = FXCollections.observableArrayList();

        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            Customer name = (pojo.Customer) session.createCriteria(pojo.Customer.class).add(Restrictions.eq("idCustomer", modle.StaticBadu.getpCustomer().getIdCustomer())).uniqueResult();

            Set<Assessment> assessments = name.getAssessments();
            for (Assessment assessment : assessments) {

                WSA wsa = new modle.WSA(assessment.getStreet().getWard().getIdWard(), assessment.getStreet().getWard().getWardName(), assessment.getStreet().getIdStreet(), assessment.getStreet().getStreetName(), assessment.getIdAssessment(), assessment.getAssessmentNo());
                WASlist.add(wsa);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        if (WASlist != null) {

            col_ward.setCellValueFactory(new PropertyValueFactory<>("Ward"));
            col_street.setCellValueFactory(new PropertyValueFactory<>("Street"));
            col_ases.setCellValueFactory(new PropertyValueFactory<>("Assesmant"));

            tbl_asess.setItems(WASlist);

        }
    }

    @FXML
    private void selectAssessmant(MouseEvent event) {
        WSA selectedItem = tbl_asess.getSelectionModel().getSelectedItem();
        int idAssesmant = selectedItem.getIdAssesmant();

        Session openSession = conn.NewHibernateUtil.getSessionFactory().openSession();

        try {

            if (selectedItem.getIdAssesmant() > 0) {
                modle.StaticBadu.setAssessment((pojo.Assessment) openSession.load(pojo.Assessment.class, selectedItem.getIdAssesmant()));
                txt_assess.setText(modle.StaticBadu.getAssessment().getAssessmentNo());
            }
            if (selectedItem.getIdStreet() > 0) {
                modle.StaticBadu.setStreet((pojo.Street) openSession.load(pojo.Street.class, selectedItem.getIdStreet()));
                txt_street.setText(modle.StaticBadu.getStreet().getStreetName());
            }
            if (selectedItem.getIdWard() > 0) {
                modle.StaticBadu.setWard((pojo.Ward) openSession.load(pojo.Ward.class, selectedItem.getIdWard()));
                txt_ward.setText(modle.StaticBadu.getWard().getWardName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            openSession.close();
        }

    }

    @FXML
    private void getSelected(ActionEvent event) {
        btn_get.getScene().getWindow().hide();
    }

}
