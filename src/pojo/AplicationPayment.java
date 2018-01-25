package pojo;
// Generated Jan 25, 2018 9:52:50 AM by Hibernate Tools 4.3.1



/**
 * AplicationPayment generated by hbm2java
 */
public class AplicationPayment  implements java.io.Serializable {


     private Integer idAplicationPayment;
     private Application application;
     private Payment payment;
     private Integer syn;

    public AplicationPayment() {
    }

	
    public AplicationPayment(Application application, Payment payment) {
        this.application = application;
        this.payment = payment;
    }
    public AplicationPayment(Application application, Payment payment, Integer syn) {
       this.application = application;
       this.payment = payment;
       this.syn = syn;
    }
   
    public Integer getIdAplicationPayment() {
        return this.idAplicationPayment;
    }
    
    public void setIdAplicationPayment(Integer idAplicationPayment) {
        this.idAplicationPayment = idAplicationPayment;
    }
    public Application getApplication() {
        return this.application;
    }
    
    public void setApplication(Application application) {
        this.application = application;
    }
    public Payment getPayment() {
        return this.payment;
    }
    
    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    public Integer getSyn() {
        return this.syn;
    }
    
    public void setSyn(Integer syn) {
        this.syn = syn;
    }




}


