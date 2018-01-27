/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author suhada
 */
public class Payment implements DAO<pojo.Payment> {

    @Override
    public boolean save(pojo.Payment t) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {
            session.save(t);
            bt.commit();
            if (t.getIdPayment() > 0) {
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
    public boolean save(List<pojo.Payment> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(pojo.Payment t) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {
            session.update(t);
            bt.commit();
            if (t.getIdPayment() > 0) {
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
    public boolean update(List<pojo.Payment> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveOrUpdate(pojo.Payment t) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {
            session.saveOrUpdate(t);
            bt.commit();
            return true;
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
    public boolean saveOrUpdate(List<pojo.Payment> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(pojo.Payment t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<pojo.Payment> getList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getNextReceiptNo() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            List<pojo.Payment> list = session.createCriteria(pojo.Payment.class).setMaxResults(1).addOrder(Order.desc("idPayment")).list();
            if (list.size() > 0) {
                try {
                    return Integer.parseInt(list.get(0).getReceiptNo()) + 1;
                } catch (Exception e) {
                    return -1;
                }
            } else {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            session.close();
        }
    }

    public pojo.Payment getByIdFull(int id) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            return (pojo.Payment) session.createCriteria(pojo.Payment.class)
                    .setFetchMode("vort", FetchMode.JOIN)
                    .setFetchMode("tradeLicenses", FetchMode.JOIN)
                    .add(Restrictions.eq("idPayment", id))
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    /**
     *
     * @param date filter date
     * @param status 1 active, 0 deactive, -1 all
     * @return filtered payment list
     */
    public List<pojo.Payment> getByDate(Date date, int status) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            Criteria ct = session.createCriteria(pojo.Payment.class)
                    .add(Restrictions.eq("paymentDate", date));
            if (status != -1) {
                ct.add(Restrictions.eq("status", status));
            }
            return ct.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    /**
     *
     * @param number receipt number
     * * @param status 1 active, 0 deactive, -1 all
     * @return
     */
    public List<pojo.Payment> getReceiptNumberLike(String number, int status) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            Criteria ct = session.createCriteria(pojo.Payment.class)
                    .add(Restrictions.like("receiptNo", number, MatchMode.START));
            if (status != -1) {
                ct.add(Restrictions.eq("status", status));
            }
            return ct.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
    /**
     * 
     * @param year  year
     * @param status    1 active, 0 deactive, -1 all
     * @return 
     */
    public List<pojo.Payment> getByYear(int year, int status) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            Criteria ct = session.createCriteria(pojo.Payment.class)
                    .add(Restrictions.eq("year", year));
            if (status != -1) {
                ct.add(Restrictions.eq("status", status));
            }
            return ct.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}
