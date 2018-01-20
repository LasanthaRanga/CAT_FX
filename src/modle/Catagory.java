/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import controller.NatureController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import pojo.TradeNature;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class Catagory {

    private Integer idCatagory;
    private String catagoryName;
    private String catagoryNo;
    private String catagorycol;
    private Integer status;
    private Integer syn;

    /**
     * @return the idCatagory
     */
    public Integer getIdCatagory() {
        return idCatagory;
    }

    /**
     * @param idCatagory the idCatagory to set
     */
    public void setIdCatagory(Integer idCatagory) {
        this.idCatagory = idCatagory;
    }

    /**
     * @return the catagoryName
     */
    public String getCatagoryName() {
        return catagoryName;
    }

    /**
     * @param catagoryName the catagoryName to set
     */
    public void setCatagoryName(String catagoryName) {
        this.catagoryName = catagoryName;
    }

    /**
     * @return the catagoryNo
     */
    public String getCatagoryNo() {
        return catagoryNo;
    }

    /**
     * @param catagoryNo the catagoryNo to set
     */
    public void setCatagoryNo(String catagoryNo) {
        this.catagoryNo = catagoryNo;
    }

    /**
     * @return the catagorycol
     */
    public String getCatagorycol() {
        return catagorycol;
    }

    /**
     * @param catagorycol the catagorycol to set
     */
    public void setCatagorycol(String catagorycol) {
        this.catagorycol = catagorycol;
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

    public ObservableList loadCatList() {
        ObservableList<String> List = FXCollections.observableArrayList();
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            List<pojo.Catagory> list = session.createCriteria(pojo.Catagory.class).add(Restrictions.eq("status", 1)).list();
            for (pojo.Catagory cat : list) {
                List.add(cat.getCatagoryName());
            }
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return List;

    }

    public boolean save(String cat) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {
            pojo.Catagory catagory = new pojo.Catagory();
            catagory.setCatagoryName(cat);

            catagory.setStatus(1);
            catagory.setSyn(1);
            session.save(catagory);
            bt.commit();
            if (catagory.getIdCatagory() > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            if (bt != null) {
                bt.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public List<pojo.Catagory> loadCatListForTable() {

        List<pojo.Catagory> list = null;

        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            list = session.createCriteria(pojo.Catagory.class).add(Restrictions.eq("status", 1)).list();
            
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

}
