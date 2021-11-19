package com.d4viddf.Tablas;

import java.sql.Connection;

import com.d4viddf.Error.Errores;

import java.sql.*;

public class Borrar {
    /**
     * Método que borra todos los datos de la base de datos
     * @param con
     */
    public static void borr(Connection con) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("delete from Departamentos");
            st.executeUpdate("delete from Alumnos");
            st.executeUpdate("delete from Asignaturas");
            st.executeUpdate("delete from Profesores");
            st.executeUpdate("delete from Profesores");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
