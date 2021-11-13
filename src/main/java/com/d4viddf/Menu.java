package com.d4viddf;

import java.util.Scanner;

public class Menu {
    public int mostrarMenu() {
        Scanner teclado = new Scanner(System.in);

        System.out.println("1) Crear tablas");
        System.out.println("2) Borrar datos tablas");
        System.out.println("3) Mostrar Profesores");
        System.out.println("4) Mostrar Alumnos");
        System.out.println("5) Mostrar Asignaturas");
        System.out.println("6) Mostrar Departamentos");
        System.out.println("7) Mostrar Imparten");
        System.out.println("8) Insertar Profesores");
        System.out.println("9) Insertar Alumnos");
        System.out.println("10) Insertar Departamentos");
        System.out.println("11) Insertar Asignaturas");
        System.out.println("12) Insertar Imparten");
        System.out.println("21) Salir");

        int op = teclado.nextInt();

        return op;
    }

    public boolean showMessage(String message) {
        System.out.println(message);
        return true;
    }
}
