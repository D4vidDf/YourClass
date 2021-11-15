package com.d4viddf.TablasDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.d4viddf.Factory.Dao;
import com.d4viddf.Tablas.Asignaturas;

public class AsignaturasDAO implements Dao<Asignaturas> {

    static Scanner teclado = new Scanner (System.in);

    @Override
    public Asignaturas get(Connection con, int id) {
        return null;
    }

    @Override
    public List<Asignaturas> getAll(Connection conn) {
        List <Asignaturas> lista = null;
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery("SELECT * FROM Asignaturas;");
            int totalRows = 0;
            rs.last();
            totalRows = rs.getRow();
            rs.beforeFirst();
            lista = new ArrayList<Asignaturas>(totalRows);
            while(rs.next()) {
                Asignaturas as = new Asignaturas();
                as.setId(rs.getInt(1));
                as.setNombre(rs.getString(2));
                as.setCurso(rs.getString(3));
                lista.add(as);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void insertarLote (Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Asignaturas (id, nombre) VALUES (?, ?);" , ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            System.out.println("¿Cuántas asignaturas desea añadir?");
            int cant = Integer.parseInt(teclado.nextLine());
            for (int i = 0; i < cant; i++) {
                System.out.println("Inserte número id: ");
                ps.setInt(1, Integer.parseInt(teclado.nextLine()));
                System.out.println("Inserte nombre Asignatura: ");
                ps.setString(2, teclado.nextLine());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    
}
