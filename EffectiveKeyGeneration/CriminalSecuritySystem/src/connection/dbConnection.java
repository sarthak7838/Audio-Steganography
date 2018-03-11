/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

public class dbConnection {

    static Connection con = null;
    Statement st = null;
    ResultSet rs = null;

    /** Creates a new instance of ConnectionBean */
    public static Connection makeConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost/imagesecuritysystem", "root", "root");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }

    public static void closeConnection() throws Exception {
        if (con != null) {
            con.close();
        }
    }
}
