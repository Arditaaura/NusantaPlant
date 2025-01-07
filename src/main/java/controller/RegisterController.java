package controller;

import dao.InventoryDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import dao.UserDAO;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class RegisterController {
    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Button regisBtn;

    @FXML
    private Button backBtn;

    @FXML
    private Text text;
    
    @FXML
    private Label labelUsername;
    
    @FXML
    private Label labelUsnKosong;
    private User user;
    

    @FXML
    public void registerUser() throws IOException {
        String name = username.getText();
        String pass = password.getText();
        labelUsnKosong.setVisible(false);
        
        if(name.isEmpty() || pass.isEmpty()){
            labelUsnKosong.setVisible(true);
        }
        else{
            user = UserDAO.validateUsername(username.getText(), password.getText());
            if(user != null){
                labelUsername.setVisible(false);
                UserDAO.registerUser(user);
                InventoryDAO.makeInventory(user.getUid());
                LoginController.user = this.user;
                Stage stage = (Stage) regisBtn.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/PilihTanaman.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
    //            text.setText(user.getUsername() + "berhasil terdaftar!")

            }
            else{
                labelUsername.setVisible(true);
            }
        }
    }
    @FXML
    public void goToMainMenu() throws IOException {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    
}
