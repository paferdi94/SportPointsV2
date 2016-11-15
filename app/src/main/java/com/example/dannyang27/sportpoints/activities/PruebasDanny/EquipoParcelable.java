package com.example.dannyang27.sportpoints.activities.PruebasDanny;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.dannyang27.sportpoints.activities.Modelos.Participante;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dannyang27 on 15/11/16.
 */

public class EquipoParcelable implements Parcelable {

    private String nombre;
    private String deporte;
    private String imagenEquipo;
    private int numJugadores;
    private int maxJugadores;
    private List<Participante> participantes;


    public EquipoParcelable() {
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public String getImagenEquipo() {
        return imagenEquipo;
    }

    public void setImagenEquipo(String imagenEquipo) {
        this.imagenEquipo = imagenEquipo;
    }

    public int getMaxJugadores() {
        return maxJugadores;
    }

    public void setMaxJugadores(int maxJugadores) {
        this.maxJugadores = maxJugadores;
    }

    public int getNumJugadores() {
        return numJugadores;
    }

    public void setNumJugadores(int numJugadores) {
        this.numJugadores = numJugadores;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nombre);
        dest.writeString(this.deporte);
        dest.writeString(this.imagenEquipo);
        dest.writeInt(this.numJugadores);
        dest.writeInt(this.maxJugadores);
        dest.writeList(this.participantes);
    }

    protected EquipoParcelable(Parcel in) {
        this.nombre = in.readString();
        this.deporte = in.readString();
        this.imagenEquipo = in.readString();
        this.numJugadores = in.readInt();
        this.maxJugadores = in.readInt();
        this.participantes = new ArrayList<Participante>();
        in.readList(this.participantes, Participante.class.getClassLoader());
    }

    public static final Parcelable.Creator<EquipoParcelable> CREATOR = new Parcelable.Creator<EquipoParcelable>() {
        @Override
        public EquipoParcelable createFromParcel(Parcel source) {
            return new EquipoParcelable(source);
        }

        @Override
        public EquipoParcelable[] newArray(int size) {
            return new EquipoParcelable[size];
        }
    };
}
