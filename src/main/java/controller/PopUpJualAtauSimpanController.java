/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import static controller.LoginController.user;
import dao.StatusDAO;
import dao.UserDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.util.UUID;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class PopUpJualAtauSimpanController implements Initializable {

    @FXML
    private Button btnSimpan;
    @FXML
    private Button btnJual;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void simpanTanaman(ActionEvent event) {
        try {
            InventoryController.setTanaman(GamePlayController.tanaman);
            UserDAO.updateTanamanId(LoginController.user.getUid(), 0);
            GamePlayController.tanaman= null;
            Stage popupStage = (Stage) btnSimpan.getScene().getWindow();

            // Mendapatkan stage utama
            Stage mainStage = (Stage) popupStage.getOwner();

            // Memuat FXML baru untuk scene yang ingin ditampilkan
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Inventory.fxml"));
            Parent newRoot = loader.load();

            // Mengubah scene dari main stage
            mainStage.setScene(new Scene(newRoot));
            mainStage.setTitle("Inventory");

            // Menutup popup
            popupStage.close();
        } catch (IOException ex) {
            Logger.getLogger(PopUpJualAtauSimpanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void jualTanaman(ActionEvent event) {
        try {
            UUID uid = LoginController.user.getUid();
            int hargaJual = GamePlayController.tanaman.getHargaJual();
            int honey = LoginController.user.getPoin();
            StatusDAO.TransaksiJualTanaman (LoginController.user.getUid(), StatusDAO.getTanaman_id(uid), user.getPoin());
            GamePlayController.tanaman= null;
            UserDAO.updateTanamanId(LoginController.user.getUid(), 0);
            LoginController.user.setPoin(honey + hargaJual);
            Stage popupStage = (Stage) btnJual.getScene().getWindow();

            // Mendapatkan stage utama
            Stage mainStage = (Stage) popupStage.getOwner();

            // Memuat FXML baru untuk scene yang ingin ditampilkan
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Shop.fxml"));
            Parent newRoot = loader.load();

            // Mengubah scene dari main stage
            mainStage.setScene(new Scene(newRoot));
            mainStage.setTitle("Shop");

            // Menutup popup
            popupStage.close();
        } catch (IOException ex) {
            Logger.getLogger(PopUpJualAtauSimpanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
