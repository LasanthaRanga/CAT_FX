/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShopRent.modle;

import modle.Strret;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class GetModle {

    private modle.Strret strret;
    private modle.Ward ward;
    private ShopRent.modle.Building building;
    private ShopRent.modle.Floor floor;

    /**
     * @return the strret
     */
    public modle.Strret getStrret() {
        if (strret == null) {
            strret = new modle.Strret();
        }
        return strret;
    }

    /**
     * @return the ward
     */
    public modle.Ward getWard() {
        if (ward == null) {
            ward = new modle.Ward();
        }
        return ward;
    }

    /**
     * @return the building
     */
    public ShopRent.modle.Building getBuilding() {
        if (building == null) {
            building = new Building();
        }
        return building;
    }

    /**
     * @return the floor
     */
    public ShopRent.modle.Floor getFloor() {
        if (floor == null) {
            floor = new Floor();
        }
        return floor;
    }

}
