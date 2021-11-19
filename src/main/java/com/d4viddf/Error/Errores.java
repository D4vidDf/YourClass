package com.d4viddf.Error;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.time.DateTimeException;

import com.d4viddf.Controller.ErrorController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Errores {
    
    /** 
     * Método que muestra una ventana con la excepción SQL manejada
     * @param e
     */
    public void muestraErrorSQL(SQLException e) {
        String outString = new String("SQL ERROR mensaje: " + e.getMessage() + "\nSQL Estado: " + e.getSQLState()
                + "\nSQL código específico: " + e.getErrorCode());
        FXMLLoader fx = new FXMLLoader();
        fx.setLocation(getClass().getResource("/fxml/error.fxml"));
        ErrorController cf = new ErrorController();
        cf.setError(outString);
        fx.setController(cf);
        Parent froot;
        try {
            froot = fx.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(froot));
            stage.setTitle("Error");
            stage.getIcons().add(new Image("/drawable/blackboard.png"));
            stage.setResizable(false);
            stage.show();
        } catch (IOException ei) {
            ei.printStackTrace();
        }
    }

    
    /** 
     * Método que muestra una ventana con la excepción IOException manejada
     * @param e
     */
    public void muestraErrorIO(IOException e) {
        e.printStackTrace();
        String outString = new String(
                "ERROR mensaje: " + e.getMessage() + "\nCausa: " + e.getCause() + "\nClase: " + e.getClass());
        FXMLLoader fx = new FXMLLoader();
        fx.setLocation(getClass().getResource("/fxml/error.fxml"));
        ErrorController cf = new ErrorController();
        cf.setError(outString);
        fx.setController(cf);
        Parent froot;
        try {
            froot = fx.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(froot));
            stage.setTitle("Error");
            stage.getIcons().add(new Image("/drawable/blackboard.png"));
            stage.setResizable(false);
            stage.show();
        } catch (IOException ei) {
            ei.printStackTrace();
        }
    }

    
    /** 
     * Método que muestra una ventana con la excepción genérica manejada
     * @param e
     */
    public void muestraError(Exception e) {
        e.printStackTrace();
        String outString = new String(
                "ERROR mensaje: " + e.getMessage() + "\nCausa: " + e.getCause() + "\nClase: " + e.getClass());
        FXMLLoader fx = new FXMLLoader();
        fx.setLocation(getClass().getResource("/fxml/error.fxml"));
        ErrorController cf = new ErrorController();
        cf.setError(outString);
        fx.setController(cf);
        Parent froot;
        try {
            froot = fx.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(froot));
            stage.setTitle("Error");
            stage.getIcons().add(new Image("/drawable/blackboard.png"));
            stage.setResizable(false);
            stage.show();
        } catch (IOException ei) {
            ei.printStackTrace();
        }
    }

    
    /** 
     * Método que muestra una ventana con la excepción de tipo URY manejada
     * @param e
     */
    public void muestraErrorURY(URISyntaxException e) {
        e.printStackTrace();
        String outString = new String(
                "ERROR mensaje: " + e.getMessage() + "\nCausa: " + e.getCause() + "\nClase: " + e.getClass());
        FXMLLoader fx = new FXMLLoader();
        fx.setLocation(getClass().getResource("/fxml/error.fxml"));
        ErrorController cf = new ErrorController();
        cf.setError(outString);
        fx.setController(cf);
        Parent froot;
        try {
            froot = fx.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(froot));
            stage.setTitle("Error");
            stage.getIcons().add(new Image("/drawable/blackboard.png"));
            stage.setResizable(false);
            stage.show();
        } catch (IOException ei) {
            ei.printStackTrace();
        }
    }

    
    /** 
     * Método que muestra una ventana cuando salta una excepción por una fecha
     * @param e
     */
    public void muestraErrorDate(DateTimeException e) {
        e.printStackTrace();
        String outString = new String(
                "ERROR mensaje: " + e.getMessage() + "\nCausa: " + e.getCause() + "\nClase: " + e.getClass());
        FXMLLoader fx = new FXMLLoader();
        fx.setLocation(getClass().getResource("/fxml/error.fxml"));
        ErrorController cf = new ErrorController();
        cf.setError(outString);
        fx.setController(cf);
        Parent froot;
        try {
            froot = fx.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(froot));
            stage.setTitle("Error");
            stage.getIcons().add(new Image("/drawable/blackboard.png"));
            stage.setResizable(false);
            stage.show();
        } catch (IOException ei) {
            ei.printStackTrace();
        }
    }

    
    /** 
     * Método que muestra una ventana con el mensaje que se le pase
     * @param string Mensaje
     */
    public void mostrar(String string) {
        FXMLLoader fx = new FXMLLoader();
        fx.setLocation(getClass().getResource("/fxml/error.fxml"));
        ErrorController cf = new ErrorController();
        cf.setError(string);
        fx.setController(cf);
        Parent froot;
        try {
            froot = fx.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(froot));
            stage.setTitle("Atención");
            stage.getIcons().add(new Image("/drawable/blackboard.png"));
            stage.setResizable(false);
            stage.show();
        } catch (IOException ei) {
            ei.printStackTrace();
        }
    }
}
