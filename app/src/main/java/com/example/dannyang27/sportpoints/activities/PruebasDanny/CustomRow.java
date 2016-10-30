package com.example.dannyang27.sportpoints.activities.PruebasDanny;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dannyang27.sportpoints.R;
import com.example.dannyang27.sportpoints.activities.Modelos.EventoParcelable;

import java.util.ArrayList;
import java.util.List;


public class CustomRow extends ArrayAdapter<EventoParcelable> {



    public CustomRow(Context context, List<EventoParcelable> eventos) {
        super(context,0, eventos);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
            convertView = inflater.inflate(R.layout.custom_row,parent,false);
        }

        TextView nombre = (TextView) convertView.findViewById(R.id.nameTxt);
        TextView lugar = (TextView) convertView.findViewById(R.id.PlaceTxt);
        TextView hora = (TextView) convertView.findViewById(R.id.hourTxt);
        TextView fecha = (TextView) convertView.findViewById(R.id.dateTxt);

        EventoParcelable ep = getItem(position);
        nombre.setText(ep.getNombre());
        lugar.setText(ep.getLugar());
        hora.setText(ep.getHora());
        fecha.setText(ep.getFecha());

        return convertView;

    }

    /**
     * Created by Pablo_Fernandez on 30/10/16.
     * Item de la lista Equipos
     */

    public static class EquipoItemHolder extends ArrayAdapter<EquipoParceable> {

        public TextView txtNombreEq, subti;
        public ImageView imageView2;
        public View mView;

        public EquipoItemHolder(Context context, List<EquipoParceable> equipos) {
            super(context, 0, equipos);

        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.itemequip, parent, false);
            }

            txtNombreEq = (TextView) convertView.findViewById(R.id.txtNombreEq);
            // subti = (TextView) convertView.findViewById(R.id.subti);
            mView = (View) convertView.findViewById(R.id.imageView_equipo);

            EquipoParceable ep = getItem(position);
            txtNombreEq.setText(ep.getNombre());
            //hacer imagen
            //añadir subti si lo usamos
            return convertView;
        }
    }

    /**
     * Created by Pablo_Fernandez on 29/10/16.
     */

    public static class EquipoParceable implements Parcelable {



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

        public static final Creator<EquipoParceable> CREATOR = new Creator<EquipoParceable>() {

            @Override
            public EquipoParceable createFromParcel(Parcel source){
                return new EquipoParceable(source);
            }


            public EquipoParceable[] newArray(int size) {
                return new EquipoParceable[size];
            }

        };
    }
}
