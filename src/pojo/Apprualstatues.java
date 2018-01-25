package pojo;
// Generated Jan 25, 2018 9:52:50 AM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Apprualstatues generated by hbm2java
 */
public class Apprualstatues  implements java.io.Serializable {


     private Integer idApprualStatues;
     private Application application;
     private User user;
     private Integer statues;
     private String description;
     private Date date;
     private Integer syn;
     private Integer idOtheritisCat;

    public Apprualstatues() {
    }

	
    public Apprualstatues(Application application, User user) {
        this.application = application;
        this.user = user;
    }
    public Apprualstatues(Application application, User user, Integer statues, String description, Date date, Integer syn, Integer idOtheritisCat) {
       this.application = application;
       this.user = user;
       this.statues = statues;
       this.description = description;
       this.date = date;
       this.syn = syn;
       this.idOtheritisCat = idOtheritisCat;
    }
   
    public Integer getIdApprualStatues() {
        return this.idApprualStatues;
    }
    
    public void setIdApprualStatues(Integer idApprualStatues) {
        this.idApprualStatues = idApprualStatues;
    }
    public Application getApplication() {
        return this.application;
    }
    
    public void setApplication(Application application) {
        this.application = application;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public Integer getStatues() {
        return this.statues;
    }
    
    public void setStatues(Integer statues) {
        this.statues = statues;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    public Integer getSyn() {
        return this.syn;
    }
    
    public void setSyn(Integer syn) {
        this.syn = syn;
    }
    public Integer getIdOtheritisCat() {
        return this.idOtheritisCat;
    }
    
    public void setIdOtheritisCat(Integer idOtheritisCat) {
        this.idOtheritisCat = idOtheritisCat;
    }




}


