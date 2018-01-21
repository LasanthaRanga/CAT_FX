package pojo;
// Generated Jan 21, 2018 11:09:37 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Ward generated by hbm2java
 */
public class Ward  implements java.io.Serializable {


     private Integer idWard;
     private String wardNo;
     private String wardName;
     private String wardcol;
     private Integer status;
     private Integer syn;
     private Set<Street> streets = new HashSet<Street>(0);

    public Ward() {
    }

    public Ward(String wardNo, String wardName, String wardcol, Integer status, Integer syn, Set<Street> streets) {
       this.wardNo = wardNo;
       this.wardName = wardName;
       this.wardcol = wardcol;
       this.status = status;
       this.syn = syn;
       this.streets = streets;
    }
   
    public Integer getIdWard() {
        return this.idWard;
    }
    
    public void setIdWard(Integer idWard) {
        this.idWard = idWard;
    }
    public String getWardNo() {
        return this.wardNo;
    }
    
    public void setWardNo(String wardNo) {
        this.wardNo = wardNo;
    }
    public String getWardName() {
        return this.wardName;
    }
    
    public void setWardName(String wardName) {
        this.wardName = wardName;
    }
    public String getWardcol() {
        return this.wardcol;
    }
    
    public void setWardcol(String wardcol) {
        this.wardcol = wardcol;
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
    public Set<Street> getStreets() {
        return this.streets;
    }
    
    public void setStreets(Set<Street> streets) {
        this.streets = streets;
    }




}


