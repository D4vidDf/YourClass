package com.d4viddf.Tablas;

import java.sql.Date;
import java.time.LocalDate;

public class Alumnos {
    private int expediente;
    private String DNI;
    private String nombre;
    private String apellidos;
    private LocalDate nacimiento;

    public Alumnos() {
    }

    public Alumnos(int expediente,String DNI, String nombre, String apellidos, LocalDate nacimiento) {
        this.expediente = expediente;
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nacimiento = nacimiento;
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

    public LocalDate getNacimiento() {
        return this.nacimiento;
    }

    public void setNacimiento(LocalDate nacimiento) {
        this.nacimiento = nacimiento;
    }
    public int getExpediente() {
        return expediente;
    }
    public void setExpediente(int expediente) {
        this.expediente = expediente;
    }
}
