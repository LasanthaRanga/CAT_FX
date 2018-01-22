/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import pojo.Apprualstatues;
import pojo.Otheritiscat;

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

    public ArrayList<modle.Approve> getListByApplication(pojo.Application app) {

        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {

            ArrayList<modle.Approve> appl = new ArrayList<>();

            Criteria cry = session.createCriteria(pojo.Apprualstatues.class);
            List<pojo.Apprualstatues> list = cry.add(Restrictions.eq("application", app)).list();

            for (pojo.Apprualstatues aps : list) {
                // Otheritiscat otc = (pojo.Otheritiscat) session.createCriteria(pojo.Otheritiscat.class).add(Restrictions.eq("idOtheritiscat", aps.getIdOtheritisCat())).uniqueResult();
                Otheritiscat otc = (pojo.Otheritiscat) session.createCriteria(pojo.Otheritiscat.class).add(Restrictions.eq("idOtheritisCat", aps.getIdOtheritisCat())).uniqueResult();
                appl.add(new Approve(aps.getIdApprualStatues(), aps.getStatues(), aps.getUser(), aps.getUser().getIdUser(), app, app.getIdApplication(), otc, otc.getCatname(), aps.getIdOtheritisCat(), aps.getDescription(), aps.getDate()));
            }

            return appl;
        } catch (Exception e) {
            System.out.println("++++++++++++++++++++");
            e.printStackTrace();
            bt.rollback();
            return null;
        } finally {
            session.close();
        }

    }

}
