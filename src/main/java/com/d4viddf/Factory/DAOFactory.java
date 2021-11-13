package com.d4viddf.Factory;

import java.sql.Connection;

import com.d4viddf.Tablas.Imparten;
import com.d4viddf.TablasDAO.AlumnosDAO;
import com.d4viddf.TablasDAO.AsignaturasDAO;
import com.d4viddf.TablasDAO.DepartamentosDAO;
import com.d4viddf.TablasDAO.ImpartenDAO;
import com.d4viddf.TablasDAO.ProfesoresDAO;

public abstract class DAOFactory {
    public static final int MYSQL = 1;
    public abstract Connection getConnection() throws Exception;
    public abstract AlumnosDAO getAlumnosDAO();
    public abstract AsignaturasDAO getAsignaturasDAO();
    public abstract DepartamentosDAO getDepartamentosDAO();
    public abstract ProfesoresDAO getProfesoresDAO();
    public abstract ImpartenDAO getImpartenDAO();

    public static DAOFactory getDAOFactory(int whichFactory) {

        switch (whichFactory) {
        case MYSQL:
            return new MySQLDAOFactory();
        default:
            return null;
        }
    }

    public boolean releaseConnection(Connection connection) {
        return false;
    }

    public int getSize() {
        return 0;
    }

    public void shutdown() throws Exception {
    }
}