package com.example.dannyang27.sportpoints.activities.PruebasDanny;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dannyang27.sportpoints.R;
import com.example.dannyang27.sportpoints.activities.Modelos.EventoParcelable;

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
}
