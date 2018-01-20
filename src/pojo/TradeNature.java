package pojo;
// Generated Jan 20, 2018 8:59:03 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * TradeNature generated by hbm2java
 */
public class TradeNature  implements java.io.Serializable {


     private Integer idTradeNature;
     private TradeType tradeType;
     private String natureNo;
     private String nature;
     private Integer status;
     private Integer syn;
     private Set<Application> applications = new HashSet<Application>(0);
     private Set<SubNature> subNatures = new HashSet<SubNature>(0);

    public TradeNature() {
    }

	
    public TradeNature(TradeType tradeType) {
        this.tradeType = tradeType;
    }
    public TradeNature(TradeType tradeType, String natureNo, String nature, Integer status, Integer syn, Set<Application> applications, Set<SubNature> subNatures) {
       this.tradeType = tradeType;
       this.natureNo = natureNo;
       this.nature = nature;
       this.status = status;
       this.syn = syn;
       this.applications = applications;
       this.subNatures = subNatures;
    }
   
    public Integer getIdTradeNature() {
        return this.idTradeNature;
    }
    
    public void setIdTradeNature(Integer idTradeNature) {
        this.idTradeNature = idTradeNature;
    }
    public TradeType getTradeType() {
        return this.tradeType;
    }
    
    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
    }
    public String getNatureNo() {
        return this.natureNo;
    }
    
    public void setNatureNo(String natureNo) {
        this.natureNo = natureNo;
    }
    public String getNature() {
        return this.nature;
    }
    
    public void setNature(String nature) {
        this.nature = nature;
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
    public Set<Application> getApplications() {
        return this.applications;
    }
    
    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }
    public Set<SubNature> getSubNatures() {
        return this.subNatures;
    }
    
    public void setSubNatures(Set<SubNature> subNatures) {
        this.subNatures = subNatures;
    }




}


