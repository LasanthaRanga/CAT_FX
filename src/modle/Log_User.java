/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pojo.Login;
import pojo.User;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class Log_User {

    public static pojo.UserLog getLogUser() {
        // meka edit karanna one thawa
        pojo.UserLog lu = null;
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();

        try {
            lu = (pojo.UserLog) session.createCriteria(pojo.UserLog.class).add(Restrictions.eq("idUserLog", 1)).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return lu;
    }

    public static String getLogUserName() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        String uname = null;
        try {
            pojo.UserLog lu = (pojo.UserLog) session.createCriteria(pojo.UserLog.class).add(Restrictions.eq("idUserLog", 1)).uniqueResult();
            uname = lu.getUser().getFullName();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return uname;
    }

    public int loginCheack(String uname, String pass) {
        boolean b = false;
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction().commit();

            Login log = (pojo.Login) session.createCriteria(pojo.Login.class)
                    .add(Restrictions.and(Restrictions.eq("uname", uname), 
                            Restrictions.eq("pword", pass)))
                    .uniqueResult();
            if (log != null) {
                AuthUser.setUser(log.getUser());
                Integer idUser = log.getUser().getIdUser();
                return idUser;
                
            } else {
                return 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            session.close();
        }

    }

    public pojo.User login(String uname, String pass){
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            return (User) session.createCriteria(pojo.Login.class)
                    .add(Restrictions.and(Restrictions.eq("uname", uname), 
                            Restrictions.eq("pword", pass)))
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}
