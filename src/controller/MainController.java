/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.application.Platform;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.hibernate.Session;
import pojo.User;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class MainController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane anchor_main;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private AnchorPane container;

    @FXML
    private JFXHamburger hambuger;

    public static StackPane mainStack;

    @FXML
    private Label lbl_logUser;
    @FXML
    private JFXButton btn_min;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Session ses = conn.NewHibernateUtil.getSessionFactory().openSession();
        ses.beginTransaction().commit();
        ses.close();

        User user = modle.AuthUser.getUser();
        lbl_logUser.setText(user.getFullName());

        try {

            VBox box = FXMLLoader.load(getClass().getResource("/view/Box.fxml"));

            drawer.setSidePane(box);

            for (Node node : box.getChildren()) {
                String accessibleText = node.getAccessibleText();

                if (accessibleText != null) {

                    node.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, (e) -> {
                        try {

                            container.getChildren().removeAll();
                            container.getChildren().clear();

                            switch (accessibleText) {

                                case "btn_test1":
                                    AnchorPane ward = FXMLLoader.load(getClass().getResource("/view/ward.fxml"));
                                    container.getChildren().add(ward);
                                    break;

                                case "btn_test2":
                                    AnchorPane nature = FXMLLoader.load(getClass().getResource("/view/nature.fxml"));
                                    container.getChildren().add(nature);
                                    break;

                                case "customer":
                                    AnchorPane customer = FXMLLoader.load(getClass().getResource("/view/customer.fxml"));
                                    container.getChildren().add(customer);
                                    break;

                                case "Aplication":
                                    AnchorPane aplication = FXMLLoader.load(getClass().getResource("/view/application.fxml"));
                                    container.getChildren().add(aplication);
                                    break;

                                case "applist":
                                    AnchorPane applist = FXMLLoader.load(getClass().getResource("/view/ApplicationList.fxml"));
                                    container.getChildren().add(applist);
                                    break;

                                case "payment":
                                    AnchorPane paymant = FXMLLoader.load(getClass().getResource("/view/Payment.fxml"));
                                    container.getChildren().add(paymant);
                                    break;

                                case "paymantView":
                                    AnchorPane paymantView = FXMLLoader.load(getClass().getResource("/view/PaymantView.fxml"));
                                    container.getChildren().add(paymantView);
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

        HamburgerBackArrowBasicTransition hamt = new HamburgerBackArrowBasicTransition(hambuger);

        hamt.setRate(1);
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

    @FXML
    private void closeDrawer(MouseEvent event) {

        // drawer.open();
    }

    @FXML
    private void cloaseDrawerEnterd(MouseEvent event) {
        if (drawer.isShown()) {
            // drawer.close();
        } else {
            drawer.open();
        }
    }

    @FXML
    private void minimize(ActionEvent event) {

        Stage  st= (Stage) btn_min.getParent().getScene().getWindow();
        st.setIconified(true);

    }

}
