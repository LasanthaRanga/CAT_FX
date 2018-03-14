/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShopRent.modle;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class StaticBadu {
    private static ShopRent.controller.MainController mainController;

    /**
     * @return the mainController
     */
    public static ShopRent.controller.MainController getMainController() {
        return mainController;
    }

    /**
     * @param aMainController the mainController to set
     */
    public static void setMainController(ShopRent.controller.MainController aMainController) {
        mainController = aMainController;
    }
}
