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
import pojo.User;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class Users implements DAO<pojo.User> {

    @Override
    public boolean save(User t) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {
            session.save(t);
            bt.commit();
            if(t.getIdUser()>0)return true;
            else return false;
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
    public boolean save(List<User> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(User t) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {
            session.update(t);
            bt.commit();
            if(t.getIdUser()>0)return true;
            else return false;
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
    public boolean update(List<User> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveOrUpdate(User t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveOrUpdate(List<User> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(User t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public pojo.User getByNIC(String nic){
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            return (User) session.createCriteria(pojo.User.class).add(Restrictions.eq("nic", nic)).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            session.close();
        }
    }
    
}
