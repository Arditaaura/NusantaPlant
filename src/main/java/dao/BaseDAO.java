package dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author AERO
 */
public class BaseDAO {



    private static String DB_NAME = "np";
    private static String DB_HOST = "localhost";
    private static String DB_USER = "root";
    private static String DB_PASS = "";

    public static Connection getCon() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + DB_HOST + ":3306/" + DB_NAME, DB_USER, DB_PASS);
            if (con == null) {
                System.out.println("Connection failed!");
            } else {
                System.out.println("Connection successful!");
            }} catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void closeCon(Connection con) {
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
