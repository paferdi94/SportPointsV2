package com.example.dannyang27.sportpoints.activities.Equipo;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.example.dannyang27.sportpoints.activities.Base64Custom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pablo_Fernandez on 29/10/16.
 */

public class EquipoParceable implements Parcelable{

    String identificador;
    String nombre;
    String deporte;
    ArrayList<String> jugadores;
    Bitmap logo;
    int max_jug;

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

    // Añade los jugadores al arraylist de jugadores
    public void addJugadores(String jugs) {
        String[] ids = jugs.split(",");
        for(String id : ids){
            this.jugadores.add(id);
        }
    }

    // Elimina los jugadores del arraylist de jugadores
    public void deleteJugadores(String jugs) {
        String[] ids = jugs.split(",");
        for(String id : ids){
            this.jugadores.remove(id);
        }
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nombre", nombre);
        String jugadores_db = "";
        if(!jugadores.equals(null)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < jugadores.size(); i++) {
                if (i != jugadores.size() - 1) {
                    sb.append(jugadores.get(i) + ",");
                } else {
                    sb.append(jugadores.get(i));
                }
            }
            jugadores_db = sb.toString();
        }
        result.put("jugadores", jugadores_db);
        result.put("deporte", deporte);
        String logo_db = Base64Custom.encodeToBase64(logo,Bitmap.CompressFormat.PNG,100);
        result.put("logo", logo_db);
        result.put("max_jugadores",max_jug);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.identificador);
        dest.writeString(this.nombre);
        dest.writeString(this.deporte);
        dest.writeStringList(jugadores);
        dest.writeParcelable(this.logo,15);
        dest.writeInt(this.max_jug);
    }

    public EquipoParceable() {
    }

    public EquipoParceable(String id, String n, String deporte, int max, Bitmap logo) {
        this.identificador = id;
        this.nombre = n;
        this.deporte = deporte;
        this.jugadores = new ArrayList<>();
        this.logo = logo;
        this.max_jug = max;
    }

    protected EquipoParceable(Parcel in) {
        this.identificador = in.readString();
        this.nombre = in.readString();
        this.deporte = in.readString();
        jugadores = new ArrayList<String>();
        in.readStringList(jugadores);
        this.logo = in.readParcelable(Bitmap.class.getClassLoader());
        this.max_jug = in.readInt();
    }

    public static final Creator<EquipoParceable> CREATOR = new Creator<EquipoParceable>() {
        @Override
        public EquipoParceable createFromParcel(Parcel source) {
            return new EquipoParceable(source);
        }

        @Override
        public EquipoParceable[] newArray(int size) {
            return new EquipoParceable[size];
        }
    };
}
