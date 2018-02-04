package pojo;
// Generated Feb 4, 2018 3:51:56 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * ProgramSubtitle generated by hbm2java
 */
public class ProgramSubtitle  implements java.io.Serializable {


     private Integer idProgramSubTitle;
     private ProgramTitle programTitle;
     private String subTitleNo;
     private String subTitleName;
     private Integer status;
     private Integer syn;
     private Set<Vort> vorts = new HashSet<Vort>(0);

    public ProgramSubtitle() {
    }

	
    public ProgramSubtitle(ProgramTitle programTitle) {
        this.programTitle = programTitle;
    }
    public ProgramSubtitle(ProgramTitle programTitle, String subTitleNo, String subTitleName, Integer status, Integer syn, Set<Vort> vorts) {
       this.programTitle = programTitle;
       this.subTitleNo = subTitleNo;
       this.subTitleName = subTitleName;
       this.status = status;
       this.syn = syn;
       this.vorts = vorts;
    }
   
    public Integer getIdProgramSubTitle() {
        return this.idProgramSubTitle;
    }
    
    public void setIdProgramSubTitle(Integer idProgramSubTitle) {
        this.idProgramSubTitle = idProgramSubTitle;
    }
    public ProgramTitle getProgramTitle() {
        return this.programTitle;
    }
    
    public void setProgramTitle(ProgramTitle programTitle) {
        this.programTitle = programTitle;
    }
    public String getSubTitleNo() {
        return this.subTitleNo;
    }
    
    public void setSubTitleNo(String subTitleNo) {
        this.subTitleNo = subTitleNo;
    }
    public String getSubTitleName() {
        return this.subTitleName;
    }
    
    public void setSubTitleName(String subTitleName) {
        this.subTitleName = subTitleName;
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
    public Set<Vort> getVorts() {
        return this.vorts;
    }
    
    public void setVorts(Set<Vort> vorts) {
        this.vorts = vorts;
    }




}


