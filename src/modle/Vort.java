/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.util.List;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author suhada
 */
public class Vort implements DAO<pojo.Vort>{

    @Override
    public boolean save(pojo.Vort t) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {
            session.save(t);
            bt.commit();
            if (t.getIdVort()> 0) {
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

    @Override
    public boolean save(List<pojo.Vort> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(pojo.Vort t) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {
            session.update(t);
            bt.commit();
            if (t.getIdVort()> 0) {
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

    @Override
    public boolean update(List<pojo.Vort> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveOrUpdate(pojo.Vort t) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {
            session.saveOrUpdate(t);
            bt.commit();
            if (t.getIdVort()> 0) {
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

    @Override
    public boolean saveOrUpdate(List<pojo.Vort> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(pojo.Vort t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<pojo.Vort> getList() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            return session.createCriteria(pojo.Vort.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
    public pojo.Vort getById(int id){
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            return (pojo.Vort) session.createCriteria(pojo.Vort.class)
                    .add(Restrictions.eq("idVort", id))
                    .setFetchMode("cashFlows", FetchMode.JOIN)
                    .setFetchMode("payments", FetchMode.JOIN)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
}
