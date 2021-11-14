package com.d4viddf.Controller;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.d4viddf.Error.Errores;
import com.d4viddf.Tablas.Alumnos;
import com.d4viddf.TablasDAO.AlumnosDAO;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ProfesorController extends DBViewController implements Initializable {
    Errores errores = new Errores();
    @FXML
    private TableView<Alumnos> tabAlumnos;
    @FXML
    private TableColumn<Alumnos, Integer> colNum;
    @FXML
    private TableColumn<Alumnos, String> colDNI;
    @FXML
    private TableColumn<Alumnos, String> colNombre;
    @FXML
    private TableColumn<Alumnos, String> colApellidos;
    @FXML
    private TableColumn<Alumnos, LocalDate> colNac;
    @FXML
    private ComboBox<String> cbxBuscarPor;
    @FXML
    private TextField txtBusqueda, txtNum, txtApellidos, txtNombre, txtDNI, path;
    @FXML
    private DatePicker fecha;
    @FXML
    private TextArea estado;

    private String selectedItem = null;

    /**
     * Método que inicializa la tabla con las columnas asigandas para cada atributo
     * de la clase Alumno
     */
    @FXML
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        colNum.setCellValueFactory(new PropertyValueFactory<Alumnos, Integer>("expediente"));
        colDNI.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("DNI"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("apellidos"));
        colNac.setCellValueFactory(new PropertyValueFactory<Alumnos, LocalDate>("nacimiento"));

        cbxBuscarPor.getItems().setAll("Número de expediente", "DNI", "Nombre", "Apellidos", "Año de nacimiento",
                "DNI de profesor", "Todos");
        cbxBuscarPor.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> selected, String oldI, String newI) {
                selectedItem = newI;
            }
        });
    }

    /**
     * Método que gestiona el evento del botón buscar.
     * 
     * @param ae
     */
    @FXML
    private void buscar(ActionEvent ae) {
        if (selectedItem != null) {
            switch (selectedItem) {
            case "Número de expediente":
                findByExpediente();
                break;
            case "DNI":
                findByDNI();
                break;
            case "Nombre":
                findByRowLike(AlumnosDAO.ROW_NOMBRE);
                break;
            case "Apellidos":
                findByRowLike(AlumnosDAO.ROW_APELLIDOS);
                break;
            case "Año de nacimiento":
                findByAnho();
                break;
            case "DNI de profesor":
                findByProfesor();
                break;
            case "Todos":
                mostrar();
                break;
            }
        } else
            mostrar();
    }

    /**
     * Método que muestra todos los alumnos existentes en la tabla.
     */
    private void mostrar() {
        List<Alumnos> als = new ArrayList<>();
        try {
            als = mySQLDAOFactory.getAlumnosDAO().getAll(mySQLDAOFactory.getConnection());
        } catch (SQLException e) {
            errores.muestraErrorSQL(e);
        }
        tabAlumnos.getItems().setAll(als);
    }

    /**
     * Método que muestra al alumno que coincida con el número de expediente del
     * TextField en la tabla
     */
    private void findByExpediente() {
        int id = Integer.parseInt(txtBusqueda.getText());
        List<Alumnos> als = new ArrayList<>();
        try {
            als.add(mySQLDAOFactory.getAlumnosDAO().get(mySQLDAOFactory.getConnection(), id));
        } catch (SQLException e) {
            errores.mostrar("Por favor,\nAñade el Número de expediente para poder buscar");
        }
        tabAlumnos.getItems().setAll(als);
    }

    /**
     * Método que muestra al Alumno que coincida con el DNI del campo TextField
     */
    private void findByDNI() {
        List<Alumnos> als = new ArrayList<>();
        try {
            als = mySQLDAOFactory.getAlumnosDAO().getByDNI(mySQLDAOFactory.getConnection(), txtBusqueda.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tabAlumnos.getItems().setAll(als);
    }

    /**
     * Método que muestra el alumno por el nombre o apellido introducido
     * 
     * @param row
     */
    private void findByRowLike(String row) {
        List<Alumnos> als = new ArrayList<>();
        try {
            als = mySQLDAOFactory.getAlumnosDAO().getByRowLike(mySQLDAOFactory.getConnection(), row,
                    txtBusqueda.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tabAlumnos.getItems().setAll(als);
    }

    /**
     * Mëtodo que muestra a los Alumnos que coincidan con la fecha de nacimiento
     */
    private void findByAnho() {
        List<Alumnos> als = new ArrayList<>();
        try {
            als = mySQLDAOFactory.getAlumnosDAO().getByYear(mySQLDAOFactory.getConnection(), txtBusqueda.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tabAlumnos.getItems().setAll(als);
    }

    /**
     * Método que muestra a los alumnos que tengan como profesor en alguna
     * asignatura matriculada
     */
    private void findByProfesor() {
        List<Alumnos> als = new ArrayList<>();
        try {
            als = mySQLDAOFactory.getAlumnosDAO().getByProfesor(mySQLDAOFactory.getConnection(), txtBusqueda.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tabAlumnos.getItems().setAll(als);
    }

    /**
     * Método para crear un alumno
     * 
     * @param ae
     */
    @FXML
    private void crearalumno(ActionEvent ae) {
        try {
            AlumnosDAO alm = new AlumnosDAO();
            alm.insertar(mySQLDAOFactory.getConnection(), txtNombre.getText().toString(),
                    txtApellidos.getText().toString(), txtDNI.getText().toString(),
                    Integer.parseInt(txtNum.getText().toString()), fecha.getValue());
        } catch (Exception e) {
            errores.muestraError(e);
        }
    }

    /**
     * Método para abrir el archivo del cuál insertar datos a la base de datos
     * 
     * @param ae
     */
    @FXML
    private void abrir(ActionEvent ae) {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Seleccionar archivo", "*.json"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        path.setText(selectedFile.toPath().toString());
    }

    /**
     * Método para exportar la tabla Alumnos en un fichero JSON
     * 
     * @param ae
     */
    @FXML
    private void exportar(ActionEvent ae) {
        if (path.getText().isEmpty()) {
            guardar();
            try {
                mySQLDAOFactory.getAlumnosDAO().exportar(mySQLDAOFactory.getConnection(), path.getText().toString());
                estado.setText("Se ha exportado correctamente.");
            } catch (SQLException e) {
                errores.muestraErrorSQL(e);
            }
        } else {
            try {
                mySQLDAOFactory.getAlumnosDAO().exportar(mySQLDAOFactory.getConnection(), path.getText().toString());
                estado.setText("Se ha exportado correctamente.");
            } catch (SQLException e) {
                errores.muestraErrorSQL(e);
            }

        }
    }

    /**
     * Método para abrir un selector de archivos para escoger el nombre y ruta donde
     * guardar el fichero json
     */
    private void guardar() {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar archivo");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Guardar archivo", "*.json"));
        File selectedFile = fileChooser.showSaveDialog(stage);
        path.setText(selectedFile.toPath().toString());
    }

    /**
     * Método para importar desde un fichero JSON los datos de un alumno para la
     * tabla Alumno
     * 
     * @param ae
     */
    @FXML
    private void importar(ActionEvent ae) {
        if (path.getText().isEmpty()) {
            abrir(ae);
            try {
                mySQLDAOFactory.getAlumnosDAO().insertarLote(mySQLDAOFactory.getConnection(),
                        path.getText().toString());
                estado.setText("Se han importado correctamente los datos.");
            } catch (SQLException se) {
                errores.muestraErrorSQL(se);
            } catch (Exception e) {
                errores.muestraError(e);
            }
        } else {
            try {
                mySQLDAOFactory.getAlumnosDAO().insertarLote(mySQLDAOFactory.getConnection(),
                        path.getText().toString());
                estado.setText("Se han importado correctamente los datos.");
            } catch (SQLException se) {
                errores.muestraErrorSQL(se);
            } catch (Exception e) {
                errores.muestraError(e);
            }

        }
    }

}
