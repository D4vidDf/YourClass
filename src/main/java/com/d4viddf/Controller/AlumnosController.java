package com.d4viddf.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.d4viddf.Error.Errores;
import com.d4viddf.Tablas.Alumnos;
import com.d4viddf.TablasDAO.AlumnosDAO;
import com.google.protobuf.DescriptorProtos.FieldDescriptorProto.Label;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class AlumnosController extends DBViewController implements Initializable {
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

    @FXML
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        colNum.setCellValueFactory(new PropertyValueFactory<Alumnos, Integer>("expediente"));
        colDNI.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("DNI"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("apellidos"));
        colNac.setCellValueFactory(new PropertyValueFactory<Alumnos, LocalDate>("nacimiento"));

        cbxBuscarPor.getItems().setAll("Número de expediente", "DNI", "Nombre", "Apellidos", "Año de nacimiento",
                "DNI de profesor");
        cbxBuscarPor.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> selected, String oldI, String newI) {
                selectedItem = newI;
            }
        });
    }

    @FXML
    private void buscar(ActionEvent ae) {
        if (selectedItem != null) {
            switch(selectedItem) {
              case "Número de expediente": findByID(); break;
              case "DNI": findByDNI(); break;
              case "Nombre": findByRowLike(AlumnosDAO.ROW_NOMBRE); break;
              case "Apellidos": findByRowLike(AlumnosDAO.ROW_APELLIDOS); break;
              case "Año de nacimiento": findByAnho(); break;
              case "DNI de profesor": findByProfesor(); break;
            }
          }
    }

    private void findByID(){
        int id = Integer.parseInt(txtBusqueda.getText());
        List<Alumnos> als = new ArrayList<>();
        try {
            als.add(mySQLDAOFactory.getAlumnosDAO().get(mySQLDAOFactory.getConnection(), id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tabAlumnos.getItems().setAll(als);
    }

    private void findByDNI(){
        List<Alumnos> als = new ArrayList<>();
        try {
            als = mySQLDAOFactory.getAlumnosDAO().getByDNI(mySQLDAOFactory.getConnection(), txtBusqueda.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tabAlumnos.getItems().setAll(als);
    }

    private void findByRowLike(String row){
        List<Alumnos> als = new ArrayList<>();
        try {
            als = mySQLDAOFactory.getAlumnosDAO().getByRowLike(mySQLDAOFactory.getConnection(), row, txtBusqueda.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tabAlumnos.getItems().setAll(als);
    }

    private void findByAnho(){
        List<Alumnos> als = new ArrayList<>();
        try {
            als = mySQLDAOFactory.getAlumnosDAO().getByYear(mySQLDAOFactory.getConnection(), txtBusqueda.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tabAlumnos.getItems().setAll(als);
    }

    private void findByProfesor(){
        List<Alumnos> als = new ArrayList<>();
        try {
            als = mySQLDAOFactory.getAlumnosDAO().getByProfesor(mySQLDAOFactory.getConnection(), txtBusqueda.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tabAlumnos.getItems().setAll(als);
    }

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

    @FXML
    private void abrir(ActionEvent ae) {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Seleccionar archivo", "*.json"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        path.setText(selectedFile.toPath().toString());
    }

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

    private void guardar() {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar archivo");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Guardar archivo", "*.json"));
        File selectedFile = fileChooser.showSaveDialog(stage);
        path.setText(selectedFile.toPath().toString());
    }

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
