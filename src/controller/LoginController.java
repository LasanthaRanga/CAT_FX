/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.awt.SystemTray;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import modle.AuthUser;
import modle.Log_User;
import modle.UserCat;
import org.controlsfx.control.Notifications;
import org.hibernate.Session;
import pojo.UserHasOtheritiscat;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField txt_uname;

    @FXML
    private JFXPasswordField txt_pass;

    @FXML
    private JFXButton btn_singin;

    @FXML
    private JFXButton btn_foget;
    @FXML
    private JFXButton btn_min;
    @FXML
    private AnchorPane anchor_main;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Session openSession = conn.NewHibernateUtil.getSessionFactory().openSession();
        openSession.beginTransaction().commit();
        openSession.close();

        btn_singin.setOnAction((event) -> {
            login();
        });

        btn_foget.setOnAction((event) -> {
            foget();
        });

    }

    public void foget() {
        AnchorPane paymant;
        try {
            btn_foget.getParent().getScene().getWindow().hide();
            paymant = FXMLLoader.load(getClass().getResource("/view/Foget.fxml"));
            btn_singin.getParent().getScene();
            Scene scene = new Scene(paymant);
            Stage stage = new Stage();
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void login() {

        String uname = txt_uname.getText();
        String pass = txt_pass.getText();

        Log_User log_User = new modle.Log_User();

        int b = log_User.loginCheack(uname, pass);

        if (b == 1) {
            System.out.println("Admin Login.....");

            try {
                btn_singin.getParent().getScene().getWindow().hide();
                AnchorPane paymant = FXMLLoader.load(getClass().getResource("/view/AdminView.fxml"));
                btn_singin.getParent().getScene();
                Scene scene = new Scene(paymant);

                Stage stage = new Stage();
                stage.getIcons().add(new Image("/info.png"));
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
                Logger.getLogger(PayController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (b == 0) {
            Notifications.create()
                    .title("Warning")
                    .text("Check username and password again.")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT).showWarning();
        } else if (b > 1) {

            List<UserCat> userCats = AuthUser.getUserCats();
            if (userCats.size() == 0) {
                try {
                    btn_singin.getParent().getScene().getWindow().hide();
                    AnchorPane paymant = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
                    btn_singin.getParent().getScene();
                    Scene scene = new Scene(paymant);
                    Stage stage = new Stage();
                    stage.getIcons().add(new Image("/Cat.png"));
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(PayController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (userCats.size() == 1) {
                try {

                    UserCat oCat = userCats.get(0);
                    AuthUser.setIdOc(oCat.getOthoid());

                    btn_singin.getParent().getScene().getWindow().hide();
                    AnchorPane paymant = FXMLLoader.load(getClass().getResource("/view/authoritist.fxml"));
                    btn_singin.getParent().getScene();
                    Scene scene = new Scene(paymant);
                    Stage stage = new Stage();
                    stage.getIcons().add(new Image("/Cat.png"));
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(PayController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                try {
                    Set<UserHasOtheritiscat> userHasOtheritiscats = AuthUser.getUser().getUserHasOtheritiscats();
                    ArrayList<UserCat> ucs = new ArrayList<UserCat>();
                    if (userHasOtheritiscats != null) {
                        for (UserHasOtheritiscat uho : userHasOtheritiscats) {
                            ucs.add(new UserCat(uho.getOtheritiscat().getIdOtheritisCat(), uho.getOtheritiscat().getCatname()));
                        }
                    }
                    AuthUser.setUserCats(ucs);

                    btn_singin.getParent().getScene().getWindow().hide();
                    AnchorPane paymant = FXMLLoader.load(getClass().getResource("/view/SelectLoginType.fxml"));
                    btn_singin.getParent().getScene();
                    Scene scene = new Scene(paymant);
                    Stage stage = new Stage();
                    stage.getIcons().add(new Image("/Cat.png"));
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(PayController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

    }

    @FXML
    private void enterLog(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            login();
        }
    }

    @FXML
    private void btnMin(ActionEvent event) {
        Stage window = (Stage) btn_min.getParent().getScene().getWindow();
        //window.setMaximized(true);

        window.setIconified(true);
        // window.hide();
    }
    
    
    @FXML
    private void mouseDragged(MouseEvent event) {
        cat_fx.CAT_FX.getPrimaryStage().setX(event.getX() + xOffset);
        cat_fx.CAT_FX.getPrimaryStage().setY(event.getY() + yOffset);
        //anchor_main.setCursor(Cursor.CLOSED_HAND);
    }

    @FXML
    private void mouseReleased(MouseEvent event) {
       // anchor_main.setCursor(Cursor.DEFAULT);
    }

    @FXML
    private void mousePressed(MouseEvent event) {
        xOffset = cat_fx.CAT_FX.getPrimaryStage().getX() - event.getSceneX();
        yOffset = cat_fx.CAT_FX.getPrimaryStage().getY() - event.getSceneY();
    }
    private double xOffset;
    private double yOffset;
    
    
    
    
    
    
    
    
    
    
}
