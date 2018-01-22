/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.util.List;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class StaticBadu {

    private static pojo.Application app;
    private static String cus_fullname;
    static List<modle.Customer> cuslist;

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

}
