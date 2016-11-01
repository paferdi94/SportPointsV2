package com.example.dannyang27.sportpoints.activities.Equipo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dannyang27.sportpoints.R;

import java.util.List;

/**
 * Created by Pablo_Fernandez on 30/10/16.
 * Item de la lista Equipos
 */

public class EquipoItemHolder extends ArrayAdapter<EquipoParceable> {

    private TextView txtNombreEq, subti;
    private ImageView imageView_equipo;

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
        subti = (TextView) convertView.findViewById(R.id.subti);
        imageView_equipo = (ImageView) convertView.findViewById(R.id.imageView_equipo);

        EquipoParceable ep = getItem(position);
        txtNombreEq.setText(ep.getNom());
        int jugadores = ep.getJugadores().size();
        int max_jugadores = ep.getMaxJugadores();
        if(max_jugadores==-1){
            subti.setText("Jugadores: "+jugadores+"/∞");
        }else{
            subti.setText("Jugadores: "+jugadores+"/"+max_jugadores);
        }
        imageView_equipo.setImageBitmap(ep.getLogo());
        return convertView;
    }
}

