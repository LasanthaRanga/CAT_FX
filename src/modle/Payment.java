/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

/**
 *
 * @author suhada
 */
public class Payment implements DAO<pojo.Payment>{

    @Override
    public boolean save(pojo.Payment t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(List<pojo.Payment> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(pojo.Payment t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(List<pojo.Payment> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveOrUpdate(pojo.Payment t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveOrUpdate(List<pojo.Payment> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(pojo.Payment t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<pojo.Payment> getList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getNextReceiptNo(){
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            List<pojo.Payment> list = session.createCriteria(pojo.Payment.class).setMaxResults(1).addOrder(Order.desc("idPayment")).list();
            if(list.size()>0){
                try {
                    return Integer.parseInt(list.get(0).getReceiptNo())+1;
                } catch (Exception e) {
                    return -1;
                }
            }else{
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            session.close();
        }
    }
    
}
