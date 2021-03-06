package pojo;
// Generated Mar 14, 2018 12:29:09 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * CusCat generated by hbm2java
 */
public class CusCat  implements java.io.Serializable {


     private Integer idCusCat;
     private String category;
     private Integer status;
     private Integer syn;
     private Set<CustomerHasCusCat> customerHasCusCats = new HashSet<CustomerHasCusCat>(0);

    public CusCat() {
    }

    public CusCat(String category, Integer status, Integer syn, Set<CustomerHasCusCat> customerHasCusCats) {
       this.category = category;
       this.status = status;
       this.syn = syn;
       this.customerHasCusCats = customerHasCusCats;
    }
   
    public Integer getIdCusCat() {
        return this.idCusCat;
    }
    
    public void setIdCusCat(Integer idCusCat) {
        this.idCusCat = idCusCat;
    }
    public String getCategory() {
        return this.category;
    }
    
    public void setCategory(String category) {
        this.category = category;
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
    public Set<CustomerHasCusCat> getCustomerHasCusCats() {
        return this.customerHasCusCats;
    }
    
    public void setCustomerHasCusCats(Set<CustomerHasCusCat> customerHasCusCats) {
        this.customerHasCusCats = customerHasCusCats;
    }




}


