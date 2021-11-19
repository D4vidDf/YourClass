package com.d4viddf.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class ErrorController implements Initializable {
    @FXML TextArea msg;
    public String error;

    
    /** 
     * Inicializa la ventana Errores
     * @param arg0
     * @param arg1
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        msg.setText(error);
    }
    
    
    /** 
     * Establece el mensaje de error
     * @param error
     */
    public void setError(String error) {
        this.error = error;
    }
}
