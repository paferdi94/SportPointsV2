package com.example.dannyang27.sportpoints.activities.Modelos;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.constraint.solver.ArrayLinkedVariables;

import java.util.ArrayList;

/**
 * Created by Danny on 22/11/2016.
 */

public class EventoPruebaDanny implements Parcelable {

    private String imagen;
    private String nombre;
    private String lugar;
    private String hora;
    private String fecha;
    private String descripcion;
    private String admin;
    private String capacidadActual;
    private String capacidadMaxima;
    private ArrayList<String> participantes = new ArrayList<>();

    public EventoPruebaDanny() {
    }

    public EventoPruebaDanny(String imagen, String nombre, String lugar, String hora, String fecha,
                             String descripcion, String admin, String capacidadActual,
                             String capacidadMaxima, ArrayList<String> participantes) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.lugar = lugar;
        this.hora = hora;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.admin = admin;
        this.capacidadActual = capacidadActual;
        this.capacidadMaxima = capacidadMaxima;
        this.participantes = participantes;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getCapacidadActual() {
        return capacidadActual;
    }

    public void setCapacidadActual(String capacidadActual) {
        this.capacidadActual = capacidadActual;
    }

    public String getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(String capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public ArrayList<String> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(ArrayList<String> participantes) {
        this.participantes = participantes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.imagen);
        dest.writeString(this.nombre);
        dest.writeString(this.lugar);
        dest.writeString(this.hora);
        dest.writeString(this.fecha);
        dest.writeString(this.descripcion);
        dest.writeString(this.admin);
        dest.writeString(this.capacidadActual);
        dest.writeString(this.capacidadMaxima);
        dest.writeStringList(this.participantes);
    }

    protected EventoPruebaDanny(Parcel in) {
        this.imagen = in.readString();
        this.nombre = in.readString();
        this.lugar = in.readString();
        this.hora = in.readString();
        this.fecha = in.readString();
        this.descripcion = in.readString();
        this.admin = in.readString();
        this.capacidadActual = in.readString();
        this.capacidadMaxima = in.readString();
        this.participantes = in.createStringArrayList();
    }

    public static final Creator<EventoPruebaDanny> CREATOR = new Creator<EventoPruebaDanny>() {
        @Override
        public EventoPruebaDanny createFromParcel(Parcel source) {
            return new EventoPruebaDanny(source);
        }

        @Override
        public EventoPruebaDanny[] newArray(int size) {
            return new EventoPruebaDanny[size];
        }
    };
}
