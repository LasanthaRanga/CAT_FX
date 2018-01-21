package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import pojo.Application;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class SendToApproveController implements Initializable {

    

   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Application app = modle.StaticBadu.getApp();
        System.out.println(app.getIdApplication());
    }

}
