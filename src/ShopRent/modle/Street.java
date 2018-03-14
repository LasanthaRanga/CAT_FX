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
public class Street extends Ward {

    private pojo.Street pojoStreet;
    private Integer idStreet;
    private String streetName;
    private String strretNo;
    private Integer strretStatus;
    private Integer strretSyn;
    
    
    
    
    public boolean savePojoStreet() {
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

    public boolean updatePojoStreet() {
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

    public boolean deactivePojoStreet() {
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
    
    public boolean searchPojoStreetByID(int id) {
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
    
    public boolean searchPojoStreetByName(String name) {
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
     * @return the pojoStreet
     */
    public pojo.Street getPojoStreet() {
        return pojoStreet;
    }

    /**
     * @param pojoStreet the pojoStreet to set
     */
    public void setPojoStreet(pojo.Street pojoStreet) {
        this.pojoStreet = pojoStreet;
    }

    /**
     * @return the idStreet
     */
    public Integer getIdStreet() {
        return idStreet;
    }

    /**
     * @param idStreet the idStreet to set
     */
    public void setIdStreet(Integer idStreet) {
        this.idStreet = idStreet;
    }

    /**
     * @return the streetName
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * @param streetName the streetName to set
     */
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    /**
     * @return the strretNo
     */
    public String getStrretNo() {
        return strretNo;
    }

    /**
     * @param strretNo the strretNo to set
     */
    public void setStrretNo(String strretNo) {
        this.strretNo = strretNo;
    }

    /**
     * @return the strretStatus
     */
    public Integer getStrretStatus() {
        return strretStatus;
    }

    /**
     * @param strretStatus the strretStatus to set
     */
    public void setStrretStatus(Integer strretStatus) {
        this.strretStatus = strretStatus;
    }

    /**
     * @return the strretSyn
     */
    public Integer getStrretSyn() {
        return strretSyn;
    }

    /**
     * @param strretSyn the strretSyn to set
     */
    public void setStrretSyn(Integer strretSyn) {
        this.strretSyn = strretSyn;
    }

}
