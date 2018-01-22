package pojo;
// Generated Jan 22, 2018 1:40:54 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Customer generated by hbm2java
 */
public class Customer  implements java.io.Serializable {


     private Integer idCustomer;
     private UserLog userLog;
     private String nic;
     private String fullName;
     private String customercol;
     private Date regDate;
     private Integer statues;
     private Integer syn;
     private Set<CustomerHasTradeLicense> customerHasTradeLicenses = new HashSet<CustomerHasTradeLicense>(0);
     private Set<CustomerHasAssessment> customerHasAssessments = new HashSet<CustomerHasAssessment>(0);
     private Set<Application> applications = new HashSet<Application>(0);
     private Set<Contact> contacts = new HashSet<Contact>(0);

    public Customer() {
    }

	
    public Customer(UserLog userLog) {
        this.userLog = userLog;
    }
    public Customer(UserLog userLog, String nic, String fullName, String customercol, Date regDate, Integer statues, Integer syn, Set<CustomerHasTradeLicense> customerHasTradeLicenses, Set<CustomerHasAssessment> customerHasAssessments, Set<Application> applications, Set<Contact> contacts) {
       this.userLog = userLog;
       this.nic = nic;
       this.fullName = fullName;
       this.customercol = customercol;
       this.regDate = regDate;
       this.statues = statues;
       this.syn = syn;
       this.customerHasTradeLicenses = customerHasTradeLicenses;
       this.customerHasAssessments = customerHasAssessments;
       this.applications = applications;
       this.contacts = contacts;
    }
   
    public Integer getIdCustomer() {
        return this.idCustomer;
    }
    
    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }
    public UserLog getUserLog() {
        return this.userLog;
    }
    
    public void setUserLog(UserLog userLog) {
        this.userLog = userLog;
    }
    public String getNic() {
        return this.nic;
    }
    
    public void setNic(String nic) {
        this.nic = nic;
    }
    public String getFullName() {
        return this.fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getCustomercol() {
        return this.customercol;
    }
    
    public void setCustomercol(String customercol) {
        this.customercol = customercol;
    }
    public Date getRegDate() {
        return this.regDate;
    }
    
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
    public Integer getStatues() {
        return this.statues;
    }
    
    public void setStatues(Integer statues) {
        this.statues = statues;
    }
    public Integer getSyn() {
        return this.syn;
    }
    
    public void setSyn(Integer syn) {
        this.syn = syn;
    }
    public Set<CustomerHasTradeLicense> getCustomerHasTradeLicenses() {
        return this.customerHasTradeLicenses;
    }
    
    public void setCustomerHasTradeLicenses(Set<CustomerHasTradeLicense> customerHasTradeLicenses) {
        this.customerHasTradeLicenses = customerHasTradeLicenses;
    }
    public Set<CustomerHasAssessment> getCustomerHasAssessments() {
        return this.customerHasAssessments;
    }
    
    public void setCustomerHasAssessments(Set<CustomerHasAssessment> customerHasAssessments) {
        this.customerHasAssessments = customerHasAssessments;
    }
    public Set<Application> getApplications() {
        return this.applications;
    }
    
    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }
    public Set<Contact> getContacts() {
        return this.contacts;
    }
    
    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }




}


