/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;

import org.hibernate.criterion.Restrictions;

import pojo.Application;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class Aplication implements DAO<pojo.Application> {

    @Override
    public boolean save(Application t) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {
            session.save(t);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            bt.rollback();
            return false;
        } finally {
            session.close();
        }

    }

    @Override
    public boolean save(List<Application> list) {

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
        }
    }

    @Override
    public boolean update(Application t) {

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
        }

    }

    @Override
    public boolean update(List<Application> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveOrUpdate(Application t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveOrUpdate(List<Application> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Application t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Application> getList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getLastApllicationID() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {
            Criteria c = session.createCriteria(pojo.Application.class);
            c.addOrder(Order.desc("idApplication"));
            c.setMaxResults(1);
            Application name = (pojo.Application) c.uniqueResult();
            int id = 0;
            id = name.getIdApplication();
            return id + 1;
        } catch (Exception e) {
            e.printStackTrace();
            bt.rollback();
            return 0;
        }
    }
}

//    public List<pojo.Application> getApprovedApplications(){
//        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
//        try {
////            pojo.User user=((pojo.User)session.createCriteria(pojo.User.class)
////                    .add(Restrictions.eq("idUser", u.getIdUser()))
////                    .uniqueResult());
//            ArrayList<pojo.Application> list=new ArrayList();
////            for (UserHasCatagory userHasCatagory : user.getUserHasCatagories()) {
////                list.add(userHasCatagory.getCatagory());
////            }
//            return list;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//>>>>>>> b7fde0cf0d1aeb4b2f6aaf036645deeed85b083e
//        } finally {
//            session.close();
//        }
//    }
//<<<<<<< HEAD
//
//=======
//>>>>>>> b7fde0cf0d1aeb4b2f6aaf036645deeed85b083e

