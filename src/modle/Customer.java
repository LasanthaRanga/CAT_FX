/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import pojo.Assessment;
import pojo.Contact;

import pojo.Street;
import pojo.UserLog;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class Customer {

    /**
     * @return the WASlist
     */
    public static ObservableList getWASlist() {
        return WASlist;
    }

    /**
     * @param aWASlist the WASlist to set
     */
    public static void setWASlist(ObservableList aWASlist) {
        WASlist = aWASlist;
    }

    private Integer idCustomer;
    private UserLog userLog;
    private String nic;
    private String fullName;
    private String customercol;
    private Date regDate;
    private Integer statuesCus;
    private Integer synCus;

    private Integer idContact;
    private pojo.Customer customer;
    private String phone;
    private String mobile;
    private String email;
    private String address1;
    private String address2;
    private String address3;
    private String city;
    private Integer statuesCon;
    private Integer synCon;

    private String selectedStreet;
    private String selectedWard;
    private String assesmentNO;
    private pojo.Assessment asses;
    private Set<Assessment> assessments = new HashSet<Assessment>(0);

    /**
     * @return the selectedStreet
     */
    public String getSelectedStreet() {
        return selectedStreet;
    }

    /**
     * @param selectedStreet the selectedStreet to set
     */
    public void setSelectedStreet(String selectedStreet) {
        this.selectedStreet = selectedStreet;
    }

    /**
     * @return the selectedWard
     */
    public String getSelectedWard() {
        return selectedWard;
    }

    /**
     * @param selectedWard the selectedWard to set
     */
    public void setSelectedWard(String selectedWard) {
        this.selectedWard = selectedWard;
    }

    /**
     * @return the idCustomer
     */
    public Integer getIdCustomer() {
        return idCustomer;
    }

    /**
     * @return the assesmentNO
     */
    public String getAssesmentNO() {
        return assesmentNO;
    }

    /**
     * @param assesmentNO the assesmentNO to set
     */
    public void setAssesmentNO(String assesmentNO) {
        this.assesmentNO = assesmentNO;
    }

    /**
     * @param idCustomer the idCustomer to set
     */
    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    /**
     * @return the userLog
     */
    public UserLog getUserLog() {
        return userLog;
    }

    /**
     * @param userLog the userLog to set
     */
    public void setUserLog(UserLog userLog) {
        this.userLog = userLog;
    }

    /**
     * @return the nic
     */
    public String getNic() {
        return nic;
    }

    /**
     * @param nic the nic to set
     */
    public void setNic(String nic) {
        this.nic = nic;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the customercol
     */
    public String getCustomercol() {
        return customercol;
    }

    /**
     * @param customercol the customercol to set
     */
    public void setCustomercol(String customercol) {
        this.customercol = customercol;
    }

    /**
     * @return the regDate
     */
    public Date getRegDate() {
        return regDate;
    }

    /**
     * @param regDate the regDate to set
     */
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    /**
     * @return the statuesCus
     */
    public Integer getStatuesCus() {
        return statuesCus;
    }

    /**
     * @param statuesCus the statuesCus to set
     */
    public void setStatuesCus(Integer statuesCus) {
        this.statuesCus = statuesCus;
    }

    /**
     * @return the synCus
     */
    public Integer getSynCus() {
        return synCus;
    }

    /**
     * @param synCus the synCus to set
     */
    public void setSynCus(Integer synCus) {
        this.synCus = synCus;
    }

    /**
     * @return the idContact
     */
    public Integer getIdContact() {
        return idContact;
    }

    /**
     * @param idContact the idContact to set
     */
    public void setIdContact(Integer idContact) {
        this.idContact = idContact;
    }

    /**
     * @return the customer
     */
    public pojo.Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(pojo.Customer customer) {
        this.customer = customer;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the address1
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * @param address1 the address1 to set
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     * @return the address2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * @param address2 the address2 to set
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * @return the address3
     */
    public String getAddress3() {
        return address3;
    }

    /**
     * @param address3 the address3 to set
     */
    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the statuesCon
     */
    public Integer getStatuesCon() {
        return statuesCon;
    }

    /**
     * @param statuesCon the statuesCon to set
     */
    public void setStatuesCon(Integer statuesCon) {
        this.statuesCon = statuesCon;
    }

    /**
     * @return the synCon
     */
    public Integer getSynCon() {
        return synCon;
    }

    /**
     * @param synCon the synCon to set
     */
    public void setSynCon(Integer synCon) {
        this.synCon = synCon;
    }

    public boolean saveCustomer() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {

            Criteria criteria = session
                    .createCriteria(pojo.Customer.class)
                    .setProjection(Projections.max("idCustomer"));
            Integer maxAge = (Integer) criteria.uniqueResult();

            System.out.println(maxAge);

            pojo.Customer cus = new pojo.Customer();
            cus.setUserLog(modle.AuthUser.getUserLog());
            String nic1 = getNic();

            cus.setFullName(getFullName());
            cus.setStatues(1);
            cus.setSyn(1);
            cus.setRegDate(new Date());

            Serializable savedcus = session.save(cus);

            Contact contact = new pojo.Contact();
            contact.setCustomer(cus);
            contact.setAddress1(getAddress1());
            contact.setAddress2(getAddress2());
            contact.setAddress3(getAddress3());
            contact.setCity(getCity());
            contact.setPhone(getPhone());
            contact.setMobile(getMobile());
            contact.setEmail(getEmail());
            contact.setStatues(1);
            contact.setSyn(1);

            Assessment assessment = new pojo.Assessment();

            pojo.Ward ward = (pojo.Ward) session.createCriteria(pojo.Ward.class).add(Restrictions.eq("wardName", getSelectedWard())).uniqueResult();
            System.out.println(ward.getWardName());
            Criteria cr = session.createCriteria(pojo.Street.class);
            cr.add(Restrictions.eq("streetName", getSelectedStreet()));
            pojo.Street street = (pojo.Street) cr.add(Restrictions.eq("ward", ward)).uniqueResult();

            System.out.println(street.getStreetName());

            assessment.setStreet(street);
            assessment.setAssessmentNo(getAssesmentNO());
            assessment.setStatus(1);
            assessment.setSyn(1);
            assessment.setCustomer(cus);

            session.save(assessment);

            session.save(contact);

            if (getNic().length() < 9) {
                cus.setNic("NIC " + cus.getIdCustomer());
            } else {
                cus.setNic(getNic());
            }

            session.update(cus);
            bt.commit();
            return true;

        } catch (Exception e) {
            bt.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }

    }

    public void updateCustomer() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {

            pojo.Customer upcus = (pojo.Customer) session.createCriteria(pojo.Customer.class).add(Restrictions.eq("idCustomer", getIdCustomer())).uniqueResult();

            upcus.setFullName(getFullName());

            String selectedWard1 = getSelectedWard();
            
            
            
          
            

            upcus.setStatues(1);
            upcus.setSyn(1);

            upcus.setNic(getNic());
            
            

            session.update(upcus);

            pojo.Contact upcon = (pojo.Contact) session.createCriteria(pojo.Contact.class).add(Restrictions.eq("idContact", getIdContact())).uniqueResult();
            
           
            
            upcon.setAddress1(getAddress1());
            upcon.setAddress2(getAddress2());
            upcon.setAddress3(getAddress3());
            upcon.setCity(getCity());
            upcon.setPhone(getPhone());
            upcon.setMobile(getMobile());
            upcon.setEmail(getEmail());
            upcon.setStatues(1);
            upcon.setSyn(1);

//            Set<CustomerHasAssessment> chas = upcus.getCustomerHasAssessments();
//            for (CustomerHasAssessment cha : chas) {
//                Assessment assessment = cha.getAssessment();
//                String asno = getAssesmentNO();
//                if (assessment.getAssessmentNo().equals(asno)) {
//                    assessment.setAssessmentNo(asno);
//                    session.update(assessment);
//                }
//
//            }
            session.update(upcon);

            bt.commit();

        } catch (Exception e) {
            e.printStackTrace();
            bt.rollback();
        } finally {
            session.close();
        }

    }

    public void deactiveCustomer() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            pojo.Customer upcus = (pojo.Customer) session.createCriteria(pojo.Customer.class).add(Restrictions.eq("idCustomer", getIdCustomer())).uniqueResult();
            upcus.setStatues(0);
            session.update(upcus);
            session.beginTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public pojo.Customer searchCustomer(String nic, String fname) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            Criteria cry = session.createCriteria(pojo.Customer.class);
            cry.add(Restrictions.eq("nic", nic));
            cry.add(Restrictions.eq("fullName", fname));
            pojo.Customer name = (pojo.Customer) cry.uniqueResult();
            return name;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }

    }

    private static ObservableList WASlist = FXCollections.observableArrayList();

    public List<Customer> searchCustomer(String fname) {
        List<Customer> clist = new ArrayList<Customer>();
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            List<pojo.Customer> list = session.createCriteria(pojo.Customer.class).add(Restrictions.and(Restrictions.eq("fullName", fname), Restrictions.eq("statues", 1))).list();

            for (pojo.Customer c : list) {
                modle.Customer cus = new modle.Customer();

                if (c != null) {
                    System.out.println("FULNAME TIBBA");

                    cus.setFullName(c.getFullName());
                    cus.setNic(c.getNic());
                    cus.setIdCustomer(c.getIdCustomer());

                    cus.setAssessments(c.getAssessments());

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
                    Set<Assessment> assessments1 = c.getAssessments();
                    getWASlist().clear();
                    for (Assessment assessment : assessments1) {
                        System.out.println("Asessmant Set Una");
                        setAsses(assessment);
                        WSA wsa = new modle.WSA(assessment.getStreet().getWard().getIdWard(), assessment.getStreet().getWard().getWardName(), assessment.getStreet().getIdStreet(), assessment.getStreet().getStreetName(), assessment.getIdAssessment(), assessment.getAssessmentNo());
                        getWASlist().add(wsa);
                    }

                }
                clist.add(cus);
            }
            return clist;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public modle.Customer searchCustomerByID(int idCus) {

        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            pojo.Customer c = (pojo.Customer) session.createCriteria(pojo.Customer.class).add(Restrictions.and(Restrictions.eq("idCustomer", idCus), Restrictions.eq("statues", 1))).uniqueResult();

            modle.Customer cus = new modle.Customer();

            if (c != null) {

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
                    }
                }
                Set<Assessment> assessments1 = c.getAssessments();
                for (Assessment assessment : assessments1) {
                    setAsses(assessment);
                    WSA wsa = new modle.WSA(assessment.getStreet().getWard().getIdWard(), assessment.getStreet().getWard().getWardName(), assessment.getStreet().getIdStreet(), assessment.getStreet().getStreetName(), assessment.getIdAssessment(), assessment.getAssessmentNo());
                    getWASlist().add(wsa);
                }
                //asesmant table

                //asesmant table
            }

            return cus;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public modle.Customer searchCustomerByNic() {
        modle.Customer cus = new modle.Customer();

        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            pojo.Customer c = (pojo.Customer) session.createCriteria(pojo.Customer.class).add(Restrictions.and(Restrictions.eq("nic", getNic()), Restrictions.eq("statues", 1))).uniqueResult();

            if (c != null) {

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
                    }
                }

                WASlist.clear();

                //       }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return cus;
    }

    public modle.Customer searchCustomerFullName() {
        modle.Customer cus = new modle.Customer();

        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            pojo.Customer c = (pojo.Customer) session.createCriteria(pojo.Customer.class).add(Restrictions.and(Restrictions.eq("fullName", getFullName()), Restrictions.eq("statues", 1))).uniqueResult();

            if (c != null) {

                cus.setFullName(c.getFullName());
                cus.setNic(c.getNic());
                cus.setIdCustomer(c.getIdCustomer());
                cus.setAssessments(c.getAssessments());

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

    public ArrayList<String> getCustomerFnameList() {
        ArrayList<String> ar = new ArrayList();
        HashMap<Integer, String> arr = new HashMap<>();
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        // System.out.println("LISR Create");
        try {
            List<pojo.Customer> c = session.createCriteria(pojo.Customer.class).add(Restrictions.eq("statues", 1)).list();

            for (pojo.Customer customer1 : c) {
                arr.put(customer1.getIdCustomer(), customer1.getFullName());
                ar.add(customer1.getFullName());
                // System.out.println("ADD LIST");
                // System.out.println(customer1.getFullName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return ar;
    }

    public pojo.Customer getById(int id) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            return (pojo.Customer) session.createCriteria(pojo.Customer.class).add(Restrictions.eq("idCustomer", id)).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public String genarateAssesmant() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {

            Criteria c = session.createCriteria(pojo.Assessment.class);
            c.setProjection(Projections.max("idAssessment"));
            Integer maxAge = (Integer) c.uniqueResult();
            System.out.println(maxAge);
            return "none " + (maxAge + 1);

        } catch (Exception e) {
            e.printStackTrace();
            return "none";
        } finally {
            session.close();
        }

    }

    public pojo.Assessment getAssesmantPojo(String ward, String strreet, String assesmantNo) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        pojo.Assessment ass = null;
        try {

            pojo.Ward pward = (pojo.Ward) session.createCriteria(pojo.Ward.class).add(Restrictions.eq("wardName", ward)).uniqueResult();

            Set<Street> streets = pward.getStreets();

            for (Street street : streets) {
                if (street.getStreetName().equals(strreet)) {
                    Set<Assessment> assessments = street.getAssessments();
                    for (Assessment assessment : assessments) {
                        String assessmentNo = assessment.getAssessmentNo();
                        if (assesmantNo.equals(assesmantNo)) {
                            ass = assessment;
                            break;
                        }
                    }
                }
            }
            return ass;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }

    }

    /**
     * @return the assessments
     */
    public Set<Assessment> getAssessments() {
        return assessments;
    }

    /**
     * @param assessments the assessments to set
     */
    public void setAssessments(Set<Assessment> assessments) {
        this.assessments = assessments;
    }

    /**
     * @return the asses
     */
    public pojo.Assessment getAsses() {
        return asses;
    }

    /**
     * @param asses the asses to set
     */
    public void setAsses(pojo.Assessment asses) {
        this.asses = asses;
    }

}
