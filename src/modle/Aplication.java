/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.poi.hssf.record.chart.DatRecord;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import pojo.Application;
import pojo.Apprualstatues;
import pojo.Customer;
import pojo.User;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class Aplication implements DAO<pojo.Application> {

    @Override
    public boolean save(Application t) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {
            session.save(t);
            bt.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            bt.rollback();
            return false;
        } finally {
            session.close();
        }

    }

    public boolean update(Application t) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {
            session.update(t);
            bt.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            bt.rollback();
            return false;
        } finally {
            session.close();
        }

    }

    @Override
    public boolean save(List<Application> list) {

        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            bt.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    public boolean updateApp(Application t) {

        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {
            session.update(t);
            //       session.flush();
            bt.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            bt.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(List<Application> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveOrUpdate(Application t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveOrUpdate(List<Application> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Application t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Application> getList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getLastApllicationID() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {
            Criteria c = session.createCriteria(pojo.Application.class);
            c.addOrder(Order.desc("idApplication"));
            c.setMaxResults(1);
            Application name = (pojo.Application) c.uniqueResult();
            int id = 0;
            if (name != null) {
                id = name.getIdApplication();
            }

            return id + 1;
        } catch (Exception e) {
            e.printStackTrace();
            bt.rollback();
            return 0;
        } finally {
            session.close();
        }
    }

    public List<pojo.Application> getUnpaiedApprovedApplications() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            List<pojo.Application> list = session.createCriteria(pojo.Application.class)
                    .add(Restrictions.eq("approveToPaymant", 1))
                    .add(Restrictions.eq("statues", 1))
                    .setFetchMode("aplicationPayments", FetchMode.JOIN)
                    .setFetchMode("tradeType", FetchMode.JOIN)
                    .setFetchMode("customer", FetchMode.JOIN)
                    .setFetchMode("apprualstatueses", FetchMode.JOIN)
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .list();
            ArrayList<Application> ap_list = new ArrayList<pojo.Application>();
            for (Application application : list) {
                //if (application.getAplicationPayments().size() == 0) {
                ap_list.add(application);
                // }
            }
            return ap_list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
    public List<pojo.Application> getUnpaiedApprovedApplications(Date date) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            List<pojo.Application> list = session.createCriteria(pojo.Application.class)
                    .add(Restrictions.eq("applicationDate", date))
                    .add(Restrictions.eq("approveToPaymant", 1))
                    .add(Restrictions.eq("statues", 1))
                    .setFetchMode("aplicationPayments", FetchMode.JOIN)
                    .setFetchMode("tradeType", FetchMode.JOIN)
                    .setFetchMode("customer", FetchMode.JOIN)
                    .setFetchMode("apprualstatueses", FetchMode.JOIN)
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .list();
            ArrayList<Application> ap_list = new ArrayList<pojo.Application>();
            for (Application application : list) {
                //if (application.getAplicationPayments().size() == 0) {
                ap_list.add(application);
                // }
            }
            return ap_list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public List<modle.AppTbl> getAppListToTable(int paid, String appno) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            Criteria c = session.createCriteria(pojo.Application.class);
            if (appno != null) {
                c.add(Restrictions.eq("applicationNo", appno));
            }
            c.add(Restrictions.eq("statues", 1));
            c.add(Restrictions.eq("approveToPaymant", paid));
            List<pojo.Application> list = c.list();
            ArrayList<AppTbl> normal = new ArrayList<modle.AppTbl>();
            for (Application application : list) {
                normal.add(new AppTbl(application.getIdApplication(), application.getApplicationNo(), application.getTradeType().getTypeName(), application.getTradeNature().getNature(), application.getAllocation(), application.getTaxAmount(), application.getApproveToPaymant(), application.getTradeName()));
            }
            return normal;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public List<modle.AppTbl> getAppListToTableForOtho() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            List<pojo.Application> list = session.createCriteria(pojo.Application.class).list();
            ArrayList<AppTbl> ap_list = new ArrayList<modle.AppTbl>();
            for (Application application : list) {

                if (application.getApproveToPaymant() == 0) { // Paymant Walata Approv karapu nethi

                    Set<Apprualstatues> app = application.getApprualstatueses();
                    for (Apprualstatues apprualstatues : app) {
                        Integer idoc = apprualstatues.getIdOtheritisCat();
                        int idOc = AuthUser.getIdOc();
                        if (idoc == idOc) {// othoriti eka samanada beluwa log wela inna kenara
                            Integer statues = apprualstatues.getStatues();
                            if (statues == 0) {// approv karala nethi application
                                ap_list.add(new AppTbl(application.getIdApplication(), application.getApplicationNo(), application.getTradeType().getTypeName(), application.getTradeNature().getNature(), application.getAllocation(), application.getTaxAmount(), application.getApproveToPaymant(), application.getTradeName()));
                            }
                        }

                    }
                }

            }
            return ap_list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public List<modle.AppTbl> getAppListToTableForOtho_Approve() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            List<pojo.Application> list = session.createCriteria(pojo.Application.class).list();
            ArrayList<AppTbl> ap_list = new ArrayList<modle.AppTbl>();
            for (Application application : list) {

                if (application.getApproveToPaymant() == 0) { // Paymant Walata Approv karapu nethi

                    Set<Apprualstatues> app = application.getApprualstatueses();
                    for (Apprualstatues apprualstatues : app) {
                        Integer idoc = apprualstatues.getIdOtheritisCat();
                        int idOc = AuthUser.getIdOc();
                        if (idoc == idOc) {// othoriti eka samanada beluwa log wela inna kenara
                            Integer statues = apprualstatues.getStatues();
                            if (statues == 1) {// approv karala nethi application
                                ap_list.add(new AppTbl(application.getIdApplication(), application.getApplicationNo(), application.getTradeType().getTypeName(), application.getTradeNature().getNature(), application.getAllocation(), application.getTaxAmount(), application.getApproveToPaymant(), application.getTradeName()));
                            }
                        }

                    }
                }

            }
            return ap_list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public List<modle.AppTbl> getAppListToTableForOtho_None() {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            List<pojo.Application> list = session.createCriteria(pojo.Application.class).list();
            ArrayList<AppTbl> ap_list = new ArrayList<modle.AppTbl>();
            for (Application application : list) {

                if (application.getApproveToPaymant() == 0) { // Paymant Walata Approv karapu nethi

                    Set<Apprualstatues> app = application.getApprualstatueses();
                    for (Apprualstatues apprualstatues : app) {
                        Integer idoc = apprualstatues.getIdOtheritisCat();
                        int idOc = AuthUser.getIdOc();
                        if (idoc == idOc) {// othoriti eka samanada beluwa log wela inna kenara
                            Integer statues = apprualstatues.getStatues();
                            if (statues == 2) {// approv karala nethi application
                                ap_list.add(new AppTbl(application.getIdApplication(), application.getApplicationNo(), application.getTradeType().getTypeName(), application.getTradeNature().getNature(), application.getAllocation(), application.getTaxAmount(), application.getApproveToPaymant(), application.getTradeName()));
                            }
                        }

                    }
                }

            }
            return ap_list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public pojo.Application getApllicationPojoByID(int no) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {
            Criteria c = session.createCriteria(pojo.Application.class);
            pojo.Application uniqueResult = (pojo.Application) c.add(Restrictions.eq("idApplication", no)).uniqueResult();
            return uniqueResult;
        } catch (Exception e) {
            e.printStackTrace();
            bt.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public pojo.Application getApllicationPojoByApplicationNo(String no) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {
            Criteria c = session.createCriteria(pojo.Application.class);
            List<pojo.Application> list = c.add(Restrictions.eq("applicationNo", no)).list();
            pojo.Application uniqueResult = list.get(0);
            return uniqueResult;
        } catch (Exception e) {
            e.printStackTrace();
            bt.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public pojo.Apprualstatues getApproveStatus(int no) {
        System.out.println(no);
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {
            Criteria c = session.createCriteria(pojo.Apprualstatues.class);
            pojo.Apprualstatues uniqueResult = (pojo.Apprualstatues) c.add(Restrictions.eq("idApprualStatues", no)).uniqueResult();
            System.out.println(uniqueResult.getApproveDate());
            return uniqueResult;
        } catch (Exception e) {
            e.printStackTrace();
            bt.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public pojo.Application getByIdFull(int id) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction bt = session.beginTransaction();
        try {
            pojo.Application uniqueResult = (pojo.Application) session.createCriteria(pojo.Application.class)
                    .add(Restrictions.eq("idApplication", id))
                    .setFetchMode("tradeType", FetchMode.JOIN)
                    .setFetchMode("tradeNature", FetchMode.JOIN)
                    .setFetchMode("subNature", FetchMode.JOIN)
                    .setFetchMode("aplicationPayments", FetchMode.JOIN)
                    .setFetchMode("customer", FetchMode.JOIN)
                    .setFetchMode("user", FetchMode.JOIN)
                    .setFetchMode("apprualstatueses", FetchMode.JOIN)
                    .setFetchMode("tradeLicenses", FetchMode.JOIN)
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .uniqueResult();
            return uniqueResult;
        } catch (Exception e) {
            e.printStackTrace();
            bt.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public List<modle.AppTbl> getAppListByCustomer(int idCustomer) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            Customer customer = (pojo.Customer) session.load(pojo.Customer.class, idCustomer);
            Criteria c = session.createCriteria(pojo.Application.class);
            c.add(Restrictions.eq("statues", 1));
            c.add(Restrictions.eq("customer", customer));
            List<pojo.Application> list = c.list();
            ArrayList<AppTbl> normal = new ArrayList<modle.AppTbl>();
            for (Application application : list) {
                normal.add(new AppTbl(application.getIdApplication(), application.getApplicationNo(), application.getTradeType().getTypeName(), application.getTradeNature().getNature(), application.getAllocation(), application.getTaxAmount(), application.getApproveToPaymant(), application.getTradeName()));
            }
            return normal;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public List<modle.AppTbl> getAppListByCustomerThisYear(int idCustomer) {
        String format = new SimpleDateFormat("yyyy").format(new java.util.Date());

        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            Customer customer = (pojo.Customer) session.load(pojo.Customer.class, idCustomer);
            Criteria c = session.createCriteria(pojo.Application.class);
            c.add(Restrictions.eq("statues", 1));
            c.add(Restrictions.eq("year", Integer.parseInt(format)));
            c.add(Restrictions.eq("customer", customer));
            List<pojo.Application> list = c.list();
            ArrayList<AppTbl> normal = new ArrayList<modle.AppTbl>();
            for (Application application : list) {
                normal.add(new AppTbl(application.getIdApplication(), application.getApplicationNo(), application.getTradeType().getTypeName(), application.getTradeNature().getNature(), application.getAllocation(), application.getTaxAmount(), application.getApproveToPaymant(), application.getTradeName()));
            }
            return normal;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public boolean hasApplication(String appNO) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            Criteria c = session.createCriteria(pojo.Application.class);
            c.add(Restrictions.eq("applicationNo", appNO));
            List<pojo.Application> list = c.list();
            if (list.size() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        } finally {
            session.close();
        }
    }

    public List<pojo.Application> getApplicationNoList(String TradeType) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            pojo.TradeType uniqueResult = (pojo.TradeType) session.createCriteria(pojo.TradeType.class).add(Restrictions.eq("typeName", TradeType)).uniqueResult();
            Criteria criteria = session.createCriteria(pojo.Application.class);
            List<pojo.Application> list = criteria.add(Restrictions.eq("tradeType", uniqueResult)).list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public List<pojo.Application> getApplicationNoList(String TradeType, String appNo, String Ro) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {

            Criteria criteria = session.createCriteria(pojo.Application.class);
            if (TradeType != null) {
                pojo.TradeType uniqueResult = (pojo.TradeType) session.createCriteria(pojo.TradeType.class).add(Restrictions.eq("typeName", TradeType)).uniqueResult();
                criteria.add(Restrictions.eq("tradeType", uniqueResult));
            }
            if (appNo != null) {
                criteria.add(Restrictions.like("applicationNo", appNo, MatchMode.START));
            }
            if (Ro != null) {
                User user = (pojo.User) session.createCriteria(pojo.User.class).add(Restrictions.eq("fullName", Ro)).uniqueResult();
                criteria.add(Restrictions.eq("user", user));
            }
            List<pojo.Application> list = criteria.list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

}
