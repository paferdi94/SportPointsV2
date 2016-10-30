package com.example.dannyang27.sportpoints.activities.Equipo;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Pablo_Fernandez on 29/10/16.
 */

public class EquipoParceable implements Parcelable{



    private String identificador;
    private String nombre;
    ArrayList<String> jugadores = new ArrayList<String>();
    Bitmap logo;
    String logo_b64;

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<String> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<String> jugadores) {
        this.jugadores = jugadores;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.identificador);
        dest.writeString(this.nombre);
        dest.writeList(this.jugadores);
        //investigar foto
    }

    public EquipoParceable(){

    }

    protected EquipoParceable(Parcel in){
        this.identificador = in.readString();
        this.nombre = in.readString();
        this.jugadores = in.createStringArrayList();
        //byte[] decodedString = Base64.decode(logo, Base64.DEFAULT);
       // Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
       // this.logo = decodedByte;
       // this.logo_b64 = logo;

        //investigar foto
    }

    public static final Parcelable.Creator<EquipoParceable> CREATOR = new Parcelable.Creator<EquipoParceable>() {

        @Override
        public  EquipoParceable createFromParcel(Parcel source){
            return new EquipoParceable(source);
        }


        public EquipoParceable[] newArray(int size) {
            return new EquipoParceable[size];
        }

    };
}
