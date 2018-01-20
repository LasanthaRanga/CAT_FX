package modle;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import pojo.Otheritiscat;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class Authority implements DAO<pojo.Otheritiscat> {

    @Override
    public boolean save(Otheritiscat t) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {
            session.save(t);
            bt.commit();
            return true;
        } catch (Exception e) {
            bt.rollback();
            return false;
        } finally {
            session.close();
        }

    }

    @Override
    public boolean save(List<Otheritiscat> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Otheritiscat t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(List<Otheritiscat> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveOrUpdate(Otheritiscat t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveOrUpdate(List<Otheritiscat> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Otheritiscat t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Otheritiscat> getList() {
        List<pojo.Otheritiscat> list = null;

        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            list = session.createCriteria(pojo.Otheritiscat.class).add(Restrictions.eq("statues", 1)).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return list;
    }

}
