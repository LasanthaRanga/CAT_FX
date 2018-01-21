package pojo;
// Generated Jan 21, 2018 11:09:37 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * UserLog generated by hbm2java
 */
public class UserLog  implements java.io.Serializable {


     private Integer idUserLog;
     private User user;
     private Date outTyme;
     private Date inTyme;
     private Integer status;
     private Integer syn;
     private Set<Payment> payments = new HashSet<Payment>(0);
     private Set<Customer> customers = new HashSet<Customer>(0);
     private Set<Application> applications = new HashSet<Application>(0);

    public UserLog() {
    }

	
    public UserLog(User user) {
        this.user = user;
    }
    public UserLog(User user, Date outTyme, Date inTyme, Integer status, Integer syn, Set<Payment> payments, Set<Customer> customers, Set<Application> applications) {
       this.user = user;
       this.outTyme = outTyme;
       this.inTyme = inTyme;
       this.status = status;
       this.syn = syn;
       this.payments = payments;
       this.customers = customers;
       this.applications = applications;
    }
   
    public Integer getIdUserLog() {
        return this.idUserLog;
    }
    
    public void setIdUserLog(Integer idUserLog) {
        this.idUserLog = idUserLog;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public Date getOutTyme() {
        return this.outTyme;
    }
    
    public void setOutTyme(Date outTyme) {
        this.outTyme = outTyme;
    }
    public Date getInTyme() {
        return this.inTyme;
    }
    
    public void setInTyme(Date inTyme) {
        this.inTyme = inTyme;
    }
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getSyn() {
        return this.syn;
    }
    
    public void setSyn(Integer syn) {
        this.syn = syn;
    }
    public Set<Payment> getPayments() {
        return this.payments;
    }
    
    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }
    public Set<Customer> getCustomers() {
        return this.customers;
    }
    
    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }
    public Set<Application> getApplications() {
        return this.applications;
    }
    
    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }




}


