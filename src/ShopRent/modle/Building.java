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
public class Building extends Street {

    private pojo.SrBuilding pojoBuilding;
    private Integer idBuilding;
    private Street street;
    private String buildingName;
    private Integer buildingStatus;
    private Integer buildingSyn;
    
    public boolean savePojoBuilding() {
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

    public boolean updatePojoBuilding() {
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

    public boolean deactivePojoBuilding() {
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
    
    public boolean searchPojoBuildingByID(int id) {
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
    
    public boolean searchPojoBuildingByName(String name) {
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
     * @return the pojoBuilding
     */
    public pojo.SrBuilding getPojoBuilding() {
        return pojoBuilding;
    }

    /**
     * @param pojoBuilding the pojoBuilding to set
     */
    public void setPojoBuilding(pojo.SrBuilding pojoBuilding) {
        this.pojoBuilding = pojoBuilding;
    }

    /**
     * @return the idBuilding
     */
    public Integer getIdBuilding() {
        return idBuilding;
    }

    /**
     * @param idBuilding the idBuilding to set
     */
    public void setIdBuilding(Integer idBuilding) {
        this.idBuilding = idBuilding;
    }

    /**
     * @return the street
     */
    public Street getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(Street street) {
        this.street = street;
    }

    /**
     * @return the buildingName
     */
    public String getBuildingName() {
        return buildingName;
    }

    /**
     * @param buildingName the buildingName to set
     */
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    /**
     * @return the buildingStatus
     */
    public Integer getBuildingStatus() {
        return buildingStatus;
    }

    /**
     * @param buildingStatus the buildingStatus to set
     */
    public void setBuildingStatus(Integer buildingStatus) {
        this.buildingStatus = buildingStatus;
    }

    /**
     * @return the buildingSyn
     */
    public Integer getBuildingSyn() {
        return buildingSyn;
    }

    /**
     * @param buildingSyn the buildingSyn to set
     */
    public void setBuildingSyn(Integer buildingSyn) {
        this.buildingSyn = buildingSyn;
    }

}
