package com.dannyang27.sportpoints.activities.Evento;

import android.os.Parcel;
import android.os.Parcelable;

import com.dannyang27.sportpoints.activities.Entidades.Participante;

import java.util.ArrayList;
import java.util.List;

public class EventoParcelable implements Parcelable {
    private String creador;
    private String nombre;
    private String lugar;
    private String fecha;
    private String hora;
    private String descripcion;
    private int capacidadActual;
    private int capacidadMaxima;
    private String eventoPhoto;

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public String getEventoPhoto() {
        return eventoPhoto;
    }

    public void setEventoPhoto(String eventoPhoto) {
        this.eventoPhoto = eventoPhoto;
    }

    private List<Participante> participantes = new ArrayList<>();

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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getCapacidadActual() {
        return capacidadActual;
    }

    public void setCapacidadActual(int capacidadActual) {
        this.capacidadActual = capacidadActual;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }

    public EventoParcelable() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.creador);
        dest.writeString(this.nombre);
        dest.writeString(this.lugar);
        dest.writeString(this.fecha);
        dest.writeString(this.hora);
        dest.writeString(this.descripcion);
        dest.writeInt(this.capacidadActual);
        dest.writeInt(this.capacidadMaxima);
        dest.writeString(this.eventoPhoto);
        dest.writeList(this.participantes);
    }

    protected EventoParcelable(Parcel in) {
        this.creador = in.readString();
        this.nombre = in.readString();
        this.lugar = in.readString();
        this.fecha = in.readString();
        this.hora = in.readString();
        this.descripcion = in.readString();
        this.capacidadActual = in.readInt();
        this.capacidadMaxima = in.readInt();
        this.eventoPhoto = in.readString();
        this.participantes = new ArrayList<Participante>();
        in.readList(this.participantes, Participante.class.getClassLoader());
    }

    public static final Creator<EventoParcelable> CREATOR = new Creator<EventoParcelable>() {
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

















