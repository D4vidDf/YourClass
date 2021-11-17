package com.d4viddf.Factory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

import com.d4viddf.Connections.BasicConnectionPool;
import com.d4viddf.Error.Errores;
import com.d4viddf.TablasDAO.AlumnosDAO;
import com.d4viddf.TablasDAO.AsignaturasDAO;
import com.d4viddf.TablasDAO.DepartamentosDAO;
import com.d4viddf.TablasDAO.ImpartenDAO;
import com.d4viddf.TablasDAO.ProfesoresDAO;
import com.d4viddf.TablasDAO.ViewImpartenDAO;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MySQLDAOFactory extends DAOFactory {
    Errores errores = new Errores();
    final static String url = "jdbc:mysql:///";
    private String user = "";
    private String password = "";
    private String urlF;
    static BasicConnectionPool bcp;

    public MySQLDAOFactory() {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        try 
        {
            //Read JSON file
            Object obj = jsonParser.parse(new FileReader("settings.json"));
            JSONObject jsonObject = (JSONObject) obj;
            user = jsonObject.get("user").toString();
            password = jsonObject.get("pass").toString();
            urlF = new String(url+jsonObject.get("base_datos").toString()); 
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            errores.muestraErrorIO(e);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {

            bcp = BasicConnectionPool.create(urlF, user, password);
        } catch (SQLException e) {
            errores.muestraErrorSQL(e);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return bcp.getConnection();
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        return bcp.releaseConnection(connection);
    }

    @Override
    public int getSize() {
        return bcp.getSize();
    }

    // add getUser, getURL....
    @Override
    public void shutdown() throws SQLException {
        bcp.shutdown();
    }

    @Override
    public AlumnosDAO getAlumnosDAO() {
        return new AlumnosDAO();
    }

    @Override
    public AsignaturasDAO getAsignaturasDAO() {
        return new AsignaturasDAO();
    }

    @Override
    public DepartamentosDAO getDepartamentosDAO() {
        return new DepartamentosDAO();
    }

    @Override
    public ProfesoresDAO getProfesoresDAO() {
        return new ProfesoresDAO();
    }

    @Override
    public ImpartenDAO getImpartenDAO() {
        return new ImpartenDAO();
    }

    @Override
    public ViewImpartenDAO getViewImpartenDAO() {
        return new ViewImpartenDAO();
    }

}
