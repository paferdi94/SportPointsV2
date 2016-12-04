package com.example.dannyang27.sportpoints.activities;

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
    private int telefono;
    private Date fechaNacimiento;

    public Jugador(String login, String password, String nombre, String apellidos, String email, int telefono, Date fechaNacimiento){
        this.login = login;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getLogin(){
        return this.login;
    }
    public String getPassword(){
        return this.password;
    }
    public String getNombre(){
        return this.nombre;
    }
    public String getApellidos(){
        return this.apellidos;
    }
    public String getEmail(){
        return this.email;
    }
    public int getTelefono(){
        return this.telefono;
    }
    public Date getFechaNacimiento(){
        return this.fechaNacimiento;
    }

    public void setLogin(String login){
        this.login = login;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setApellidos(String apellidos){
        this.apellidos = apellidos;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setTelefono(int telefono){
        this.telefono = telefono;
    }
    public void setFechaNacimiento(Date fechaNacimiento){
        this.fechaNacimiento = fechaNacimiento;
    }

    public Map<String, Object> toMap() {

        HashMap<String, Object> result = new HashMap<>();
        result.put("login", login);
        result.put("password", password);
        result.put("nombre", nombre);
        result.put("apellidos", apellidos);
        result.put("email", email);
        result.put("telefono", telefono);
        result.put("fechaNacimiento", (new SimpleDateFormat("dd/MM/yyyy")).format(fechaNacimiento));
        return result;
    }


    // Metodos Parceable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(login);
        parcel.writeString(password);
        parcel.writeString(nombre);
        parcel.writeString(apellidos);
        parcel.writeString(email);
        parcel.writeInt(telefono);
        parcel.writeLong(fechaNacimiento.getTime());
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
        login = in.readString();
        password = in.readString();
        nombre = in.readString();
        apellidos = in.readString();
        email = in.readString();
        telefono = in.readInt();
        fechaNacimiento = new Date(in.readLong());
    }
}