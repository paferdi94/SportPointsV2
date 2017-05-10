package com.dannyang27.sportpoints.activities.Producto;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dannyang27.sportpoints.R;

import org.w3c.dom.Text;

/**
 * Created by Danny on 04/12/2016.
 */

public class ProductoViewHolder extends RecyclerView.ViewHolder{

    ImageView img;
    TextView nombrePm;
    TextView precioPm;
    View view;
    public ProductoViewHolder(View itemView) {
        super(itemView);
        view = itemView;

        img = (ImageView) view.findViewById(R.id.imagen_producto_md);
        nombrePm = (TextView) view.findViewById(R.id.nombre_producto_md);
        precioPm = (TextView)view.findViewById(R.id.precio_producto_md);
    }


}
