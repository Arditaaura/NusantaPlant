package dao;

import static dao.BaseDAO.closeCon;
import static dao.BaseDAO.getCon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import model.User;
import model.Tanaman;

/**
 *
 * @author angga
 */
public class UserDAO extends BaseDAO {
    private static PreparedStatement st;
    private static Connection con;

    public static User validate(String username, String password) {
        User u = null;
        String query = "select user_id from user where username = ? and password = ? ";
        try {
            con = getCon();
            st = con.prepareStatement(query);
            st.setString(1, username);
            st.setString(2, password);
            try(ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    String userIdString = rs.getString("user_id");
                    UUID userId = UUID.fromString(userIdString);
                    u = new User(username, password, userId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }
    public static User searchByUid(String uid) {
        User u = null;
        try {
            con = getCon();
            String query = "select * from user where user_id = '%s'";
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
            String query = String.format(
                    "insert into user (user_id, username, password) values ('%s', '%s', '%s')",
                    u.getUid(),
                    u.getUsername(),
                    u.getPassword());
            try (Connection con = getCon();
                 PreparedStatement st = con.prepareStatement(query)) {
                st.executeUpdate();
            }
        }
        catch (SQLException e) { 
            e.printStackTrace();
        }
    }
    
    public static User validateUsername(String username, String password){
        User u = null;
        try {
            con = getCon();
            String query = "select * from user where username = '%s'";
            query = String.format(query, username);

            st = con.prepareStatement(query);
            ResultSet rsUser = st.executeQuery();
            
            if(!rsUser.next()){
                 u = new User(username, password);
            }
           

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeCon(con);
        }
        return u;     
    }

    public static int validateTanaman_id(String u){
        int i = 0;
        try {
            con = getCon();
            String query = "select tanaman_id from user where username = '%s'";
            query = String.format(query, u);

            st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                i = rs.getInt("tanaman_id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeCon(con);
        }
        return i;
    }

    public static void registerTanaman(String u, int i ) {
        try {
            String query = String.format(
                    "UPDATE user SET tanaman_id = '%s' WHERE username = '%s'", i,
                    u);
            try (Connection con = getCon();
                 PreparedStatement st = con.prepareStatement(query)) {
                st.executeUpdate();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void updateStatusTanamanPertama(String username){
        try {
            String query = String.format(
                    "UPDATE user SET statusTanamanPertama = '%s' WHERE username = '%s'", 1,
                    username);
            try (Connection con = getCon();
                 PreparedStatement st = con.prepareStatement(query)) {
                st.executeUpdate();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static int validateTanamanPertama(String username){
        int i = 0;
        try {
            con = getCon();
            String query = "select statusTanamanPertama from user where username = '%s'";
            query = String.format(query, username);

            st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                i = rs.getInt("statusTanamanPertama");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeCon(con);
        }
        return i;
    }
}