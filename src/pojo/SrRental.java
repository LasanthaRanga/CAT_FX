package pojo;
// Generated Mar 14, 2018 12:29:09 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * SrRental generated by hbm2java
 */
public class SrRental  implements java.io.Serializable {


     private Integer idRental;
     private SrShopRentInfo srShopRentInfo;
     private Date date;
     private Double rental;
     private Double keymony;
     private Double arrears;
     private Double warrnt;
     private Double totalPay;
     private Double vat;
     private Double nbt;
     private Double stamp;
     private Integer status;
     private Integer syn;

    public SrRental() {
    }

	
    public SrRental(SrShopRentInfo srShopRentInfo) {
        this.srShopRentInfo = srShopRentInfo;
    }
    public SrRental(SrShopRentInfo srShopRentInfo, Date date, Double rental, Double keymony, Double arrears, Double warrnt, Double totalPay, Double vat, Double nbt, Double stamp, Integer status, Integer syn) {
       this.srShopRentInfo = srShopRentInfo;
       this.date = date;
       this.rental = rental;
       this.keymony = keymony;
       this.arrears = arrears;
       this.warrnt = warrnt;
       this.totalPay = totalPay;
       this.vat = vat;
       this.nbt = nbt;
       this.stamp = stamp;
       this.status = status;
       this.syn = syn;
    }
   
    public Integer getIdRental() {
        return this.idRental;
    }
    
    public void setIdRental(Integer idRental) {
        this.idRental = idRental;
    }
    public SrShopRentInfo getSrShopRentInfo() {
        return this.srShopRentInfo;
    }
    
    public void setSrShopRentInfo(SrShopRentInfo srShopRentInfo) {
        this.srShopRentInfo = srShopRentInfo;
    }
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    public Double getRental() {
        return this.rental;
    }
    
    public void setRental(Double rental) {
        this.rental = rental;
    }
    public Double getKeymony() {
        return this.keymony;
    }
    
    public void setKeymony(Double keymony) {
        this.keymony = keymony;
    }
    public Double getArrears() {
        return this.arrears;
    }
    
    public void setArrears(Double arrears) {
        this.arrears = arrears;
    }
    public Double getWarrnt() {
        return this.warrnt;
    }
    
    public void setWarrnt(Double warrnt) {
        this.warrnt = warrnt;
    }
    public Double getTotalPay() {
        return this.totalPay;
    }
    
    public void setTotalPay(Double totalPay) {
        this.totalPay = totalPay;
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
    public Double getStamp() {
        return this.stamp;
    }
    
    public void setStamp(Double stamp) {
        this.stamp = stamp;
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




}


