/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static dao.BaseDAO.closeCon;
import static dao.BaseDAO.getCon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Plant;

/**
 *
 * @author angga
 */
public class transbibitDAO extends BaseDAO {
     private static PreparedStatement st;
     private static Connection con;
     
     public static List<Plant> tampilanBibit (Plant s){
     List<Plant> tanamanList = new ArrayList<>();
       try {
            
            String query = "SELECT * FROM tanaman WHERE nama LIKE ?"
                    + " values ('%s','%s','%s','%s','%s')";
            query = String.format(query, 
                    s.getName(),
                    s.getUmur(),
                    s.getLapar(),
                    s.getHaus(),
                    s.getKesehatan());
            con = getCon();
            st = con.prepareStatement(query);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeCon(con);
        }
         return tanamanList;
     }
}
