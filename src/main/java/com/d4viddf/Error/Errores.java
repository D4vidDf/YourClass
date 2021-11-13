package com.d4viddf.Error;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.time.DateTimeException;

public class Errores {
    public static String muestraErrorSQL(SQLException e) {
        System.err.println("SQL ERROR mensaje: " + e.getMessage());
        System.err.println("SQL Estado: " + e.getSQLState());
        System.err.println("SQL código específico: " + e.getErrorCode());
        String outString = new String("SQL ERROR mensaje: " + e.getMessage()+"\nSQL Estado: " + e.getSQLState()+"\nSQL código específico: " + e.getErrorCode());
        return outString;
    }
    public static String muestraErrorIO(IOException e) {
        e.printStackTrace();
        String outString = new String("ERROR mensaje: " + e.getMessage()+"\nCausa: " + e.getCause()+"\nClase: " + e.getClass());
        return outString;
    }
    public static String muestraError(Exception e) {
        e.printStackTrace();
        String outString = new String("ERROR mensaje: " + e.getMessage()+"\nCausa: " + e.getCause()+"\nClase: " + e.getClass());
        return outString;
    }
    public static String muestraErrorURY(URISyntaxException e) {
        e.printStackTrace();
        String outString = new String("ERROR mensaje: " + e.getMessage()+"\nCausa: " + e.getCause()+"\nClase: " + e.getClass());
        return outString;
    }
    public static String muestraErrorDate(DateTimeException e) {
        e.printStackTrace();
        String outString = new String("ERROR mensaje: " + e.getMessage()+"\nCausa: " + e.getCause()+"\nClase: " + e.getClass());
        return outString;
    }
}
