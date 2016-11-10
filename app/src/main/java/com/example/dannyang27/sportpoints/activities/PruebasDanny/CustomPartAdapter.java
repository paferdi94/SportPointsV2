package com.example.dannyang27.sportpoints.activities.PruebasDanny;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dannyang27.sportpoints.R;
import com.example.dannyang27.sportpoints.activities.Modelos.Participante;

import java.util.List;

/**
 * Created by Danny on 30/10/2016.
 */

public class CustomPartAdapter extends ArrayAdapter<Participante> {
    public CustomPartAdapter(Context context, List<Participante> participantes) {
        super(context,0, participantes);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
            convertView = inflater.inflate(R.layout.evento_custom_adapter_part,parent,false);
        }

        TextView nombre = (TextView) convertView.findViewById(R.id.nombre_part_id);
        TextView tlf = (TextView) convertView.findViewById(R.id.tlf_part_id);

        Participante p = getItem(position);
        nombre.setText(p.getNombre());
        tlf.setText(p.getTelefono());


        return convertView;

    }
}
