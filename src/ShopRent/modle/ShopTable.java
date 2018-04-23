/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShopRent.modle;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class ShopTable {

    private final int idBuilding;
    private final SimpleStringProperty buildingName;

    private final int idFloor;
    private final SimpleStringProperty floorName;

    private final int idRet;
    private final SimpleStringProperty retType;

    private final int idType;
    private final SimpleStringProperty shopType;

    private final int idShop;
    private final SimpleStringProperty shopNo;
    private final SimpleStringProperty shopName;
    private final int statusShop;

    private final int idShopRentInfo;
    private final int idCustomer;
    private final SimpleStringProperty customer;

    public ShopTable(int idBuilding, String buildingName, int idFloor, String floorName, int idRet, String retType, int idType, String shopType, int idShop, String shopNo, String shopName, int statusShop, int idShopRentInfo, int idCustomer, String customer) {
        this.idBuilding = idBuilding;
        this.buildingName = new SimpleStringProperty(buildingName);
        this.idFloor = idFloor;
        this.floorName = new SimpleStringProperty(floorName);
        this.idRet = idRet;
        this.retType = new SimpleStringProperty(retType);
        this.idType = idType;
        this.shopType = new SimpleStringProperty(shopType);
        this.idShop = idShop;
        this.shopNo = new SimpleStringProperty(shopNo);
        this.shopName = new SimpleStringProperty(shopName);
        this.statusShop = statusShop;
        this.idShopRentInfo = idShopRentInfo;
        this.idCustomer = idCustomer;
        this.customer = new SimpleStringProperty(customer);
    }

    /**
     * @return the idBuilding
     */
    public int getIdBuilding() {
        return idBuilding;
    }

    /**
     * @return the buildingName
     */
    public String getBuildingName() {
        return buildingName.get();
    }

    /**
     * @return the idFloor
     */
    public int getIdFloor() {
        return idFloor;
    }

    /**
     * @return the floorName
     */
    public String getFloorName() {
        return floorName.get();
    }

    /**
     * @return the idRet
     */
    public int getIdRet() {
        return idRet;
    }

    /**
     * @return the retType
     */
    public String getRetType() {
        return retType.get();
    }

    /**
     * @return the idType
     */
    public int getIdType() {
        return idType;
    }

    /**
     * @return the shopType
     */
    public String getShopType() {
        return shopType.get();
    }

    /**
     * @return the idShop
     */
    public int getIdShop() {
        return idShop;
    }

    /**
     * @return the shopNo
     */
    public String getShopNo() {
        return shopNo.get();
    }

    /**
     * @return the shopName
     */
    public String getShopName() {
        return shopName.get();
    }

    /**
     * @return the statusShop
     */
    public int getStatusShop() {
        return statusShop;
    }

    /**
     * @return the idShopRentInfo
     */
    public int getIdShopRentInfo() {
        return idShopRentInfo;
    }

    /**
     * @return the idCustomer
     */
    public int getIdCustomer() {
        return idCustomer;
    }

    /**
     * @return the customer
     */
    public String getCustomer() {
        return customer.get();
    }

}
