/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class DashBordController implements Initializable {

    @FXML
    private PieChart chart1;
    @FXML
    private JFXButton btn_backup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                new PieChart.Data("Ela Kiri", 300),
                new PieChart.Data("MEE Kiri", 200),
                new PieChart.Data("Rambutan", 400),
                new PieChart.Data("ithuru", 100)
        );
        chart1.setData(pieData);
    }
    
    
    
    
    
    

    @FXML
    private void backup(ActionEvent event) {
        modle.BackUp.Backup();
       
    }

}
