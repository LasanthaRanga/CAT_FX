package pojo;
// Generated Jan 15, 2018 9:38:27 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Application generated by hbm2java
 */
public class Application  implements java.io.Serializable {


     private Integer idApplication;
     private Customer customer;
     private TradeNature tradeNature;
     private TradeType tradeType;
     private Integer subNatureIdSubNature;
     private Integer roIdRo;
     private Integer applicationNo;
     private Date applicationDate;
     private Integer year;
     private Integer month;
     private Double allocation;
     private String type;
     private String tradeName;
     private String tradeAddress1;
     private String tradeAddress2;
     private String tradeAddress3;
     private String tradeNature_1;
     private Double taxAmount;
     private Integer approve;
     private Integer sendId;
     private String discription;
     private String applicationcol;
     private Integer statues;
     private Integer syn;
     private Set<Payment> payments = new HashSet<Payment>(0);

    public Application() {
    }

	
    public Application(Customer customer, TradeNature tradeNature, TradeType tradeType) {
        this.customer = customer;
        this.tradeNature = tradeNature;
        this.tradeType = tradeType;
    }
    public Application(Customer customer, TradeNature tradeNature, TradeType tradeType, Integer subNatureIdSubNature, Integer roIdRo, Integer applicationNo, Date applicationDate, Integer year, Integer month, Double allocation, String type, String tradeName, String tradeAddress1, String tradeAddress2, String tradeAddress3, String tradeNature_1, Double taxAmount, Integer approve, Integer sendId, String discription, String applicationcol, Integer statues, Integer syn, Set<Payment> payments) {
       this.customer = customer;
       this.tradeNature = tradeNature;
       this.tradeType = tradeType;
       this.subNatureIdSubNature = subNatureIdSubNature;
       this.roIdRo = roIdRo;
       this.applicationNo = applicationNo;
       this.applicationDate = applicationDate;
       this.year = year;
       this.month = month;
       this.allocation = allocation;
       this.type = type;
       this.tradeName = tradeName;
       this.tradeAddress1 = tradeAddress1;
       this.tradeAddress2 = tradeAddress2;
       this.tradeAddress3 = tradeAddress3;
       this.tradeNature_1 = tradeNature_1;
       this.taxAmount = taxAmount;
       this.approve = approve;
       this.sendId = sendId;
       this.discription = discription;
       this.applicationcol = applicationcol;
       this.statues = statues;
       this.syn = syn;
       this.payments = payments;
    }
   
    public Integer getIdApplication() {
        return this.idApplication;
    }
    
    public void setIdApplication(Integer idApplication) {
        this.idApplication = idApplication;
    }
    public Customer getCustomer() {
        return this.customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public TradeNature getTradeNature() {
        return this.tradeNature;
    }
    
    public void setTradeNature(TradeNature tradeNature) {
        this.tradeNature = tradeNature;
    }
    public TradeType getTradeType() {
        return this.tradeType;
    }
    
    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
    }
    public Integer getSubNatureIdSubNature() {
        return this.subNatureIdSubNature;
    }
    
    public void setSubNatureIdSubNature(Integer subNatureIdSubNature) {
        this.subNatureIdSubNature = subNatureIdSubNature;
    }
    public Integer getRoIdRo() {
        return this.roIdRo;
    }
    
    public void setRoIdRo(Integer roIdRo) {
        this.roIdRo = roIdRo;
    }
    public Integer getApplicationNo() {
        return this.applicationNo;
    }
    
    public void setApplicationNo(Integer applicationNo) {
        this.applicationNo = applicationNo;
    }
    public Date getApplicationDate() {
        return this.applicationDate;
    }
    
    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }
    public Integer getYear() {
        return this.year;
    }
    
    public void setYear(Integer year) {
        this.year = year;
    }
    public Integer getMonth() {
        return this.month;
    }
    
    public void setMonth(Integer month) {
        this.month = month;
    }
    public Double getAllocation() {
        return this.allocation;
    }
    
    public void setAllocation(Double allocation) {
        this.allocation = allocation;
    }
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    public String getTradeName() {
        return this.tradeName;
    }
    
    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }
    public String getTradeAddress1() {
        return this.tradeAddress1;
    }
    
    public void setTradeAddress1(String tradeAddress1) {
        this.tradeAddress1 = tradeAddress1;
    }
    public String getTradeAddress2() {
        return this.tradeAddress2;
    }
    
    public void setTradeAddress2(String tradeAddress2) {
        this.tradeAddress2 = tradeAddress2;
    }
    public String getTradeAddress3() {
        return this.tradeAddress3;
    }
    
    public void setTradeAddress3(String tradeAddress3) {
        this.tradeAddress3 = tradeAddress3;
    }
    public String getTradeNature_1() {
        return this.tradeNature_1;
    }
    
    public void setTradeNature_1(String tradeNature_1) {
        this.tradeNature_1 = tradeNature_1;
    }
    public Double getTaxAmount() {
        return this.taxAmount;
    }
    
    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }
    public Integer getApprove() {
        return this.approve;
    }
    
    public void setApprove(Integer approve) {
        this.approve = approve;
    }
    public Integer getSendId() {
        return this.sendId;
    }
    
    public void setSendId(Integer sendId) {
        this.sendId = sendId;
    }
    public String getDiscription() {
        return this.discription;
    }
    
    public void setDiscription(String discription) {
        this.discription = discription;
    }
    public String getApplicationcol() {
        return this.applicationcol;
    }
    
    public void setApplicationcol(String applicationcol) {
        this.applicationcol = applicationcol;
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
    public Set<Payment> getPayments() {
        return this.payments;
    }
    
    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }




}


