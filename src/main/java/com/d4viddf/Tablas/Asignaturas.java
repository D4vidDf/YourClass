package com.d4viddf.Tablas;

public class Asignaturas {
    public String nombre;
    public int id;

    public Asignaturas() {
    }

    public Asignaturas(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
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

}
