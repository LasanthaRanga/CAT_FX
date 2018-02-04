package pojo;
// Generated Feb 4, 2018 3:51:56 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Payment generated by hbm2java
 */
public class Payment  implements java.io.Serializable {


     private Integer idPayment;
     private UserLog userLog;
     private Vort vort;
     private Integer applicationNo;
     private Integer year;
     private Integer month;
     private Date paymentDate;
     private String receiptNo;
     private Double taxAmount;
     private Double vat;
     private Double nbt;
     private Double spamp;
     private Double totaleAmount;
     private String cashCheack;
     private Integer status;
     private Integer syn;
     private Set<TradeLicense> tradeLicenses = new HashSet<TradeLicense>(0);
     private Set<AplicationPayment> aplicationPayments = new HashSet<AplicationPayment>(0);

    public Payment() {
    }

	
    public Payment(UserLog userLog, Vort vort) {
        this.userLog = userLog;
        this.vort = vort;
    }
    public Payment(UserLog userLog, Vort vort, Integer applicationNo, Integer year, Integer month, Date paymentDate, String receiptNo, Double taxAmount, Double vat, Double nbt, Double spamp, Double totaleAmount, String cashCheack, Integer status, Integer syn, Set<TradeLicense> tradeLicenses, Set<AplicationPayment> aplicationPayments) {
       this.userLog = userLog;
       this.vort = vort;
       this.applicationNo = applicationNo;
       this.year = year;
       this.month = month;
       this.paymentDate = paymentDate;
       this.receiptNo = receiptNo;
       this.taxAmount = taxAmount;
       this.vat = vat;
       this.nbt = nbt;
       this.spamp = spamp;
       this.totaleAmount = totaleAmount;
       this.cashCheack = cashCheack;
       this.status = status;
       this.syn = syn;
       this.tradeLicenses = tradeLicenses;
       this.aplicationPayments = aplicationPayments;
    }
   
    public Integer getIdPayment() {
        return this.idPayment;
    }
    
    public void setIdPayment(Integer idPayment) {
        this.idPayment = idPayment;
    }
    public UserLog getUserLog() {
        return this.userLog;
    }
    
    public void setUserLog(UserLog userLog) {
        this.userLog = userLog;
    }
    public Vort getVort() {
        return this.vort;
    }
    
    public void setVort(Vort vort) {
        this.vort = vort;
    }
    public Integer getApplicationNo() {
        return this.applicationNo;
    }
    
    public void setApplicationNo(Integer applicationNo) {
        this.applicationNo = applicationNo;
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
    public Date getPaymentDate() {
        return this.paymentDate;
    }
    
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
    public String getReceiptNo() {
        return this.receiptNo;
    }
    
    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }
    public Double getTaxAmount() {
        return this.taxAmount;
    }
    
    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }
    public Double getVat() {
        return this.vat;
    }
    
    public void setVat(Double vat) {
        this.vat = vat;
    }
    public Double getNbt() {
        return this.nbt;
    }
    
    public void setNbt(Double nbt) {
        this.nbt = nbt;
    }
    public Double getSpamp() {
        return this.spamp;
    }
    
    public void setSpamp(Double spamp) {
        this.spamp = spamp;
    }
    public Double getTotaleAmount() {
        return this.totaleAmount;
    }
    
    public void setTotaleAmount(Double totaleAmount) {
        this.totaleAmount = totaleAmount;
    }
    public String getCashCheack() {
        return this.cashCheack;
    }
    
    public void setCashCheack(String cashCheack) {
        this.cashCheack = cashCheack;
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
    public Set<TradeLicense> getTradeLicenses() {
        return this.tradeLicenses;
    }
    
    public void setTradeLicenses(Set<TradeLicense> tradeLicenses) {
        this.tradeLicenses = tradeLicenses;
    }
    public Set<AplicationPayment> getAplicationPayments() {
        return this.aplicationPayments;
    }
    
    public void setAplicationPayments(Set<AplicationPayment> aplicationPayments) {
        this.aplicationPayments = aplicationPayments;
    }




}


