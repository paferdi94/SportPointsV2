package com.example.dannyang27.sportpoints.activities.Promocion;




import java.util.Date;
import java.util.List;

/**
 * Created by Pablo_Fernandez on 23/11/16.
 */

public class Promocion {

    String nombrePromo;
    String descripcion;
    Date fechaInit;
    Date fechaFin;
    List<Promocion> promociones;
    String deporte;

    public Promocion(String nombrePromo, String descripcion, Date fechaInit, Date fechaFin, String deporte) {
        this.nombrePromo = nombrePromo;
        this.descripcion = descripcion;
        this.fechaInit = fechaInit;
        this.fechaFin = fechaFin;
        this.deporte = deporte;
    }

    public String getNombrePromo() {
        return nombrePromo;
    }

    public void setNombrePromo(String nombrePromo) {
        this.nombrePromo = nombrePromo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInit() {
        return fechaInit;
    }

    public void setFechaInit(Date fechaInit) {
        this.fechaInit = fechaInit;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<Promocion> getPromociones() {
        return promociones;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }
}
