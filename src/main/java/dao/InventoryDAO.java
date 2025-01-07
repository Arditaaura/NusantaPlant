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
  public static void menyimpanTanaman(int idtanaman, int i) {
    Connection con = null;
    PreparedStatement st1 = null;
    PreparedStatement st2 = null;

    try {
        con = getCon();

        // Step 1: Cari inventaris_id di mana tanaman_id NULL
        String selectQuery = "SELECT inventaris_id FROM inventaris WHERE tanaman_id IS NULL LIMIT 1";
        st1 = con.prepareStatement(selectQuery);

        ResultSet rs = st1.executeQuery();
        if (rs.next()) {
            int inventarisId = rs.getInt("inventaris_id");
            System.out.println("Inventaris ID yang ditemukan: " + inventarisId);

            // Step 2: Update tanaman_id di inventaris
            String updateQuery = "UPDATE inventaris SET tanaman_id = ? WHERE inventaris_id = ?";
            st2 = con.prepareStatement(updateQuery);
            st2.setInt(1, idtanaman);
            st2.setInt(2, inventarisId);

            int rowsUpdated = st2.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);

            if (rowsUpdated > 0) {
                System.out.println("Tanaman berhasil disimpan.");
            } else {
                System.out.println("Tidak ada baris yang diperbarui.");
            }
        } else {
            System.out.println("Tidak ditemukan inventaris_id yang sesuai.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (st1 != null) st1.close();
            if (st2 != null) st2.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



    
    public static Tanaman[] cekInventori(UUID uid) {
        Tanaman[] cek = new Tanaman[9]; // Inisialisasi array dengan ukuran 9
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
                        cek[i] = new Tanaman(rs.getInt("tanaman_id"));
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

}
