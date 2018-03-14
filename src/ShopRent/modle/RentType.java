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
public class RentType {

    private pojo.SrRentalType pojoRentalType;
    private Integer idRentalType;
    private String RType;
    private Integer rentTypeStatus;
    private Integer rentTypeSyn;
    
    
    
    public boolean savePojoRentType() {
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

    public boolean updatePojoRentType() {
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

    public boolean deactivePojoRentType() {
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
    
    public boolean searchPojoRentTypeByID(int id) {
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
    
    public boolean searchPojoRentTypeByName(String name) {
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
     * @return the pojoRentalType
     */
    public pojo.SrRentalType getPojoRentalType() {
        return pojoRentalType;
    }

    /**
     * @param pojoRentalType the pojoRentalType to set
     */
    public void setPojoRentalType(pojo.SrRentalType pojoRentalType) {
        this.pojoRentalType = pojoRentalType;
    }

    /**
     * @return the idRentalType
     */
    public Integer getIdRentalType() {
        return idRentalType;
    }

    /**
     * @param idRentalType the idRentalType to set
     */
    public void setIdRentalType(Integer idRentalType) {
        this.idRentalType = idRentalType;
    }

    /**
     * @return the RType
     */
    public String getRType() {
        return RType;
    }

    /**
     * @param RType the RType to set
     */
    public void setRType(String RType) {
        this.RType = RType;
    }

    /**
     * @return the rentTypeStatus
     */
    public Integer getRentTypeStatus() {
        return rentTypeStatus;
    }

    /**
     * @param rentTypeStatus the rentTypeStatus to set
     */
    public void setRentTypeStatus(Integer rentTypeStatus) {
        this.rentTypeStatus = rentTypeStatus;
    }

    /**
     * @return the rentTypeSyn
     */
    public Integer getRentTypeSyn() {
        return rentTypeSyn;
    }

    /**
     * @param rentTypeSyn the rentTypeSyn to set
     */
    public void setRentTypeSyn(Integer rentTypeSyn) {
        this.rentTypeSyn = rentTypeSyn;
    }

}
