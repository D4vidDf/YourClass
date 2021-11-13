package com.d4viddf.Tablas;

public class Imparten {
    public int curso, profesor, asignatura;
    public String alumno;

    public Imparten() {
    }

    public Imparten(int curso, int profesor, int asignatura, String alumno) {
        this.curso = curso;
        this.profesor = profesor;
        this.asignatura = asignatura;
        this.alumno = alumno;
    }

    public int getCurso() {
        return this.curso;
    }

    public void setCurso(int curso) {
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

    public String getAlumno() {
        return this.alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

}
