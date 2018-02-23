/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class AppHolder {

    private Integer idApplication;
    private String applicationNo;
    private Date applicationDate;
    private Integer year;
    private Integer month;
    private Double allocation;
    private String tradeName;
    private String tradeAddress1;
    private String tradeAddress2;
    private String tradeAddress3;
    private Double taxAmount;
    private String discription;
    private Integer statues;   
    private Integer approveToPaymant;
    private Integer userLog_app;
    
    //approve
    private HashSet<ApprvalHolder> approveList;
    // RO
    private String roName;
    private Integer idRo; // RO is User type by RO cat

    //Assessmant 
    private Integer idAssessmant;
    private String assessmantNO;

    private Integer idStreet;
    private String streetName;

    private Integer idWard;
    private String wardName;

    //Nature
    private Integer idNature;
    private String nature;

    private Integer idSubNatrue;
    private String subNature;

    //Trade Type
    private Integer idTradeType;
    private String tradeTypeName;

    //Vort
    private Integer idVort;
    private String VortNo;

    //paymant
    private Integer idApplication_Paymant;
    private Integer idPaymant;

    private Integer userLog_pay;
    private Date paymentDate;
    private String receiptNo;
    private Double taxAmount_inPaymant;
    private Double vat;
    private Double nbt;
    private Double spamp;
    private Double totaleAmount;
    private String cashCheack;
    private Integer status_pay;
  

    //TradeLicens
    private Integer idTradeLicense;
    private String licenNo;
    private Date tradeLicenseDate;
    private Integer status_License;
   

    /**
     * @return the roName
     */
    public String getRoName() {
        return roName;
    }

    /**
     * @param roName the roName to set
     */
    public void setRoName(String roName) {
        this.roName = roName;
    }

    /**
     * @return the idRo
     */
    public Integer getIdRo() {
        return idRo;
    }

    /**
     * @param idRo the idRo to set
     */
    public void setIdRo(Integer idRo) {
        this.idRo = idRo;
    }

    /**
     * @return the idApplication
     */
    public Integer getIdApplication() {
        return idApplication;
    }

    /**
     * @param idApplication the idApplication to set
     */
    public void setIdApplication(Integer idApplication) {
        this.idApplication = idApplication;
    }

    /**
     * @return the applicationNo
     */
    public String getApplicationNo() {
        return applicationNo;
    }

    /**
     * @param applicationNo the applicationNo to set
     */
    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    /**
     * @return the applicationDate
     */
    public Date getApplicationDate() {
        return applicationDate;
    }

    /**
     * @param applicationDate the applicationDate to set
     */
    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    /**
     * @return the year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * @return the month
     */
    public Integer getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(Integer month) {
        this.month = month;
    }

    /**
     * @return the allocation
     */
    public Double getAllocation() {
        return allocation;
    }

    /**
     * @param allocation the allocation to set
     */
    public void setAllocation(Double allocation) {
        this.allocation = allocation;
    }

    /**
     * @return the tradeName
     */
    public String getTradeName() {
        return tradeName;
    }

    /**
     * @param tradeName the tradeName to set
     */
    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    /**
     * @return the tradeAddress1
     */
    public String getTradeAddress1() {
        return tradeAddress1;
    }

    /**
     * @param tradeAddress1 the tradeAddress1 to set
     */
    public void setTradeAddress1(String tradeAddress1) {
        this.tradeAddress1 = tradeAddress1;
    }

    /**
     * @return the tradeAddress2
     */
    public String getTradeAddress2() {
        return tradeAddress2;
    }

    /**
     * @param tradeAddress2 the tradeAddress2 to set
     */
    public void setTradeAddress2(String tradeAddress2) {
        this.tradeAddress2 = tradeAddress2;
    }

    /**
     * @return the tradeAddress3
     */
    public String getTradeAddress3() {
        return tradeAddress3;
    }

    /**
     * @param tradeAddress3 the tradeAddress3 to set
     */
    public void setTradeAddress3(String tradeAddress3) {
        this.tradeAddress3 = tradeAddress3;
    }

    /**
     * @return the taxAmount
     */
    public Double getTaxAmount() {
        return taxAmount;
    }

    /**
     * @param taxAmount the taxAmount to set
     */
    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    /**
     * @return the discription
     */
    public String getDiscription() {
        return discription;
    }

    /**
     * @param discription the discription to set
     */
    public void setDiscription(String discription) {
        this.discription = discription;
    }

    /**
     * @return the statues
     */
    public Integer getStatues() {
        return statues;
    }

    /**
     * @param statues the statues to set
     */
    public void setStatues(Integer statues) {
        this.statues = statues;
    }

    /**
     * @return the approveToPaymant
     */
    public Integer getApproveToPaymant() {
        return approveToPaymant;
    }

    /**
     * @param approveToPaymant the approveToPaymant to set
     */
    public void setApproveToPaymant(Integer approveToPaymant) {
        this.approveToPaymant = approveToPaymant;
    }

    /**
     * @return the userLog_app
     */
    public Integer getUserLog_app() {
        return userLog_app;
    }

    /**
     * @param userLog_app the userLog_app to set
     */
    public void setUserLog_app(Integer userLog_app) {
        this.userLog_app = userLog_app;
    }

    /**
     * @return the approveList
     */
    public HashSet<ApprvalHolder> getApproveList() {
        return approveList;
    }

    /**
     * @param approveList the approveList to set
     */
    public void setApproveList(HashSet<ApprvalHolder> approveList) {
        this.approveList = approveList;
    }

    /**
     * @return the idAssessmant
     */
    public Integer getIdAssessmant() {
        return idAssessmant;
    }

    /**
     * @param idAssessmant the idAssessmant to set
     */
    public void setIdAssessmant(Integer idAssessmant) {
        this.idAssessmant = idAssessmant;
    }

    /**
     * @return the assessmantNO
     */
    public String getAssessmantNO() {
        return assessmantNO;
    }

    /**
     * @param assessmantNO the assessmantNO to set
     */
    public void setAssessmantNO(String assessmantNO) {
        this.assessmantNO = assessmantNO;
    }

    /**
     * @return the idStreet
     */
    public Integer getIdStreet() {
        return idStreet;
    }

    /**
     * @param idStreet the idStreet to set
     */
    public void setIdStreet(Integer idStreet) {
        this.idStreet = idStreet;
    }

    /**
     * @return the streetName
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * @param streetName the streetName to set
     */
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    /**
     * @return the idWard
     */
    public Integer getIdWard() {
        return idWard;
    }

    /**
     * @param idWard the idWard to set
     */
    public void setIdWard(Integer idWard) {
        this.idWard = idWard;
    }

    /**
     * @return the wardName
     */
    public String getWardName() {
        return wardName;
    }

    /**
     * @param wardName the wardName to set
     */
    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    /**
     * @return the idNature
     */
    public Integer getIdNature() {
        return idNature;
    }

    /**
     * @param idNature the idNature to set
     */
    public void setIdNature(Integer idNature) {
        this.idNature = idNature;
    }

    /**
     * @return the nature
     */
    public String getNature() {
        return nature;
    }

    /**
     * @param nature the nature to set
     */
    public void setNature(String nature) {
        this.nature = nature;
    }

    /**
     * @return the idSubNatrue
     */
    public Integer getIdSubNatrue() {
        return idSubNatrue;
    }

    /**
     * @param idSubNatrue the idSubNatrue to set
     */
    public void setIdSubNatrue(Integer idSubNatrue) {
        this.idSubNatrue = idSubNatrue;
    }

    /**
     * @return the subNature
     */
    public String getSubNature() {
        return subNature;
    }

    /**
     * @param subNature the subNature to set
     */
    public void setSubNature(String subNature) {
        this.subNature = subNature;
    }

    /**
     * @return the idTradeType
     */
    public Integer getIdTradeType() {
        return idTradeType;
    }

    /**
     * @param idTradeType the idTradeType to set
     */
    public void setIdTradeType(Integer idTradeType) {
        this.idTradeType = idTradeType;
    }

    /**
     * @return the tradeTypeName
     */
    public String getTradeTypeName() {
        return tradeTypeName;
    }

    /**
     * @param tradeTypeName the tradeTypeName to set
     */
    public void setTradeTypeName(String tradeTypeName) {
        this.tradeTypeName = tradeTypeName;
    }

    /**
     * @return the idVort
     */
    public Integer getIdVort() {
        return idVort;
    }

    /**
     * @param idVort the idVort to set
     */
    public void setIdVort(Integer idVort) {
        this.idVort = idVort;
    }

    /**
     * @return the VortNo
     */
    public String getVortNo() {
        return VortNo;
    }

    /**
     * @param VortNo the VortNo to set
     */
    public void setVortNo(String VortNo) {
        this.VortNo = VortNo;
    }

    /**
     * @return the idApplication_Paymant
     */
    public Integer getIdApplication_Paymant() {
        return idApplication_Paymant;
    }

    /**
     * @param idApplication_Paymant the idApplication_Paymant to set
     */
    public void setIdApplication_Paymant(Integer idApplication_Paymant) {
        this.idApplication_Paymant = idApplication_Paymant;
    }

    /**
     * @return the idPaymant
     */
    public Integer getIdPaymant() {
        return idPaymant;
    }

    /**
     * @param idPaymant the idPaymant to set
     */
    public void setIdPaymant(Integer idPaymant) {
        this.idPaymant = idPaymant;
    }

    /**
     * @return the userLog_pay
     */
    public Integer getUserLog_pay() {
        return userLog_pay;
    }

    /**
     * @param userLog_pay the userLog_pay to set
     */
    public void setUserLog_pay(Integer userLog_pay) {
        this.userLog_pay = userLog_pay;
    }

    /**
     * @return the paymentDate
     */
    public Date getPaymentDate() {
        return paymentDate;
    }

    /**
     * @param paymentDate the paymentDate to set
     */
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * @return the receiptNo
     */
    public String getReceiptNo() {
        return receiptNo;
    }

    /**
     * @param receiptNo the receiptNo to set
     */
    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    /**
     * @return the taxAmount_inPaymant
     */
    public Double getTaxAmount_inPaymant() {
        return taxAmount_inPaymant;
    }

    /**
     * @param taxAmount_inPaymant the taxAmount_inPaymant to set
     */
    public void setTaxAmount_inPaymant(Double taxAmount_inPaymant) {
        this.taxAmount_inPaymant = taxAmount_inPaymant;
    }

    /**
     * @return the vat
     */
    public Double getVat() {
        return vat;
    }

    /**
     * @param vat the vat to set
     */
    public void setVat(Double vat) {
        this.vat = vat;
    }

    /**
     * @return the nbt
     */
    public Double getNbt() {
        return nbt;
    }

    /**
     * @param nbt the nbt to set
     */
    public void setNbt(Double nbt) {
        this.nbt = nbt;
    }

    /**
     * @return the spamp
     */
    public Double getSpamp() {
        return spamp;
    }

    /**
     * @param spamp the spamp to set
     */
    public void setSpamp(Double spamp) {
        this.spamp = spamp;
    }

    /**
     * @return the totaleAmount
     */
    public Double getTotaleAmount() {
        return totaleAmount;
    }

    /**
     * @param totaleAmount the totaleAmount to set
     */
    public void setTotaleAmount(Double totaleAmount) {
        this.totaleAmount = totaleAmount;
    }

    /**
     * @return the cashCheack
     */
    public String getCashCheack() {
        return cashCheack;
    }

    /**
     * @param cashCheack the cashCheack to set
     */
    public void setCashCheack(String cashCheack) {
        this.cashCheack = cashCheack;
    }

    /**
     * @return the status_pay
     */
    public Integer getStatus_pay() {
        return status_pay;
    }

    /**
     * @param status_pay the status_pay to set
     */
    public void setStatus_pay(Integer status_pay) {
        this.status_pay = status_pay;
    }

    
    /**
     * @return the idTradeLicense
     */
    public Integer getIdTradeLicense() {
        return idTradeLicense;
    }

    /**
     * @param idTradeLicense the idTradeLicense to set
     */
    public void setIdTradeLicense(Integer idTradeLicense) {
        this.idTradeLicense = idTradeLicense;
    }

    /**
     * @return the licenNo
     */
    public String getLicenNo() {
        return licenNo;
    }

    /**
     * @param licenNo the licenNo to set
     */
    public void setLicenNo(String licenNo) {
        this.licenNo = licenNo;
    }

    /**
     * @return the tradeLicenseDate
     */
    public Date getTradeLicenseDate() {
        return tradeLicenseDate;
    }

    /**
     * @param tradeLicenseDate the tradeLicenseDate to set
     */
    public void setTradeLicenseDate(Date tradeLicenseDate) {
        this.tradeLicenseDate = tradeLicenseDate;
    }

    /**
     * @return the status_License
     */
    public Integer getStatus_License() {
        return status_License;
    }

    /**
     * @param status_License the status_License to set
     */
    public void setStatus_License(Integer status_License) {
        this.status_License = status_License;
    }

    
    //
}
