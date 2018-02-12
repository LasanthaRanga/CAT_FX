package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pojo.Application;
import pojo.Assessment;
import pojo.Contact;
import pojo.Customer;
import pojo.SubNature;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class DitailsController implements Initializable {

    @FXML
    private Label t_no;
    @FXML
    private Label t_date;
    @FXML
    private Label t_name;
    @FXML
    private Label t_ward;
    @FXML
    private Label t_nic;
    @FXML
    private Label t_pone;
    @FXML
    private Label t_mobile;
    @FXML
    private Label t_adl1;
    @FXML
    private Label t_adl2;
    @FXML
    private Label t_adl3;
    @FXML
    private Label t_city;
    @FXML
    private Label t_street;
    @FXML
    private Label t_assesmant;
    @FXML
    private JFXTextArea t_dis;
    @FXML
    private Label t_tradeName;
    @FXML
    private Label t_allocation;

    @FXML
    private Label t_tradeType;
    @FXML
    private Label t_natureType;
    @FXML
    private Label t_tradeSubNature;
    @FXML
    private Label t_ro;
    @FXML
    private JFXButton btn_close;

    Application app;
    @FXML
    private Label t_tax;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        app = modle.StaticBadu.getApp();

        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();

        try {
            app = (pojo.Application) session.createCriteria(pojo.Application.class).add(Restrictions.eq("idApplication", app.getIdApplication())).uniqueResult();

            t_no.setText(app.getApplicationNo());
            t_date.setText(app.getApplicationDate() + "");

            Customer cus = app.getCustomer();

            t_name.setText(cus.getFullName());
            t_nic.setText(cus.getNic());

            pojo.Contact con = null;

            Set<Contact> contacts = cus.getContacts();
            for (Contact contact : contacts) {
                con = contact;
            }

            t_pone.setText(con.getPhone());
            t_mobile.setText(con.getMobile());
            t_adl1.setText(con.getAddress1());
            t_adl2.setText(con.getAddress2());
            t_adl3.setText(con.getAddress3());
            t_city.setText(con.getCity());

            Assessment ases = app.getAssessment();
            t_assesmant.setText(ases.getAssessmentNo());
            t_street.setText(ases.getStreet().getStreetName());
            t_ward.setText(ases.getStreet().getWard().getWardName());

            t_dis.setText(app.getDiscription());
            t_tradeName.setText(app.getTradeName());
            t_tradeType.setText(app.getTradeType().getTypeName());

            t_natureType.setText(app.getTradeNature().getNature());
            t_allocation.setText(app.getAllocation() + "");
            t_tax.setText(app.getTaxAmount() + "");

            SubNature subNature = app.getSubNature();
            if (subNature != null) {
                t_tradeSubNature.setText(app.getSubNature().getSubNature());
            } else {
                t_tradeSubNature.setText("none");
            }

            if (app.getUser() != null) {
                t_ro.setText(app.getUser().getFullName());
            } else {
                t_ro.setText("none");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    @FXML
    private void closeDetails(ActionEvent event) {

        btn_close.getScene().getWindow().hide();

    }

}
