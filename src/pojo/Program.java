package pojo;
// Generated Mar 14, 2018 12:29:09 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Program generated by hbm2java
 */
public class Program  implements java.io.Serializable {


     private Integer idProgram;
     private String name;
     private String programcol;
     private Integer status;
     private Integer syn;
     private Set<ProgramTitle> programTitles = new HashSet<ProgramTitle>(0);

    public Program() {
    }

    public Program(String name, String programcol, Integer status, Integer syn, Set<ProgramTitle> programTitles) {
       this.name = name;
       this.programcol = programcol;
       this.status = status;
       this.syn = syn;
       this.programTitles = programTitles;
    }
   
    public Integer getIdProgram() {
        return this.idProgram;
    }
    
    public void setIdProgram(Integer idProgram) {
        this.idProgram = idProgram;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getProgramcol() {
        return this.programcol;
    }
    
    public void setProgramcol(String programcol) {
        this.programcol = programcol;
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
    public Set<ProgramTitle> getProgramTitles() {
        return this.programTitles;
    }
    
    public void setProgramTitles(Set<ProgramTitle> programTitles) {
        this.programTitles = programTitles;
    }




}


