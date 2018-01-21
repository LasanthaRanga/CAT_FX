/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pojo.Otheritiscat;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class RO {

    public List<String> getRoList() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            Criteria cry = session.createCriteria(pojo.Otheritiscat.class);
            cry.add(Restrictions.eq("catname", "RO"));
            cry.add(Restrictions.eq("statues", 1));
            Otheritiscat ot = (pojo.Otheritiscat) cry.uniqueResult();

            Criteria cryy = session.createCriteria(pojo.UserHasOtheritiscat.class);
            List<pojo.UserHasOtheritiscat> list = cryy.add(Restrictions.eq("otheritiscat", ot)).list();
            ArrayList<String> users = new ArrayList<>();
            for (pojo.UserHasOtheritiscat ol : list) {
                users.add(ol.getUser().getFullName());
            }
            return users;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public pojo.User getRobyRoname(String Roname) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            Criteria cry = session.createCriteria(pojo.User.class);
            cry.add(Restrictions.eq("fullName", Roname));
            pojo.User ot = (pojo.User) cry.uniqueResult();
            return ot;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

}
