/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
public class AdminViewController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Session ses = conn.NewHibernateUtil.getSessionFactory().openSession();
        ses.beginTransaction().commit();
        ses.close();

        try {

            VBox box = FXMLLoader.load(getClass().getResource("/view/AdminNavicationPanel.fxml"));
            AnchorPane user = FXMLLoader.load(getClass().getResource("/view/Admin_user.fxml"));
            AnchorPane usercat = FXMLLoader.load(getClass().getResource("/view/Admin_user_cat.fxml"));

            drawer.setSidePane(box);

            for (Node node : box.getChildren()) {
                String accessibleText = node.getAccessibleText();

                if (accessibleText != null) {

                    node.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, (e) -> {
                        container.getChildren().removeAll(user);

                        switch (accessibleText) {
                            case "btn_users":
                                container.getChildren().add(user);
                                break;

                            case "user_types":
                                container.getChildren().add(usercat);
                                break;

                            case "btn_exit":
                                System.out.println("EXIT");
                                ((Node) (e.getSource())).getScene().getWindow().hide();
                                break;
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

        // TODO
    }

}
