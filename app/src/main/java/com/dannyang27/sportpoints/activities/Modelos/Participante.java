package com.dannyang27.sportpoints.activities.Modelos;

/**
 * Created by Dannyang27 on 30/10/16.
 */

public class Participante {
    private String dni;
    private String nombre;
    private String fecha;
    private String email;
    private String telefono;

    public Participante() {
    }

    public Participante(String dni, String email, String fecha, String telefono, String nombre) {
        this.dni = dni;
        this.email = email;
        this.fecha = fecha;
        this.telefono = telefono;
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
