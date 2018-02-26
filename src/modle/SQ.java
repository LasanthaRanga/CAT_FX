/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class SQ {

    ObservableList<String> List = FXCollections.observableArrayList();

    public ObservableList getSQ() {
        List.add("What was your childhood nickname?");
        List.add("What is the middle name of your oldest child?");
        List.add("What is your favorite movie?");
        List.add("What was your favorite sport in high school?");
        List.add("What is the first name of the boy or girl that you first kissed?");
        List.add("What was the name of the hospital where you were born?");
        List.add("What school did you attend for sixth grade?");       
        List.add("In what town was your first job?");
        List.add("What was the name of the company where you had your first job?");
        return List;
    }

}
