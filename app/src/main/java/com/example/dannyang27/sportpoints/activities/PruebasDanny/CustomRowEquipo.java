package com.example.dannyang27.sportpoints.activities.PruebasDanny;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dannyang27.sportpoints.R;
import com.example.dannyang27.sportpoints.activities.Equipo.EquipoParceable;

import java.util.List;


public class CustomRowEquipo extends ArrayAdapter<EquipoParceable> {

    public CustomRowEquipo(Context context, List<EquipoParceable> equipos) {
        super(context,0, equipos);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
            convertView = inflater.inflate(R.layout.custom_row_equipo,parent,false);
        }

        TextView nombre = (TextView) convertView.findViewById(R.id.nameTxt);
        TextView deporte = (TextView) convertView.findViewById(R.id.deporteTxt);
        TextView jugadores = (TextView) convertView.findViewById(R.id.jugTxt);
        TextView max_jugadores = (TextView) convertView.findViewById(R.id.maxjugTxt);

        EquipoParceable eq = getItem(position);
        nombre.setText(eq.getNom());
        Log.e("Sport",(eq.getMaxJugadores()==-1)+"");
        deporte.setText("Deporte: "+eq.getDeporte());
        jugadores.setText("Jugadores: "+eq.getJugadores().size());
        if(eq.getMaxJugadores()==-1){
            max_jugadores.setText("Jugadores máximos: Sin límite");
        }else{
            max_jugadores.setText("Jugadores máximos: "+eq.getMaxJugadores());
        }
        return convertView;

    }
}
