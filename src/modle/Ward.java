/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import pojo.Street;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class Ward implements DAO<pojo.Ward> {

    private int idWard;
    private String wardname;

    /**
     * @return the idWord
     */
    public int getIdWord() {
        return idWard;
    }

    /**
     * @param idWard the idWord to set
     */
    public void setIdWard(int idWard) {
        this.idWard = idWard;
    }

    /**
     * @return the wordname
     */
    public String getWardname() {
        return wardname;
    }

    /**
     * @param wardname the wordname to set
     */
    public void setWardname(String wardname) {
        this.wardname = wardname;
    }

    public int saveWaard() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        int x = 0;
        try {
            pojo.Ward ward = new pojo.Ward();
            ward.setWardName(getWardname());
            ward.setStatus(1);
            ward.setSyn(1);
            Serializable save = session.save(ward);
            session.beginTransaction().commit();
            //System.out.println(save.toString());
            x = Integer.parseInt(save.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return x;
    }

    public List loadWardTable() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction().commit();
        List list = null;
        try {
            list = session.createCriteria(pojo.Ward.class).add(Restrictions.eq("status", 1)).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public void updateWard() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            pojo.Ward upWard = (pojo.Ward) session.createCriteria(pojo.Ward.class).add(Restrictions.eq("idWard", getIdWord())).uniqueResult();
            upWard.setWardName(getWardname());
            session.update(upWard);
            session.beginTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public void deactivWard() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            pojo.Ward upWard = (pojo.Ward) session.createCriteria(pojo.Ward.class).add(Restrictions.eq("idWard", getIdWord())).uniqueResult();
            upWard.setStatus(0);
            session.update(upWard);
            session.beginTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public ObservableList getStreetBySelectedWard() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        ObservableList List = null;
        List<pojo.Street> streets = null;
        if (getWardname() != null) {
            try {
                pojo.Ward upWard = (pojo.Ward) session.createCriteria(pojo.Ward.class).add(Restrictions.eq("wardName", getWardname())).uniqueResult();

                streets = session.createCriteria(pojo.Street.class).add(Restrictions.eq("ward", upWard)).addOrder(Order.asc("streetName")).list();
             
                List = FXCollections.observableArrayList();

                for (Street street : streets) {
                    List.add(street.getStreetName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                session.close();
            }
        }
        return List;
    }

    @Override
    public boolean save(pojo.Ward t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(List<pojo.Ward> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(pojo.Ward t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(List<pojo.Ward> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveOrUpdate(pojo.Ward t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveOrUpdate(List<pojo.Ward> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(pojo.Ward t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<pojo.Ward> getList() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction().commit();
        List list = null;
        try {
            list = session.createCriteria(pojo.Ward.class).add(Restrictions.eq("status", 1)).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public pojo.Ward getWardByWardName(String wardname) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction().commit();
        try {
            pojo.Ward ward = (pojo.Ward) session.createCriteria(pojo.Ward.class).add(Restrictions.eq("wardName", wardname)).uniqueResult();
            return ward;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

}
