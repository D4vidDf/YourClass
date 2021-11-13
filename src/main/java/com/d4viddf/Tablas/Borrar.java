package com.d4viddf.Tablas;

import java.sql.Connection;

import com.d4viddf.Error.Errores;

import java.sql.*;

public class Borrar {
    public static void borr(Connection con) {
        try {
            Statement st = con.createStatement();
            // process the ResultSet object
            st.executeUpdate("delete from Departamentos");
            st.executeUpdate("delete from Alumnos");
            st.executeUpdate("delete from Asignaturas");
            st.executeUpdate("delete from Profesores");
            st.executeUpdate("delete from Profesores");

        } catch (SQLException e) {
            Errores.muestraErrorSQL(e);
        }
    }
}
