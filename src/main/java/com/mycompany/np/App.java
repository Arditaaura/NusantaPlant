package com.mycompany.np;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import java.io.IOException;
import java.net.URL;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        URL url = new File("src/main/resources/view/MainMenu.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);

        
        //Parent mainPage = FXMLLoader.load(getClass().getResource("GamePlay.fxml"));
        //Scene scene = new Scene(mainPage, 1024, 600);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();

    }
    public static void main(String[] args) {
        launch();
    }

}