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
public class SubNature {

    private Integer idSubNature;
    private TradeNature tradeNature;
    private String subNatureNo;
    private String subNature;
    private Integer status;
    private Integer syn;

    /**
     * @return the idSubNature
     */
    public Integer getIdSubNature() {
        return idSubNature;
    }

    /**
     * @return the tradeNature
     */
    public TradeNature getTradeNature() {
        return tradeNature;
    }

    /**
     * @return the subNatureNo
     */
    public String getSubNatureNo() {
        return subNatureNo;
    }

    /**
     * @return the subNature
     */
    public String getSubNature() {
        return subNature;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @return the syn
     */
    public Integer getSyn() {
        return syn;
    }

    public SubNature(Integer idSubNature, TradeNature tradeNature, String subNatureNo, String subNature, Integer status, Integer syn) {
        this.idSubNature = idSubNature;
        this.tradeNature = tradeNature;
        this.subNatureNo = subNatureNo;
        this.subNature = subNature;
        this.status = status;
        this.syn = syn;
    }

    public void saveNature() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            pojo.SubNature sn = new pojo.SubNature();
            sn.setTradeNature(getTradeNature());
            sn.setSubNatureNo(getSubNatureNo());
            sn.setSubNature(getSubNature());
            sn.setStatus(1);
            sn.setSyn(1);
            session.save(sn);
            session.beginTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public void updateNature() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();

        try {
            
            pojo.SubNature snature = (pojo.SubNature)session.createCriteria(pojo.SubNature.class).add(Restrictions.eq("idSubNature", getIdSubNature())).uniqueResult();
            snature.setSubNatureNo(getSubNatureNo());
            snature.setSubNature(getSubNature());            
            snature.setSyn(1);
            session.update(snature);
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
            
            pojo.SubNature snature = (pojo.SubNature)session.createCriteria(pojo.SubNature.class).add(Restrictions.eq("idSubNature", getIdSubNature())).uniqueResult();                    
            snature.setStatus(0);
            session.update(snature);
            session.beginTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }


    }

    public List<pojo.SubNature> loadNature() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        List<pojo.SubNature> sub = null;
        try {
           sub = session.createCriteria(pojo.SubNature.class).add(Restrictions.and(Restrictions.eq("tradeNature", getTradeNature()),Restrictions.eq("status", 1))).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return sub;
    }

    public pojo.SubNature getNatureById() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();

        try {

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

}
