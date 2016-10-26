package com.example.dannyang27.sportpoints.activities;
import com.example.dannyang27.sportpoints.activities.Jugador;

import java.util.ArrayList;

/**
 * Created by paferdi94 on 23/10/2016.
 * Clase equipo->Lógica
 */

public class Equipo {

    String identificador;
    String nombre;
    ArrayList<Jugador> jugadores;

    public Equipo(String id, String n, ArrayList<Jugador> js){
        this.identificador = id;
        this.nombre = n;
        this.jugadores = js;
    }

    public String getIdentificador() {
        return identificador;
    }
    public String getNom() {
        return nombre;
    }
    public int getNum_integrantes() {
        return this.jugadores.size();
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    public void setNom(String nom) {
        this.nombre = nom;
    }

    // Devuelve el ArrayList de jugadores
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    // Sobreescribe los jugadores con los nuevos.
    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    // Añade un jugador al arraylist de jugadores
    public void addJugador(Jugador j){
        this.jugadores.add(j);
    }

    // Elimina un jugador del arraylist de jugadores
    // Por hacer.
    public Jugador deleteJugador(String id){
        return null;
    }
}
