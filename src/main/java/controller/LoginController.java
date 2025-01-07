package controller;

import dao.UserDAO;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.User;


public class LoginController {
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginBtn;

    @FXML
    private Button backBtn;

    @FXML
    private Text text;
    public static User user;

    @FXML
    public void loginUser() throws IOException {
        user = null;
//        Tanaman tanaman = null;
        user = UserDAO.validate(username.getText(), password.getText());
        if (user != null)
        {
            
            int status = UserDAO.validateTanamanPertama(user.getUsername());
            if(status != 0){
                Stage stage = (Stage) loginBtn.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/GamePlay.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setTitle("Game Play");
                stage.setScene(scene);
            }else{
                Stage stage = (Stage) loginBtn.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/PilihTanaman.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setTitle("Pilih Tanaman");
                stage.setScene(scene);
            }
            

        }
        else
        {
            text.setText("Data tidak valid!");
        }
    }

    @FXML
    public void goToMainMenu() throws IOException {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(RegisterController.class.getResource("/view/MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    
    public static User getUser(){
        return user;
    }
}
