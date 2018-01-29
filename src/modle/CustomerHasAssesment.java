/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.util.List;
import java.util.Set;
import org.hibernate.Session;
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
public class CustomerHasAssesment {

    private String ward;
    private String street;
    private String assesment;

    /**
     * @return the ward
     */
    public String getWard() {
        return ward;
    }

    /**
     * @param ward the ward to set
     */
    public void setWard(String ward) {
        this.ward = ward;
    }

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return the assesment
     */
    public String getAssesment() {
        return assesment;
    }

    /**
     * @param assesment the assesment to set
     */
    public void setAssesment(String assesment) {
        this.assesment = assesment;
    }

    public modle.Customer searchAsesmant() {
        modle.Customer cus = new modle.Customer();

        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();

        try {

//            List<pojo.Assessment> list = session.createCriteria(pojo.Assessment.class).add(Restrictions.eq("assessmentNo", getAssesment())).list();
//            for (Assessment assessment : list) {
//                if (assessment.getStreet().equals(getStreet())) {
//                    Ward ward1 = assessment.getStreet().getWard();
//                    if (ward1.getWardName().equals(getWard())) {
//                        Set<CustomerHasAssessment> customerHasAssessments = assessment.getCustomerHasAssessments();
//                        for (CustomerHasAssessment cha : customerHasAssessments) {
//                            if (cha.getAssessment().getIdAssessment() == assessment.getIdAssessment()) {
//                                
//                                Customer customer = cha.getCustomer();
//
//                                cus = new modle.Customer();
//                                cus.setNic(customer.getNic());
//                                cus.setFullName(customer.getFullName());
//                                cus.setIdCustomer(customer.getIdCustomer());
//
//                                Set<Contact> contacts = customer.getContacts();
//                                for (Contact contact : contacts) {
//                                    if (contact.getStatues() == 1) {
//
//                                        cus.setAddress1(contact.getAddress1());
//                                        cus.setAddress2(contact.getAddress2());
//                                        cus.setAddress3(contact.getAddress3());
//                                        cus.setPhone(contact.getPhone());
//                                        cus.setMobile(contact.getMobile());
//                                        cus.setEmail(contact.getEmail());
//                                        cus.setIdContact(contact.getIdContact());
//                                    }
//                                }
//
//                            }
//                        }
//
//                    }
//                }
//            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return cus;
    }

    
}
