package com.dannyang27.sportpoints.activities.Modelos;

/**
 * Created by Dannyang27 on 30/10/16.
 */

public class Participante extends Usuario{
    private String dni;
    private String fecha;
    private String email;


    public Participante() {
    }

    public Participante(String nombre, long telefono, Integer tipoUser, String email, String login, String password, String dni, String fecha) {
        super(nombre, telefono, tipoUser, email, login, password);
        this.dni = dni;
        this.nombre = nombre;
        this.fecha = fecha;
        this.email = email;
        this.telefono = telefono;
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

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

}
