package com.example.ejercicio31a.Configuracion;

import java.io.Serializable;

public class empleados implements Serializable {

    String Nombre, Apellidos, Direccion, Puesto,Edad;
   // int Edad;// Id;
    private Integer id;

    public empleados(String nombre, String apellidos, String direccion, String puesto, String edad, Integer id) {
        Nombre = nombre;
        Apellidos = apellidos;
        Direccion = direccion;
        Puesto = puesto;
        Edad = edad;
        this.id = id;
    }
/* public empleados(String nombre, String apellidos, String direccion, String puesto, int edad, Integer id) {
        Nombre = nombre;
        Apellidos = apellidos;
        Direccion = direccion;
        Puesto = puesto;
        Edad = edad;
        this.id = id;
    }*/
 /*public empleados(String nombre, String apellidos, String direccion, String puesto, int edad, int id) {
        Nombre = nombre;
        Apellidos = apellidos;
        Direccion = direccion;
        Puesto = puesto;
        Edad = edad;
        Id = id;
    }*/

    public empleados() {
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getPuesto() {
        return Puesto;
    }

    public void setPuesto(String puesto) {
        Puesto = puesto;
    }

    public String getEdad() {
        return Edad;
    }

    public void setEdad(String edad) {
        Edad = edad;
    }

    /* public int getEdad() {
        return Edad;
    }

    public void setEdad(int edad) {
        Edad = edad;
    }*/

   public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
  /*public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }*/
}
