package pojo;
// Generated Jan 22, 2018 1:40:54 PM by Hibernate Tools 4.3.1



/**
 * UserHasOtheritiscat generated by hbm2java
 */
public class UserHasOtheritiscat  implements java.io.Serializable {


     private Integer idUserHasOtheritisCat;
     private Otheritiscat otheritiscat;
     private User user;
     private Integer syn;

    public UserHasOtheritiscat() {
    }

	
    public UserHasOtheritiscat(Otheritiscat otheritiscat, User user) {
        this.otheritiscat = otheritiscat;
        this.user = user;
    }
    public UserHasOtheritiscat(Otheritiscat otheritiscat, User user, Integer syn) {
       this.otheritiscat = otheritiscat;
       this.user = user;
       this.syn = syn;
    }
   
    public Integer getIdUserHasOtheritisCat() {
        return this.idUserHasOtheritisCat;
    }
    
    public void setIdUserHasOtheritisCat(Integer idUserHasOtheritisCat) {
        this.idUserHasOtheritisCat = idUserHasOtheritisCat;
    }
    public Otheritiscat getOtheritiscat() {
        return this.otheritiscat;
    }
    
    public void setOtheritiscat(Otheritiscat otheritiscat) {
        this.otheritiscat = otheritiscat;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public Integer getSyn() {
        return this.syn;
    }
    
    public void setSyn(Integer syn) {
        this.syn = syn;
    }




}


