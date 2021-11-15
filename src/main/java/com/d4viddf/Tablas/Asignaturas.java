package com.d4viddf.Tablas;

public class Asignaturas {
    public String nombre;
    public int id;
    public String curso;

    public Asignaturas() {
    }

    public Asignaturas(String nombre, int id, String curso) {
        this.nombre = nombre;
        this.id = id;
        this.curso= curso;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setCurso(String curso) {
        this.curso = curso;
    }
    public String getCurso() {
        return curso;
    }

}
