package com.d4viddf.TablasDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.d4viddf.Factory.Dao;
import com.d4viddf.Tablas.Profesores;
import com.mysql.cj.exceptions.DataReadException;

public class ProfesoresDAO implements Dao<Profesores> {
    static Scanner teclado = new Scanner(System.in);

    @Override
    public Profesores get(Connection con, int id) {
        return null;
    }

    @Override
    public List<Profesores> getAll(Connection con) {
        List<Profesores> lista = null;
        try {
            Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery("SELECT * FROM Profesores;");
            int totalRows = 0;
            rs.last();
            totalRows = rs.getRow();
            rs.beforeFirst();
            lista = new ArrayList<Profesores>(totalRows);
            while (rs.next()) {
                Profesores as = new Profesores();
                as.setDNI(rs.getString(1));
                as.setNombre(rs.getString(2));
                as.setApellidos(rs.getString(3));
                as.setFecha_nacimiento(LocalDate.parse(rs.getString(4)));
                as.setDepartamento(rs.getInt(5));
                as.setCod_prof(rs.getInt(6));
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
                    "INSERT INTO profesores (dni, nombre, apellidos, fecha_nacimiento, departamento, cod_prof) VALUES (?, ?,?,?,?,?);",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            System.out.println("¿Cuántos profesores desea añadir?");
            int cant = Integer.parseInt(teclado.nextLine());
            for (int i = 0; i < cant; i++) {
                System.out.println("Inserte el DNI: ");
                ps.setString(1, teclado.nextLine());
                System.out.println("Inserte nombre: ");
                ps.setString(2, teclado.nextLine());
                System.out.println("Inserte Apellido: ");
                ps.setString(3, teclado.nextLine());
                System.out.println("Inserte fecha con el formato dd/mm/aaaa: ");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate date = LocalDate.parse(teclado.nextLine(), formatter);
                ps.setDate(4, Date.valueOf(date));
                System.out.println("Inserte cod. departamento: ");
                ps.setInt(5, Integer.parseInt(teclado.nextLine()));
                System.out.println("Inserte cod. profesor: ");
                ps.setInt(6, Integer.parseInt(teclado.nextLine()));
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}