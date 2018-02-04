package pojo;
// Generated Feb 4, 2018 3:51:56 PM by Hibernate Tools 4.3.1



/**
 * UserHasDepartment generated by hbm2java
 */
public class UserHasDepartment  implements java.io.Serializable {


     private Integer idUserHasDepartment;
     private Department department;
     private User user;
     private Integer syn;

    public UserHasDepartment() {
    }

	
    public UserHasDepartment(Department department, User user) {
        this.department = department;
        this.user = user;
    }
    public UserHasDepartment(Department department, User user, Integer syn) {
       this.department = department;
       this.user = user;
       this.syn = syn;
    }
   
    public Integer getIdUserHasDepartment() {
        return this.idUserHasDepartment;
    }
    
    public void setIdUserHasDepartment(Integer idUserHasDepartment) {
        this.idUserHasDepartment = idUserHasDepartment;
    }
    public Department getDepartment() {
        return this.department;
    }
    
    public void setDepartment(Department department) {
        this.department = department;
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


