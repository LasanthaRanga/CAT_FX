package modle;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pojo.AplicationPayment;
import pojo.Application;
import pojo.Apprualstatues;
import pojo.Otheritiscat;
import pojo.Payment;
import pojo.TradeLicense;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class LoadAppList implements Runnable {

    HashSet<AppHolder> appHList = new HashSet<AppHolder>();

    public HashSet<AppHolder> loadAllAppList() {

        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction().commit();
        try {
            int x = 0;
            List<pojo.Application> list = session.createCriteria(pojo.Application.class).list();

            try {

                for (Application a : list) {
                    x++;
                    AppHolder ah = new AppHolder();
                    ah.setIdApplication(a.getIdApplication());
                    ah.setApplicationNo(a.getApplicationNo());
                    ah.setApplicationDate(a.getApplicationDate());
                    ah.setYear(a.getYear());
                    ah.setMonth(a.getMonth());
                    ah.setAllocation(a.getAllocation());
                    ah.setTradeName(a.getTradeName());
                    ah.setTradeAddress1(a.getTradeAddress1());
                    ah.setTradeAddress2(a.getTradeAddress2());
                    ah.setTradeAddress3(a.getTradeAddress3());
                    ah.setTaxAmount(a.getTaxAmount());
                    ah.setDiscription(a.getDiscription());
                    ah.setStatues(a.getStatues());
                    ah.setApproveToPaymant(a.getApproveToPaymant());
                    ah.setUserLog_app(a.getUserLog().getIdUserLog());

                    if (a.getUser() != null) {
                        ah.setIdRo(a.getUser().getIdUser());
                        ah.setRoName(a.getUser().getFullName());
                    } else {
                        ah.setIdRo(0);
                        ah.setRoName("");

                    }

                    ah.setIdAssessmant(a.getAssessment().getIdAssessment());
                    ah.setAssessmantNO(a.getAssessment().getAssessmentNo());

                    ah.setIdStreet(a.getAssessment().getStreet().getIdStreet());
                    ah.setStreetName(a.getAssessment().getStreet().getStreetName());

                    ah.setIdWard(a.getAssessment().getStreet().getWard().getIdWard());
                    ah.setWardName(a.getAssessment().getStreet().getWard().getWardName());

                    ah.setNature(a.getTradeNature().getNature());
                    ah.setIdNature(a.getTradeNature().getIdTradeNature());

                    if (a.getSubNature() != null) {
                        ah.setSubNature(a.getSubNature().getSubNature());
                        ah.setIdSubNatrue(a.getSubNature().getIdSubNature());
                    } else {
                        ah.setSubNature("");
                        ah.setIdSubNatrue(0);

                    }

                    ah.setIdTradeType(a.getTradeType().getIdTradeType());
                    ah.setTradeTypeName(a.getTradeType().getTypeName());

                    ah.setIdVort(a.getTradeType().getVort().getIdVort());
                    ah.setVortNo(a.getTradeType().getVort().getVoteNo());

                    Set<AplicationPayment> aplicationPayments = a.getAplicationPayments();
                    if (aplicationPayments.size() > 0) {

                        for (AplicationPayment aplicationPayment : aplicationPayments) {
                            Payment payment = aplicationPayment.getPayment();
                            if (payment.getStatus() != 0) {

                                ah.setIdPaymant(payment.getIdPayment());

                                ah.setUserLog_pay(payment.getUserLog().getIdUserLog());
                                ah.setPaymentDate(payment.getPaymentDate());
                                ah.setReceiptNo(payment.getReceiptNo());
                                ah.setTaxAmount_inPaymant(payment.getTaxAmount());
                                ah.setVat(payment.getVat());
                                ah.setNbt(payment.getNbt());
                                ah.setSpamp(payment.getSpamp());
                                ah.setTotaleAmount(payment.getTotaleAmount());
                                ah.setCashCheack(payment.getCashCheack());
                                ah.setStatus_pay(payment.getStatus());
                            }
                        }

                    }

                    Set<TradeLicense> tradeLicenses = a.getTradeLicenses();
                    if (tradeLicenses.size() > 0) {
                        for (TradeLicense tradeLicense : tradeLicenses) {
                            if (tradeLicense.getPayment().getIdPayment() == ah.getIdPaymant()) {
                                ah.setIdTradeLicense(tradeLicense.getIdTradeLicense());
                                ah.setLicenNo(tradeLicense.getLicenNo());
                                ah.setTradeLicenseDate(tradeLicense.getTradeLicenseDate());
                                ah.setStatus_License(tradeLicense.getStatus());

                            }
                        }

                    }

                    Set<Apprualstatues> apprualstatueses = a.getApprualstatueses();
                    if (apprualstatueses.size() > 0) {

                        HashSet<ApprvalHolder> aphList = new HashSet<ApprvalHolder>();

                        for (Apprualstatues apr : apprualstatueses) {
                            ApprvalHolder aprh = new ApprvalHolder();
                            aprh.setIdApprualStatues(apr.getIdApprualStatues());
                            aprh.setIdApplication(a.getIdApplication());
                            aprh.setDescription(apr.getDescription());
                            aprh.setSendDate(apr.getSendDate());
                            aprh.setApproveDate(apr.getApproveDate());
                            aprh.setStatues_approve(apr.getStatues());
                            aprh.setIdOtheritisCat(apr.getIdOtheritisCat());
                            Integer idOtheritisCat = apr.getIdOtheritisCat();
                            Otheritiscat name = (pojo.Otheritiscat) session.createCriteria(pojo.Otheritiscat.class).add(Restrictions.eq("idOtheritisCat", idOtheritisCat)).uniqueResult();
                            aprh.setOtheritisCat(name.getCatname());
                            aprh.setIdUser(apr.getUser().getIdUser());
                            aprh.setFullName(apr.getUser().getFullName());
                            aprh.setNic(apr.getUser().getNic());
                            aprh.setMobile(apr.getUser().getMobile());
                            aprh.setRegDate(apr.getUser().getRegDate());
                            aprh.setStatus(apr.getStatues());
                            aphList.add(aprh);
                        }
                        ah.setApproveList(aphList);

                    }

                    appHList.add(ah);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(x);
            return appHList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public void run() {
        loadAllAppList();
    }

}
