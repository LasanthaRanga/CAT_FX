/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;



/**
 *
 * @author suhada
 */
public class Report {

//    private Connection getConnection() {
//        try {
//            return ((SessionFactoryImplementor) conn.NewHibernateUtil.getSessionFactory()).getConnectionProvider().getConnection();
//        } catch (SQLException ex) {
//            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//    }
//
//    public void paymentReceipt(int paymentId) {
//        try {
//            String path = "C:\\cat\\report\\paymentReceipt.jrxml";
//            JasperReport jr = JasperCompileManager.compileReport(path);
//            HashMap param=new HashMap<String, Integer>();
//            param.put("paymentId", paymentId);
//            JasperPrint jp = JasperFillManager.fillReport(jr, param, this.getConnection());
//            JasperViewer.viewReport(jp);
//        } catch (JRException jRException) {
//            Notifications.create()
//                    .title("Warning")
//                    .text("Can not generate report. Something went wrong.\n(" + jRException.getMessage() + ")")
//                    .hideAfter(Duration.seconds(3))
//                    .position(Pos.BOTTOM_RIGHT).showWarning();
//        }
//    }

}
