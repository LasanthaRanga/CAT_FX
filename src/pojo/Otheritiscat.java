package pojo;
// Generated Jan 20, 2018 8:59:03 AM by Hibernate Tools 4.3.1



/**
 * Otheritiscat generated by hbm2java
 */
public class Otheritiscat  implements java.io.Serializable {


     private Integer idOtheritisCat;
     private User user;
     private String catname;
     private String disignation;
     private Integer statues;
     private Integer syn;

    public Otheritiscat() {
    }

	
    public Otheritiscat(User user) {
        this.user = user;
    }
    public Otheritiscat(User user, String catname, String disignation, Integer statues, Integer syn) {
       this.user = user;
       this.catname = catname;
       this.disignation = disignation;
       this.statues = statues;
       this.syn = syn;
    }
   
    public Integer getIdOtheritisCat() {
        return this.idOtheritisCat;
    }
    
    public void setIdOtheritisCat(Integer idOtheritisCat) {
        this.idOtheritisCat = idOtheritisCat;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public String getCatname() {
        return this.catname;
    }
    
    public void setCatname(String catname) {
        this.catname = catname;
    }
    public String getDisignation() {
        return this.disignation;
    }
    
    public void setDisignation(String disignation) {
        this.disignation = disignation;
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




}


