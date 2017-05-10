package com.dannyang27.sportpoints.activities.Equipo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dannyang27.sportpoints.R;
import com.dannyang27.sportpoints.activities.Jugador;

import java.util.List;

/**
 * Created by Danny on 30/10/2016.
 */

public class CustomJugAdapter extends ArrayAdapter<Jugador> {
    public CustomJugAdapter(Context context, List<Jugador> jugadores) {
        super(context,0, jugadores);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
            convertView = inflater.inflate(R.layout.equipo_custom_adapter_jug,parent,false);
        }

        TextView nombre = (TextView) convertView.findViewById(R.id.nombre_jug_id);
        TextView email = (TextView) convertView.findViewById(R.id.email_jug_id);

        Jugador j = getItem(position);
        nombre.setText(j.getNombre());
        email.setText(j.getEmail());
        return convertView;
    }
}
