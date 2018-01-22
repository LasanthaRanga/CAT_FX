/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import pojo.Application;
import pojo.Otheritiscat;
import pojo.User;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class Approve {

    public Approve(int idApprove, int statues, User user, int idUser, Application aplication, int idApplication, Otheritiscat autho, int idOtheritisCat, String Description, Date date) {
        this.idApprove = idApprove;
        this.statues = statues;
        this.user = user;
        this.idUser = idUser;
        this.aplication = aplication;
        this.idApplication = idApplication;
        this.autho = autho;
        this.idOtheritisCat = idOtheritisCat;
        this.Description = Description;
        this.date = date;
    }

    public Approve() {
        super();
    }

    /**
     * @return the idApprove
     */
    public int getIdApprove() {
        return idApprove;
    }

    /**
     * @param idApprove the idApprove to set
     */
    public void setIdApprove(int idApprove) {
        this.idApprove = idApprove;
    }

    /**
     * @return the statues
     */
    public int getStatues() {
        return statues;
    }

    /**
     * @param statues the statues to set
     */
    public void setStatues(int statues) {
        this.statues = statues;
    }

    /**
     * @return the user
     */
    public pojo.User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(pojo.User user) {
        this.user = user;
    }

    /**
     * @return the idUser
     */
    public int getIdUser() {
        return idUser;
    }

    /**
     * @param idUser the idUser to set
     */
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    /**
     * @return the aplication
     */
    public pojo.Application getAplication() {
        return aplication;
    }

    /**
     * @param aplication the aplication to set
     */
    public void setAplication(pojo.Application aplication) {
        this.aplication = aplication;
    }

    /**
     * @return the idApplication
     */
    public int getIdApplication() {
        return idApplication;
    }

    /**
     * @param idApplication the idApplication to set
     */
    public void setIdApplication(int idApplication) {
        this.idApplication = idApplication;
    }

    /**
     * @return the autho
     */
    public pojo.Otheritiscat getAutho() {
        return autho;
    }

    /**
     * @param autho the autho to set
     */
    public void setAutho(pojo.Otheritiscat autho) {
        this.autho = autho;
    }

    /**
     * @return the idOtheritisCat
     */
    public int getIdOtheritisCat() {
        return idOtheritisCat;
    }

    /**
     * @param idOtheritisCat the idOtheritisCat to set
     */
    public void setIdOtheritisCat(int idOtheritisCat) {
        this.idOtheritisCat = idOtheritisCat;
    }

    /**
     * @return the Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param Description the Description to set
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    private int idApprove;
    private int statues;
    private pojo.User user;
    private int idUser;
    private pojo.Application aplication;
    private int idApplication;
    private pojo.Otheritiscat autho;
    private int idOtheritisCat;
    private String Description;
    private Date date;

}
