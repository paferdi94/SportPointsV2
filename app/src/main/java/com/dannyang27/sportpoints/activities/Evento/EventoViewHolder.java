package com.dannyang27.sportpoints.activities.Evento;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dannyang27.sportpoints.R;

/**
 * Created by Dannyang27 on 23/11/16.
 */

public class EventoViewHolder extends RecyclerView.ViewHolder {
    ImageView img;
    TextView nombreTv;
    TextView lugarTv;
    TextView horaTv;
    TextView fechaTv;
    View view;

    public EventoViewHolder(View itemView) {
        super(itemView);
        view = itemView;

        img = (ImageView) itemView.findViewById(R.id.imagen_evento_md);
        nombreTv = (TextView) itemView.findViewById(R.id.nombre_evento_md);
        lugarTv = (TextView) itemView.findViewById(R.id.lugar_evento_md);
        horaTv = (TextView) itemView.findViewById(R.id.hora_evento_md);
        fechaTv = (TextView) itemView.findViewById(R.id.fecha_evento_md);


    }
}

