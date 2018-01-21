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
public class StaticBadu {

    private static pojo.Application app;

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
    
}
