package com.d4viddf.TablasDAO;

import java.io.IOException;
import java.sql.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.d4viddf.Main;
import com.d4viddf.Controller.ErrorController;
import com.d4viddf.Error.Errores;
import com.d4viddf.Factory.Dao;
import com.d4viddf.Tablas.Alumnos;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlumnosDAO implements Dao<Alumnos> {

    static Scanner teclado = new Scanner(System.in);

    @Override
    public List<Alumnos> getAll(Connection conn) {
        List<Alumnos> lista = null;
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery("SELECT * FROM Alumnos;");
            int totalRows = 0;
            rs.last();
            totalRows = rs.getRow();
            rs.beforeFirst();
            lista = new ArrayList<Alumnos>(totalRows);
            while (rs.next()) {
                Alumnos emp = new Alumnos();
                emp.setDNI(rs.getString(1));
                emp.setNombre(rs.getString(2));
                emp.setApellidos(rs.getString(3));
                emp.setNacimiento(LocalDate.parse(rs.getString(4)));
                lista.add(emp);
            }
        } catch (SQLException e) {

        }
        return lista;
    }

    @Override
    public Alumnos get(long id) {
        return new Alumnos();
    }

    public void insertarLote(Connection con) throws Exception {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Alumnos (dni, nombre, apellidos, fecha_nacimiento) VALUES (?, ?, ?, ?);",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            System.out.println("¿Cuántos alumnos desea añadir?");
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
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (DateTimeException e) {

        }

        catch (SQLException e) {

        }

    }

    public void insertar(Connection con, String nombre, String apellidos, String DNI, int expediente, LocalDate fecha) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Alumnos (expediente,dni, nombre, apellidos, fecha_nacimiento) VALUES (?,?, ?, ?, ?);");
            ps.setInt(1, expediente);
            ps.setString(2, DNI);
            ps.setString(3, nombre);
            ps.setString(4, apellidos);
            ps.setDate(5, Date.valueOf(fecha));
            ps.execute();
        } catch (SQLException e) {
            FXMLLoader fx = new FXMLLoader();
                fx.setLocation(getClass().getResource("/fxml/error.fxml"));
                ErrorController cf = new ErrorController();
                cf.setError(Errores.muestraErrorSQL(e));
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
