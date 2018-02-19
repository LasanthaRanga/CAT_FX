/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import pojo.TaxRate;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class TaxCal {

    public static List<pojo.TaxRate> loadTaxRate() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {

            List<pojo.TaxRate> list = session.createCriteria(pojo.TaxRate.class).add(Restrictions.eq("status", 1)).addOrder(Order.asc("idTaxRate")).list();
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }

    }

    public static double getTaxValue(Double alocation, String TradeType) {
        List<TaxRate> loadTaxRate = loadTaxRate();
        double rat = 0;
        if (loadTaxRate != null) {
            for (TaxRate taxRate : loadTaxRate) {
                if (taxRate.getTaxType().equals(TradeType)) {
                    Double min = taxRate.getMin();
                    Double max = taxRate.getMax();
                    if (alocation > min && alocation <= max) {
                        rat = taxRate.getRate();
                        break;
                    }
                }
            }
        }
        return rat;
    }
}
