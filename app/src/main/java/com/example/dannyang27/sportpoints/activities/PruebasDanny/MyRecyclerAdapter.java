package com.example.dannyang27.sportpoints.activities.PruebasDanny;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.dannyang27.sportpoints.R;
import com.example.dannyang27.sportpoints.activities.Evento.EventoInfo_MD;
import com.example.dannyang27.sportpoints.activities.Evento.EventoParcelable;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Dannyang27 on 13/11/16.
 */

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    ArrayList<EventoParcelable> eventos = new ArrayList<>();


    private Context context;

    public RecyclerAdapter(Context context) {
        this.context = context;
    }

    public void addEventos() {

        for (int i = 0; i < 10; i++) {
            EventoParcelable e = new EventoParcelable();
            e.setNombre("EVENTO" + i);
            e.setLugar("DENIA");
            e.setHora("12:30");
            e.setFecha("12/12/12");
            e.setEventoPhoto("mestalla2.jpg");

            eventos.add(e);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView descripcionTv;
        private TextView lugarTv;
        private TextView horaTv;
        private TextView fechaTv;
        private CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.imagen_evento_md);
            descripcionTv = (TextView) itemView.findViewById(R.id.nombre_evento_md);
            lugarTv = (TextView) itemView.findViewById(R.id.lugar_evento_md);
            horaTv = (TextView) itemView.findViewById(R.id.hora_evento_md);
            fechaTv = (TextView) itemView.findViewById(R.id.fecha_evento_md);
            cardView = (CardView) itemView.findViewById(R.id.evento_cardview);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    int position = getAdapterPosition();
//                    Snackbar.make(view, "Click detected on item: " + position,
//                            Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    view.getContext().startActivity(new Intent(context, EventoInfo_MD.class));

                }
            });
        }
    }

    public interface OnItemLongClickListener {
        public boolean onItemLongClicked(int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //  this.addEventos();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.aaa_md_eventos, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        viewHolder.descripcionTv.setText("Partido de fútbol sala, gente amateur, se acepta  a cualquier persona.");
        viewHolder.lugarTv.setText("Campo UPV");
        viewHolder.horaTv.setText(eventos.get(position).getHora());
        viewHolder.fechaTv.setText(eventos.get(position).getFecha());
        Picasso.with(this.context).load(R.drawable.upv_campo).resize(600, 280).into(viewHolder.img);
        //viewHolder.img.setImageResource(R.drawable.mestalla2);
        YoYo.with(Techniques.FadeIn).playOn(viewHolder.cardView);

    }


    @Override
    public int getItemCount() {
        return eventos.size();
    }

}