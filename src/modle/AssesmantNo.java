/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.util.List;
import java.util.Set;
import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import pojo.Assessment;
import pojo.Contact;
import pojo.Customer;
import pojo.Street;
import pojo.Ward;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class AssesmantNo {

    public static modle.Customer searchByAssesmantNO(String ward, String street, String assesmant) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        modle.Customer cus = null;
        try {

            pojo.Ward w = (pojo.Ward) session.createCriteria(pojo.Ward.class).add(Restrictions.eq("wardName", ward)).uniqueResult();

            if (w != null) {

                Set<Street> streets = w.getStreets();
                for (Street street1 : streets) {

                    if (street1.getStreetName().equals(street)) {
                        //  System.out.println("Samana Una");
                        Set<Assessment> assessments = street1.getAssessments();
                        for (Assessment assessment : assessments) {

                            if (assessment.getAssessmentNo() != null) {
                                if (assessment.getAssessmentNo().equals(assesmant)) {
                                    Customer c = assessment.getCustomer();

                                    cus = new modle.Customer();
                                    StaticBadu.setAssessment(assessment);
                                    //    System.out.println("FULNAME TIBBA");

                                    cus.setFullName(c.getFullName());
                                    cus.setNic(c.getNic());
                                    cus.setIdCustomer(c.getIdCustomer());

                                    Set<Contact> contacts = c.getContacts();
                                    for (Contact contact : contacts) {
                                        if (contact.getStatues() == 1) {
                                            cus.setAddress1(contact.getAddress1());
                                            cus.setAddress2(contact.getAddress2());
                                            cus.setAddress3(contact.getAddress3());
                                            cus.setCity(contact.getCity());
                                            cus.setEmail(contact.getEmail());
                                            cus.setPhone(contact.getPhone());
                                            cus.setMobile(contact.getMobile());
                                            cus.setIdContact(contact.getIdContact());
                                            System.out.println("");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return cus;
    }

    public static void searchByAssesmantNOList(String ward, String street, String assesmant) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            session.close();
        }
    }

    public static List<pojo.Assessment> getAssessmantList(String ward, String street) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            Ward w = (pojo.Ward) session.createCriteria(pojo.Ward.class).add(Restrictions.eq("wardName", ward)).uniqueResult();
            Criteria cryStreet = session.createCriteria(pojo.Street.class);
            cryStreet.add(Restrictions.eq("ward", w));
            Street s = (pojo.Street) cryStreet.add(Restrictions.eq("streetName", street)).uniqueResult();
            List<pojo.Assessment> list = session.createCriteria(pojo.Assessment.class).add(Restrictions.eq("street", s)).list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public static List<pojo.Assessment> getAssessmantListFilter(String ward, String street, String assessmant) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            Ward w = (pojo.Ward) session.createCriteria(pojo.Ward.class).add(Restrictions.eq("wardName", ward)).uniqueResult();
            Criteria cryStreet = session.createCriteria(pojo.Street.class);
            cryStreet.add(Restrictions.eq("ward", w));
            Street s = (pojo.Street) cryStreet.add(Restrictions.eq("streetName", street)).uniqueResult();

            Criteria cryAssess = session.createCriteria(pojo.Assessment.class);
            cryAssess.add(Restrictions.eq("street", s));
            List<pojo.Assessment> list = cryAssess.add(Restrictions.like("assessmentNo", assessmant, MatchMode.START)).list();

            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public static List<pojo.Assessment> getAssessmantByWSA(String w, String s, String a) {

        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            Ward ward = (pojo.Ward) session.createCriteria(pojo.Ward.class).add(Restrictions.eq("wardName", w)).uniqueResult();
            Criteria cryStreet = session.createCriteria(pojo.Street.class);
            cryStreet.add(Restrictions.eq("ward", ward));
            cryStreet.add(Restrictions.eq("streetName", s));
            Street street = (pojo.Street) cryStreet.uniqueResult();

            Criteria cryAssess = session.createCriteria(pojo.Assessment.class);
            cryAssess.add(Restrictions.eq("street", street));
            List<pojo.Assessment> list = cryAssess.add(Restrictions.eq("assessmentNo", a)).list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }

    }

}
