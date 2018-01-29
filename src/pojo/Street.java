package pojo;
// Generated Jan 28, 2018 8:40:19 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Street generated by hbm2java
 */
public class Street  implements java.io.Serializable {


     private Integer idStreet;
     private Ward ward;
     private String streetName;
     private String strretNo;
     private Integer status;
     private Integer syn;
     private Set<Assessment> assessments = new HashSet<Assessment>(0);

    public Street() {
    }

	
    public Street(Ward ward) {
        this.ward = ward;
    }
    public Street(Ward ward, String streetName, String strretNo, Integer status, Integer syn, Set<Assessment> assessments) {
       this.ward = ward;
       this.streetName = streetName;
       this.strretNo = strretNo;
       this.status = status;
       this.syn = syn;
       this.assessments = assessments;
    }
   
    public Integer getIdStreet() {
        return this.idStreet;
    }
    
    public void setIdStreet(Integer idStreet) {
        this.idStreet = idStreet;
    }
    public Ward getWard() {
        return this.ward;
    }
    
    public void setWard(Ward ward) {
        this.ward = ward;
    }
    public String getStreetName() {
        return this.streetName;
    }
    
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
    public String getStrretNo() {
        return this.strretNo;
    }
    
    public void setStrretNo(String strretNo) {
        this.strretNo = strretNo;
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
    public Set<Assessment> getAssessments() {
        return this.assessments;
    }
    
    public void setAssessments(Set<Assessment> assessments) {
        this.assessments = assessments;
    }




}


