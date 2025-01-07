/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import dao.TanamanDAO;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Tanaman;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ShopController implements Initializable {

    @FXML
    private ImageView imgTanaman;
    @FXML
    private Label labelMadu;
    @FXML
    private Label namaTanaman;
    @FXML
    private Button btnKiri;
    @FXML
    private Button btnKanan;
    
    private int jumlahTanaman;
    
    private int posisiTanaman = 0;
    
    private Tanaman[] tanaman;
    @FXML
    private Button btnBeli;
    @FXML
    private Button btnBack;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jumlahTanaman = TanamanDAO.jumlahDBTanaman();
        for(int i = 0; i < jumlahTanaman-1; i++){
            tanaman[i] = new Tanaman (i);
        }
        // TODO
        memunculkanGambar();
    }    


    @FXML
    private void gantiTanamanKeKiri(ActionEvent event) {
        if(posisiTanaman < 0){
            posisiTanaman = jumlahTanaman-1;
            
        }else{
            posisiTanaman -= 1;
        }
        memunculkanGambar();
    }

    @FXML
    private void gantiTanamanKeKanan(ActionEvent event) {
        if(posisiTanaman > jumlahTanaman-1){
            posisiTanaman = 0;
            
        }else{
            posisiTanaman += 1;
        }
        memunculkanGambar();
    }

    @FXML
    private void beliTanaman(ActionEvent event) {
        GamePlayController.tanaman=tanaman[posisiTanaman];
        int honey = GamePlayController.getHoney();
        GamePlayController.updateHoney(honey - tanaman[posisiTanaman].getHargaBeli());
    }
    
    private void memunculkanGambar(){
        Image img = new Image("/img/" + tanaman[posisiTanaman].getNama() + "3.png");
        imgTanaman.setImage(img);
        namaTanaman.setText(tanaman[posisiTanaman].getNama());
        btnBeli.setText(String.valueOf(tanaman[posisiTanaman].getHargaBeli()));
    }

    @FXML
    private void backToGamePlay(ActionEvent event) {
        try {
            Stage stage = (Stage) btnBack.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/view/GamePlay.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Game Play");
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
