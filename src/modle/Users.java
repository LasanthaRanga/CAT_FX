/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.util.Date;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pojo.Login;
import pojo.User;
import pojo.UserHasCatagory;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class Users {

    /**
     * @return the Catagory
     */
    public String getCatagory() {
        return Catagory;
    }

    /**
     * @param Catagory the Catagory to set
     */
    public void setCatagory(String Catagory) {
        this.Catagory = Catagory;
    }

    /**
     * @return the SQ
     */
    public String getSQ() {
        return SQ;
    }

    /**
     * @param SQ the SQ to set
     */
    public void setSQ(String SQ) {
        this.SQ = SQ;
    }

    /**
     * @return the idUser
     */
    public Integer getIdUser() {
        return idUser;
    }

    /**
     * @param idUser the idUser to set
     */
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the nic
     */
    public String getNic() {
        return nic;
    }

    /**
     * @param nic the nic to set
     */
    public void setNic(String nic) {
        this.nic = nic;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the regDate
     */
    public Date getRegDate() {
        return regDate;
    }

    /**
     * @param regDate the regDate to set
     */
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    /**
     * @return the sq
     */
    public String getSq() {
        return sq;
    }

    /**
     * @param sq the sq to set
     */
    public void setSq(String sq) {
        this.sq = sq;
    }

    /**
     * @return the sqa
     */
    public String getSqa() {
        return sqa;
    }

    /**
     * @param sqa the sqa to set
     */
    public void setSqa(String sqa) {
        this.sqa = sqa;
    }

    /**
     * @return the statusUser
     */
    public Integer getStatusUser() {
        return statusUser;
    }

    /**
     * @param statusUser the statusUser to set
     */
    public void setStatusUser(Integer statusUser) {
        this.statusUser = statusUser;
    }

    /**
     * @return the synUser
     */
    public Integer getSynUser() {
        return synUser;
    }

    /**
     * @param synUser the synUser to set
     */
    public void setSynUser(Integer synUser) {
        this.synUser = synUser;
    }

    /**
     * @return the idLogin
     */
    public Integer getIdLogin() {
        return idLogin;
    }

    /**
     * @param idLogin the idLogin to set
     */
    public void setIdLogin(Integer idLogin) {
        this.idLogin = idLogin;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the uname
     */
    public String getUname() {
        return uname;
    }

    /**
     * @param uname the uname to set
     */
    public void setUname(String uname) {
        this.uname = uname;
    }

    /**
     * @return the pword
     */
    public String getPword() {
        return pword;
    }

    /**
     * @param pword the pword to set
     */
    public void setPword(String pword) {
        this.pword = pword;
    }

    /**
     * @return the statusLog
     */
    public Integer getStatusLog() {
        return statusLog;
    }

    /**
     * @param statusLog the statusLog to set
     */
    public void setStatusLog(Integer statusLog) {
        this.statusLog = statusLog;
    }

    /**
     * @return the synLog
     */
    public Integer getSynLog() {
        return synLog;
    }

    /**
     * @param synLog the synLog to set
     */
    public void setSynLog(Integer synLog) {
        this.synLog = synLog;
    }

    private Integer idUser;
    private String fullName;
    private String nic;
    private String mobile;
    private Date regDate;
    private String sq;
    private String sqa;
    private Integer statusUser;
    private Integer synUser;

    private Integer idLogin;
    private User user;
    private String uname;
    private String pword;
    private Integer statusLog;
    private Integer synLog;

    private String Catagory;
    private String SQ;

    public void saveUser() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            User us = new pojo.User();
            us.setFullName(getFullName());
            us.setNic(getNic());
            us.setMobile(getMobile());
            us.setSq(getSQ());
            us.setSqa(getSqa());
            us.setStatus(1);
            us.setSyn(1);
            us.setRegDate(new Date());

            session.save(us);

            Login login = new pojo.Login(us, getUname(), getPword(), 1, 1);
            session.save(login);

            pojo.Catagory cat = (pojo.Catagory) session.createCriteria(pojo.Catagory.class).add(Restrictions.eq("catagoryName", getCatagory())).uniqueResult();
            UserHasCatagory userHasCatagory = new pojo.UserHasCatagory(cat, us,1,1);

            session.save(userHasCatagory);

            session.beginTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateUser() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            User us = new pojo.User();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deactiveUser() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            User us = new pojo.User();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void search() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            User us = new pojo.User();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
