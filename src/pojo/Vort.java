package pojo;
// Generated Jan 30, 2018 11:20:06 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Vort generated by hbm2java
 */
public class Vort  implements java.io.Serializable {


     private Integer idVort;
     private BankInfo bankInfo;
     private ProgramSubtitle programSubtitle;
     private String voteNo;
     private String voteName;
     private Double voteCurrentBalance;
     private Double voteAlocation;
     private Integer status;
     private Integer syn;
     private Set<CashFlow> cashFlows = new HashSet<CashFlow>(0);
     private Set<Payment> payments = new HashSet<Payment>(0);
     private Set<TradeType> tradeTypes = new HashSet<TradeType>(0);

    public Vort() {
    }

	
    public Vort(BankInfo bankInfo, ProgramSubtitle programSubtitle) {
        this.bankInfo = bankInfo;
        this.programSubtitle = programSubtitle;
    }
    public Vort(BankInfo bankInfo, ProgramSubtitle programSubtitle, String voteNo, String voteName, Double voteCurrentBalance, Double voteAlocation, Integer status, Integer syn, Set<CashFlow> cashFlows, Set<Payment> payments, Set<TradeType> tradeTypes) {
       this.bankInfo = bankInfo;
       this.programSubtitle = programSubtitle;
       this.voteNo = voteNo;
       this.voteName = voteName;
       this.voteCurrentBalance = voteCurrentBalance;
       this.voteAlocation = voteAlocation;
       this.status = status;
       this.syn = syn;
       this.cashFlows = cashFlows;
       this.payments = payments;
       this.tradeTypes = tradeTypes;
    }
   
    public Integer getIdVort() {
        return this.idVort;
    }
    
    public void setIdVort(Integer idVort) {
        this.idVort = idVort;
    }
    public BankInfo getBankInfo() {
        return this.bankInfo;
    }
    
    public void setBankInfo(BankInfo bankInfo) {
        this.bankInfo = bankInfo;
    }
    public ProgramSubtitle getProgramSubtitle() {
        return this.programSubtitle;
    }
    
    public void setProgramSubtitle(ProgramSubtitle programSubtitle) {
        this.programSubtitle = programSubtitle;
    }
    public String getVoteNo() {
        return this.voteNo;
    }
    
    public void setVoteNo(String voteNo) {
        this.voteNo = voteNo;
    }
    public String getVoteName() {
        return this.voteName;
    }
    
    public void setVoteName(String voteName) {
        this.voteName = voteName;
    }
    public Double getVoteCurrentBalance() {
        return this.voteCurrentBalance;
    }
    
    public void setVoteCurrentBalance(Double voteCurrentBalance) {
        this.voteCurrentBalance = voteCurrentBalance;
    }
    public Double getVoteAlocation() {
        return this.voteAlocation;
    }
    
    public void setVoteAlocation(Double voteAlocation) {
        this.voteAlocation = voteAlocation;
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
    public Set<Payment> getPayments() {
        return this.payments;
    }
    
    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }
    public Set<TradeType> getTradeTypes() {
        return this.tradeTypes;
    }
    
    public void setTradeTypes(Set<TradeType> tradeTypes) {
        this.tradeTypes = tradeTypes;
    }




}


