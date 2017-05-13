package com.dannyang27.sportpoints.activities.Modelos;

/**
 * Created by Pablo_Fernandez on 12/5/17.
 */

public abstract class Usuario {

    public String nombre;
    public long telefono;
    public Integer tipoUser;
    public String email;
    public String login;
    public String password;

    public Usuario(){};

    public Usuario(String nombre, long telefono, Integer tipoUser, String email, String login, String password) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.tipoUser = tipoUser;
        this.email = email;
        this.login = login;
        this.password = password;
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

    public Integer getTipoUser() {
        return tipoUser;
    }

    public void setTipoUser(Integer tipoUser) {
        this.tipoUser = tipoUser;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
