package com.d4viddf.Tablas;

public class Imparten {
    public int profesor, asignatura, alumno;
    public String curso;

    public Imparten() {
    }

    public Imparten(String curso, int profesor, int asignatura, int alumno) {
        this.curso = curso;
        this.profesor = profesor;
        this.asignatura = asignatura;
        this.alumno = alumno;
    }

    public String getCurso() {
        return this.curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getProfesor() {
        return this.profesor;
    }

    public void setProfesor(int profesor) {
        this.profesor = profesor;
    }

    public int getAsignatura() {
        return this.asignatura;
    }

    public void setAsignatura(int asignatura) {
        this.asignatura = asignatura;
    }

    public int getAlumno() {
        return this.alumno;
    }

    public void setAlumno(int alumno) {
        this.alumno = alumno;
    }

}
