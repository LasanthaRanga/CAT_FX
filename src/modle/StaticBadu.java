/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.util.List;
import java.util.Set;


/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class StaticBadu {

    
    private static controller.CustomerController customerController;
    private static pojo.Application app;
    private static String cus_fullname;
    private static List<modle.Customer> cuslist;
    private static pojo.Customer pCustomer;

    private static pojo.Ward ward;
    private static pojo.Street street;
    private static pojo.Assessment assessment;
    private static List <pojo.Assessment> assessmentList;
    private static modle.Customer customerModle;

    /**
     * @return the ward
     */
    public static pojo.Ward getWard() {
        return ward;
    }

    /**
     * @param aWard the ward to set
     */
    public static void setWard(pojo.Ward aWard) {
        ward = aWard;
    }

    /**
     * @return the street
     */
    public static pojo.Street getStreet() {
        return street;
    }

    /**
     * @param aStreet the street to set
     */
    public static void setStreet(pojo.Street aStreet) {
        street = aStreet;
    }

    /**
     * @return the assessment
     */
    public static pojo.Assessment getAssessment() {
        return assessment;
    }

    /**
     * @param aAssessment the assessment to set
     */
    public static void setAssessment(pojo.Assessment aAssessment) {
        assessment = aAssessment;
    }

   

    /**
     * @return the app
     */
    public static pojo.Application getApp() {
        return app;
    }

    /**
     * @param aApp the app to set
     */
    public static void setApp(pojo.Application aApp) {
        app = aApp;
    }

    /**
     * @return the cus_fullname
     */
    public static String getCus_fullname() {
        return cus_fullname;
    }

    /**
     * @param aCus_fullname the cus_fullname to set
     */
    public static void setCus_fullname(String aCus_fullname) {
        cus_fullname = aCus_fullname;
    }

    /**
     * @return the cuslist
     */
    public static List<modle.Customer> getCuslist() {
        return cuslist;
    }

    /**
     * @param aCuslist the cuslist to set
     */
    public static void setCuslist(List<modle.Customer> aCuslist) {
        cuslist = aCuslist;
    }

    /**
     * @return the pCustomer
     */
    public static pojo.Customer getpCustomer() {
        return pCustomer;
    }

    /**
     * @param apCustomer the pCustomer to set
     */
    public static void setpCustomer(pojo.Customer apCustomer) {
        pCustomer = apCustomer;
    }

    /**
     * @return the customerController
     */
    public static controller.CustomerController getCustomerController() {
        return customerController;
    }

    /**
     * @param aCustomerController the customerController to set
     */
    public static void setCustomerController(controller.CustomerController aCustomerController) {
        customerController = aCustomerController;
    }

    /**
     * @return the assessmentList
     */
    public static List <pojo.Assessment> getAssessmentList() {
        return assessmentList;
    }

    /**
     * @param aAssessmentList the assessmentList to set
     */
    public static void setAssessmentList(List <pojo.Assessment> aAssessmentList) {
        assessmentList = aAssessmentList;
    }

    /**
     * @return the customerModle
     */
    public static modle.Customer getCustomerModle() {
        return customerModle;
    }

    /**
     * @param aCustomerModle the customerModle to set
     */
    public static void setCustomerModle(modle.Customer aCustomerModle) {
        customerModle = aCustomerModle;
    }

}
