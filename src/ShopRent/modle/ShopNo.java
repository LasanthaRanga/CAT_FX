/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShopRent.modle;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Criteria;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pojo.SrShopNo;
import pojo.SrShopRentInfo;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class ShopNo implements SUD<pojo.SrShopNo> {

    @Override
    public boolean isExist(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(SrShopNo t) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        Transaction beginTransaction = session.beginTransaction();
        try {
            session.save(t);
            beginTransaction.commit();
            return true;
        } catch (Exception e) {
            beginTransaction.rollback();
            System.out.println(e.getMessage());
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(SrShopNo t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deactiv(SrShopNo t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SrShopNo> getList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SrShopNo getT_By_Id(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SrShopNo getT_By_name(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isExist(String s, pojo.SrFlow flow) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            Criteria createCriteria = session.createCriteria(pojo.SrShopNo.class);
            createCriteria.add(Restrictions.eq("srFlow", flow));
            createCriteria.add(Restrictions.eq("shopNo", s));
            List list = createCriteria.list();
            return list.size() > 0;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            return true;
        } finally {
            session.close();
        }
    }

    public int shopCount(pojo.SrFlow flow) {
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            Criteria createCriteria = session.createCriteria(pojo.SrShopNo.class);
            createCriteria.add(Restrictions.eq("srFlow", flow));
            Integer count = Integer.parseInt(createCriteria.setProjection(Projections.rowCount()).uniqueResult().toString());
            return count;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            return 0;
        } finally {
            session.close();
        }
    }

    public ObservableList<ShopTable> getList(String buildibng, String floor, String Type, String rent) {
        ObservableList<ShopTable> arList = FXCollections.observableArrayList();
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        try {
            List<SrShopNo> list = session.createCriteria(pojo.SrShopNo.class).list();
            for (SrShopNo shop : list) {

                Criteria cry = session.createCriteria(pojo.SrShopRentInfo.class);
                cry.add(Restrictions.eq("srShopNo", shop));
                cry.add(Restrictions.eq("rentalTypeStatus", 1));
                SrShopRentInfo srShopRentInfo = (pojo.SrShopRentInfo) cry.uniqueResult();
                String cus = "";
                int idShop = 0;
                int idCus = 0;
                if (srShopRentInfo != null) {
                    cus = srShopRentInfo.getCustomer().getFullName();
                    idShop = srShopRentInfo.getIdShopRentInfo();
                    idCus = srShopRentInfo.getCustomer().getIdCustomer();
                }

                if (buildibng != null) {
                    if (shop.getSrFlow().getSrBuilding().getBuildingName().equals(buildibng)) {
                        if (floor != null) {
                            if (shop.getSrFlow().getFlowName().equals(floor)) {
                                if (Type != null) {
                                    if (shop.getSrShopType().getShopType().equals(Type)) {
                                        if (rent != null) {
                                            if (shop.getSrRentalType().getRType().equals(rent)) {
                                                //Rent Eka Hari
                                                arList.add(new ShopTable(
                                                        shop.getSrFlow().getSrBuilding().getIdBuilding(),
                                                        shop.getSrFlow().getSrBuilding().getBuildingName(),
                                                        shop.getSrFlow().getIdFlow(),
                                                        shop.getSrFlow().getFlowName(),
                                                        shop.getSrRentalType().getIdRentalType(),
                                                        shop.getSrRentalType().getRType(),
                                                        shop.getSrShopType().getIdShopType(),
                                                        shop.getSrShopType().getShopType(),
                                                        shop.getIdShopNo(),
                                                        shop.getShopNo(),
                                                        shop.getTradeName(),
                                                        shop.getStatus(),
                                                        idShop, idCus, cus
                                                ));
                                            }
                                        } else {
                                            //Type Eka hari
                                            arList.add(new ShopTable(
                                                    shop.getSrFlow().getSrBuilding().getIdBuilding(),
                                                    shop.getSrFlow().getSrBuilding().getBuildingName(),
                                                    shop.getSrFlow().getIdFlow(),
                                                    shop.getSrFlow().getFlowName(),
                                                    shop.getSrRentalType().getIdRentalType(),
                                                    shop.getSrRentalType().getRType(),
                                                    shop.getSrShopType().getIdShopType(),
                                                    shop.getSrShopType().getShopType(),
                                                    shop.getIdShopNo(),
                                                    shop.getShopNo(),
                                                    shop.getTradeName(),
                                                    shop.getStatus(),
                                                    idShop, idCus, cus
                                            ));
                                        }
                                    }
                                } else {
                                    //Flor eka hari
                                    arList.add(new ShopTable(
                                            shop.getSrFlow().getSrBuilding().getIdBuilding(),
                                            shop.getSrFlow().getSrBuilding().getBuildingName(),
                                            shop.getSrFlow().getIdFlow(),
                                            shop.getSrFlow().getFlowName(),
                                            shop.getSrRentalType().getIdRentalType(),
                                            shop.getSrRentalType().getRType(),
                                            shop.getSrShopType().getIdShopType(),
                                            shop.getSrShopType().getShopType(),
                                            shop.getIdShopNo(),
                                            shop.getShopNo(),
                                            shop.getTradeName(),
                                            shop.getStatus(),
                                            idShop, idCus, cus
                                    ));
                                }
                            }
                        } else {
                            //bulding eka hari
                            arList.add(new ShopTable(
                                    shop.getSrFlow().getSrBuilding().getIdBuilding(),
                                    shop.getSrFlow().getSrBuilding().getBuildingName(),
                                    shop.getSrFlow().getIdFlow(),
                                    shop.getSrFlow().getFlowName(),
                                    shop.getSrRentalType().getIdRentalType(),
                                    shop.getSrRentalType().getRType(),
                                    shop.getSrShopType().getIdShopType(),
                                    shop.getSrShopType().getShopType(),
                                    shop.getIdShopNo(),
                                    shop.getShopNo(),
                                    shop.getTradeName(),
                                    shop.getStatus(),
                                    idShop, idCus, cus
                            ));
                        }
                    }
                } else {
                    //NO filter
                    arList.add(new ShopTable(
                            shop.getSrFlow().getSrBuilding().getIdBuilding(),
                            shop.getSrFlow().getSrBuilding().getBuildingName(),
                            shop.getSrFlow().getIdFlow(),
                            shop.getSrFlow().getFlowName(),
                            shop.getSrRentalType().getIdRentalType(),
                            shop.getSrRentalType().getRType(),
                            shop.getSrShopType().getIdShopType(),
                            shop.getSrShopType().getShopType(),
                            shop.getIdShopNo(),
                            shop.getShopNo(),
                            shop.getTradeName(),
                            shop.getStatus(),
                            idShop, idCus, cus
                    ));
                }

            }

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return arList;
    }

}
