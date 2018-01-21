package pojo;
// Generated Jan 21, 2018 11:09:37 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * ProgramTitle generated by hbm2java
 */
public class ProgramTitle  implements java.io.Serializable {


     private Integer idProgramTitle;
     private Program program;
     private String titleNo;
     private String titleName;
     private Integer status;
     private Integer int_;
     private Set<ProgramSubtitle> programSubtitles = new HashSet<ProgramSubtitle>(0);

    public ProgramTitle() {
    }

	
    public ProgramTitle(Program program) {
        this.program = program;
    }
    public ProgramTitle(Program program, String titleNo, String titleName, Integer status, Integer int_, Set<ProgramSubtitle> programSubtitles) {
       this.program = program;
       this.titleNo = titleNo;
       this.titleName = titleName;
       this.status = status;
       this.int_ = int_;
       this.programSubtitles = programSubtitles;
    }
   
    public Integer getIdProgramTitle() {
        return this.idProgramTitle;
    }
    
    public void setIdProgramTitle(Integer idProgramTitle) {
        this.idProgramTitle = idProgramTitle;
    }
    public Program getProgram() {
        return this.program;
    }
    
    public void setProgram(Program program) {
        this.program = program;
    }
    public String getTitleNo() {
        return this.titleNo;
    }
    
    public void setTitleNo(String titleNo) {
        this.titleNo = titleNo;
    }
    public String getTitleName() {
        return this.titleName;
    }
    
    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getInt_() {
        return this.int_;
    }
    
    public void setInt_(Integer int_) {
        this.int_ = int_;
    }
    public Set<ProgramSubtitle> getProgramSubtitles() {
        return this.programSubtitles;
    }
    
    public void setProgramSubtitles(Set<ProgramSubtitle> programSubtitles) {
        this.programSubtitles = programSubtitles;
    }




}


