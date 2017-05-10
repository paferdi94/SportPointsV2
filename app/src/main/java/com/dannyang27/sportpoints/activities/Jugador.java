package com.dannyang27.sportpoints.activities;

import android.os.Parcel;
import android.os.Parcelable;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Jugador implements Parcelable {

    private String login;
    private String password;
    private String nombre;
    private String apellidos;
    private String email;
    private long telefono;
    private String fechaNacimiento;
    double valoracion;
    int numValoraciones;

    public Jugador() {
    }

    public Jugador(String apellidos, String email, String fechaNacimiento, String login, String nombre, int numValoraciones, String password, long telefono, double valoracion) {
        this.login = login;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.valoracion = valoracion;
        this.numValoraciones = numValoraciones;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public String getEmail() {
        return this.email;
    }

    public long getTelefono() {
        return this.telefono;
    }

    public String getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public int getNumValoraciones() {
        return numValoraciones;
    }

    public double getValoracion() {
        return valoracion;
    }


    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }

    public void setNumValoraciones(int numValoraciones) {
        this.numValoraciones = numValoraciones;
    }


    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("apellidos", apellidos);
        result.put("email", email);
        result.put("fechaNacimiento", fechaNacimiento);
        result.put("login", login);
        result.put("nombre", nombre);
        result.put("numValoraciones", numValoraciones);
        result.put("password", password);
        result.put("telefono", telefono);
        result.put("valoracion", valoracion);
        //result.put("fechaNacimiento", (new SimpleDateFormat("dd/MM/yyyy")).format(fechaNacimiento));
        //result.put("valoracion", valoracion);
        return result;
    }


    // Metodos Parceable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(apellidos);
        parcel.writeString(email);
        parcel.writeString(fechaNacimiento);
        parcel.writeString(login);
        parcel.writeString(nombre);
        parcel.writeInt(numValoraciones);
        parcel.writeString(password);
        parcel.writeLong(telefono);
        parcel.writeDouble(valoracion);
        //parcel.writeLong(fechaNacimiento.getTime());
        // parcel.writeInt(valoracion);
    }

    public static final Parcelable.Creator<Jugador> CREATOR = new Parcelable.Creator<Jugador>() {
        public Jugador createFromParcel(Parcel in) {
            return new Jugador(in);
        }

        public Jugador[] newArray(int size) {
            return new Jugador[size];
        }
    };

    private Jugador(Parcel in) {
        apellidos = in.readString();
        email = in.readString();
        fechaNacimiento = in.readString();
        login = in.readString();
        nombre = in.readString();
        numValoraciones = in.readInt();
        password = in.readString();
        telefono = in.readLong();
        valoracion = in.readDouble();
        //fechaNacimiento = new Date(in.readLong());
        // valoracion= in.readInt();
    }
}