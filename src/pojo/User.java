package pojo;
// Generated Jan 20, 2018 8:59:03 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * User generated by hbm2java
 */
public class User  implements java.io.Serializable {


     private Integer idUser;
     private String fullName;
     private String nic;
     private String mobile;
     private Date regDate;
     private String sq;
     private String sqa;
     private Integer status;
     private Integer syn;
     private Set<Privilage> privilages = new HashSet<Privilage>(0);
     private Set<Application> applications = new HashSet<Application>(0);
     private Set<Apprualstatues> apprualstatueses = new HashSet<Apprualstatues>(0);
     private Set<UserHasCatagory> userHasCatagories = new HashSet<UserHasCatagory>(0);
     private Set<UserLog> userLogs = new HashSet<UserLog>(0);
     private Set<Otheritiscat> otheritiscats = new HashSet<Otheritiscat>(0);
     private Set<UserHasDepartment> userHasDepartments = new HashSet<UserHasDepartment>(0);
     private Set<Login> logins = new HashSet<Login>(0);

    public User() {
    }

    public User(String fullName, String nic, String mobile, Date regDate, String sq, String sqa, Integer status, Integer syn, Set<Privilage> privilages, Set<Application> applications, Set<Apprualstatues> apprualstatueses, Set<UserHasCatagory> userHasCatagories, Set<UserLog> userLogs, Set<Otheritiscat> otheritiscats, Set<UserHasDepartment> userHasDepartments, Set<Login> logins) {
       this.fullName = fullName;
       this.nic = nic;
       this.mobile = mobile;
       this.regDate = regDate;
       this.sq = sq;
       this.sqa = sqa;
       this.status = status;
       this.syn = syn;
       this.privilages = privilages;
       this.applications = applications;
       this.apprualstatueses = apprualstatueses;
       this.userHasCatagories = userHasCatagories;
       this.userLogs = userLogs;
       this.otheritiscats = otheritiscats;
       this.userHasDepartments = userHasDepartments;
       this.logins = logins;
    }
   
    public Integer getIdUser() {
        return this.idUser;
    }
    
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
    public String getFullName() {
        return this.fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getNic() {
        return this.nic;
    }
    
    public void setNic(String nic) {
        this.nic = nic;
    }
    public String getMobile() {
        return this.mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public Date getRegDate() {
        return this.regDate;
    }
    
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
    public String getSq() {
        return this.sq;
    }
    
    public void setSq(String sq) {
        this.sq = sq;
    }
    public String getSqa() {
        return this.sqa;
    }
    
    public void setSqa(String sqa) {
        this.sqa = sqa;
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
    public Set<Privilage> getPrivilages() {
        return this.privilages;
    }
    
    public void setPrivilages(Set<Privilage> privilages) {
        this.privilages = privilages;
    }
    public Set<Application> getApplications() {
        return this.applications;
    }
    
    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }
    public Set<Apprualstatues> getApprualstatueses() {
        return this.apprualstatueses;
    }
    
    public void setApprualstatueses(Set<Apprualstatues> apprualstatueses) {
        this.apprualstatueses = apprualstatueses;
    }
    public Set<UserHasCatagory> getUserHasCatagories() {
        return this.userHasCatagories;
    }
    
    public void setUserHasCatagories(Set<UserHasCatagory> userHasCatagories) {
        this.userHasCatagories = userHasCatagories;
    }
    public Set<UserLog> getUserLogs() {
        return this.userLogs;
    }
    
    public void setUserLogs(Set<UserLog> userLogs) {
        this.userLogs = userLogs;
    }
    public Set<Otheritiscat> getOtheritiscats() {
        return this.otheritiscats;
    }
    
    public void setOtheritiscats(Set<Otheritiscat> otheritiscats) {
        this.otheritiscats = otheritiscats;
    }
    public Set<UserHasDepartment> getUserHasDepartments() {
        return this.userHasDepartments;
    }
    
    public void setUserHasDepartments(Set<UserHasDepartment> userHasDepartments) {
        this.userHasDepartments = userHasDepartments;
    }
    public Set<Login> getLogins() {
        return this.logins;
    }
    
    public void setLogins(Set<Login> logins) {
        this.logins = logins;
    }




}


