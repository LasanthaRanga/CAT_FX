package pojo;
// Generated Jan 28, 2018 8:40:19 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Application generated by hbm2java
 */
public class Application  implements java.io.Serializable {


     private Integer idApplication;
     private Assessment assessment;
     private Customer customer;
     private SubNature subNature;
     private TradeNature tradeNature;
     private TradeType tradeType;
     private User user;
     private UserLog userLog;
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
     private String discription;
     private Integer statues;
     private Integer syn;
     private Integer approveToPaymant;
     private Set<AplicationPayment> aplicationPayments = new HashSet<AplicationPayment>(0);
     private Set<Apprualstatues> apprualstatueses = new HashSet<Apprualstatues>(0);
     private Set<TradeLicense> tradeLicenses = new HashSet<TradeLicense>(0);

    public Application() {
    }

	
    public Application(Customer customer, TradeNature tradeNature, TradeType tradeType, UserLog userLog) {
        this.customer = customer;
        this.tradeNature = tradeNature;
        this.tradeType = tradeType;
        this.userLog = userLog;
    }
    public Application(Assessment assessment, Customer customer, SubNature subNature, TradeNature tradeNature, TradeType tradeType, User user, UserLog userLog, Date applicationDate, Integer year, Integer month, Double allocation, String type, String tradeName, String tradeAddress1, String tradeAddress2, String tradeAddress3, String tradeNature_1, Double taxAmount, String discription, Integer statues, Integer syn, Integer approveToPaymant, Set<AplicationPayment> aplicationPayments, Set<Apprualstatues> apprualstatueses, Set<TradeLicense> tradeLicenses) {
       this.assessment = assessment;
       this.customer = customer;
       this.subNature = subNature;
       this.tradeNature = tradeNature;
       this.tradeType = tradeType;
       this.user = user;
       this.userLog = userLog;
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
       this.discription = discription;
       this.statues = statues;
       this.syn = syn;
       this.approveToPaymant = approveToPaymant;
       this.aplicationPayments = aplicationPayments;
       this.apprualstatueses = apprualstatueses;
       this.tradeLicenses = tradeLicenses;
    }
   
    public Integer getIdApplication() {
        return this.idApplication;
    }
    
    public void setIdApplication(Integer idApplication) {
        this.idApplication = idApplication;
    }
    public Assessment getAssessment() {
        return this.assessment;
    }
    
    public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
    }
    public Customer getCustomer() {
        return this.customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public SubNature getSubNature() {
        return this.subNature;
    }
    
    public void setSubNature(SubNature subNature) {
        this.subNature = subNature;
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
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public UserLog getUserLog() {
        return this.userLog;
    }
    
    public void setUserLog(UserLog userLog) {
        this.userLog = userLog;
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
    public String getDiscription() {
        return this.discription;
    }
    
    public void setDiscription(String discription) {
        this.discription = discription;
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
    public Integer getApproveToPaymant() {
        return this.approveToPaymant;
    }
    
    public void setApproveToPaymant(Integer approveToPaymant) {
        this.approveToPaymant = approveToPaymant;
    }
    public Set<AplicationPayment> getAplicationPayments() {
        return this.aplicationPayments;
    }
    
    public void setAplicationPayments(Set<AplicationPayment> aplicationPayments) {
        this.aplicationPayments = aplicationPayments;
    }
    public Set<Apprualstatues> getApprualstatueses() {
        return this.apprualstatueses;
    }
    
    public void setApprualstatueses(Set<Apprualstatues> apprualstatueses) {
        this.apprualstatueses = apprualstatueses;
    }
    public Set<TradeLicense> getTradeLicenses() {
        return this.tradeLicenses;
    }
    
    public void setTradeLicenses(Set<TradeLicense> tradeLicenses) {
        this.tradeLicenses = tradeLicenses;
    }




}


