/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShopRent.modle;

import pojo.SrShopNo;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class Shop extends Flow {

    private ShopType shopType;
    private RentType rentType;
    private pojo.SrShopNo pojShopNo;
    private Integer idShopNo;
    private String shopNo;
    private String tradeName;
    private Integer shopStatus;
    private Integer shopSyn;

    public Shop() {
        super();
    }

    public Shop(ShopType shopType, RentType rentType, SrShopNo pojShopNo, Integer idShopNo, String shopNo, String tradeName, Integer shopStatus, Integer shopSyn) {
        this.shopType = shopType;
        this.rentType = rentType;
        this.pojShopNo = pojShopNo;
        this.idShopNo = idShopNo;
        this.shopNo = shopNo;
        this.tradeName = tradeName;
        this.shopStatus = shopStatus;
        this.shopStatus = shopStatus;
    }

    public Shop(ShopType shopType, RentType rentType) {
        this.shopType = shopType;
        this.rentType = rentType;
    }

    /**
     * @return the shopType
     */
    public ShopType getShopType() {
        return shopType;
    }

    /**
     * @param shopType the shopType to set
     */
    public void setShopType(ShopType shopType) {
        this.shopType = shopType;
    }

    /**
     * @return the rentType
     */
    public RentType getRentType() {
        return rentType;
    }

    /**
     * @param rentType the rentType to set
     */
    public void setRentType(RentType rentType) {
        this.rentType = rentType;
    }

    /**
     * @return the pojShopNo
     */
    public pojo.SrShopNo getPojShopNo() {
        return pojShopNo;
    }

    /**
     * @param pojShopNo the pojShopNo to set
     */
    public void setPojShopNo(pojo.SrShopNo pojShopNo) {
        this.pojShopNo = pojShopNo;
    }

    /**
     * @return the idShopNo
     */
    public Integer getIdShopNo() {
        return idShopNo;
    }

    /**
     * @param idShopNo the idShopNo to set
     */
    public void setIdShopNo(Integer idShopNo) {
        this.idShopNo = idShopNo;
    }

    /**
     * @return the shopNo
     */
    public String getShopNo() {
        return shopNo;
    }

    /**
     * @param shopNo the shopNo to set
     */
    public void setShopNo(String shopNo) {
        this.shopNo = shopNo;
    }

    /**
     * @return the tradeName
     */
    public String getTradeName() {
        return tradeName;
    }

    /**
     * @param tradeName the tradeName to set
     */
    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    /**
     * @return the shopStatus
     */
    public Integer getStatus() {
        return shopStatus;
    }

    /**
     * @param shopStatus the status to set
     */
    public void setStatus(Integer shopStatus) {
        this.shopStatus = shopStatus;
    }

    /**
     * @return the shopSyn
     */
    public Integer getShopSyn() {
        return shopSyn;
    }

    /**
     * @param shopSyn the shopSyn to set
     */
    public void setSyn(Integer shopSyn) {
        this.shopSyn = shopSyn;
    }

}
