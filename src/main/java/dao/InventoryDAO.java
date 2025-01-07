/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static dao.BaseDAO.getCon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import model.Tanaman;

/**
 *
 * @author Admin
 */
public class InventoryDAO extends BaseDAO{
    private static PreparedStatement st;
    private static Connection con;
    
    public static void makeInventory(UUID uid){
        for(int i = 0; i < 9; i++){
            try {
                String query = String.format(
                        "insert into inventaris (user_id) values ('%s')",
                        uid);
                try (Connection con = getCon();
                     PreparedStatement st = con.prepareStatement(query)) {
                    st.executeUpdate();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void menyimpanTanaman(int idtanaman, int i, UUID uid) {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = getCon();
            String query = "UPDATE inventaris SET tanaman_id = ? WHERE inventaris_id = ( SELECT inventaris_id FROM ( SELECT inventaris_id FROM inventaris WHERE user_id = ? ORDER BY inventaris_id LIMIT 1 OFFSET ?) AS subquery)";
            st = con.prepareStatement(query);
            st.setInt(1, idtanaman);
            st.setString(2, uid.toString()); // Set parameter user_id
            st.setInt(3, i); // Set parameter offset
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeCon(con);
        }
    }

   
    public static int[] cekInventori(UUID uid) {
        int[] cek = new int [9]; // Inisialisasi array dengan ukuran 9
        int i = 0;
        con = getCon(); // Pastikan koneksi diinisialisasi
        String query = "SELECT tanaman_id FROM inventaris WHERE user_id = ? ORDER BY inventaris_id LIMIT 1 OFFSET ?";

        try {
            while (i < 9) {
                try (PreparedStatement st = con.prepareStatement(query)) {
                    st.setString(1, uid.toString()); // Set parameter user_id
                    st.setInt(2, i); // Set parameter offset
                    try (ResultSet rs = st.executeQuery()) {
                        if (rs.next()) {
                            cek[i] =(rs.getInt("tanaman_id"));
                            System.out.println("id: " + rs.getInt("tanaman_id"));
                        }
                    }
                }
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return cek;
    }
    
    public static void hapusTanaman(UUID uid, int index) {
    Connection con = null;
    PreparedStatement st = null;
    
    try {
        con = getCon();
        // Query untuk mengupdate tanaman_id menjadi 0 pada posisi yang sesuai
        String query = "UPDATE inventaris SET tanaman_id = 0 WHERE user_id = ? AND inventaris_id = ("
                     + "SELECT inventaris_id FROM inventaris WHERE user_id = ? ORDER BY inventaris_id LIMIT 1 OFFSET ?)";
        
        st = con.prepareStatement(query);
        st.setString(1, uid.toString()); // Set parameter user_id
        st.setString(2, uid.toString()); // Set parameter user_id
        st.setInt(3, index); // Set parameter offset (index tanaman)

        int rowsAffected = st.executeUpdate();
        
        if (rowsAffected > 0) {
            System.out.println("Tanaman di index " + index + " telah dihapus.");
        } else {
            System.out.println("Gagal menghapus tanaman di index " + index);
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Pastikan untuk menutup koneksi dan statement
        try {
            if (st != null) st.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


}
