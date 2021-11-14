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
import com.d4viddf.Tablas.Departamentos;

public class DepartamentosDAO implements Dao<Departamentos> {
    static Scanner teclado = new Scanner(System.in);
    @Override
    public Departamentos get(Connection con, int id) {
        return null;
    }

    @Override
    public List<Departamentos> getAll(Connection con) {
        List <Departamentos> lista = null;
        try {
            Statement s =  con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery("SELECT * FROM departamentos;");
            int totalRows = 0;
            rs.last();
            totalRows = rs.getRow();
            rs.beforeFirst();
            lista = new ArrayList<Departamentos>(totalRows);
            while(rs.next()) {
                Departamentos as = new Departamentos();
                as.setId(rs.getInt(1));
                as.setNombre(rs.getString(2));
                as.setPresupuesto(rs.getFloat(3));
                lista.add(as);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    public void insertarLote (Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO departamentos (id, nombre, presupuesto) VALUES (?, ?, ?);" , ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            System.out.println("¿Cuántos departamentos desea añadir?");
            int cant = Integer.parseInt(teclado.nextLine());
            for (int i = 0; i < cant; i++) {
                System.out.println("Inserte número id: ");
                ps.setInt(1, Integer.parseInt(teclado.nextLine()));
                System.out.println("Inserte nombre Departamento: ");
                ps.setString(2, teclado.nextLine());
                System.out.println("Inserte el presupuesto: ");
                ps.setInt(3, Integer.parseInt(teclado.nextLine()));
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
