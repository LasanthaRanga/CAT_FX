/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.chart.PieChart;

import modle.Vort;

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
        getVoteDetails();
      //  mh(, chart1);
        

    }

    ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();

    public void getVoteDetails() {
        pieData.clear();
        Vort vort = new modle.Vort();
        List<pojo.Vort> list = vort.getList();
        for (pojo.Vort vort1 : list) {
            pieData.add(new PieChart.Data(vort1.getVoteName(), vort1.getVoteCurrentBalance()));
        }
       
        chart1.setData(pieData);

    }

    @FXML
    private void backup(ActionEvent event) {
        modle.BackUp.Backup();

    }

    

   
    
}
