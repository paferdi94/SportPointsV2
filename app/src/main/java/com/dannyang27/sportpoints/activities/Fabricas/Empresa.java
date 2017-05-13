package com.dannyang27.sportpoints.activities.Fabricas;

import android.os.Parcel;
import android.os.Parcelable;

import com.dannyang27.sportpoints.activities.Modelos.Usuario;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pablo_ Fernandez on 13/05/2017.
 */

public class Empresa extends Usuario implements Parcelable {

    private String CIF;

    public Empresa(String nombre, long telefono, Integer tipoUser, String email, String login, String password, String CIF) {
        super(nombre, telefono, tipoUser, email, login, password);
        this.CIF = CIF;
    }
    public String getCIF() {
        return CIF;
    }

    public void setCIF(String CIF) {
        this.CIF = CIF;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("CIF", CIF);
        result.put("email", email);
        result.put("login", login);
        result.put("nombre", nombre);
        result.put("password", password);
        result.put("telefono", telefono);
        result.put("tipoUser", tipoUser);
        //result.put("fechaNacimiento", (new SimpleDateFormat("dd/MM/yyyy")).format(fechaNacimiento));
        //result.put("valoracion", valoracion);
        return result;
    }


    // Metodos Parceable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(CIF);
        parcel.writeString(email);
        parcel.writeString(login);
        parcel.writeString(nombre);
        parcel.writeString(password);
        parcel.writeLong(telefono);
        parcel.writeInt(tipoUser);
        //parcel.writeLong(fechaNacimiento.getTime());
        // parcel.writeInt(valoracion);
    }

    public static final Parcelable.Creator<Empresa> CREATOR = new Parcelable.Creator<Empresa>() {
        public Empresa createFromParcel(Parcel in) {
            return new Empresa(in);
        }

        public Empresa[] newArray(int size) {
            return new Empresa[size];
        }
    };

    private Empresa(Parcel in) {
        CIF = in.readString();
        email = in.readString();
        login = in.readString();
        nombre = in.readString();
        password = in.readString();
        telefono = in.readLong();
        //fechaNacimiento = new Date(in.readLong());
        // valoracion= in.readInt();
    }
}
