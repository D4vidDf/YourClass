package com.d4viddf.TablasDAO;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.d4viddf.Error.Errores;
import com.d4viddf.Factory.Dao;
import com.d4viddf.Tablas.Imparten;

public class ImpartenDAO implements Dao<Imparten> {
Errores errores = new Errores();
    static Scanner teclado = new Scanner(System.in);

    @Override
    public Imparten get(Connection con, int id) {
        return null;
    }

    @Override
    public List<Imparten> getAll(Connection conn) {
        List<Imparten> lista = null;
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery("SELECT * FROM Profesores;");
            int totalRows = 0;
            rs.last();
            totalRows = rs.getRow();
            rs.beforeFirst();
            lista = new ArrayList<Imparten>(totalRows);
            while (rs.next()) {
                Imparten as = new Imparten();
                as.setAlumno(rs.getInt(2));
                as.setAsignatura(rs.getInt(4));
                as.setCurso(rs.getString(1));
                as.setProfesor(rs.getInt(3));
                lista.add(as);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void insertarLote(Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO imparten (curso, alumno, profesor, asignatura) VALUES (?, ?, ?, ?);",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            System.out.println("¿Cuántas matrículas desea añadir?");
            int cant = Integer.parseInt(teclado.nextLine());
            for (int i = 0; i < cant; i++) {
                System.out.println("Inserte el id del curso: ");
                ps.setInt(1, Integer.parseInt(teclado.nextLine()));
                System.out.println("Inserte el DNI del alumno: ");
                ps.setString(2, teclado.nextLine());
                System.out.println("Inserte el id del profesor: ");
                ps.setInt(3, Integer.parseInt(teclado.nextLine()));
                System.out.println("Inserte el id de la asignatura: ");
                ps.setInt(4, Integer.parseInt(teclado.nextLine()));
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void insertar(Connection con, String string, int string2, int parseInt, int parseInt2) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO imparten (curso,alumno, profesor, asignatura) VALUES (?,?, ?, ?);");
            ps.setString(1, string);
            ps.setInt(2, string2);
            ps.setInt(3, parseInt);
            ps.setInt(4, parseInt2);
            ps.execute();
        } catch (SQLException e) {
            errores.muestraErrorSQL(e);
        }
    }
}
