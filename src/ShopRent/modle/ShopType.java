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
import pojo.SrShopType;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class ShopType implements SUD<pojo.SrShopType> {

    @Override
    public boolean isExist(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(SrShopType t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(SrShopType t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deactiv(SrShopType t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SrShopType> getList() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            List list = session.createCriteria(pojo.SrShopType.class).list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }

    }

    @Override
    public SrShopType getT_By_Id(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SrShopType getT_By_name(String name) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            return (pojo.SrShopType) session.createCriteria(pojo.SrShopType.class).add(Restrictions.eq("shopType", name)).uniqueResult();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            session.close();
        }
    }

}
