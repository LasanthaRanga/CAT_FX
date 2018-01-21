/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class TradeType {

    public List<pojo.TradeType> loadTreadType() {
        Session ses = conn.NewHibernateUtil.getSessionFactory().openSession();
        List<pojo.TradeType> list = null;
        try {
            list = ses.createCriteria(pojo.TradeType.class).add(Restrictions.eq("status", 1)).list();
        } catch (Exception e) {
        } finally {
            ses.close();
        }
        return list;

    }

    public pojo.TradeType loadTreadType(String tn) {
        Session ses = conn.NewHibernateUtil.getSessionFactory().openSession();
        pojo.TradeType tt = null;
        try {
            tt = (pojo.TradeType)ses.createCriteria(pojo.TradeType.class).add(Restrictions.eq("typeName", tn)).uniqueResult();
        } catch (Exception e) {
        } finally {
            ses.close();
        }
        return tt;

    }
    
     public pojo.TradeType loadTreadType(int idTradeType) {
        Session ses = conn.NewHibernateUtil.getSessionFactory().openSession();
        pojo.TradeType tt = null;
        try {
            tt = (pojo.TradeType)ses.createCriteria(pojo.TradeType.class).add(Restrictions.eq("idTradeType", idTradeType)).uniqueResult();
        } catch (Exception e) {
        } finally {
            ses.close();
        }
        return tt;

    }
     
      public pojo.TradeType loadTreadTypeByTypeName(String tradtype) {
        Session ses = conn.NewHibernateUtil.getSessionFactory().openSession();
        pojo.TradeType tt = null;
        try {
            tt = (pojo.TradeType)ses.createCriteria(pojo.TradeType.class).add(Restrictions.eq("typeName", tradtype)).uniqueResult();
        } catch (Exception e) {
        } finally {
            ses.close();
        }
        return tt;

    }

}
