package com.example.dannyang27.sportpoints.activities;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.dannyang27.sportpoints.activities.Equipo.Equipo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Jugador implements Parcelable {

    private String id;
    private String login;
    private String password;
    private String nombre;
    private String apellidos;
    private String email;
    private String direccion;
    private Date fechaNacimiento;
    private ArrayList<Equipo> equipos;

    public Jugador(String id, String login, String password, String nombre, String apellidos, String email, String direccion, Date fechaNacimiento, ArrayList<Equipo> equipos){
        this.id = id;
        this.login = login;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.equipos = equipos;
    }

    public String getID(){
        return this.id;
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
    public String getDireccion(){
        return this.direccion;
    }
    public Date getFechaNacimiento(){
        return this.fechaNacimiento;
    }
    public ArrayList<Equipo> getEquipos(){
        return this.equipos;
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
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    public void setFechaNacimiento(Date fechaNacimiento){
        this.fechaNacimiento = fechaNacimiento;
    }
    public void setEquipos(ArrayList<Equipo> equipos) {
        this.equipos = equipos;
    }

    // Añade un equipo al arraylist de equipos.
    public void addEquipo(Equipo q){
        this.equipos.add(q);
    }

    // Elimina un jugador del arraylist de jugadores
    public Boolean deleteEquipo(Equipo q){
        return this.equipos.remove(q);
    }

    public Map<String, Object> toMap() {

        HashMap<String, Object> result = new HashMap<>();
        result.put("login", login);
        result.put("password", password);
        result.put("nombre", nombre);
        result.put("apellidos", apellidos);
        result.put("email", email);
        result.put("direccion", direccion);
        result.put("fechaNacimiento", (new SimpleDateFormat("dd/MM/yyyy")).format(fechaNacimiento));
        String equipos_db = "";
        for(Equipo q : equipos){ equipos_db+=q.getID()+","; }
        result.put("equipos",equipos_db);
        return result;
    }


    // Metodos Parceable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(login);
        parcel.writeString(password);
        parcel.writeString(nombre);
        parcel.writeString(apellidos);
        parcel.writeString(email);
        parcel.writeString(direccion);
        parcel.writeLong(fechaNacimiento.getTime());
        parcel.writeList(equipos);
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
        id = in.readString();
        login = in.readString();
        password = in.readString();
        nombre = in.readString();
        apellidos = in.readString();
        email = in.readString();
        direccion = in.readString();
        fechaNacimiento = new Date(in.readLong());
        equipos = in.readArrayList(null);
    }
}