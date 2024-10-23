/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static dao.BaseDAO.closeCon;
import static dao.BaseDAO.getCon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import model.User;

/**
 *
 * @author angga
 */
public class UserDAO extends BaseDAO {
    private static PreparedStatement st;
    private static Connection con;

    public static User validate(String username, String password) {
        User u = null;
        try {
            con = getCon();
            String query = "select uid from user where username = '%s' and password = '%s' ";
            query = String.format(query,
                    username,
                    password);
            st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                u = new User(username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeCon(con);
        }
        return u;
    }

    public static User searchByUid(String uid) {
        User u = null;
        try {
            con = getCon();
            String query = "select * from user where uid = '%s'";
            query = String.format(query, uid);

            st = con.prepareStatement(query);
            ResultSet rsUser = st.executeQuery();
            u = new User(rsUser.getString("username"), rsUser.getString("password"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeCon(con);
        }
        return u;
    }

    public static void registerUser(User u) {
        try {
            con = getCon();
            String query = "insert into user"
                    + " values ('%s', '%s', '%s') ";
            query = String.format(query,
                    u.getUid(),
                    u.getUsername(),
                    u.getPassword());
            st = con.prepareStatement(query);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeCon(con);
        }

    }
}
