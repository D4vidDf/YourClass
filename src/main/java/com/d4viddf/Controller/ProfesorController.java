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
import com.d4viddf.Tablas.Profesores;
import com.d4viddf.TablasDAO.AlumnosDAO;
import com.d4viddf.TablasDAO.ProfesoresDAO;

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
    private TableView<Profesores> tabAlumnos;
    @FXML
    private TableColumn<Profesores, Integer> colNum;
    @FXML
    private TableColumn<Profesores, String> colDNI;
    @FXML
    private TableColumn<Profesores, String> colNombre;
    @FXML
    private TableColumn<Profesores, String> colApellidos;
    @FXML
    private TableColumn<Profesores, LocalDate> colNac;
    @FXML
    private TableColumn<Profesores, Integer> colDepar;
    @FXML
    private ComboBox<String> cbxBuscarPor;
    @FXML
    private TextField txtBusqueda, txtNum, txtApellidos, txtNombre, txtDNI, txtDep, path;
    @FXML
    private DatePicker fecha;
    @FXML
    private TextArea estado;

    private String selectedItem = "";

    /**
     * Método que inicializa la tabla con las columnas asigandas para cada atributo
     * de la clase Alumno
     */
    @FXML
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        colNum.setCellValueFactory(new PropertyValueFactory<Profesores, Integer>("cod_prof"));
        colDNI.setCellValueFactory(new PropertyValueFactory<Profesores, String>("DNI"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Profesores, String>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<Profesores, String>("apellidos"));
        colNac.setCellValueFactory(new PropertyValueFactory<Profesores, LocalDate>("nacimiento"));
        colDepar.setCellValueFactory(new PropertyValueFactory<Profesores, Integer>("departamento"));

        cbxBuscarPor.getItems().setAll("Código de profesor", "DNI", "Nombre", "Apellidos", "Año de nacimiento",
                "Departamento", "Todos");
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
        if (selectedItem.isEmpty() && txtBusqueda.getText().isEmpty()) {
            mostrar();
        } else if (selectedItem.equals("Todos") && txtBusqueda.getText().isEmpty()) {
            mostrar();
        } else if (txtBusqueda.getText().isEmpty()) {
            errores.mostrar("Por favor,\nIntroduce un valor para realizar la búsqueda");
        } else {
            if (selectedItem != null) {
                switch (selectedItem) {
                case "Código del profesor":
                    findByID();
                    break;
                case "DNI":
                    findByDNI();
                    break;
                case "Nombre":
                    findByRowLike(ProfesoresDAO.ROW_NOMBRE);
                    break;
                case "Apellidos":
                    findByRowLike(ProfesoresDAO.ROW_APELLIDOS);
                    break;
                case "Año de nacimiento":
                    findByAnho();
                    break;
                case "Departamento":
                    findByRowLike(ProfesoresDAO.ROW_DEPARTAMENTO);
                    break;
                case "Todos":
                    mostrar();
                    break;
                }
            } else
                mostrar();
        }
    }

    private void findByID() {

        int id = Integer.parseInt(txtBusqueda.getText());
        Profesores als = new Profesores();
        try {
            als = mySQLDAOFactory.getProfesoresDAO().get(mySQLDAOFactory.getConnection(), id);
        } catch (SQLException e) {
            errores.mostrar("Por favor,\nAñade el Código del profesor para poder buscar");
        }
        tabAlumnos.getItems().setAll(als);
    }

    /**
     * Método que muestra todos los alumnos existentes en la tabla.
     */
    private void mostrar() {
        List<Profesores> pro = new ArrayList<>();
        try {
            pro = mySQLDAOFactory.getProfesoresDAO().getAll(mySQLDAOFactory.getConnection());
        } catch (SQLException e) {
            errores.muestraErrorSQL(e);
        }
        tabAlumnos.getItems().setAll(pro);
    }

    /**
     * Método que muestra al Alumno que coincida con el DNI del campo TextField
     */
    private void findByDNI() {
        Profesores pro = new Profesores();
        try {
            pro = mySQLDAOFactory.getProfesoresDAO().getByDNI(mySQLDAOFactory.getConnection(), txtBusqueda.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tabAlumnos.getItems().setAll(pro);

    }

    /**
     * Método que muestra el alumno por el nombre o apellido introducido
     * 
     * @param row
     */
    private void findByRowLike(String row) {
        
          List<Profesores> als = new ArrayList<>(); try { als =
          mySQLDAOFactory.getProfesoresDAO().getByRowLike(mySQLDAOFactory.getConnection(),
          row, txtBusqueda.getText()); } catch (SQLException e) { e.printStackTrace();
          } tabAlumnos.getItems().setAll(als);
    }

    /**
     * Mëtodo que muestra a los Alumnos que coincidan con la fecha de nacimiento
     */
    private void findByAnho() {
        List<Profesores> als = new ArrayList<>(); try { als =
         mySQLDAOFactory.getProfesoresDAO().getByYear(mySQLDAOFactory.getConnection(),
          txtBusqueda.getText()); } catch (SQLException e) { e.printStackTrace(); }
          tabAlumnos.getItems().setAll(als);
         
    }

    /**
     * Método para crear un alumno
     * 
     * @param ae
     */
    @FXML
    private void crear(ActionEvent ae) {

        try {
            ProfesoresDAO alm = new ProfesoresDAO();
            alm.insertar(mySQLDAOFactory.getConnection(), txtNombre.getText().toString(),
                    txtApellidos.getText().toString(), txtDNI.getText().toString(),
                    Integer.parseInt(txtNum.getText().toString()), fecha.getValue(), txtDep.getText().toString());
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
                mySQLDAOFactory.getProfesoresDAO().exportar(mySQLDAOFactory.getConnection(), path.getText().toString());
                estado.setText("Se ha exportado correctamente.");
            } catch (SQLException e) {
                errores.muestraErrorSQL(e);
            }
        } else {
            try {
                mySQLDAOFactory.getProfesoresDAO().exportar(mySQLDAOFactory.getConnection(), path.getText().toString());
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
                mySQLDAOFactory.getProfesoresDAO().insertarLote(mySQLDAOFactory.getConnection(),
                        path.getText().toString());
                estado.setText("Se han importado correctamente los datos.");
            } catch (SQLException se) {
                errores.muestraErrorSQL(se);
            } catch (Exception e) {
                errores.muestraError(e);
            }
        } else {
            try {
                mySQLDAOFactory.getProfesoresDAO().insertarLote(mySQLDAOFactory.getConnection(),
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
