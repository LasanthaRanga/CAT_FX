/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pojo.ChequeBank;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class ChqueBank {

    public ObservableList getBankList() {
        ObservableList<String> bankList = FXCollections.observableArrayList();
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            List<pojo.ChequeBank> list = session.createCriteria(pojo.ChequeBank.class).list();
            for (ChequeBank chequeBank : list) {
                bankList.add(chequeBank.getBankName());
            }
            return bankList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public pojo.ChequeBank getSelecteChequeBank(String bankName) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            ChequeBank cb = (pojo.ChequeBank) session.createCriteria(pojo.ChequeBank.class).add(Restrictions.eq("bankName", bankName)).uniqueResult();
            return cb;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public void getSelecteChequeBank() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
