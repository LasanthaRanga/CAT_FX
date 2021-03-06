/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import pojo.TradeNature;
import pojo.User;
import pojo.UserLog;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class AppTbl {

    public AppTbl(int appno,String appNOString, String Type, String nature, Double alocation, Double txt, int payapp,String Tname) {
        
        this.appno = appno;
        this.appNOString = new SimpleStringProperty(appNOString);
        this.Type = new SimpleStringProperty(Type);
        this.nature = new SimpleStringProperty(nature);
        this.alocation = alocation;
        this.txt = txt;
        this.payapp = payapp;
        this.Tname = new SimpleStringProperty(Tname);
    }

    /**
     * @return the appno
     */
    public int getAppno() {
        return appno;
    }

    /**
     * @param appno the appno to set
     */
    public void setAppno(int appno) {
        this.appno = appno;
    }

    /**
     * @return the Type
     */
    public String getType() {
        return Type.get();
    }

    /**
     * @param Type the Type to set
     */
    public void setType(SimpleStringProperty Type) {
        this.Type = Type;
    }

    /**
     * @return the nature
     */
    public String getNature() {
        return nature.get();
    }

    /**
     * @param nature the nature to set
     */
    public void setNature(SimpleStringProperty nature) {
        this.nature = nature;
    }

    /**
     * @return the alocation
     */
    public Double getAlocation() {
        return alocation;
    }

    /**
     * @param alocation the alocation to set
     */
    public void setAlocation(Double alocation) {
        this.alocation = alocation;
    }

    /**
     * @return the txt
     */
    public Double getTxt() {
        return txt;
    }

    /**
     * @param txt the txt to set
     */
    public void setTxt(Double txt) {
        this.txt = txt;
    }

    /**
     * @return the payapp
     */
    public int getPayapp() {
        return payapp;
    }

    /**
     * @param payapp the payapp to set
     */
    public void setPayapp(int payapp) {
        this.payapp = payapp;
    }

    private int appno;
    private SimpleStringProperty appNOString;
    private SimpleStringProperty Type;
    private SimpleStringProperty nature;
    private Double alocation;
    private Double txt;
    private int payapp;
    private SimpleStringProperty Tname;

    /**
     * @return the Tname
     */
    public String getTname() {
        return Tname.get();
    }

    /**
     * @param Tname the Tname to set
     */
    public void setTname(SimpleStringProperty Tname) {
        this.Tname = Tname;
    }

    /**
     * @return the appNOString
     */
    public String getAppNOString() {
        return appNOString.get();
    }

    /**
     * @param appNOString the appNOString to set
     */
    public void setAppNOString(SimpleStringProperty appNOString) {
        this.appNOString = appNOString;
    }

}
