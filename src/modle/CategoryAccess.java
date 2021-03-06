/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import pojo.Catagory;

/**
 *
 * @author suhada
 */
public class CategoryAccess implements DAO<pojo.Catagory>{

    @Override
    public boolean save(Catagory t) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {
            session.save(t);
            bt.commit();
            if (t.getIdCatagory() > 0) {
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
    public boolean save(List<Catagory> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Catagory t) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {
            session.update(t);
            bt.commit();
            if (t.getIdCatagory() > 0) {
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
    public boolean update(List<Catagory> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveOrUpdate(Catagory t) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {
            session.saveOrUpdate(t);
            bt.commit();
            if (t.getIdCatagory() > 0) {
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
    public boolean saveOrUpdate(List<Catagory> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Catagory t) {
        t.setStatus(0);
        return this.update(t);
    }

    @Override
    public List<Catagory> getList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
