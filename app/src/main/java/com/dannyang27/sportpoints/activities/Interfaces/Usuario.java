package com.dannyang27.sportpoints.activities.Interfaces;

/**
 * Created by paferdi on 12/05/2017.
 * Interfaz base para tipo de usuarios de la app
 */

public abstract class Usuario {

    private String nombre;
    private Integer telefono;



    private Integer tipoUser;

    public Usuario(String nombre, Integer telefono, Integer tipoUSer){

        this.nombre = nombre;
        this.telefono = telefono;
        this.tipoUser = tipoUSer;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        telefono = telefono;
    }

    public Integer getTipoUser() {
        return tipoUser;
    }

    public void setTipoUser(Integer tipoUser) {
        this.tipoUser = tipoUser;
    }



}
