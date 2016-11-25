package com.example.dannyang27.sportpoints.activities.Modelos;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Danny on 23/11/2016.
 */

public class EquipoPruebaDanny implements Parcelable {
    private String creador;
    private String nombre;
    private String deporte;
    private String capacidadActual;
    private String capacidadMaxima;
    private String imagen;
    private String descripcion;
    private ArrayList<String> participantes;

    public EquipoPruebaDanny() {
    }

    public EquipoPruebaDanny(String creador, String nombre, String deporte, String descripcion, String capacidadActual, String capacidadMaxima, String imagen, ArrayList<String> participantes) {
        this.creador = creador;
        this.nombre = nombre;
        this.deporte = deporte;
        this.descripcion = descripcion;
        this.capacidadActual = capacidadActual;
        this.capacidadMaxima = capacidadMaxima;
        this.imagen = imagen;
        this.participantes = participantes;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.creador);
        dest.writeString(this.nombre);
        dest.writeString(this.deporte);
        dest.writeString(this.capacidadActual);
        dest.writeString(this.capacidadMaxima);
        dest.writeString(this.imagen);
        dest.writeString(this.descripcion);
        dest.writeStringList(this.participantes);
    }

    protected EquipoPruebaDanny(Parcel in) {
        this.creador = in.readString();
        this.nombre = in.readString();
        this.deporte = in.readString();
        this.capacidadActual = in.readString();
        this.capacidadMaxima = in.readString();
        this.imagen = in.readString();
        this.descripcion = in.readString();
        this.participantes = in.createStringArrayList();
    }

    public static final Creator<EquipoPruebaDanny> CREATOR = new Creator<EquipoPruebaDanny>() {
        @Override
        public EquipoPruebaDanny createFromParcel(Parcel source) {
            return new EquipoPruebaDanny(source);
        }

        @Override
        public EquipoPruebaDanny[] newArray(int size) {
            return new EquipoPruebaDanny[size];
        }
    };
}
