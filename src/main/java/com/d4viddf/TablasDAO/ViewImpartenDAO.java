package com.d4viddf.TablasDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.d4viddf.Error.Errores;
import com.d4viddf.Factory.Dao;
import com.d4viddf.Tablas.ViewImparten;

public class ViewImpartenDAO implements Dao<ViewImparten>{
    public static  String ROW_COD_asg = "IDasignatura";
    public static  String ROW_NOMBRE_Aasg = "Nombreasignatura";
    public static String ROW_NOMBRE_Alumnos = "Nombrealumno";
    public static String ROW_APELLIDOS_alumnos = "Apellidosalumno";
    public static String ROW_NOMBRE_profesor = "NombreProfesor";
    public static String ROW_APELLIDOS_profesor = "ApellidosProfesor";
    public static String ROW_DNI_profesor = "DNIprofesor";
    public static String ROW_DNI_alumno = "DNIalumno";
    public static String ROW_COD_alumno = "Expedientealumno";
    public static String ROW_COD_prof = "CodProf";
    public static String ROW_Curso_imparten = "CursoImparten";
    Errores errores = new Errores();
    @Override
    public ViewImparten get(Connection con, int id) {
        
        return null;
    }

    @Override
    public List<ViewImparten> getAll(Connection conn) {
        List<ViewImparten> lista = null;
        try {
            Statement s = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = ((java.sql.Statement) s).executeQuery("SELECT * FROM ViewImparten;");
            int totalRows = 0;
            rs.last();
            totalRows = rs.getRow();
            rs.beforeFirst();
            lista = new ArrayList<ViewImparten>(totalRows);
            while (rs.next()) {
                ViewImparten as = new ViewImparten();
                as.setCursoImparten(rs.getString(1));
                as.setExpedientealumno(rs.getInt(2));
                as.setNombrealumno(rs.getString(3));
                as.setApellidosalumno(rs.getString(4));
                as.setDNIalumno(rs.getString(5));
                as.setCodProf(rs.getInt(6));
                as.setDNIprofesor(rs.getString(7));
                as.setNombreProfesor(rs.getString(8));
                as.setApellidosProfesor(rs.getString(9));
                as.setNombredepartamento(rs.getString(10));
                as.setIDasignatura(rs.getInt(11));
                as.setNombreasignatura(rs.getString(12));
                as.setCursoasignatura(rs.getString(13));
                lista.add(as);
            }
        } catch (SQLException e) {
            errores.muestraErrorSQL(e);
        }
        return lista;
    }

    public List<ViewImparten> getByRowLike(Connection conn, String row, String query) {
        List<ViewImparten> lista = new ArrayList<>();
        try {
            PreparedStatement s = conn.prepareStatement("select * from ViewImparten where " + row + " like upper(?)");
            s.setString(1, "%" + query + "%");
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                ViewImparten as = new ViewImparten();
                as.setCursoImparten(rs.getString(1));
                as.setExpedientealumno(rs.getInt(2));
                as.setNombrealumno(rs.getString(3));
                as.setApellidosalumno(rs.getString(4));
                as.setDNIalumno(rs.getString(5));
                as.setCodProf(rs.getInt(6));
                as.setDNIprofesor(rs.getString(7));
                as.setNombreProfesor(rs.getString(8));
                as.setApellidosProfesor(rs.getString(9));
                as.setNombredepartamento(rs.getString(10));
                as.setIDasignatura(rs.getInt(11));
                as.setNombreasignatura(rs.getString(12));
                as.setCursoasignatura(rs.getString(13));
                lista.add(as);
            }
        } catch (SQLException e) {
            errores.muestraErrorSQL(e);
        }
        return lista;
    }
    public List<ViewImparten> getByRowLikeINT(Connection conn, String row, String query) {
        List<ViewImparten> lista = new ArrayList<>();
        try {
            PreparedStatement s = conn.prepareStatement("select * from ViewImparten where " + row + " =?");
            s.setString(1, "%" + query + "%");
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                ViewImparten as = new ViewImparten();
                as.setCursoImparten(rs.getString(1));
                as.setExpedientealumno(rs.getInt(2));
                as.setNombrealumno(rs.getString(3));
                as.setApellidosalumno(rs.getString(4));
                as.setDNIalumno(rs.getString(5));
                as.setCodProf(rs.getInt(6));
                as.setDNIprofesor(rs.getString(7));
                as.setNombreProfesor(rs.getString(8));
                as.setApellidosProfesor(rs.getString(9));
                as.setNombredepartamento(rs.getString(10));
                as.setIDasignatura(rs.getInt(11));
                as.setNombreasignatura(rs.getString(12));
                as.setCursoasignatura(rs.getString(13));
                lista.add(as);
            }
        } catch (SQLException e) {
            errores.muestraErrorSQL(e);
        }
        return lista;
    }
    
}
