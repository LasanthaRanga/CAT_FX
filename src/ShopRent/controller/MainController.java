/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShopRent.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import controller.LoginController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Session;
import pojo.User;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class MainController implements Initializable {

    @FXML
    private AnchorPane anchor_main;
    @FXML
    private JFXHamburger hambuger;
    @FXML
    private Label lbl_logUser;
    @FXML
    private JFXButton btn_min;
    @FXML
    private JFXButton btn_exit;
    @FXML
    private AnchorPane container;
    @FXML
    private JFXDrawer drawer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Session ses = conn.NewHibernateUtil.getSessionFactory().openSession();
        ses.beginTransaction().commit();
        ses.close();

        User user = modle.AuthUser.getUser();
        //      lbl_logUser.setText(user.getFullName());

        try {

            VBox box = FXMLLoader.load(getClass().getResource("/ShopRent/view/Box.fxml"));

            drawer.setSidePane(box);

            //container.getChildren().add(FXMLLoader.load(getClass().getResource("/view/DashBord.fxml")));
            for (Node node : box.getChildren()) {
                String accessibleText = node.getAccessibleText();

                if (accessibleText != null) {

                    node.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, (e) -> {
                        try {

                            container.getChildren().removeAll();
                            container.getChildren().clear();

                            switch (accessibleText) {

                                case "dashboard":
                                    AnchorPane dashh = FXMLLoader.load(getClass().getResource("/ShopRent/view/dashboard.fxml"));
                                    container.getChildren().add(dashh);
                                    break;

                                case "shop":
                                    AnchorPane shop = FXMLLoader.load(getClass().getResource("/ShopRent/view/AddShop.fxml"));
                                    container.getChildren().add(shop);
                                    break;

                                case "building":
                                    AnchorPane building = FXMLLoader.load(getClass().getResource("/ShopRent/view/AddBuilding.fxml"));
                                    container.getChildren().add(building);
                                    break;

                                case "btn_exit":
                                    System.out.println("EXIT");
                                    Platform.exit();
                                    System.exit(0);
                                    //((Node) (e.getSource())).getScene().getWindow().hide();
                                    break;

                            }
                        } catch (Exception ee) {
                            ee.printStackTrace();
                        }

                    });

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        drawer.open();

//        HamburgerBackArrowBasicTransition hamt = new HamburgerBackArrowBasicTransition(hambuger);
        HamburgerNextArrowBasicTransition hamt = new HamburgerNextArrowBasicTransition(hambuger);

        hamt.setRate(1);
        hambuger.setStyle("-fx-ripple-color:WHITE");
        hambuger.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED,
                (e) -> {

                    hamt.setRate(hamt.getRate() * -1);
                    hamt.play();
                    //====
                    if (drawer.isShown()) {
                        drawer.close();
                    } else {
                        drawer.open();
                    }
                    //====
                }
        );

    }

    public void setText() {
        System.out.println("Click");
    }

    @FXML
    private void minimize(ActionEvent event) {
    }

    @FXML
    private void exit(ActionEvent event) {
        System.out.println("EXIT");
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void mouseReleased(MouseEvent event) {
    }

    @FXML
    private void mouseDragged(MouseEvent event) {
    }

    @FXML
    private void mousePressed(MouseEvent event) {
    }

    @FXML
    private void cloaseDrawerEnterd(MouseEvent event) {
    }

    @FXML
    private void closeDrawer(MouseEvent event) {
    }
}
