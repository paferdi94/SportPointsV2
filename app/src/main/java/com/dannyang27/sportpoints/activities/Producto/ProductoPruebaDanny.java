package com.dannyang27.sportpoints.activities.Producto;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Danny on 04/12/2016.
 */

public class ProductoPruebaDanny implements Parcelable {
    private String autor;
    private String nombre;
    private String precio;
    private String descripcion;
    private String imagen;
    private String telefono;
    //private String fecha;

    public ProductoPruebaDanny() {
    }

    public ProductoPruebaDanny(String autor, String nombre, String precio, String telefono,String imagen, String descripcion) {
        this.autor = autor;
        this.nombre = nombre;
        this.precio = precio;
        this.telefono = telefono;
        this.imagen = imagen;
        this.descripcion = descripcion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.autor);
        dest.writeString(this.nombre);
        dest.writeString(this.precio);
        dest.writeString(this.descripcion);
        dest.writeString(this.imagen);
        dest.writeString(this.telefono);
    }

    protected ProductoPruebaDanny(Parcel in) {
        this.autor = in.readString();
        this.nombre = in.readString();
        this.precio = in.readString();
        this.descripcion = in.readString();
        this.imagen = in.readString();
        this.telefono = in.readString();
    }

    public static final Creator<ProductoPruebaDanny> CREATOR = new Creator<ProductoPruebaDanny>() {
        @Override
        public ProductoPruebaDanny createFromParcel(Parcel source) {
            return new ProductoPruebaDanny(source);
        }

        @Override
        public ProductoPruebaDanny[] newArray(int size) {
            return new ProductoPruebaDanny[size];
        }
    };
}
