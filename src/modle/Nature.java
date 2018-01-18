/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pojo.TradeNature;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class Nature {

    private Integer idTradeNature;
    private pojo.TradeType tradeType;
    private String natureNo;
    private String nature;
    private Integer status;
    private Integer syn;

    /**
     * @return the idTradeNature
     */
    public Integer getIdTradeNature() {
        return idTradeNature;
    }

    /**
     * @param idTradeNature the idTradeNature to set
     */
    public void setIdTradeNature(Integer idTradeNature) {
        this.idTradeNature = idTradeNature;
    }

    /**
     * @return the tradeType
     */
    public pojo.TradeType getTradeType() {
        return tradeType;
    }

    /**
     * @param tradeType the tradeType to set
     */
    public void setTradeType(pojo.TradeType tradeType) {
        this.tradeType = tradeType;
    }

    /**
     * @return the natureNo
     */
    public String getNatureNo() {
        return natureNo;
    }

    /**
     * @param natureNo the natureNo to set
     */
    public void setNatureNo(String natureNo) {
        this.natureNo = natureNo;
    }

    /**
     * @return the nature
     */
    public String getNature() {
        return nature;
    }

    /**
     * @param nature the nature to set
     */
    public void setNature(String nature) {
        this.nature = nature;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return the syn
     */
    public Integer getSyn() {
        return syn;
    }

    /**
     * @param syn the syn to set
     */
    public void setSyn(Integer syn) {
        this.syn = syn;
    }

    public void saveNature() {
        //System.out.println("call nature save");
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            TradeNature tradeNature = new pojo.TradeNature();
            tradeNature.setTradeType(getTradeType());
            tradeNature.setNatureNo(getNatureNo());
            tradeNature.setNature(getNature());
            tradeNature.setStatus(1);
            tradeNature.setSyn(1);
            session.save(tradeNature);
            session.beginTransaction().commit();
            //  System.out.println("SAVED");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public void updateNature() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            TradeNature upNature = (pojo.TradeNature) session.createCriteria(pojo.TradeNature.class).add(Restrictions.eq("idTradeNature", getIdTradeNature())).uniqueResult();
            upNature.setNature(getNature());
            upNature.setNatureNo(getNatureNo());
            upNature.setSyn(1);
            session.update(upNature);
            session.beginTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deactiveNature() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            TradeNature upNature = (pojo.TradeNature) session.createCriteria(pojo.TradeNature.class).add(Restrictions.eq("idTradeNature", getIdTradeNature())).uniqueResult();
            upNature.setStatus(0);
            upNature.setSyn(1);
            session.update(upNature);
            session.beginTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<pojo.TradeNature> getNaturesList() {
        List<pojo.TradeNature> nList = null;
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            nList = session.createCriteria(pojo.TradeNature.class).add(Restrictions.and(Restrictions.eq("tradeType", getTradeType()), Restrictions.eq("status", 1))).list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return nList;
    }

    public pojo.TradeNature getNatureById(int idNature) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        pojo.TradeNature upNature = null;
        try {
            upNature = (pojo.TradeNature) session.createCriteria(pojo.TradeNature.class).add(Restrictions.eq("idTradeNature", idNature)).uniqueResult();
            session.beginTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return upNature;
    }

}
