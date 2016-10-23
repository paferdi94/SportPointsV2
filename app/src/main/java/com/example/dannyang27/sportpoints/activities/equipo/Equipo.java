package com.example.dannyang27.sportpoints.activities.equipo;
import com.example.dannyang27.sportpoints.activities.jugador.jugador;

import java.util.ArrayList;

/**
 * Created by paferdi94 on 23/10/2016.
 * Clase equipo->Lógica
 */

public class Equipo {

    int identificador = -1;
    String nom = "";
    int num_integrantes = 0;
    ArrayList<jugador> jugadores = null;



    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNum_integrantes() {
        return num_integrantes;
    }

    public void setNum_integrantes(int num_integrantes) {
        this.num_integrantes = num_integrantes;
    }

    public ArrayList<jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public Equipo(int id, String nombre, int n_integrantes, ArrayList<jugador> j){
        this.identificador = id;
        this.nom= nombre;
        this.num_integrantes = n_integrantes;
        this.jugadores = j;
    }

    public importarEquipo(int ident){


    }
}
