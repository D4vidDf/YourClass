package com.d4viddf;

import java.util.List;

import com.d4viddf.Factory.DAOFactory;
import com.d4viddf.Tablas.Alumnos;
import com.d4viddf.Tablas.Asignaturas;
import com.d4viddf.Tablas.Borrar;
import com.d4viddf.Tablas.Crear;
import com.d4viddf.Tablas.Departamentos;
import com.d4viddf.Tablas.Imparten;
import com.d4viddf.Tablas.Profesores;
import com.d4viddf.TablasDAO.AlumnosDAO;
import com.d4viddf.TablasDAO.AsignaturasDAO;
import com.d4viddf.TablasDAO.DepartamentosDAO;
import com.d4viddf.TablasDAO.ImpartenDAO;
import com.d4viddf.TablasDAO.ProfesoresDAO;

public class App {
    static List<Alumnos> alumnos;
    static List<Asignaturas> asignaturas;
    static List<Profesores> profesores;
    static List<Departamentos> departamentos;
    static List<Imparten> imparten;
    static AlumnosDAO almDAO;
    static AsignaturasDAO asgDAO;
    static ProfesoresDAO proDAO;
    static DepartamentosDAO depDAO;
    static ImpartenDAO impDAO;
    static DAOFactory mySQLFactory;

    public static void main(String[] args) throws Exception {
        boolean salir = false;

        // Create dAO
        try {
            mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            almDAO = mySQLFactory.getAlumnosDAO();
            asgDAO = mySQLFactory.getAsignaturasDAO();
            proDAO = mySQLFactory.getProfesoresDAO();
            depDAO = mySQLFactory.getDepartamentosDAO();
            impDAO = mySQLFactory.getImpartenDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }

        do {
            Menu menu = new Menu();
            int op = menu.mostrarMenu();
            switch (op) {
            case 1:
                Crear.createTables(mySQLFactory.getConnection());
                break;
            case 2:
                Borrar.borr(mySQLFactory.getConnection());
                break;
            case 3:
                getProfesores();
                break;
            case 4:
                getAlumnos();
                break;
            case 5:
                getAsignaturas();
                break;
            case 6:
                getDepartamentos();
                break;
            case 7:
                getImparten();
                break;
            case 8:
                insertarProfesor();
                break;
            case 9:
                insertarAlumno();
                break;
            case 10:
                insertarDepartamento();
                break;
            case 11:
                insertarAsignatura();
                break;
            case 12:
                insertarImparten();
                break;
                case 23: launchApp(); break;

            case 21:
                salir = true;
                break;
            default:
                System.out.println("Opción no válida");
            }

        } while (!salir);
    }

    

    private static void launchApp() {
        
    }



    private static void getImparten() {
        try {
            imparten = impDAO.getAll(mySQLFactory.getConnection());
            for (int i = 0; i < imparten.size(); i++) {
                System.out.println("--------------------------------------");
                System.out.println(imparten.get(i).getAlumno());
                System.out.println(imparten.get(i).getAsignatura());
                System.out.println(imparten.get(i).getCurso());
                System.out.println(imparten.get(i).getProfesor());
            }

        } catch (Exception e) {
            System.out.println("No hay datos en la tabla imparten");
        }
    }

    private static void getDepartamentos() {
        try {
            departamentos = depDAO.getAll(mySQLFactory.getConnection());
            for (int i = 0; i < departamentos.size(); i++) {
                System.out.println("--------------------------------------");
                System.out.println("ID: "+ departamentos.get(i).getId());
                System.out.println(departamentos.get(i).getNombre().toString());
            }

        } catch (Exception e) {
            System.out.println("No hay Departamentos");
        }
    }

    private static void getProfesores() {
        try {
            profesores = proDAO.getAll(mySQLFactory.getConnection());
            for (int i = 0; i < profesores.size(); i++) {
                System.out.println("--------------------------------------");
                System.out.println(profesores.get(i).getCod_prof());
                System.out.println(profesores.get(i).getNombre().toString());
                System.out.println(profesores.get(i).getApellidos().toString());
                System.out.println(profesores.get(i).getDNI().toString());
                System.out.println(profesores.get(i).getFecha_nacimiento().toString());
                System.out.println(profesores.get(i).getDepartamento());
            }

        } catch (Exception e) {
            System.out.println("No hay profesores");
        }
    }

    private static void getAsignaturas() {
        try {
            asignaturas = asgDAO.getAll(mySQLFactory.getConnection());
            for (int i = 0; i < asignaturas.size(); i++) {
                System.out.println("--------------------------------------");
                System.out.println(asignaturas.get(i).getId());
                System.out.println(asignaturas.get(i).getNombre().toString());
                
            }

        } catch (Exception e) {
            System.out.println("No hay asignaturas");
        }
    }

    private static void getAlumnos() {
        alumnos.clear();
        try {
            alumnos = almDAO.getAll(mySQLFactory.getConnection());
            for (int i = 0; i < alumnos.size(); i++) {
                System.out.println("--------------------------------------");
                System.out.println("DNI: "+ alumnos.get(i).getDNI().toString());
                System.out.println("Apellidos: "+alumnos.get(i).getApellidos().toString());
                System.out.println("Nombre: "+alumnos.get(i).getNombre().toString());
                System.out.println("Fecha nacimiento: "+alumnos.get(i).getNacimiento().toString());
            
            }

        } catch (Exception e) {
            System.out.println("No hay alumnos");
        }

    }

    public static void insertarAlumno() {
        try {
            //almDAO.insertarLote(mySQLFactory.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertarProfesor() {
        try {
            proDAO.insertarLote(mySQLFactory.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertarDepartamento() {
        try {
            depDAO.insertarLote(mySQLFactory.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertarAsignatura() {
        try {
            asgDAO.insertarLote(mySQLFactory.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertarImparten() {
        try {
            impDAO.insertarLote(mySQLFactory.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
