package dao;

import model.Tanaman;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TanamanDAO extends BaseDAO {
    private static PreparedStatement st;
    private static Connection con;

    public void MasukkanTanaman(Tanaman tanaman) {
        String query = "INSERT INTO tanaman (tanaman_id, nama, umur, kesehatan, lapar, haus) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = getCon(); PreparedStatement pS = con.prepareStatement(query)) {
            // Set parameter untuk query
            //pS.setString(1, tanaman.getTanaman_id());
            //pS.setString(2, tanaman.getNama());
            pS.setInt(3, tanaman.getUmur());
            pS.setInt(4, tanaman.getKesehatan());
            pS.setInt(5, tanaman.getKelaparan());
            pS.setInt(6, tanaman.getHaus());

            // Eksekusi query
            int rowsAffected = pS.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data tanaman berhasil dimasukkan ke database.");
            } else {
                System.out.println("Tidak ada data yang dimasukkan.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Gagal memasukkan data tanaman ke database: " + e.getMessage());
        }
    }
    
    public static void searchUid(String user,String tanaman_id, String jenisTransaksi) {
        try {
            con = getCon();
            String query = "select user_id from user where username = '%s'";
            query = String.format(query, user);

            st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                System.out.println("UID " + rs.getString("user_id") );
                if(jenisTransaksi.equals("transaksiBibit")){
                    TransaksiBibit(rs.getString("user_id"), tanaman_id);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeCon(con);
        }
    }
    public static void TransaksiBibit(String uid, String tanaman_id){
        try {
            String query = String.format(
                    "insert into transaksi_bibit (user_id, tanaman_id) values ('%s', '%s')",
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
    public static String cekNamaTanaman(int idTanaman){
        String nama = null;
        try {
            con = getCon();
            String query = "select nama from tanaman where tanaman_id = '%s'";
            query = String.format(query, idTanaman);

            st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                nama = rs.getString("nama");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeCon(con);
        }
        return nama;
    }
    public static int[] cekBatasStatus(int idTanaman){
        int[] batasStatus = new int[3];;
        try {
            con = getCon();
            String query = "select kesehatan, lapar, haus from tanaman where tanaman_id = '%s'";
            query = String.format(query, idTanaman);

            st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                batasStatus[0] = rs.getInt("haus");
                batasStatus[1] = rs.getInt("lapar");
                batasStatus[2] = rs.getInt("kesehatan");
            } else {
            System.out.println("Data tidak ditemukan untuk tanaman_id: " + idTanaman);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeCon(con);
        }
        return batasStatus;
        
    }
    public static int[] cekHarga(int idTanaman){
        int[] harga = new int[2];
        try {
            con = getCon();
            String query = "select harga_beli, harga_jual from tanaman where tanaman_id = '%s'";
            query = String.format(query, idTanaman);

            st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                harga[0] = rs.getInt("harga_beli");
                harga[1] = rs.getInt("harga_jual");
            } else {
            System.out.println("Data tidak ditemukan untuk tanaman_id: " + idTanaman);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeCon(con);
        }
        return harga;
    }
    public static int jumlahDBTanaman(){
        int jumlah = 0;
        con = getCon();
        try {
            String query = "SELECT tanaman_id FROM tanaman ORDER BY tanaman_id DESC LIMIT 1;";
            
            st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                jumlah = rs.getInt("tanaman_id");
            } else {
                System.out.println("Data tidak ditemukan");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TanamanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jumlah;
    }
}

