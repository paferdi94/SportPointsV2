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

    /**
     * Created by paferdi on 12/05/2017.
     * Interfaz base para tipo de usuarios de la app
     */

    public abstract static class Usuario {

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
}
