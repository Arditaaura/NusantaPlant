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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Tanaman;
import javafx.scene.Parent;

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
    private static int[] plantCek;
    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;
    @FXML
    private ImageView img5;
    @FXML
    private ImageView img6;
    @FXML
    private ImageView img4;
    @FXML
    private ImageView img7;
    @FXML
    private ImageView img8;
    @FXML
    private ImageView img9;
    
    private ImageView[] img;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        img = new ImageView[]{img1, img2, img3, img4, img5, img6, img7, img8, img9};
        tanaman = new Tanaman[9];
        plantCek = InventoryDAO.cekInventori(LoginController.user.getUid());
        for(int i = 0; i < 9; i++){
            if(plantCek[i] != 0){
                tanaman[i] = new Tanaman(plantCek[i]);  
            }  
        }
        memunculkanTanaman();
        // TODO
    } 
    
    public static void setTanaman(Tanaman plant){
        plantCek = InventoryDAO.cekInventori(LoginController.user.getUid());
        
        for(int i = 0;i < 9; i++){
            if (plantCek[i] == 0){
                InventoryDAO.menyimpanTanaman(plant.getTanaman_id(), i, LoginController.user.getUid());
                break;
            }
        }
    }
    @FXML
    public void backtoGameplay() throws IOException {
        Stage stage = (Stage) gotoGameplay.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/GamePlay.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    
    public void memunculkanTanaman(){
        for (int i = 0; i < 9; i++){
            if(plantCek[i] != 0){
                System.out.println("NAMA TANAMAN: " + tanaman[i].getNama() );
                Image img = new Image("/img/" + tanaman[i].getNama() +"3.png");
                this.img[i].setImage(img);
            }
        }
    }

 
}
