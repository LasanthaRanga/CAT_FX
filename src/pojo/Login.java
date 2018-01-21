package pojo;
// Generated Jan 21, 2018 11:09:37 AM by Hibernate Tools 4.3.1



/**
 * Login generated by hbm2java
 */
public class Login  implements java.io.Serializable {


     private Integer idLogin;
     private User user;
     private String uname;
     private String pword;
     private Integer status;
     private Integer syn;

    public Login() {
    }

	
    public Login(User user) {
        this.user = user;
    }
    public Login(User user, String uname, String pword, Integer status, Integer syn) {
       this.user = user;
       this.uname = uname;
       this.pword = pword;
       this.status = status;
       this.syn = syn;
    }
   
    public Integer getIdLogin() {
        return this.idLogin;
    }
    
    public void setIdLogin(Integer idLogin) {
        this.idLogin = idLogin;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public String getUname() {
        return this.uname;
    }
    
    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getPword() {
        return this.pword;
    }
    
    public void setPword(String pword) {
        this.pword = pword;
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


