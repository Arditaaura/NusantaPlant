package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


import java.io.IOException;
import javafx.scene.text.Font;

public class MainMenuController {
    @FXML
    private Button loginBtn;

    @FXML
    private Button regisBtn;


    @FXML
    public void goToRegister() throws IOException {
        Stage stage = (Stage) regisBtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Register.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    @FXML
    public void goToLogin() throws IOException {
        Stage stage = (Stage) loginBtn.getScene().getWindow();
        Font.loadFont(getClass().getResourceAsStream("/font/GothamBook.ttf"), 18);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}
