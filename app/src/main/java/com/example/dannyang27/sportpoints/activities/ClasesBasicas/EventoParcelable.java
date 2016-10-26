package com.example.dannyang27.sportpoints.activities.ClasesBasicas;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dannyang27 on 26/10/16.
 */

public class EventoParcelable implements Parcelable {
    private String nombre;
    private String fecha;
    private String hora;
    private String descripcion;
    private int capacidadActual;
    private int capacidadMaxima;
    private List<String> participantes = new ArrayList<String>();

    public int getCapacidadActual() {
        return capacidadActual;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getNombre() {
        return nombre;
    }

    public List<String> getParticipantes() {
        return participantes;
    }

    public void setCapacidadActual(int capacidadActual) {
        this.capacidadActual = capacidadActual;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setParticipantes(List<String> participantes) {
        this.participantes = participantes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nombre);
        dest.writeString(this.fecha);
        dest.writeString(this.hora);
        dest.writeString(this.descripcion);
        dest.writeInt(this.capacidadActual);
        dest.writeInt(this.capacidadMaxima);
        dest.writeStringList(this.participantes);
    }

    public EventoParcelable() {
    }

    protected EventoParcelable(Parcel in) {
        this.nombre = in.readString();
        this.fecha = in.readString();
        this.hora = in.readString();
        this.descripcion = in.readString();
        this.capacidadActual = in.readInt();
        this.capacidadMaxima = in.readInt();
        this.participantes = in.createStringArrayList();
    }

    public static final Parcelable.Creator<EventoParcelable> CREATOR = new Parcelable.Creator<EventoParcelable>() {
        @Override
        public EventoParcelable createFromParcel(Parcel source) {
            return new EventoParcelable(source);
        }

        @Override
        public EventoParcelable[] newArray(int size) {
            return new EventoParcelable[size];
        }
    };
}

















