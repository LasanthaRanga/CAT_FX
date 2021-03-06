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
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;

import javafx.application.Platform;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;

import javafx.scene.Node;

import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
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
     * @return the container
     */
    public AnchorPane getContainer() {
        return container;
    }

    /**
     * @param container the container to set
     */
    public void setContainer(AnchorPane container) {
        this.container = container;
    }

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
    @FXML
    private JFXButton btn_exit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        modle.StaticView.setMc(this);
        
        Session ses = conn.NewHibernateUtil.getSessionFactory().openSession();
        ses.beginTransaction().commit();
        ses.close();

        User user = modle.AuthUser.getUser();
        lbl_logUser.setText(user.getFullName());

        try {

            VBox box = FXMLLoader.load(getClass().getResource("/view/Box.fxml"));

            AnchorPane about = FXMLLoader.load(getClass().getResource("/view/About.fxml"));

            drawer.setSidePane(box);

            getContainer().getChildren().add(FXMLLoader.load(getClass().getResource("/view/DashBord.fxml")));

            for (Node node : box.getChildren()) {
                String accessibleText = node.getAccessibleText();

                if (accessibleText != null) {

                    node.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, (e) -> {
                        try {

                            getContainer().getChildren().removeAll();
                            getContainer().getChildren().clear();

                            switch (accessibleText) {

                                case "dashboard":
                                    AnchorPane dashh = FXMLLoader.load(getClass().getResource("/view/DashBord.fxml"));
                                    getContainer().getChildren().add(dashh);
                                    break;

                                case "btn_test1":
                                    AnchorPane ward = FXMLLoader.load(getClass().getResource("/view/ward.fxml"));
                                    getContainer().getChildren().add(ward);
                                    break;

                                case "btn_test2":
                                    AnchorPane nature = FXMLLoader.load(getClass().getResource("/view/nature.fxml"));
                                    getContainer().getChildren().add(nature);
                                    break;

                                case "customer":
                                    AnchorPane customer = FXMLLoader.load(getClass().getResource("/view/customer.fxml"));
                                    getContainer().getChildren().add(customer);
                                    break;

                                case "Aplication":
                                    AnchorPane aplication = FXMLLoader.load(getClass().getResource("/view/application.fxml"));
                                    getContainer().getChildren().add(aplication);
                                    break;

                                case "applist":
                                    AnchorPane applist = FXMLLoader.load(getClass().getResource("/view/ApplicationList.fxml"));
                                    getContainer().getChildren().add(applist);
                                    break;

                                case "searchapp":
                                    AnchorPane sarch = FXMLLoader.load(getClass().getResource("/view/SarchApp.fxml"));
                                    getContainer().getChildren().add(sarch);
                                    break;

                                case "payment":
                                    AnchorPane paymant = FXMLLoader.load(getClass().getResource("/view/Payment.fxml"));
                                    getContainer().getChildren().add(paymant);
                                    break;

                                case "paymantView":
                                    AnchorPane paymantView = FXMLLoader.load(getClass().getResource("/view/PaymantView.fxml"));
                                    getContainer().getChildren().add(paymantView);
                                    break;

                                case "Reports":
                                    AnchorPane report = FXMLLoader.load(getClass().getResource("/view/Report.fxml"));
                                    getContainer().getChildren().add(report);
                                    break;

                                case "about":
                                    getContainer().getChildren().add(about);
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

        Stage st = (Stage) btn_min.getParent().getScene().getWindow();
        st.setIconified(true);

    }

    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void mouseDragged(MouseEvent event) {
        cat_fx.CAT_FX.getPrimaryStage().setX(event.getX() + xOffset);
        cat_fx.CAT_FX.getPrimaryStage().setY(event.getY() + yOffset);
        anchor_main.setCursor(Cursor.CLOSED_HAND);
    }

    @FXML
    private void mouseReleased(MouseEvent event) {
        anchor_main.setCursor(Cursor.DEFAULT);
    }

    @FXML
    private void mousePressed(MouseEvent event) {
        xOffset = cat_fx.CAT_FX.getPrimaryStage().getX() - event.getSceneX();
        yOffset = cat_fx.CAT_FX.getPrimaryStage().getY() - event.getSceneY();
    }
    private double xOffset;
    private double yOffset;

}
