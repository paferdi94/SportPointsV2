package com.example.dannyang27.sportpoints.activities.PruebasDanny;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dannyang27.sportpoints.R;
import com.example.dannyang27.sportpoints.activities.EventoInfo;
import com.example.dannyang27.sportpoints.activities.Modelos.EventoParcelable;

import org.w3c.dom.Text;

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

    public void addEventos(){

        for(int i=0;i<10;i++) {
            EventoParcelable e = new EventoParcelable();
            e.setNombre("EVENTO"+i);
            e.setLugar("DENIA");
            e.setHora("12:30");
            e.setFecha("12/12/12");
            e.setEventoPhoto("mestalla2.jpg");

            eventos.add(e);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView nombreTv;
        private TextView lugarTv;
        private TextView horaTv;
        private TextView fechaTv;

        public ViewHolder(View itemView) {
            super(itemView);

            img = (ImageView)itemView.findViewById(R.id.imagen_evento_md);
            nombreTv = (TextView) itemView.findViewById(R.id.nombre_evento_md);
            lugarTv = (TextView) itemView.findViewById(R.id.lugar_evento_md);
            horaTv = (TextView) itemView.findViewById(R.id.hora_evento_md);
            fechaTv = (TextView) itemView.findViewById(R.id.fecha_evento_md);

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



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      //  this.addEventos();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.aaa_md_eventos, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        viewHolder.nombreTv.setText("PRUEBA");
        viewHolder.lugarTv.setText("PRUEBAS");
        viewHolder.horaTv.setText(eventos.get(position).getHora());
        viewHolder.fechaTv.setText(eventos.get(position).getFecha());
        viewHolder.img.setImageResource(R.drawable.mestalla2);

    }


    @Override
    public int getItemCount() {
        return eventos.size();
    }

}