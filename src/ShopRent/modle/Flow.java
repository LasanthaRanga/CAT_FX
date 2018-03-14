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
public class Flow extends Building {

    private pojo.SrFlow pojoFlow;
    private Integer idFlow;
    private String flowName;
    private Integer flowStatus;
    private Integer flowSyn;
    
    
    public boolean savePojoFlow() {
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

    public boolean updatePojoFlow() {
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

    public boolean deactivePojoFlow() {
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
    
    public boolean searchPojoFlowByID(int id) {
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
    
    public boolean searchPojoFlowByName(String name) {
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
     * @return the pojoFlow
     */
    public pojo.SrFlow getPojoFlow() {
        return pojoFlow;
    }

    /**
     * @param pojoFlow the pojoFlow to set
     */
    public void setPojoFlow(pojo.SrFlow pojoFlow) {
        this.pojoFlow = pojoFlow;
    }

    /**
     * @return the idFlow
     */
    public Integer getIdFlow() {
        return idFlow;
    }

    /**
     * @param idFlow the idFlow to set
     */
    public void setIdFlow(Integer idFlow) {
        this.idFlow = idFlow;
    }

    /**
     * @return the flowName
     */
    public String getFlowName() {
        return flowName;
    }

    /**
     * @param flowName the flowName to set
     */
    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    /**
     * @return the flowStatus
     */
    public Integer getFlowStatus() {
        return flowStatus;
    }

    /**
     * @param flowStatus the flowStatus to set
     */
    public void setFlowStatus(Integer flowStatus) {
        this.flowStatus = flowStatus;
    }

    /**
     * @return the flowSyn
     */
    public Integer getFlowSyn() {
        return flowSyn;
    }

    /**
     * @param flowSyn the flowSyn to set
     */
    public void setFlowSyn(Integer flowSyn) {
        this.flowSyn = flowSyn;
    }

}
