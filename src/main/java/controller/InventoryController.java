/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import dao.InventoryDAO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Tanaman;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class InventoryController implements Initializable {
    
    @FXML
    private Button gotoGameplay;
    private static int tanamanCek;
    private static Tanaman[] tanaman;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         tanaman = InventoryDAO.cekInventori(LoginController.user.getUid());
         
        // TODO
    } 
    
    public static void setTanaman(Tanaman plant){
        tanaman = InventoryDAO.cekInventori(LoginController.user.getUid());
        int i =0;
        while(i < 9){
            if (tanaman[i] == null){
                InventoryDAO.menyimpanTanaman(plant.getTanaman_id(), i);
                break;
            }
            i ++;
        }
    }
    @FXML
    public void backtoGameplay() throws IOException {
        Stage stage = (Stage) gotoGameplay.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/GamePlay.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    
}
