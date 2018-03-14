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
public class Ward {

    private pojo.Ward pojoWard;
    private Integer idWard;
    private String wardNo;
    private String wardName;
    private String wardcol;
    private Integer wardStatus;
    private Integer wardSyn;

    public boolean savePojoWard() {
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

    public boolean updatePojoWard() {
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

    public boolean deactivePojoWard() {
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
    
    public boolean searchPojoWardByID(int id) {
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
    
    public boolean searchPojoWardByName(String name) {
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
     * @return the pojoWard
     */
    public pojo.Ward getPojoWard() {
        return pojoWard;
    }

    /**
     * @param pojoWard the pojoWard to set
     */
    public void setPojoWard(pojo.Ward pojoWard) {
        this.pojoWard = pojoWard;
    }

    /**
     * @return the idWard
     */
    public Integer getIdWard() {
        return idWard;
    }

    /**
     * @param idWard the idWard to set
     */
    public void setIdWard(Integer idWard) {
        this.idWard = idWard;
    }

    /**
     * @return the wardNo
     */
    public String getWardNo() {
        return wardNo;
    }

    /**
     * @param wardNo the wardNo to set
     */
    public void setWardNo(String wardNo) {
        this.wardNo = wardNo;
    }

    /**
     * @return the wardName
     */
    public String getWardName() {
        return wardName;
    }

    /**
     * @param wardName the wardName to set
     */
    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    /**
     * @return the wardcol
     */
    public String getWardcol() {
        return wardcol;
    }

    /**
     * @param wardcol the wardcol to set
     */
    public void setWardcol(String wardcol) {
        this.wardcol = wardcol;
    }

    /**
     * @return the wardStatus
     */
    public Integer getWardStatus() {
        return wardStatus;
    }

    /**
     * @param wardStatus the wardStatus to set
     */
    public void setWardStatus(Integer wardStatus) {
        this.wardStatus = wardStatus;
    }

    /**
     * @return the wardSyn
     */
    public Integer getWardSyn() {
        return wardSyn;
    }

    /**
     * @param wardSyn the wardSyn to set
     */
    public void setWardSyn(Integer wardSyn) {
        this.wardSyn = wardSyn;
    }

}
