/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShopRent.modle;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import pojo.SrBuilding;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class Building implements SUD<pojo.SrBuilding> {

    @Override
    public boolean save(SrBuilding t) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {
            session.save(t);
            bt.commit();
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

    @Override
    public boolean update(SrBuilding t) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {
            session.update(t);
            bt.commit();
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

    @Override
    public boolean deactiv(SrBuilding t) {
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

    @Override
    public List<SrBuilding> getList() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            List<SrBuilding> list = session.createCriteria(pojo.SrBuilding.class).add(Restrictions.eq("status", 1)).list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
            ShopRent.modle.LogWrite.writeLog();
        }
    }

    @Override
    public SrBuilding getT_By_Id(int id) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            SrBuilding srBuilding = (pojo.SrBuilding) session.createCriteria(pojo.SrBuilding.class).add(Restrictions.eq("idBuilding", id)).uniqueResult();
            return srBuilding;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
            ShopRent.modle.LogWrite.writeLog();
        }
    }

    @Override
    public SrBuilding getT_By_name(String name) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            SrBuilding srBuilding = (pojo.SrBuilding) session.createCriteria(pojo.SrBuilding.class).add(Restrictions.eq("buildingName", name)).uniqueResult();
            return srBuilding;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
            ShopRent.modle.LogWrite.writeLog();
        }
    }

    @Override
    public boolean isExist(String s) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {
            List list = session.createCriteria(pojo.SrBuilding.class).add(Restrictions.eq("buildingName", s)).list();
            if (list.size() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            bt.rollback();
            return false;
        } finally {
            session.close();
            ShopRent.modle.LogWrite.writeLog();
        }

    }

    // method
    public List<pojo.SrBuilding> getBuildingListBy_StreetID(int streetId) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            Criteria criteria = session.createCriteria(pojo.SrBuilding.class);
            criteria.add(Restrictions.eq("status", 1));
            criteria.add(Restrictions.eq("street", (pojo.Street) session.load(pojo.Street.class, streetId)));
            List<pojo.SrBuilding> list = criteria.list();
            if (list != null) {
                return list;
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

    public String getStreetByBuilidingID(int x) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            SrBuilding name = (pojo.SrBuilding) session.createCriteria(pojo.SrBuilding.class).add(Restrictions.eq("idBuilding", x)).uniqueResult();
            return name.getStreet().getStreetName();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
            ShopRent.modle.LogWrite.writeLog();
        }

    }

    public Integer getStreetIDByBuilidingID(int x) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            SrBuilding name = (pojo.SrBuilding) session.createCriteria(pojo.SrBuilding.class).add(Restrictions.eq("idBuilding", x)).uniqueResult();
            return name.getStreet().getIdStreet();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
            ShopRent.modle.LogWrite.writeLog();
        }

    }

    public String getWardByBuilidingID(int x) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            SrBuilding name = (pojo.SrBuilding) session.createCriteria(pojo.SrBuilding.class).add(Restrictions.eq("idBuilding", x)).uniqueResult();
            return name.getStreet().getWard().getWardName();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
            ShopRent.modle.LogWrite.writeLog();
        }

    }

    public Integer getWardIdByBuilidingID(int x) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            SrBuilding name = (pojo.SrBuilding) session.createCriteria(pojo.SrBuilding.class).add(Restrictions.eq("idBuilding", x)).uniqueResult();
            return name.getStreet().getWard().getIdWard();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
            ShopRent.modle.LogWrite.writeLog();
        }

    }
}
