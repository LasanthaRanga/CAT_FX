/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import pojo.User;
import pojo.UserHasDepartment;

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
            if (t.getIdUser() > 0) {
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
            if (t.getIdUser() > 0) {
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
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        List<pojo.User> list = null;
        try {
            List<pojo.User> list1 = session.createCriteria(pojo.User.class).list();
            list1.remove(0);
//            for (pojo.User object : list1) {
//                if (object.getIdUser() == 1) {
//                    
//                } else {
//                    list.add(object);
//                }
//
//            }
            return list1;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }

    }

    public pojo.User getByNIC(String nic) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            return (User) session.createCriteria(pojo.User.class).add(Restrictions.eq("nic", nic)).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public List<pojo.User> getActiveUsers(){
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            return session.createCriteria(pojo.User.class).add(Restrictions.eq("status", 1)).setFirstResult(2).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
    public pojo.User getByIdUser(int idUser) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            return (User) session.createCriteria(pojo.User.class).add(Restrictions.eq("idUser", idUser)).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    public HashMap getDepartments(int user) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        HashMap<Integer, String> dip = new HashMap<>();

        try {
            pojo.User User = (pojo.User) session.createCriteria(pojo.User.class).add(Restrictions.eq("idUser", user)).uniqueResult();
            Set<UserHasDepartment> userHasDepartments = User.getUserHasDepartments();
            for (UserHasDepartment userHasDepartment : userHasDepartments) {
                dip.put(userHasDepartment.getDepartment().getIdDepartment(), userHasDepartment.getDepartment().getDepartment());
            }
            return dip;
        } catch (Exception e) {
            e.printStackTrace();
            return dip;
        } finally {
            session.close();
        }
    }

}
