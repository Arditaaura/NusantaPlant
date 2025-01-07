/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import dao.StatusDAO;
import dao.TanamanDAO;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Tanaman;
import model.User;

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
    
    private User user;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.user = LoginController.user;
        jumlahTanaman = TanamanDAO.jumlahDBTanaman();
        System.out.println("jumlah tanaman "+ jumlahTanaman);
        tanaman = new Tanaman[jumlahTanaman];
        for(int i = 0; i < jumlahTanaman; i++){
            tanaman[i] = new Tanaman (i+1);
            System.out.println("tanaman id = "+ tanaman[i].getNama());
        }
        // TODO
        memunculkanGambar();
        labelMadu.setText(String.valueOf(LoginController.user.getPoin()));
    }    


    @FXML
    private void gantiTanamanKeKiri(ActionEvent event) {
        if(posisiTanaman == 0){
            posisiTanaman = jumlahTanaman-1;
            
        }else{
            posisiTanaman --;
        }
        memunculkanGambar();
    }

    @FXML
    private void gantiTanamanKeKanan(ActionEvent event) {
        if(posisiTanaman == jumlahTanaman-1){
            posisiTanaman = 0;
            
        }else{
            posisiTanaman ++;
        }
        memunculkanGambar();
    }

    @FXML
    private void beliTanaman(ActionEvent event) {
        int i = UserDAO.validateTanaman_id(user.getUid());
        if(i == 0){
            GamePlayController.tanaman = tanaman[posisiTanaman];
            System.out.println("tanaman id"+ tanaman[posisiTanaman].getTanaman_id());
            System.out.println("id tanaman "+ tanaman[posisiTanaman].getTanaman_id());
            UserDAO.updateTanamanId(user.getUid(), tanaman[posisiTanaman].getTanaman_id());
            TanamanDAO.TransaksiBibit(user.getUid(),tanaman[posisiTanaman].getTanaman_id());
            StatusDAO.newTanaman(user.getUid(),tanaman[posisiTanaman].getTanaman_id());
            int honey = user.getPoin();
            LoginController.user.setPoin(honey - tanaman[posisiTanaman].getHargaBeli());
            labelMadu.setText(String.valueOf(user.getPoin()));
        }
    }
    
    private void memunculkanGambar(){
        if (tanaman[posisiTanaman] != null) {
            Image img = new Image("/img/" + tanaman[posisiTanaman].getNama() + "3.png");
            imgTanaman.setImage(img);
            namaTanaman.setText(tanaman[posisiTanaman].getNama());
            btnBeli.setText(String.valueOf(tanaman[posisiTanaman].getHargaBeli()));
            if(LoginController.user.getPoin() < tanaman[posisiTanaman].getHargaBeli() ){
                btnBeli.setDisable(true);
            }else{
                btnBeli.setDisable(false);
            }
        } else {
            System.out.println("Tanaman at index " + posisiTanaman + " is null.");
        } 
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
