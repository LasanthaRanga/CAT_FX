/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class Export_TO_Excel {

    private HashSet<AppHolder> set;

    /**
     * @return the set
     */
    public HashSet<AppHolder> getSet() {
        return set;
    }

    /**
     * @param set the set to set
     */
    public void setSet(HashSet<AppHolder> set) {
        this.set = set;
        ItarateHashSet();
    }

    String idlist = "";

    public void ItarateHashSet() {
        int size = getSet().size();

        ArrayList<String> arrayList = new ArrayList<String>();

        for (AppHolder appHolder : getSet()) {
            arrayList.add(appHolder.getIdApplication() + "");
        }

        for (int i = 0; i < arrayList.size(); i++) {
            if (i == arrayList.size() - 1) {
                idlist += arrayList.get(i);
            } else {
                idlist += arrayList.get(i) + ",";
            }
        }
        System.out.println(idlist);
        getColomName();

    }

    public void getColomName() {

        try {
            ResultSet data1 = conn.DB.getData("SELECT\n"
                    + "application.idApplication,\n"
                    + "application.application_no,\n"
                    + "application.application_date,\n"
                    // + "application.allocation,\n"
                    + "application.trade_name,\n"
                    + "application.trade_address1,\n"
                    + "application.trade_address2,\n"
                    + "application.trade_address3,\n"
                    // + "application.trade_nature,\n"
                    //+ "application.tax_amount,\n"
                    // + "application.discription,\n"
                    + "customer.full_name,\n"
                    + "customer.nic,\n"
                    + "contact.phone,\n"
                    + "contact.mobile,\n"
                    // + "contact.email,\n"
                    + "contact.address1,\n"
                    + "contact.address2,\n"
                    + "contact.address3,\n"
                    + "contact.city,\n"
                    + "assessment.assessment_no,\n"
                    + "street.street_name,\n"
                    + "ward.ward_name,\n"
                   // + "payment.payment_date,\n"
                   // + "payment.receipt_no,\n"
                   // + "payment.tax_amount,\n"
                   // + "payment.vat,\n"
                   // + "payment.nbt,\n"
                   // + "payment.spamp,\n"
                   // + "payment.totale_amount,\n"
                   // + "payment.`CASH CHEACK`,\n"
                  //  + "payment.`status`,\n"
                   // + "cash_flow.cash,\n"
                   // + "cash_flow.cheque,\n"
                   // + "cash_flow.cheque_no,\n"
                   // + "vort.vote_no,\n"
                   // + "vort.vote_current_balance,\n"
                   // + "cheque_bank.BankName,\n"
                   // + "cash_flow.cf_date,\n"
                   // + "trade_nature.nature,\n"
                    + "trade_type.type_name,\n"
                    + "`user`.full_name\n"
                    + "FROM\n"
                    + "application\n"
                    + "INNER JOIN customer ON application.Customer_idCustomer = customer.idCustomer\n"
                    + "INNER JOIN contact ON contact.Customer_idCustomer = customer.idCustomer\n"
                    + "INNER JOIN assessment ON application.Assessment_idAssessment = assessment.idAssessment\n"
                    + "INNER JOIN street ON assessment.Street_idStreet = street.idStreet\n"
                    + "INNER JOIN ward ON street.Ward_idWard = ward.idWard\n"
                    + "LEFT JOIN aplication_payment ON aplication_payment.Application_idApplication = application.idApplication\n"
                    + "LEFT JOIN payment ON aplication_payment.Payment_idPayment = payment.idPayment\n"
                    + "LEFT JOIN cash_flow ON payment.receipt_no = cash_flow.recipt_no\n"
                    + "LEFT JOIN vort ON cash_flow.Vort_idVort = vort.idVort\n"
                    + "LEFT JOIN cheque_bank ON cash_flow.Cheque_Bank_idCheque_Bank = cheque_bank.idCheque_Bank\n"
                    + "INNER JOIN trade_nature ON application.Trade_Nature_idTrade_Nature = trade_nature.idTrade_Nature\n"
                    + "INNER JOIN trade_type ON trade_nature.Trade_Type_idTrade_Type = trade_type.idTrade_Type\n"
                    + "INNER JOIN `user` ON application.User_idUser = `user`.idUser\n"
                    + "WHERE\n"
                    + "application.idApplication IN (" + idlist + ")");

            String[] colname = {"#id",
                "application_no",
                "application_date",
                //"application.allocation",
                "trade_name",
                "trade_address1",
                "trade_address2",
                "trade_address3",
                //"application.trade_nature",
               // "application.tax_amount",
                // "application.discription",
                "customer full_name",
                "customer nic",
                "contact phone",
                "contact mobile",
                //"contact.email",
                "address1",
                "address2",
                "address3",
                "contact.city",
                "assessment_no",
                "street_name",
                "ward_name",
                //"payment.payment_date",
                //"payment.receipt_no",
                //"payment.tax_amount",
               // "payment.vat",
               // "payment.nbt",
                //"payment.spamp",
                //"payment.totale_amount",
                //"payment.`CASH CHEACK`",
               // "payment.`status`",
               // "cash_flow.cash",
               // "cash_flow.cheque",
               // "cash_flow.cheque_no",
               // "vort.vote_no",
               // "vort.vote_current_balance",
               // "cheque_bank.BankName",
               // "cash_flow.cf_date",
               // "trade_nature.nature",
                "trade_type",
                " "
            };

            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet ws = wb.createSheet();

            TreeMap<String, Object[]> tm = new TreeMap<>();
            tm.put("-1", colname);

            int x = 0;
            while (data1.next()) {
                tm.put(x + "", new Object[]{data1.getString(1),
                    data1.getString(2),
                    data1.getString(3),
                    data1.getString(4),
                    data1.getString(5),
                    data1.getString(6),
                    data1.getString(7),
                    data1.getString(8),
                    data1.getString(9),
                    data1.getString(10),
                    data1.getString(11),
                    data1.getString(12),
                    data1.getString(13),
                    data1.getString(14),
                    data1.getString(15),
                    data1.getString(16),
                    data1.getString(17),
                    data1.getString(18),
                    data1.getString(19),
                });
                x++;
            }

            Set<String> ids = tm.keySet();
            XSSFRow row;
            int rowId = 0;

            for (String id : ids) {
                row = ws.createRow(rowId++);
                Object[] values = tm.get(id);
                int celID = 0;
                for (Object value : values) {
                    Cell cell = row.createCell(celID);
                    String s = "";
                    if (value == null) {
                        cell.setCellValue(s);
                    } else {
                        cell.setCellValue(value.toString());
                    }

                    celID++;
                }
            }
            FileOutputStream fos = new FileOutputStream(new File("D:\\EXCEL\\Report.xlsx"));
            wb.write(fos);
            fos.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
