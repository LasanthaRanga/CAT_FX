/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat_fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class CAT_FX extends Application {
    
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage =stage;
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
       // Parent root = FXMLLoader.load(getClass().getResource("/ShopRent/view/main.fxml"));
        stage.initStyle(StageStyle.TRANSPARENT);
        
        stage.getIcons().add(new Image("/Cat.png"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setResizable(false);
        stage.setScene(scene);
        
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * @return the primaryStage
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

}
