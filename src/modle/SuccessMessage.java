/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class SuccessMessage extends Thread {

    String title;
    String message;
    StackPane stackPane;

    public SuccessMessage(String title, String message, StackPane stackPane) {
        this.title = title;
        this.message = message;
        this.stackPane = stackPane;
    }

    @Override
    public void run() {
        System.out.println("ELA ELA");
        JFXDialogLayout content = new JFXDialogLayout();
        Text text = new Text(title);
        content.setHeading(text);
        content.setBackground(new Background(new BackgroundFill(Paint.valueOf("#4CAF50"), CornerRadii.EMPTY, Insets.EMPTY)));

        content.setBody(new Text(message));
        JFXButton button = new JFXButton("OK");
        button.setBackground(new Background(new BackgroundFill(Paint.valueOf("#FAFAFA"), CornerRadii.EMPTY, Insets.EMPTY)));
        JFXDialog jfxDialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.RIGHT);
        button.setOnAction((e) -> {
            jfxDialog.close();
        });
        content.setActions(button);
        jfxDialog.show();
        try {
            this.sleep(4000);
        } catch (InterruptedException ex) {
            Logger.getLogger(SuccessMessage.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        jfxDialog.close();
    }

}
