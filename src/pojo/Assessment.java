package pojo;
// Generated Jan 18, 2018 5:16:41 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Assessment generated by hbm2java
 */
public class Assessment  implements java.io.Serializable {


     private Integer idAssessment;
     private Street street;
     private String assessmentNo;
     private Integer status;
     private Integer syn;
     private Set<CustomerHasAssessment> customerHasAssessments = new HashSet<CustomerHasAssessment>(0);

    public Assessment() {
    }

	
    public Assessment(Street street) {
        this.street = street;
    }
    public Assessment(Street street, String assessmentNo, Integer status, Integer syn, Set<CustomerHasAssessment> customerHasAssessments) {
       this.street = street;
       this.assessmentNo = assessmentNo;
       this.status = status;
       this.syn = syn;
       this.customerHasAssessments = customerHasAssessments;
    }
   
    public Integer getIdAssessment() {
        return this.idAssessment;
    }
    
    public void setIdAssessment(Integer idAssessment) {
        this.idAssessment = idAssessment;
    }
    public Street getStreet() {
        return this.street;
    }
    
    public void setStreet(Street street) {
        this.street = street;
    }
    public String getAssessmentNo() {
        return this.assessmentNo;
    }
    
    public void setAssessmentNo(String assessmentNo) {
        this.assessmentNo = assessmentNo;
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
    public Set<CustomerHasAssessment> getCustomerHasAssessments() {
        return this.customerHasAssessments;
    }
    
    public void setCustomerHasAssessments(Set<CustomerHasAssessment> customerHasAssessments) {
        this.customerHasAssessments = customerHasAssessments;
    }




}


