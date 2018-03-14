/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShopRent.modle;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class ShopType {

    private pojo.SrShopType pojoShopType;
    private Integer idShopType;
    private String shopType;
    private Integer shopTypeStatus;
    private Integer shopTypeSyn;
    
    public boolean savePojoShopType() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            bt.rollback();
            return false;
        } finally {
            session.close();
            ShopRent.modle.LogWrite.writeLog();
        }
    }

    public boolean updatePojoShopType() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            bt.rollback();
            return false;
        } finally {
            session.close();
            ShopRent.modle.LogWrite.writeLog();
        }
    }

    public boolean deactivePojoShopType() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            bt.rollback();
            return false;
        } finally {
            session.close();
            ShopRent.modle.LogWrite.writeLog();
        }
    }
    
    public boolean searchPojoShopTypeByID(int id) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();        
        try {

            return true;
        } catch (Exception e) {
            e.printStackTrace();          
            return false;
        } finally {
            session.close();
            ShopRent.modle.LogWrite.writeLog();
        }
    }
    
    public boolean searchPojoShopTypeByName(String name) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();     
        try {

            return true;
        } catch (Exception e) {
            e.printStackTrace();          
            return false;
        } finally {
            session.close();
            ShopRent.modle.LogWrite.writeLog();
        }
    }
    

    /**
     * @return the pojoShopType
     */
    public pojo.SrShopType getPojoShopType() {
        return pojoShopType;
    }

    /**
     * @param pojoShopType the pojoShopType to set
     */
    public void setPojoShopType(pojo.SrShopType pojoShopType) {
        this.pojoShopType = pojoShopType;
    }

    /**
     * @return the idShopType
     */
    public Integer getIdShopType() {
        return idShopType;
    }

    /**
     * @param idShopType the idShopType to set
     */
    public void setIdShopType(Integer idShopType) {
        this.idShopType = idShopType;
    }

    /**
     * @return the shopType
     */
    public String getShopType() {
        return shopType;
    }

    /**
     * @param shopType the shopType to set
     */
    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    /**
     * @return the shopTypeStatus
     */
    public Integer getShopTypeStatus() {
        return shopTypeStatus;
    }

    /**
     * @param shopTypeStatus the shopTypeStatus to set
     */
    public void setShopTypeStatus(Integer shopTypeStatus) {
        this.shopTypeStatus = shopTypeStatus;
    }

    /**
     * @return the shopTypeSyn
     */
    public Integer getShopTypeSyn() {
        return shopTypeSyn;
    }

    /**
     * @param shopTypeSyn the shopTypeSyn to set
     */
    public void setShopTypeSyn(Integer shopTypeSyn) {
        this.shopTypeSyn = shopTypeSyn;
    }

}
