/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pojo.Assessment;
import pojo.Street;
import pojo.Ward;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class Strret implements DAO<pojo.Street> {

    /**
     * @return the idStrret
     */
    public int getIdStrret() {
        return idStrret;
    }

    /**
     * @param idStrret the idStrret to set
     */
    public void setIdStrret(int idStrret) {
        this.idStrret = idStrret;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the streetName
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * @param streetName the streetName to set
     */
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    /**
     * @return the syn
     */
    public int getSyn() {
        return syn;
    }

    /**
     * @param syn the syn to set
     */
    public void setSyn(int syn) {
        this.syn = syn;
    }

    /**
     * @return the idWard
     */
    public int getIdWard() {
        return idWard;
    }

    /**
     * @param idWard the idWard to set
     */
    public void setIdWard(int idWard) {
        this.idWard = idWard;
    }

    private int idStrret;
    private int status;
    private String streetName;
    private int syn;
    private int idWard;

    public void saveStreet() {
        Session ses = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            pojo.Ward ward = (pojo.Ward) ses.createCriteria(pojo.Ward.class).add(Restrictions.and(Restrictions.eq("idWard", getIdWard()), Restrictions.eq("status", 1))).uniqueResult();
            Street street = new pojo.Street();
            street.setWard(ward);
            street.setStreetName(getStreetName());
            street.setStatus(1);
            street.setSyn(1);
            ses.save(street);
            ses.beginTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ses.close();
        }
    }

    public List<pojo.Street> loadStrretTable() {
        List<pojo.Street> list = null;
        Session ses = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            pojo.Ward ward = (pojo.Ward) ses.createCriteria(pojo.Ward.class).add(Restrictions.and(Restrictions.eq("idWard", getIdWard()), Restrictions.eq("status", 1))).uniqueResult();
            list = ses.createCriteria(pojo.Street.class).add(Restrictions.and(Restrictions.eq("ward", ward), Restrictions.eq("status", 1))).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ses.close();
        }

        return list;
    }

    public void updateStrret() {
        Session ses = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            pojo.Street street = (pojo.Street) ses.createCriteria(pojo.Street.class).add(Restrictions.and(Restrictions.eq("idStreet", getIdStrret()), Restrictions.eq("status", 1))).uniqueResult();
            street.setStreetName(getStreetName());
            ses.update(street);
            ses.beginTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ses.close();
        }

    }

    public void updateDeactive() {
        Session ses = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            pojo.Street street = (pojo.Street) ses.createCriteria(pojo.Street.class).add(Restrictions.and(Restrictions.eq("idStreet", getIdStrret()), Restrictions.eq("status", 1))).uniqueResult();
            street.setStatus(0);
            ses.update(street);
            ses.beginTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ses.close();
        }

    }

    @Override
    public boolean save(Street t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(List<Street> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Street t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(List<Street> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveOrUpdate(Street t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveOrUpdate(List<Street> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Street t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Street> getList() {
        List<pojo.Street> list = null;
        Session ses = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            pojo.Ward ward = (pojo.Ward) ses.createCriteria(pojo.Ward.class).add(Restrictions.and(Restrictions.eq("idWard", getIdWard()), Restrictions.eq("status", 1))).uniqueResult();
            list = ses.createCriteria(pojo.Street.class).add(Restrictions.and(Restrictions.eq("ward", ward), Restrictions.eq("status", 1))).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ses.close();
        }
        return list;
    }

    public List<pojo.Street> getStreetsByWard() {
        List<pojo.Street> list = null;
        Session ses = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            pojo.Ward ward = (pojo.Ward) ses.createCriteria(pojo.Ward.class).add(Restrictions.and(Restrictions.eq("idWard", getIdWard()), Restrictions.eq("status", 1))).uniqueResult();
            list = ses.createCriteria(pojo.Street.class).add(Restrictions.and(Restrictions.eq("ward", ward), Restrictions.eq("status", 1))).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ses.close();
        }
        return list;

    }

    public pojo.Street getStreetsByStreetName(String strret) {
        Session ses = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            Street stre = (pojo.Street) ses.createCriteria(pojo.Street.class).add(Restrictions.eq("streetName", strret)).uniqueResult();
            return stre;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ses.close();
        }
        return null;

    }

    public pojo.Street getStreetsByStreetNameAndWard(String strret, pojo.Ward ward) {
        Session ses = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {

            Criteria cry = ses.createCriteria(pojo.Street.class);
            cry.add(Restrictions.eq("ward", ward));
            Street stre = (pojo.Street) cry.add(Restrictions.eq("streetName", strret)).uniqueResult();
            return stre;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ses.close();
        }
        return null;

    }

//    public void getSWbyAssesmant(pojo.CustomerHasAssessment cha) {
//
//        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
//
//        try {
//            CustomerHasAssessment cushas = (pojo.CustomerHasAssessment) session.createCriteria(pojo.CustomerHasAssessment.class).add(Restrictions.eq("idCustomerHasAssessmentcol", cha.getIdCustomerHasAssessmentcol())).uniqueResult();
//            Assessment assessment = cushas.getAssessment();
//            Street street = assessment.getStreet();
//            Ward ward = street.getWard();
//
//            StaticBadu.setAssessment(assessment);
//            StaticBadu.setStreet(street);
//            StaticBadu.setWard(ward);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//
//    }
}
