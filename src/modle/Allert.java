/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class Allert {

    public static void messageSuccess() {
        StackPane stackPane = new StackPane();
        JFXDialogLayout dialog = new JFXDialogLayout();
        dialog.setHeading(new Text("HEDDING..."));
        dialog.setBody(new Text("MEka thamai body eka meka hadanna puluwan"));
        JFXDialog jfxDialog = new JFXDialog(stackPane, dialog, JFXDialog.DialogTransition.CENTER);
        jfxDialog.show();

    }

    public static void normalMg() {

        System.out.println("Update Click una");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("I have a great message for you!");
        alert.show();

    }

    public static void messagSuccsess(StackPane stackpane, String Title, String Text) {
        System.out.println("ELA ELA");
        JFXDialogLayout content = new JFXDialogLayout();
        Text text = new Text(Title);
        content.setHeading(text);
        content.setBackground(new Background(new BackgroundFill(Paint.valueOf("#4CAF50"), CornerRadii.EMPTY, Insets.EMPTY)));

        content.setBody(new Text(Text));
        JFXButton button = new JFXButton("OK");
        button.setBackground(new Background(new BackgroundFill(Paint.valueOf("#FAFAFA"), CornerRadii.EMPTY, Insets.EMPTY)));
        JFXDialog jfxDialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.RIGHT);
        button.setOnAction((e) -> {
            jfxDialog.close();
        });
        content.setActions(button);
        jfxDialog.show();
    }

    public static void messagInfo(StackPane stackpane, String Title, String Text) {
        System.out.println("ELA ELA");
        JFXDialogLayout content = new JFXDialogLayout();
        Text text = new Text(Title);
        content.setHeading(text);
        content.setBackground(new Background(new BackgroundFill(Paint.valueOf("#2196F3"), CornerRadii.EMPTY, Insets.EMPTY)));

        content.setBody(new Text(Text));
        JFXButton button = new JFXButton("OK");
        button.setBackground(new Background(new BackgroundFill(Paint.valueOf("#FAFAFA"), CornerRadii.EMPTY, Insets.EMPTY)));
        JFXDialog jfxDialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.RIGHT);
        button.setOnAction((e) -> {
            jfxDialog.close();
        });
        content.setActions(button);
        jfxDialog.show();
    }

}
