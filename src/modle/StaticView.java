/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class StaticView {

    private static controller.MainController mc;
    private static pojo.Customer customer;
    private static pojo.Assessment assessment;
    private static modle.Customer mcus;

    /**
     * @return the customer
     */
    public static pojo.Customer getCustomer() {
        return customer;
    }

    /**
     * @param aCustomer the customer to set
     */
    public static void setCustomer(pojo.Customer aCustomer) {
        customer = aCustomer;
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
     * @return the mc
     */
    public static controller.MainController getMc() {
        return mc;
    }

    /**
     * @param aMc the mc to set
     */
    public static void setMc(controller.MainController aMc) {
        mc = aMc;
    }

    /**
     * @return the mcus
     */
    public static modle.Customer getMcus() {
        return mcus;
    }

    /**
     * @param aMcus the mcus to set
     */
    public static void setMcus(modle.Customer aMcus) {
        mcus = aMcus;
    }

}
