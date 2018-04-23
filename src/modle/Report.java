/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import javafx.geometry.Pos;
import javafx.util.Duration;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.controlsfx.control.Notifications;
import org.hibernate.engine.spi.SessionFactoryImplementor;

/**
 *
 * @author suhada
 */
public class Report {

    private Connection getConnection() {
        try {
            return ((SessionFactoryImplementor) conn.NewHibernateUtil.getSessionFactory()).getConnectionProvider().getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void paymentReceipt(int paymentId, boolean print) {
        try {
            String path = "C:\\cat\\report\\PrintBill.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(path);
            HashMap param = new HashMap<String, Integer>();
            param.put("paymentId", paymentId);
            JasperPrint jp = JasperFillManager.fillReport(jr, param, this.getConnection());
            if (print) {
                JasperViewer.viewReport(jp, false);
            } else {
                JasperViewer.viewReport(jp, false);
            }
        } catch (JRException jRException) {
            jRException.printStackTrace();
            Notifications.create()
                    .title("Warning")
                    .text("Can not generate report. Something went wrong.\n(" + jRException.getMessage() + ")")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT).showWarning();
        }
    }

    public void dayEnd(String date, boolean print) {
        try {
            String path = "C:\\cat\\report\\dayEnd.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(path);
            HashMap param = new HashMap<String, Integer>();
            param.put("date_para", date);
            JasperPrint jp = JasperFillManager.fillReport(jr, param, this.getConnection());
            if (print) {
                JasperViewer.viewReport(jp, false);
            } else {
                JasperViewer.viewReport(jp, false);
            }
        } catch (JRException jRException) {
            jRException.printStackTrace();
            Notifications.create()
                    .title("Warning")
                    .text("Can not generate report. Something went wrong.\n(" + jRException.getMessage() + ")")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT).showWarning();
        }
    }

    public void tradeLicense(String risit_no, boolean print) {
        try {
            String path = "C:\\cat\\report\\TradeLicense.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(path);
            HashMap param = new HashMap<String, Integer>();
            param.put("risit_no", risit_no);
            JasperPrint jp = JasperFillManager.fillReport(jr, param, this.getConnection());
            if (print) {
                JasperViewer.viewReport(jp, false);
            } else {
                JasperViewer.viewReport(jp, false);
            }
        } catch (JRException jRException) {
            jRException.printStackTrace();
            Notifications.create()
                    .title("Warning")
                    .text("Can not generate report. Something went wrong.\n(" + jRException.getMessage() + ")")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT).showWarning();
        }
    }

    public void ariasLatterWithTradeType(String end_date, String TradeType) {

        try {
            String path = "C:\\cat\\report\\arias_later.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(path);
            HashMap param = new HashMap<String, Integer>();
            param.put("idList", "1");
            param.put("pay_date", end_date);
            param.put("TradeType", TradeType);
            JasperPrint jp = JasperFillManager.fillReport(jr, param, this.getConnection());
            JasperViewer.viewReport(jp, false);
        } catch (JRException jRException) {
            jRException.printStackTrace();
            Notifications.create()
                    .title("Warning")
                    .text("Can not generate report. Something went wrong.\n(" + jRException.getMessage() + ")")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT).showWarning();
        }

    }

    public void ledger(String start, String end) {
        try {
            String path = "C:\\cat\\report\\tl_paymant.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(path);
            HashMap param = new HashMap<String, Integer>();
            param.put("start_date", start);
            param.put("end_date", end);
            JasperPrint jp = JasperFillManager.fillReport(jr, param, this.getConnection());
            JasperViewer.viewReport(jp, false);
        } catch (JRException jRException) {
            jRException.printStackTrace();
            Notifications.create()
                    .title("Warning")
                    .text("Can not generate report. Something went wrong.\n(" + jRException.getMessage() + ")")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT).showWarning();
        }
    }

    public void cancle(String start, String end) {
        try {
            String path = "C:\\cat\\report\\tl_paymant_cancle.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(path);
            HashMap param = new HashMap<String, Integer>();
            param.put("start_date", start);
            param.put("end_date", end);
            JasperPrint jp = JasperFillManager.fillReport(jr, param, this.getConnection());
            JasperViewer.viewReport(jp, false);
        } catch (JRException jRException) {
            jRException.printStackTrace();
            Notifications.create()
                    .title("Warning")
                    .text("Can not generate report. Something went wrong.\n(" + jRException.getMessage() + ")")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT).showWarning();
        }
    }

    public void paymant_with_RO(String start, String end, String RO) {

        try {
            String path = "C:\\cat\\report\\tl_paymant_withRO.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(path);
            HashMap param = new HashMap<String, Integer>();
            param.put("start_date", start);
            param.put("end_date", end);
            param.put("RO", RO);
            JasperPrint jp = JasperFillManager.fillReport(jr, param, this.getConnection());
            JasperViewer.viewReport(jp, false);
        } catch (JRException jRException) {
            jRException.printStackTrace();
            Notifications.create()
                    .title("Warning")
                    .text("Can not generate report. Something went wrong.\n(" + jRException.getMessage() + ")")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT).showWarning();
        }

    }

    public void ariasLatter(String RO, String pay, String TradeType) {

        try {
            String path = "C:\\cat\\report\\arias_later_with_RO.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(path);
            HashMap param = new HashMap<String, Integer>();
            param.put("idList", "1");
            param.put("RO", RO);
            param.put("pay_date", pay);
            param.put("TradeType", TradeType);
            JasperPrint jp = JasperFillManager.fillReport(jr, param, this.getConnection());
            JasperViewer.viewReport(jp, false);
        } catch (JRException jRException) {
            jRException.printStackTrace();
            Notifications.create()
                    .title("Warning")
                    .text("Can not generate report. Something went wrong.\n(" + jRException.getMessage() + ")")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT).showWarning();
        }

    }

    public void ariasLatterOne(String appid, String pay) {

        try {
            String path = "C:\\cat\\report\\arias_later_one_by_one.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(path);
            HashMap param = new HashMap<String, Integer>();
            param.put("idList", appid);
            param.put("pay_date", pay);
            JasperPrint jp = JasperFillManager.fillReport(jr, param, this.getConnection());
            JasperViewer.viewReport(jp, false);
        } catch (JRException jRException) {
            jRException.printStackTrace();
            Notifications.create()
                    .title("Warning")
                    .text("Can not generate report. Something went wrong.\n(" + jRException.getMessage() + ")")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT).showWarning();
        }

    }

//date_para
}
