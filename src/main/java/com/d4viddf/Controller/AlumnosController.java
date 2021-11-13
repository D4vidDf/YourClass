package com.d4viddf.Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import com.d4viddf.Error.Errores;
import com.d4viddf.Tablas.Alumnos;
import com.d4viddf.TablasDAO.AlumnosDAO;

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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    private TableColumn<Alumnos, Date> colNac;
    @FXML
    private ComboBox<String> cbxBuscarPor;
    @FXML
    private TextField txtBusqueda;
    @FXML
    private TextField txtNum;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtDNI;
    @FXML
    private DatePicker fecha;

    private String selectedItem = null;

    @FXML
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        colNum.setCellValueFactory(new PropertyValueFactory<Alumnos, Integer>("expediente"));
        colDNI.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("dni"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("apellidos"));
        colNac.setCellValueFactory(new PropertyValueFactory<Alumnos, Date>("nacimiento"));

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
            switch (selectedItem) {
            case "Número de expediente":
                findByID();
                break;
            case "DNI":
                findByDNI();
                break;
            case "Nombre":
                findByRowLike();
                break;
            case "Apellidos":
                findByRowLike();
                break;
            case "Año de nacimiento":
                findByAnho();
                break;
            case "DNI de profesor":
                findByProfesor();
                break;
            }
        }
    }

    private void findByID() {
    }

    private void findByDNI() {
    }

    private void findByRowLike() {
    }

    private void findByAnho() {
    }

    private void findByProfesor() {
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

}
