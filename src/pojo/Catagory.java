package pojo;
// Generated Jan 20, 2018 8:59:03 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Catagory generated by hbm2java
 */
public class Catagory  implements java.io.Serializable {


     private Integer idCatagory;
     private String catagoryName;
     private String catagoryNo;
     private String catagorycol;
     private Integer status;
     private Integer syn;
     private Set<UserHasCatagory> userHasCatagories = new HashSet<UserHasCatagory>(0);

    public Catagory() {
    }

    public Catagory(String catagoryName, String catagoryNo, String catagorycol, Integer status, Integer syn, Set<UserHasCatagory> userHasCatagories) {
       this.catagoryName = catagoryName;
       this.catagoryNo = catagoryNo;
       this.catagorycol = catagorycol;
       this.status = status;
       this.syn = syn;
       this.userHasCatagories = userHasCatagories;
    }
   
    public Integer getIdCatagory() {
        return this.idCatagory;
    }
    
    public void setIdCatagory(Integer idCatagory) {
        this.idCatagory = idCatagory;
    }
    public String getCatagoryName() {
        return this.catagoryName;
    }
    
    public void setCatagoryName(String catagoryName) {
        this.catagoryName = catagoryName;
    }
    public String getCatagoryNo() {
        return this.catagoryNo;
    }
    
    public void setCatagoryNo(String catagoryNo) {
        this.catagoryNo = catagoryNo;
    }
    public String getCatagorycol() {
        return this.catagorycol;
    }
    
    public void setCatagorycol(String catagorycol) {
        this.catagorycol = catagorycol;
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
    public Set<UserHasCatagory> getUserHasCatagories() {
        return this.userHasCatagories;
    }
    
    public void setUserHasCatagories(Set<UserHasCatagory> userHasCatagories) {
        this.userHasCatagories = userHasCatagories;
    }




}


