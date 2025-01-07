/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
            GamePlayController.tanaman = null;
            Stage stage = (Stage) btnSimpan.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Inventory.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Inventory");
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(PopUpJualAtauSimpanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void jualTanaman(ActionEvent event) {
        try {
            int hargaJual = GamePlayController.tanaman.getHargaJual();
            int honey = GamePlayController.getHoney();
            GamePlayController.tanaman= null;
            Stage stage = (Stage) btnJual.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/view/Shop.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Shop");
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(PopUpJualAtauSimpanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
