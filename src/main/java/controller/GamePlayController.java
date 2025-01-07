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
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;


import model.Tanaman;
import model.User;


public class GamePlayController implements Initializable {

    @FXML
    private Button logOut;
    @FXML
    private Label labelObat;
    public  int kesehatanCount;
    @FXML
    private Label labelPupuk;
    public  int laparCount;
    @FXML
    private Label labelAir;
    public int hausCount;
    @FXML
    private ImageView imgPlant;
    @FXML
    private Label labelUmur;
    private int umurCount;
    @FXML
    private Label labelHoney;
    private static int honeyCount;
    @FXML
    private ImageView inventoryImg;
    @FXML
    private ImageView shopImg;
    
    private User user;
    int[] statusSekarang;
    int[] batasStatus;
    String namaTanaman;
    
    /**
     *
     */
    public static  Tanaman tanaman;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        this.user = LoginController.user;
        int plant = UserDAO.validateTanaman_id(user.getUid());
        statusSekarang = StatusDAO.getStatus(user.getUid());
        System.out.println(statusSekarang[1]);
        System.out.println(statusSekarang[2]);
        System.out.println(statusSekarang[3]);
        
        System.out.println("Plant ID: " + plant);
        System.out.println("Status Sekarang: " + java.util.Arrays.toString(statusSekarang));

        if (statusSekarang == null || statusSekarang.length < 4) {
            System.out.println("Status data tidak valid. Tanaman tidak akan diatur.");
            tanaman = null;
            return;
        }
        honeyCount = user.getPoin();
        System.out.println("honey " + honeyCount);
        memunculkanGambarTanaman(plant);
       
        if(statusSekarang[0]==3 && tanaman != null){
            updateLabel("kodisiDewasa");
            
        }else{
            updateLabel("umur");
            updateLabel("haus");
            updateLabel("lapar");
            updateLabel("kesehatan");
        }
        
        updateLabel("honey");
    }    

    @FXML
    private void kasihObat(MouseEvent event) throws IOException {
        if(tanaman != null){
                 if(kesehatanCount < tanaman.getBatasSehat()  && umurCount < 3){
                kesehatanCount += 1;
                updateLabel("kesehatan");
                tanaman.setKesehatan(kesehatanCount, user.getUid());
                tambahUmurTanaman(user.getUid());
                    }
            }   
    }

    @FXML
    private void kasihPupuk(MouseEvent event) throws IOException {
        if(tanaman != null){
            if(laparCount < tanaman.getBatasLapar()  && umurCount < 3){
                laparCount += 1;
                updateLabel("lapar");
                tanaman.setKelaparan(laparCount, user.getUid());
                tambahUmurTanaman(user.getUid());
            }
        }      
    }

    @FXML
    private void kasihAir(MouseEvent event) throws IOException {
        if(tanaman != null){
            if(hausCount <  tanaman.getBatasHaus() && umurCount < 3){
                hausCount += 1;
                updateLabel("haus");
                tanaman.setHaus(hausCount, user.getUid());
                tambahUmurTanaman(user.getUid());
            }
        }
    }
    
    private void updateLabel(String tipeStatus){
        if(tanaman != null){
            if(tipeStatus.equals("umur")){
                labelUmur.setText(String.valueOf(umurCount));
            }else if(tipeStatus.equals("honey")){
            
                labelHoney.setText(String.valueOf(honeyCount));
            
            }else if(tipeStatus.equals("kondisiPertama")){
                labelAir.setText(String.valueOf(hausCount) + "/" + tanaman.getBatasHaus());
                labelPupuk.setText(String.valueOf(laparCount) + " / " + tanaman.getBatasLapar());
                labelObat.setText(String.valueOf(kesehatanCount) + " / " + tanaman.getBatasSehat());
            }else if(tipeStatus.equals("haus")){
                labelAir.setText(String.valueOf(hausCount) + " / " + tanaman.getBatasHaus());
            }else if(tipeStatus.equals("lapar")){
                labelPupuk.setText(String.valueOf(laparCount) + " / " + tanaman.getBatasLapar());
            }else if(tipeStatus.equals("kesehatan")){
                labelObat.setText(String.valueOf(kesehatanCount) + " / " + tanaman.getBatasSehat());
            }else if(tipeStatus.equals("resetKondisi")|| tipeStatus.equals("kondisiDewasa")){
                labelAir.setText("0" + " / " + tanaman.getBatasHaus());
                labelPupuk.setText("0" + " / " + tanaman.getBatasLapar());
                labelObat.setText("0" + " / " + tanaman.getBatasSehat());
            }else if(tipeStatus.equals("kodisiDewasa")){
                labelAir.setText("-");
                labelPupuk.setText("-");
                labelObat.setText("-");
                labelUmur.setText(String.valueOf(umurCount));
            }else if(tipeStatus.equals("kondisiKosong")){
                labelAir.setText("-");
                labelPupuk.setText("-");
                labelObat.setText("-");
                labelUmur.setText("-");
            }
        }else{
            labelAir.setText("-");
            labelPupuk.setText("-");
            labelObat.setText("-");
            labelUmur.setText("-");
            labelHoney.setText(String.valueOf(honeyCount));
        }
            
    }
    
    public void tambahUmurTanaman( UUID uid) throws IOException{
         
        if(hausCount == tanaman.getBatasHaus() && laparCount == tanaman.getBatasLapar() && kesehatanCount == tanaman.getBatasSehat()){
            if (umurCount < 2){
                umurCount += 1;
                //kondisiPertama(uid);
                //updateLabel("kondisiPertama");
                System.out.println(umurCount);
                updateLabel("umur");
                tanaman.setUmur(umurCount, uid);
                resetKondisi(uid);
                Image img = new Image("/img/" + tanaman.getNama() + umurCount + ".png");
                imgPlant.setImage(img);
            }else{
                umurCount += 1;
                tanaman.setUmur(umurCount, uid);
                updateLabel("kodisiDewasa");
                
                Image img = new Image("/img/" + tanaman.getNama() + umurCount + ".png");
                imgPlant.setImage(img);
                cekUmur();
            }
        } 
    }
    
    public void cekUmur() throws IOException{
       
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/PopupJualAtauSimpan.fxml"));
        Parent popupRoot = fxmlLoader.load();
        Stage popupStage = new Stage();
        popupStage.initOwner((Stage)imgPlant.getScene().getWindow());
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Pop-up");

        Scene scene = new Scene(popupRoot, 300, 200);
        popupStage.setScene(scene);
        popupStage.showAndWait();
    }
    
    public void resetKondisi(UUID uid){
        hausCount = 0;
        laparCount = 0;
        kesehatanCount = 0;
        
        tanaman.setHaus(hausCount, uid);
        tanaman.setKelaparan(laparCount, uid);
        tanaman.setKesehatan(kesehatanCount, uid);
        
        updateLabel("resetKondisi");
    }
    public void kondisiPertama(UUID uid){
        hausCount = 2;
        laparCount = 2;
        kesehatanCount = 2;
        System.out.println("Mengatur kondisi pertama: hausCount = " + hausCount + ", laparCount = " + laparCount + ", kesehatanCount = " + kesehatanCount);
        
       
        tanaman.setHaus(hausCount, uid);
        tanaman.setKelaparan(laparCount, uid);
        tanaman.setKesehatan(kesehatanCount, uid);
    
         updateLabel("kondisiPertama");
        
    }
    
    public void memunculkanGambarTanaman(int tipe){
        umurCount = statusSekarang[0];
        hausCount = statusSekarang[1];
        laparCount = statusSekarang[2];
        kesehatanCount = statusSekarang[3];
        
        System.out.println("Memunculkan gambar tanaman dengan tipe: " + tipe);
        System.out.println("Umur: " + umurCount + ", Haus: " + hausCount + ", Lapar: " + laparCount + ", Kesehatan: " + kesehatanCount);
       // if(umurCount == 0){
         //   kondisiPertama(user.getUid());
        //}
        
        if(tipe != 0){
            tanaman  = new Tanaman(tipe, umurCount , hausCount, laparCount, kesehatanCount);
            Image img = new Image("/img/" + tanaman.getNama() + umurCount + ".png");
            imgPlant.setImage(img);
        }else{
            Image img = null;
            imgPlant.setImage(img);
        }
            
    }

    @FXML
    private void goToShop(MouseEvent event) throws IOException {
        if(tanaman != null){
            if(tanaman.getUmur() == 3){
                cekUmur();
            }else{
                Stage stage = (Stage) shopImg.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/view/Shop.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setTitle("Shop");
                stage.setScene(scene);
            }
        }else{
            Stage stage = (Stage) shopImg.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/view/Shop.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Shop");
            stage.setScene(scene);
        }        
    }

    @FXML
    private void goToInventori(MouseEvent event) throws IOException {
        if(tanaman != null){
            if(tanaman.getUmur() == 3){
                cekUmur();
            }else{
                Stage stage = (Stage) inventoryImg.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/view/inventory.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setTitle("Inventory");
                stage.setScene(scene);
            }
        }else{
            
            Stage stage = (Stage) inventoryImg.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/view/inventory.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Inventory");
            stage.setScene(scene);
        }
    }
    public void setTanaman(Tanaman plant){
        if(plant == null){
            tanaman = null;
            imgPlant.setImage(null);
            updateLabel("kondisiKosong");
        }else{
            tanaman = plant;
            Image img = new Image("/img/" + tanaman.getNama() + "0.png");
            imgPlant.setImage(img);
            updateLabel("kondisiDewasa");
        }
    }
    @FXML
    public void gotoMainMenu () throws IOException{
        UUID userId = LoginController.getUser ().getUid();
        StatusDAO.updateLastExitTime(userId);
        Stage stage = (Stage) logOut.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(RegisterController.class.getResource("/view/MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}
