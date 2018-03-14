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

    public pojo.Street searchPojoStreetByID(int id) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();

        try {
            nullStreet();
            setPojoStreet((pojo.Street) session.load(pojo.Street.class, id));
            if (getPojoStreet() != null) {
                return pojoStreet;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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

    public void nullStreet() {
        setPojoStreet(null);

    }

    public void setAllDataInStreet() {

        getPojoStreet().getIdStreet();

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
}
