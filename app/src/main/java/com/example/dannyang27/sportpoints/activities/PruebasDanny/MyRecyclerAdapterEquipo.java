package com.example.dannyang27.sportpoints.activities.PruebasDanny;

/**
 * Created by Dannyang27 on 13/11/16.
 */

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dannyang27.sportpoints.R;
import com.example.dannyang27.sportpoints.activities.Equipo.EquipoParceable;

import java.util.ArrayList;

/**
 * Created by Dannyang27 on 13/11/16.
 */
class RecyclerAdapterEquipo extends RecyclerView.Adapter<RecyclerAdapterEquipo.ViewHolder> {


    ArrayList<EquipoParceable> listaEquipos = new ArrayList<>();

    public void addEquipos(){
        for(int i=0;i<10;i++) {
            EquipoParceable e = new EquipoParceable();
            e.setID(i+"");
            e.setNom("DENIA"+i);
            e.setDeporte("Futbol");
            //e.setMaxJugadores(11);

            listaEquipos.add(e);
        }
    }
    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView img;
        private TextView nombre_equipo;
        private TextView deporte;
        private TextView participantes;

        public ViewHolder(View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.logo_md_equipo);
            nombre_equipo = (TextView) itemView.findViewById(R.id.nombre_md_equipo);
            deporte = (TextView) itemView.findViewById(R.id.deporte_md_equipo);
            participantes = (TextView) itemView.findViewById(R.id.participante_md_equipo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Snackbar.make(view, "Click detected on item: " + position,
                            Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            });
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.aaa_md_equipos, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.nombre_equipo.setText("DASDSA");
        holder.deporte.setText("FUTBOL");
        //holder.participantes.setText(listaEquipos.get(position).getJugadores().size());
        holder.img.setImageResource(R.drawable.denia_logo);
    }



    @Override
    public int getItemCount() {
        return listaEquipos.size();
    }
}
