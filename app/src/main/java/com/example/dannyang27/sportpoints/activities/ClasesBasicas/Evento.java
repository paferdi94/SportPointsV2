package com.example.dannyang27.sportpoints.activities.ClasesBasicas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dannyang27 on 25/10/16.
 */

public class Evento {
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


}
