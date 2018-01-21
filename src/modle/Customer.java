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
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import pojo.Contact;
import pojo.UserLog;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class Customer {

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

            pojo.Customer cus = new pojo.Customer();
            cus.setUserLog(modle.Log_User.getLogUser());
            String nic1 = getNic();

            if (nic1 == null || nic1.length() < 5) {
                double random = Math.random();
                String ss = random + "R";
                System.out.println(ss);
                cus.setNic(ss);
            } else {
                cus.setNic(getNic());
            }

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

            session.save(contact);
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
        try {

            pojo.Customer upcus = (pojo.Customer) session.createCriteria(pojo.Customer.class).add(Restrictions.eq("idCustomer", getIdCustomer())).uniqueResult();

            upcus.setFullName(getFullName());

            String nic1 = getNic();

            if (nic1 == null || nic1.length() < 5) {
                double random = Math.random();
                String ss = random + "R";
                System.out.println(ss);
                upcus.setNic(ss);
            } else {
                upcus.setNic(getNic());
            }

            upcus.setStatues(1);
            upcus.setSyn(1);

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
            session.update(upcon);

            session.beginTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
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

    public void searchCustomer() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {

        } catch (Exception e) {
            e.printStackTrace();
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

}
