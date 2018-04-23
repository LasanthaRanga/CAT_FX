/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Punnajee
 */
public class DB {

    static Connection c = null;

    public static java.sql.Connection getConnection() throws Exception {
        if (c == null) {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/cat", "root", "Yamaha510");
            
        }
        return c;
    }

    public static int setData(String sql) throws Exception {
        int row = DB.getConnection().createStatement().executeUpdate(sql);
        System.out.println("===============\n"+sql+"\n====================");
        return row;
    }

    public static ResultSet getData(String sql) throws Exception {
        ResultSet executeQuery = DB.getConnection().createStatement().executeQuery(sql);
        System.out.println("===============\n"+sql+"\n====================");
        return executeQuery;
    }

}
