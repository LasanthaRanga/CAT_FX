/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class Department implements DAO<pojo.Department> {

    @Override
    public boolean save(pojo.Department t) {
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
    public boolean save(List<pojo.Department> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(pojo.Department t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(List<pojo.Department> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveOrUpdate(pojo.Department t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveOrUpdate(List<pojo.Department> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(pojo.Department t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<pojo.Department> getList() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        List<pojo.Department> list = null;
        try {
            list = session.createCriteria(pojo.Department.class).add(Restrictions.eq("statues", 1)).list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public pojo.Department getDepartmentByDepartmentName(String DepartmentName) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        pojo.Department dep = null;
        try {
            dep = (pojo.Department) session.createCriteria(pojo.Department.class).add(Restrictions.eq("department", DepartmentName)).uniqueResult();
            return dep;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

}
