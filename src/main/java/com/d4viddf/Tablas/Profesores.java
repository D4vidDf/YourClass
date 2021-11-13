package com.d4viddf.Tablas;

import java.sql.Date;
import java.time.LocalDate;

public class Profesores {
    public int departamento, cod_prof;
    public String DNI, nombre, apellidos;
    public LocalDate fecha_nacimiento;

    public int getDepartamento() {
        return this.departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

    public int getCod_prof() {
        return this.cod_prof;
    }

    public void setCod_prof(int cod_prof) {
        this.cod_prof = cod_prof;
    }

    public String getDNI() {
        return this.DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public LocalDate getFecha_nacimiento() {
        return this.fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Profesores(int departamento, int cod_prof, String DNI, String nombre, String apellidos,
            LocalDate fecha_nacimiento) {
        this.departamento = departamento;
        this.cod_prof = cod_prof;
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Profesores() {
    }

}
