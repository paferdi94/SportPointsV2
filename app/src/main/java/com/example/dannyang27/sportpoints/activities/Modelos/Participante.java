package com.example.dannyang27.sportpoints.activities.Modelos;

/**
 * Created by Dannyang27 on 30/10/16.
 */

public class Participante {
    private String nombre;
    private String telefono;

    public Participante() {
    }

    public Participante(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
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
