package com.dannyang27.sportpoints.activities.RecyclerViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dannyang27.sportpoints.R;

public class EventoItemHolder extends RecyclerView.ViewHolder {

    public TextView txtCelebName, txtCelebMovie;
    public View mView;

    public EventoItemHolder(View view) {
        super(view);
        mView = view;
    }

    public void setUsuario(String capAct) {
        TextView usuario = (TextView) mView.findViewById(R.id.txtCelebMovie);
        usuario.setText(capAct);
    }

    public void setHora(String c) {
        TextView hora = (TextView) mView.findViewById(R.id.txtCelebName);
        hora.setText(c);
    }

}