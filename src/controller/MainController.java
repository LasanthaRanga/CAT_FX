/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.application.Platform;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import javafx.scene.layout.VBox;
import org.hibernate.Session;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Session ses = conn.NewHibernateUtil.getSessionFactory().openSession();
        ses.beginTransaction().commit();
        ses.close();

        //  Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
//        try {
//            pojo.UserLog lu = (pojo.UserLog) session.createCriteria(pojo.UserLog.class).add(Restrictions.eq("idUserLog", 1)).uniqueResult();
//            System.out.println(lu);
//            String fullName = lu.getUser().getFullName();
//            System.out.println(fullName);
//            lbl_logUser.setText(fullName);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
        try {

            VBox box = FXMLLoader.load(getClass().getResource("/view/Box.fxml"));

            drawer.setSidePane(box);

            for (Node node : box.getChildren()) {
                String accessibleText = node.getAccessibleText();

                if (accessibleText != null) {

                    node.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, (e) -> {
                        try {

                            AnchorPane ward = FXMLLoader.load(getClass().getResource("/view/ward.fxml"));
                            AnchorPane nature = FXMLLoader.load(getClass().getResource("/view/nature.fxml"));
                            AnchorPane customer = FXMLLoader.load(getClass().getResource("/view/customer.fxml"));
                            AnchorPane aplication = FXMLLoader.load(getClass().getResource("/view/application.fxml"));
                            AnchorPane applist = FXMLLoader.load(getClass().getResource("/view/ApplicationList.fxml"));
                            AnchorPane paymant = FXMLLoader.load(getClass().getResource("/view/Payment.fxml"));

                            container.getChildren().removeAll(ward);
                            container.getChildren().clear();

                            switch (accessibleText) {

                                case "btn_test1":

                                    container.getChildren().add(ward);
                                    break;

                                case "btn_test2":

                                    container.getChildren().add(nature);
                                    break;

                                case "customer":

                                    container.getChildren().add(customer);
                                    break;

                                case "Aplication":

                                    container.getChildren().add(aplication);
                                    break;

                                case "applist":

                                    container.getChildren().add(applist);
                                    break;

                                case "payment":

                                    container.getChildren().add(paymant);
                                    break;

                                case "btn_exit":
                                    System.out.println("EXIT");
                                    Platform.exit();
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

}
