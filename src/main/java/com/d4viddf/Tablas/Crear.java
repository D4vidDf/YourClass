package com.d4viddf.Tablas;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.d4viddf.Error.Errores;

public class Crear {
    public static void createTables(Connection con) {
        Errores errores = new Errores();
        try {
            Statement st = con.createStatement();
            /**
             * Eliminar las tablas si ya existen
             */
            st.execute("drop table if exists Imparten");
            st.execute("drop table if exists Profesores");
            st.execute("drop table if exists Departamentos");
            st.execute("drop table if exists Alumnos");
            st.execute("drop table if exists Asignaturas");
            st.execute("drop view if exists ViewImparten");

            /**
             * Crear las tablas
             */
            st.execute(
                    "CREATE TABLE `Profesores`(    `dni` CHAR(9) NOT NULL,    `nombre` VARCHAR(20) NOT NULL,    `apellidos` VARCHAR(20) NOT NULL,    `fecha_nacimiento` DATE NOT NULL,    `departamentos` INT NOT NULL, `cod_prof` INT NOT NULL primary key );");
            st.execute(
                    "CREATE TABLE Departamentos(    id INT NOT NULL Primary Key,    nombre VARCHAR(20) NOT NULL,    presupuesto INT NOT NULL, descripcion longtext NOT NULL);");
            st.execute(
                    "CREATE TABLE Alumnos(expediente INT NOT NULL Primary Key,  dni CHAR(9) NOT NULL,    nombre VARCHAR(20) NOT NULL,    apellidos longtext NOT NULL,    fecha_nacimiento DATE NOT NULL);");
            st.execute(
                    "CREATE TABLE Asignaturas(    id INT  NOT NULL primary key ,    nombre VARCHAR(20) NOT NULL, curso VARCHAR(20) NOT NULL);");
            st.execute(
                    "CREATE TABLE Imparten(    curso VARCHAR(12) NOT NULL,    alumno INT NOT NULL,    profesor INT NOT NULL,   asignatura INT NOT NULL);");

            /**
             * Crear vista ViewImparten
             */
            st.execute(
                    "CREATE VIEW  ViewImparten AS SELECT im.curso CursoImparten, im.alumno Expedientealumno, al.nombre Nombrealumno, al.apellidos Apellidosalumno, al.dni DNIalumno, pr.cod_prof CodProf, pr.dni DNIprofesor, pr.nombre NombreProfesor, pr.apellidos ApellidosProfesor, dp.nombre Nombredepartamento, asg.id IDasignatura, asg.nombre Nombreasignatura, asg.curso Cursoasignatura FROM imparten AS im INNER JOIN alumnos AS al ON al.expediente = im.alumno INNER JOIN profesores AS pr ON im.profesor = pr.cod_prof INNER JOIN asignaturas AS asg ON asg.id = im.asignatura INNER JOIN departamentos AS dp ON dp.id = pr.departamentos;");
            /**
             * Actualizar las tablas con las claves foráneas
             */
            st.executeUpdate(
                    "ALTER TABLE    Imparten ADD    PRIMARY KEY imparten_curso_primary(curso, alumno, profesor, asignatura);");
            st.executeUpdate(
                    "ALTER TABLE   Profesores ADD    CONSTRAINT profesores_departamento_foreign FOREIGN KEY(departamentos) REFERENCES Departamentos(id);");
            st.executeUpdate(
                    "ALTER TABLE    Imparten ADD    CONSTRAINT imparten_profesor_foreign FOREIGN KEY(profesor) REFERENCES Profesores(cod_prof);");
            st.executeUpdate(
                    "ALTER TABLE    Imparten ADD    CONSTRAINT imparten_alumno_foreign FOREIGN KEY(alumno) REFERENCES Alumnos(expediente);");
            st.executeUpdate(
                    "ALTER TABLE    Imparten ADD    CONSTRAINT imparten_asignatura_foreign FOREIGN KEY(asignatura) REFERENCES Asignaturas(id);");

        } catch (SQLException e) {
            errores.muestraErrorSQL(e);
        }
    }
}
