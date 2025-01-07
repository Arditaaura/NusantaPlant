package controller;

import dao.StatusDAO;
import dao.TanamanDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import dao.UserDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;
import model.Tanaman;
//import DAO.TanamanDAO;

public class PilihTanamanController implements Initializable{
    @FXML
    private Button tanaman1;

    @FXML
    private Button tanaman2;

    @FXML
    private Text text1;

    @FXML
    private Text text2;

    @FXML
    private ImageView anggrek;

    @FXML
    private ImageView sedapMalam;

    private User user;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.user = LoginController.user;
    }

    @FXML
    public void goToAnggrek() throws IOException {
        UserDAO.registerTanaman(user.getUsername(), 1);
        UserDAO.updateStatusTanamanPertama(user.getUsername());
        TanamanDAO.searchUid(user.getUsername(), "1","transaksiBibit");
        StatusDAO.insertStatus(user.getUid(),"1");
        Stage stage = (Stage) tanaman1.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/GamePlay.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
         GamePlayController gamePlayController = fxmlLoader.getController();
    
        stage.setScene(scene);


    }

    public void goToSedapMalam(MouseEvent mouseEvent) throws IOException {
        UserDAO.registerTanaman(user.getUsername(), 2);
        UserDAO.updateStatusTanamanPertama(user.getUsername());
        TanamanDAO.searchUid(user.getUsername(), "2","transaksiBibit");
        StatusDAO.insertStatus(user.getUid(),"2");
        Stage stage = (Stage) tanaman2.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/GamePlay.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

}



