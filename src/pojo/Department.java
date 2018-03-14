package pojo;
// Generated Mar 14, 2018 12:29:09 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Department generated by hbm2java
 */
public class Department  implements java.io.Serializable {


     private Integer idDepartment;
     private String department;
     private String description;
     private Integer statues;
     private Integer syn;
     private Set<UserHasDepartment> userHasDepartments = new HashSet<UserHasDepartment>(0);

    public Department() {
    }

    public Department(String department, String description, Integer statues, Integer syn, Set<UserHasDepartment> userHasDepartments) {
       this.department = department;
       this.description = description;
       this.statues = statues;
       this.syn = syn;
       this.userHasDepartments = userHasDepartments;
    }
   
    public Integer getIdDepartment() {
        return this.idDepartment;
    }
    
    public void setIdDepartment(Integer idDepartment) {
        this.idDepartment = idDepartment;
    }
    public String getDepartment() {
        return this.department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
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
    public Set<UserHasDepartment> getUserHasDepartments() {
        return this.userHasDepartments;
    }
    
    public void setUserHasDepartments(Set<UserHasDepartment> userHasDepartments) {
        this.userHasDepartments = userHasDepartments;
    }




}


