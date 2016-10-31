package com.example.dannyang27.sportpoints.activities.Equipo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by paferdi94 on 23/10/2016.
 * Clase equipo->Lógica
 */

public class Equipo {

    String identificador;
    String nombre;
    String deporte;
    ArrayList<String> jugadores = new ArrayList<String>();
    Bitmap logo;
    int max_jug;

    public Equipo(String id, String n, String deporte, String id_j, int max, Bitmap logo) {
        this.identificador = id;
        this.nombre = n;
        this.deporte = deporte;
        this.jugadores.add(id_j);
        this.logo = logo;
        this.max_jug = max;
    }

    public String getID() {
        return identificador;
    }

    public String getNom() {
        return nombre;
    }

    public String getDeporte() {
        return deporte;
    }

    public Bitmap getLogo() {
        return this.logo;
    }

    public ArrayList<String> getJugadores() {
        return jugadores;
    }

    public int getMaxJugadores() {
        return max_jug;
    }

    public void setID(String identificador) {
        this.identificador = identificador;
    }

    public void setNom(String nom) {
        this.nombre = nom;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public void setLogo(Bitmap logo) {
        this.logo = logo;
    }

    public void setJugadores(ArrayList<String> jugadores) {
        this.jugadores = jugadores;
    }

    public void setMaxJugadores(int max) {
        this.max_jug = max;
    }


    // Añade un jugador al arraylist de jugadores
    public void addJugador(String id_j) {
        this.jugadores.add(id_j);
    }

    // Elimina un jugador del arraylist de jugadores
    public Boolean deleteJugador(String id_j) {
        return this.jugadores.remove(id_j);
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nombre", nombre);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < jugadores.size(); i++) {
            sb.append(jugadores.get(i));
            if (i != jugadores.size() - 1) {
                sb.append(",");
            }
        }
        String jugadores_db = sb.toString();
        result.put("jugadores", jugadores_db);
        result.put("deporte", deporte);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        logo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        String logo_db = Base64.encodeToString(byteArray, Base64.DEFAULT);
        result.put("logo", logo_db);
        result.put("max_jugadores",max_jug);
        return result;
    }
}
