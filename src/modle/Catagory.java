/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

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
}
