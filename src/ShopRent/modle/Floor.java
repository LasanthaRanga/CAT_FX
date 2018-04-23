/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShopRent.modle;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import pojo.SrFlow;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class Floor implements SUD<pojo.SrFlow> {

    @Override
    public boolean isExist(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(SrFlow s) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {
            session.save(s);
            bt.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            bt.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(SrFlow t) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {
            session.update(t);
            bt.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            bt.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean deactiv(SrFlow t) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {
            session.update(t);
            bt.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            bt.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public List<SrFlow> getList() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            return session.createCriteria(pojo.SrFlow.class).add(Restrictions.eq("status", 1)).list();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public SrFlow getT_By_Id(int id) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            return (pojo.SrFlow) session.load(pojo.SrFlow.class, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public SrFlow getT_By_name(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isExist(String s, int idBuilding) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {
            Criteria cry = session.createCriteria(pojo.SrFlow.class);
            cry.add(Restrictions.eq("flowName", s)).list();
            cry.add(Restrictions.eq("srBuilding", (pojo.SrBuilding) session.load(pojo.SrBuilding.class, idBuilding)));
            List list = cry.list();
            return list.size() > 0;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            bt.rollback();
            return true;
        } finally {
            session.close();
        }
    }

    public List<SrFlow> getList(pojo.SrBuilding srBuilding) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            Criteria cry = session.createCriteria(pojo.SrFlow.class);
            cry.add(Restrictions.eq("srBuilding", srBuilding));
            return cry.add(Restrictions.eq("status", 1)).list();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            session.close();

        }
    }

    public SrFlow getT_By_name(String name, pojo.SrBuilding building) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            Criteria createCriteria = session.createCriteria(pojo.SrFlow.class);
            createCriteria.add(Restrictions.eq("srBuilding", building));
            return (SrFlow) createCriteria.add(Restrictions.eq("flowName", name)).uniqueResult();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            session.close();
        }
    }

    

}
