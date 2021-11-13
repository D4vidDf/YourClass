package com.d4viddf;

import com.d4viddf.Controller.MainController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }

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
        primaryStage.show();
    }

}
