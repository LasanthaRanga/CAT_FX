package pojo;
// Generated Jan 18, 2018 5:44:14 PM by Hibernate Tools 4.3.1



/**
 * Privilage generated by hbm2java
 */
public class Privilage  implements java.io.Serializable {


     private Integer idPrivilage;
     private User user;
     private String componant;
     private String id;
     private String privilagecol;
     private Integer status;
     private Integer syn;

    public Privilage() {
    }

	
    public Privilage(User user) {
        this.user = user;
    }
    public Privilage(User user, String componant, String id, String privilagecol, Integer status, Integer syn) {
       this.user = user;
       this.componant = componant;
       this.id = id;
       this.privilagecol = privilagecol;
       this.status = status;
       this.syn = syn;
    }
   
    public Integer getIdPrivilage() {
        return this.idPrivilage;
    }
    
    public void setIdPrivilage(Integer idPrivilage) {
        this.idPrivilage = idPrivilage;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public String getComponant() {
        return this.componant;
    }
    
    public void setComponant(String componant) {
        this.componant = componant;
    }
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public String getPrivilagecol() {
        return this.privilagecol;
    }
    
    public void setPrivilagecol(String privilagecol) {
        this.privilagecol = privilagecol;
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


