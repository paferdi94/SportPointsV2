package com.dannyang27.sportpoints.activities.Promocion;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dannyang27.sportpoints.R;

/**
 * Created by Paferdi94 on 26/11/16.
 */

public class PromocionViewHolder extends RecyclerView.ViewHolder {
    ImageView img;
    TextView nombrePm;
    TextView lugarPm;
    TextView fechaIni;
    TextView fechaFin;
    View view;

    public PromocionViewHolder(View itemView) {
        super(itemView);
        view = itemView;

        img = (ImageView) itemView.findViewById(R.id.imagen_promo_md);
        nombrePm = (TextView) itemView.findViewById(R.id.nombre_promo_md);
        lugarPm = (TextView) itemView.findViewById(R.id.lugar_promo_md);
        fechaIni = (TextView) itemView.findViewById(R.id.fecha_promo_md);
        fechaFin = (TextView) itemView.findViewById(R.id.fecha_Fin_promo_md);


    }
}

