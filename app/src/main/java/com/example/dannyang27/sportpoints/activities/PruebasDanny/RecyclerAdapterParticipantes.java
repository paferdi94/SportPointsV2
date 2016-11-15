package com.example.dannyang27.sportpoints.activities.PruebasDanny;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dannyang27.sportpoints.R;
import com.example.dannyang27.sportpoints.activities.Equipo.EquipoParceable;
import com.example.dannyang27.sportpoints.activities.Modelos.Participante;

import java.util.ArrayList;

import static com.example.dannyang27.sportpoints.R.id.deporte;
import static com.example.dannyang27.sportpoints.R.id.nombre_equipo;

/**
 * Created by Danny on 14/11/2016.
 */

public class RecyclerAdapterParticipantes extends RecyclerView.Adapter<RecyclerAdapterParticipantes.ViewHolder> {
    ArrayList<Participante> listaParticipante = new ArrayList<>();

    public void addParticipantes(){
        for(int i=0;i<25;i++) {
            Participante p = new Participante();
           p.setNombre("Cristiano Ronaldo");
            p.setTelefono("623456789");

            listaParticipante.add(p);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.aaa_md_participantes, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.nombre_participante.setText(listaParticipante.get(position).getNombre());
        viewHolder.edad_participante.setText("21");
        viewHolder.equipo_participante.setText("Real Madrid");
        viewHolder.img_participante.setImageResource(R.drawable.cristiano_ronaldo_original);
    }

    @Override
    public int getItemCount() {
        return listaParticipante.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView img_participante;
        private TextView nombre_participante;
        private TextView edad_participante;
        private TextView equipo_participante;

        public ViewHolder(View itemView) {
            super(itemView);

            img_participante = (ImageView) itemView.findViewById(R.id.img_participante_md);
            nombre_participante = (TextView) itemView.findViewById(R.id.nombre_participante_md);
            edad_participante = (TextView) itemView.findViewById(R.id.edad_participante_md);
            equipo_participante = (TextView) itemView.findViewById(R.id.equipo_participante_md);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Toast.makeText(view.getContext(), "SIUUUUUUUUUUUUUUUUU", Toast.LENGTH_LONG).show();
                }
            });
        }

    }
}
