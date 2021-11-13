package com.d4viddf.Controller;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.d4viddf.Error.Errores;

import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AbrirAjustesController implements Initializable {
    private static FileWriter file;

    @FXML
    private TextField idUser;
    @FXML
    private TextField idPass;
    @FXML
    private TextField idBase;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }

    @FXML
    private void guardar(ActionEvent ae) {
        JSONObject obj = new JSONObject();
        obj.put("base_datos", idBase.getText().toString());
        obj.put("user", idUser.getText().toString());
        obj.put("pass", idPass.getText().toString());
        try {
            file = new FileWriter("src/main/resources/data/settings.json");
            file.write(obj.toString());
            file.flush();
            file.close();
        } catch (IOException e) {
            FXMLLoader fx = new FXMLLoader();
                fx.setLocation(getClass().getResource("/fxml/error.fxml"));
                ErrorController cf = new ErrorController();
                cf.setError(Errores.muestraErrorIO(e));
                fx.setController(cf);
                Parent froot;
                try {
                    froot = fx.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(froot));
                    stage.setTitle("Error");
                    stage.setResizable(false);
                    stage.show();
                } catch (IOException ei) {
                    ei.printStackTrace();
                }
        }
    }

}