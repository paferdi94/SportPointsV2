package com.example.dannyang27.sportpoints.activities.Promocion;

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
import com.example.dannyang27.sportpoints.activities.PruebasDanny.EquipoInfo_MD;


import java.util.ArrayList;

/**
 * Created by Pablo_Fernandez on 23/11/16.
 */

public class RecyclerAdapterPromocion extends RecyclerView.Adapter<RecyclerAdapterPromocion.ViewHolder> {
    private Context context;

    public RecyclerAdapterPromocion(Context context) {
        this.context = context;
    }

    ArrayList<PromocionParceable> listaPromociones = new ArrayList<>();

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView img;
        private TextView nombre_equipo;
        private TextView deporte;
        private TextView participantes;
        private CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.logo_md_equipo);
            nombre_equipo = (TextView) itemView.findViewById(R.id.nombre_md_equipo);
            deporte = (TextView) itemView.findViewById(R.id.deporte_md_promo);
            participantes = (TextView) itemView.findViewById(R.id.participante_md_equipo);
            cardView = (CardView) itemView.findViewById(R.id.equipo_cardview);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.getContext().startActivity(new Intent(context, EquipoInfo_MD.class));


                    /*
                    int position = getAdapterPosition();
                    Snackbar.make(view, "Click detected on item: " + position,
                            Snackbar.LENGTH_LONG).setAction("Action", null).show();
                     */
                }
            });
        }

    }

    @Override
    public RecyclerAdapterPromocion.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.aaa_md_promociones, parent, false);
        RecyclerAdapterPromocion.ViewHolder viewHolder = new RecyclerAdapterPromocion.ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterPromocion.ViewHolder holder, int position) {

        holder.nombre_equipo.setText("DASDSA");
        holder.deporte.setText("FUTBOL");
        //holder.participantes.setText(listaEquipos.get(position).getJugadores().size());
        holder.img.setImageResource(R.drawable.denia_logo);
        YoYo.with(Techniques.FadeIn).playOn(holder.cardView);
    }



    @Override
    public int getItemCount() {
        return listaPromociones.size();
    }
}


