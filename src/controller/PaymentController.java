package controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pojo.Application;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class PaymentController implements Initializable {

    @FXML
    private JFXButton btn_paynow;
    @FXML
    private TableView<pojo.Application> tbl_approved_list;
    @FXML
    private TableColumn<pojo.Application, String> tbl_clmn_application_no;
    @FXML
    private TableColumn<pojo.Application, String> tbl_clmn_allocation;
    @FXML
    private TableColumn<pojo.Application, String> tbl_clmn_application_date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        tbl_clmn_allocation.setCellValueFactory(new PropertyValueFactory<>("allocation"));
        tbl_clmn_application_no.setCellValueFactory(new PropertyValueFactory<>("idApplication"));
        tbl_clmn_application_date.setCellValueFactory(new PropertyValueFactory<>("applicationDate"));
        this.setTable();
        
        btn_paynow.setOnAction((event) -> {
            System.out.println("PAY");
            try {
                AnchorPane paymant = FXMLLoader.load(getClass().getResource("/view/pay.fxml"));
                btn_paynow.getParent().getScene();
                Scene scene = new Scene(paymant);
                Stage stage = new Stage();
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                ex.printStackTrace();
                Logger.getLogger(PayController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

    }

    private void setTable(){
        ObservableList<Application> list = FXCollections.observableArrayList(new modle.Aplication().getUnpaiedApprovedApplications());
        tbl_approved_list.setItems(list);
    }
    
    @FXML
    private void selectApplication(MouseEvent event) {
        
    }

}
