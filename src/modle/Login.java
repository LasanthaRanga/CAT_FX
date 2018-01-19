/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author suhada
 */
public class Login implements DAO<pojo.Login>{

    @Override
    public boolean save(pojo.Login t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(List<pojo.Login> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(pojo.Login t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(List<pojo.Login> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveOrUpdate(pojo.Login t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveOrUpdate(List<pojo.Login> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(pojo.Login t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<pojo.Login> getList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public pojo.Login getByUser(pojo.User user){
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            return (pojo.Login) session.createCriteria(pojo.Login.class)
                    .add(Restrictions.eq("user", user))
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            session.close();
        }
    }
    
    public pojo.Login getById(int id){
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            return (pojo.Login) session.createCriteria(pojo.Login.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            session.close();
        }
    }
    
    public pojo.Login getByUsername(String username){
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            return (pojo.Login) session.createCriteria(pojo.Login.class)
                    .add(Restrictions.eq("uname", username))
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            session.close();
        }
    }
    
}
