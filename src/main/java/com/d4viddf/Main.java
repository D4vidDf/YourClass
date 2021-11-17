package com.d4viddf;

import java.io.File;
import java.io.IOException;

import com.d4viddf.Controller.AbrirAjustesController;
import com.d4viddf.Controller.MainController;
import com.d4viddf.Error.Errores;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{
    Errores errores = new Errores();
    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    
    /** 
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/main.fxml"));
        MainController c = new MainController();
        loader.setController(c);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("YourClass");
        primaryStage.getIcons().add(new Image("/drawable/blackboard.png"));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        File f = new File("settings.json");
        if (!f.exists()) {
            FXMLLoader floader = new FXMLLoader();
            floader.setLocation(getClass().getResource("/fxml/settings.fxml"));
            AbrirAjustesController cf = new AbrirAjustesController();
            floader.setController(cf);
            Parent froot;
            try {
                froot = floader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(froot));
                stage.setTitle("Ajustes");
                stage.getIcons().add(new Image("/drawable/blackboard.png"));
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                errores.muestraErrorIO(e);
            }
        }
    }

}
