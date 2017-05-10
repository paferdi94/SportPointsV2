package com.dannyang27.sportpoints.activities.Evento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Evento {
    private String deporte;
    private String fecha;
    private String hora;
    private String descripcion;
    private int capacidadActual;
    private int capacidadMaxima;
    private String lugar;
    private String usuario;
    private List<String> participantes = new ArrayList<String>();

    public Evento() {
    }

    public Evento(int capacidadActual, int capacidadMaxima, String descripcion, String fecha,
                  String hora, String lugar, String deporte, String usuario) {

        this.capacidadActual = capacidadActual;
        this.capacidadMaxima = capacidadMaxima;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hora = hora;
        this.lugar = lugar;
        this.deporte = deporte;
        this.usuario = usuario;
    }

    public Map<String, Object> toMap() {

        HashMap<String, Object> result = new HashMap<>();
        result.put("capacidadActual", capacidadActual);
        result.put("capacidadMaxima", capacidadMaxima);
        result.put("descripcion", descripcion);
        result.put("fecha", fecha);
        result.put("hora", hora);
        result.put("lugar", lugar);
        result.put("deporte", deporte);
        result.put("usuario", usuario);

        return result;
    }


    public String getDeporteeporte() {
        return deporte;
    }

    public void setDeporte(String nombre) {
        this.deporte = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public List<String> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<String> participantes) {
        this.participantes = participantes;
    }
}
