/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Apprualstatues;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class ApplicationStatus implements DAO<pojo.Apprualstatues> {

    @Override
    public boolean save(Apprualstatues t) {
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
        }

    }

    @Override
    public boolean save(List<Apprualstatues> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Apprualstatues t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(List<Apprualstatues> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveOrUpdate(Apprualstatues t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveOrUpdate(List<Apprualstatues> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Apprualstatues t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Apprualstatues> getList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
