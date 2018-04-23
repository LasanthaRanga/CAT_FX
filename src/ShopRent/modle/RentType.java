/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShopRent.modle;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pojo.SrRentalType;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class RentType implements SUD<pojo.SrRentalType> {

    @Override
    public boolean isExist(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(SrRentalType t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(SrRentalType t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deactiv(SrRentalType t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SrRentalType> getList() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            List list = session.createCriteria(pojo.SrRentalType.class).list();
            return list;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public SrRentalType getT_By_Id(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SrRentalType getT_By_name(String name) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            return (pojo.SrRentalType) session.createCriteria(pojo.SrRentalType.class).add(Restrictions.eq("RType", name)).uniqueResult();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            session.close();
        }
    }

}
