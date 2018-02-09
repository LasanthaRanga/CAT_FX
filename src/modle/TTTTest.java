/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
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
 * @author RM.LasanthaRanga@gmail.com
 */
public class TTTTest {
    
       private Connection getConnection() {
        try {
            return ((SessionFactoryImplementor) conn.NewHibernateUtil.getSessionFactory()).getConnectionProvider().getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void daily(String day) {
        try {
            String path = "C:\\cat\\report\\daly.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(path);
            HashMap param = new HashMap<String, String>();
            param.put("dinaya", "2018-02-02");
            JasperPrint jp = JasperFillManager.fillReport(jr, param, this.getConnection());

            JasperViewer.viewReport(jp, false);
//            else
//                JasperViewer.viewReport(jp,false);
        } catch (JRException jRException) {
            jRException.printStackTrace();
            Notifications.create()
                    .title("Warning")
                    .text("Can not generate report. Something went wrong.\n(" + jRException.getMessage() + ")")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT).showWarning();
        }
    }

    public static void main(String[] args) {
        System.out.println("String");
    }
    
    
    
    
    
    
}
