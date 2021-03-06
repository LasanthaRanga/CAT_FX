/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import pojo.User;
import pojo.UserHasCatagory;
import pojo.UserHasDepartment;
import pojo.UserHasOtheritiscat;

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
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {
            session.saveOrUpdate(t);
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
        try {
            List<pojo.User> list = session.createCriteria(pojo.User.class).list();
            for (Iterator<User> iterator = list.iterator(); iterator.hasNext();) {
                User user = iterator.next();
                if (user.getIdUser() == 1) {
                    iterator.remove();
                }
            }
            return list;
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

    public List<pojo.User> getActiveUsers() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            List list = session.createCriteria(pojo.User.class).add(Restrictions.eq("status", 1)).list();
            for (Iterator<User> iterator = list.iterator(); iterator.hasNext();) {
                User user = iterator.next();
                if (user.getIdUser() == 1) {
                    iterator.remove();
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public List<pojo.User> getDeactiveUsers() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            List<pojo.User> list = session.createCriteria(pojo.User.class).add(Restrictions.eq("status", 0)).list();
            for (Iterator<User> iterator = list.iterator(); iterator.hasNext();) {
                User user = iterator.next();
                if (user.getIdUser() == 1) {
                    iterator.remove();
                }
            }
            return list;
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
            return (User) session.createCriteria(pojo.User.class)
                    .add(Restrictions.eq("idUser", idUser))
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public pojo.User getByFullname(String username) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            return (User) session.createCriteria(pojo.User.class)
                    .add(Restrictions.eq("fullName", username))
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public pojo.User getByIdUserFull(int idUser) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            return (User) session.createCriteria(pojo.User.class)
                    .add(Restrictions.eq("idUser", idUser))
                    .setFetchMode("userHasDepartments", FetchMode.JOIN)
                    .setFetchMode("userHasCatagories", FetchMode.JOIN)
                    .setFetchMode("logins", FetchMode.JOIN)
                    .uniqueResult();
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

    public HashMap getCatagory(int user) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        HashMap<Integer, String> dip = new HashMap<>();

        try {
            pojo.User User = (pojo.User) session.createCriteria(pojo.User.class).add(Restrictions.eq("idUser", user)).uniqueResult();
            Set<UserHasCatagory> userHasDepartments = User.getUserHasCatagories();
            for (UserHasCatagory userHasCatagory : userHasDepartments) {
                dip.put(userHasCatagory.getCatagory().getIdCatagory(), userHasCatagory.getCatagory().getCatagoryName());
            }
            return dip;
        } catch (Exception e) {
            e.printStackTrace();
            return dip;
        } finally {
            session.close();
        }
    }

    public HashMap getAuthoriti(int user) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        HashMap<Integer, String> dip = new HashMap<>();

        try {
            pojo.User User = (pojo.User) session.createCriteria(pojo.User.class).add(Restrictions.eq("idUser", user)).uniqueResult();
            Set<UserHasOtheritiscat> userHasOtheritiscat = User.getUserHasOtheritiscats();
            for (UserHasOtheritiscat userHasOtheritis : userHasOtheritiscat) {
                dip.put(userHasOtheritis.getOtheritiscat().getIdOtheritisCat(), userHasOtheritis.getOtheritiscat().getCatname());
            }
            return dip;
        } catch (Exception e) {
            e.printStackTrace();
            return dip;
        } finally {
            session.close();
        }
    }

    public List<pojo.UserHasDepartment> getUserDepartments(int userId) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            pojo.User user = ((pojo.User) session.createCriteria(pojo.User.class).add(Restrictions.eq("idUser", userId)).uniqueResult());
            return new ArrayList<>(user.getUserHasDepartments());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public List<pojo.Catagory> getUserCategories(int userId) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            pojo.User user = ((pojo.User) session.createCriteria(pojo.User.class)
                    .add(Restrictions.eq("idUser", userId))
                    .uniqueResult());
            ArrayList<pojo.Catagory> list = new ArrayList();
            for (UserHasCatagory userHasCatagory : user.getUserHasCatagories()) {
                list.add(userHasCatagory.getCatagory());
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

}
