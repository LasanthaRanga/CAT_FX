package pojo;
// Generated Jan 19, 2018 11:37:25 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * BankInfo generated by hbm2java
 */
public class BankInfo  implements java.io.Serializable {


     private Integer idBankInfo;
     private String bankName;
     private String acountNo;
     private String acountName;
     private Double currentBallance;
     private Integer status;
     private Integer syn;
     private Set<CashFlow> cashFlows = new HashSet<CashFlow>(0);

    public BankInfo() {
    }

    public BankInfo(String bankName, String acountNo, String acountName, Double currentBallance, Integer status, Integer syn, Set<CashFlow> cashFlows) {
       this.bankName = bankName;
       this.acountNo = acountNo;
       this.acountName = acountName;
       this.currentBallance = currentBallance;
       this.status = status;
       this.syn = syn;
       this.cashFlows = cashFlows;
    }
   
    public Integer getIdBankInfo() {
        return this.idBankInfo;
    }
    
    public void setIdBankInfo(Integer idBankInfo) {
        this.idBankInfo = idBankInfo;
    }
    public String getBankName() {
        return this.bankName;
    }
    
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    public String getAcountNo() {
        return this.acountNo;
    }
    
    public void setAcountNo(String acountNo) {
        this.acountNo = acountNo;
    }
    public String getAcountName() {
        return this.acountName;
    }
    
    public void setAcountName(String acountName) {
        this.acountName = acountName;
    }
    public Double getCurrentBallance() {
        return this.currentBallance;
    }
    
    public void setCurrentBallance(Double currentBallance) {
        this.currentBallance = currentBallance;
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
    public Set<CashFlow> getCashFlows() {
        return this.cashFlows;
    }
    
    public void setCashFlows(Set<CashFlow> cashFlows) {
        this.cashFlows = cashFlows;
    }




}


