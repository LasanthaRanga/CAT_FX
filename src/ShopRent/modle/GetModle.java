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
public class GetModle {

    private modle.Strret strret;
    private modle.Ward ward;
    private ShopRent.modle.Building building;
    private ShopRent.modle.Floor floor;
    private ShopRent.modle.ShopType shopType;
    private ShopRent.modle.RentType rentType;
    private ShopRent.modle.ShopNo shopNo;

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

    /**
     * @return the shopType
     */
    public ShopRent.modle.ShopType getShopType() {
        if (shopType == null) {
            shopType = new ShopType();
        }
        return shopType;
    }

    /**
     * @return the rentType
     */
    public ShopRent.modle.RentType getRentType() {
        if (rentType == null) {
            rentType = new RentType();
        }
        return rentType;
    }

    /**
     * @return the shopNo
     */
    public ShopRent.modle.ShopNo getShopNo() {
        if (shopNo == null) {
            shopNo = new ShopNo();
        }
        return shopNo;
    }

}
