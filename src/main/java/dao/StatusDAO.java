/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static dao.BaseDAO.closeCon;
import static dao.BaseDAO.getCon;
import static dao.TanamanDAO.TransaksiBibit;
import model.Tanaman;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
/**
 *
 * @author Admin
 */
public class StatusDAO extends BaseDAO{
    private static PreparedStatement st;
    private static Connection con;
    
    
    public static void insertStatus(UUID uid, String tanaman_id){
        try {
            String query = String.format(
                    "insert into status (user_id, tanaman_id, umur_sekarang,  kesehatan_sekarang, lapar_sekarang, haus_sekarang) values ('%s', '%s', 0, 0, 0, 0)",
                    uid,
                    tanaman_id);
            try (Connection con = getCon();
                 PreparedStatement st = con.prepareStatement(query)) {
                st.executeUpdate();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //UPDATE status SET  = nilai_baru1, kolom2 = nilai_baru2 WHERE kondisi
    public static int[] getStatus(UUID uid){
        int[] status = new int[4];
        try {
            con = getCon();
            String query = "select umur_sekarang, kesehatan_sekarang, lapar_sekarang, haus_sekarang from status where user_id = '%s'";
            query = String.format(query, uid);

            st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                status[0] = rs.getInt("umur_sekarang");
                status[1] = rs.getInt("haus_sekarang");
                status[2] = rs.getInt("lapar_sekarang");
                status[3] = rs.getInt("kesehatan_sekarang");
            } else {
            System.out.println("Data tidak ditemukan untuk user_id: " + uid);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeCon(con);
        }
        return status;
    }
    public static void setStatusSehat(int sehat, UUID uid){
        try {
            con = getCon();
            String query = "UPDATE status SET kesehatan_sekarang ='%s' WHERE user_id ='%s'";
            query = String.format(query, sehat, uid);

            st = con.prepareStatement(query);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeCon(con);
        }
    }
    public static void setStatusLapar(int lapar, UUID uid){
        try {
            con = getCon();
            String query = "UPDATE status SET lapar_sekarang ='%s' WHERE user_id ='%s'";
            query = String.format(query, lapar, uid);

            st = con.prepareStatement(query);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeCon(con);
        }
    }
    public static void setStatusHaus(int haus, UUID uid){
        try {
            con = getCon();
            String query = "UPDATE status SET haus_sekarang ='%s' WHERE user_id ='%s'";
            query = String.format(query, haus, uid);

            st = con.prepareStatement(query);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeCon(con);
        }
    }
    public static void setStatusUmur(int umur, UUID uid){
        try {
            con = getCon();
            String query = "UPDATE status SET umur_sekarang ='%s' WHERE user_id ='%s'";
            query = String.format(query, umur, uid);

            st = con.prepareStatement(query);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeCon(con);
        }
    }
    
}
