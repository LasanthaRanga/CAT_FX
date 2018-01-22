/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import pojo.TradeLicense;

/**
 *
 * @author suhada
 */
public class TradeLicen implements DAO<pojo.TradeLicense>{

    @Override
    public boolean save(TradeLicense t) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {
            session.save(t);
            bt.commit();
            if (t.getIdTradeLicense()> 0) {
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
    public boolean save(List<TradeLicense> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(TradeLicense t) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {
            session.update(t);
            bt.commit();
            if (t.getIdTradeLicense()> 0) {
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
    public boolean update(List<TradeLicense> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveOrUpdate(TradeLicense t) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {
            session.saveOrUpdate(t);
            bt.commit();
            if (t.getIdTradeLicense()> 0) {
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
    public boolean saveOrUpdate(List<TradeLicense> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(TradeLicense t) {
        t.setStatus(0);
        return this.update(t);
    }

    @Override
    public List<TradeLicense> getList() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            return session.createCriteria(pojo.TradeLicense.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
    public pojo.TradeLicense getByApplication(pojo.Application application){
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            return (TradeLicense) session.createCriteria(pojo.TradeLicense.class).add(Restrictions.eq("application", application)).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
    public pojo.TradeLicense getByPayment(pojo.Payment payment){
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            return (TradeLicense) session.createCriteria(pojo.TradeLicense.class).add(Restrictions.eq("payment", payment)).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
    public int getNextTradeLicenNo(){
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            List<pojo.TradeLicense> list = session.createCriteria(pojo.TradeLicense.class).setMaxResults(1).addOrder(Order.desc("idTradeLicense")).list();
            if(list.size()>0){
                try {
                    return Integer.parseInt(list.get(0).getLicenNo())+1;
                } catch (Exception e) {
                    return -1;
                }
            }else{
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            session.close();
        }
    }
    
}
