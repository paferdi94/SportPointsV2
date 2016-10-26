package com.example.dannyang27.sportpoints.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.dannyang27.sportpoints.R;
import com.example.dannyang27.sportpoints.activities.Modelos.EventoParcelable;

public class EventoConfigView extends AppCompatActivity {

    private TextView nombreTv;
    private TextView fechaTv;
    private TextView horaTv;
    private TextView descripcionTv;
    private TextView participantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evento_config_view);


        nombreTv = (TextView) findViewById(R.id.nombreTxt);
        fechaTv = (TextView) findViewById(R.id.fechaTxt);
        horaTv = (TextView) findViewById(R.id.horaTxt);
        descripcionTv = (TextView) findViewById(R.id.descripcionTxt);
        participantes = (TextView) findViewById(R.id.participantesTxt);

        final EventoParcelable evento = getIntent().getParcelableExtra("PARCELABLE");

        nombreTv.setText(evento.getNombre());
        fechaTv.setText(evento.getFecha());
        horaTv.setText(evento.getHora());
        descripcionTv.setText(evento.getDescripcion());
        participantes.setText(evento.getParticipantes().size() + "/22");




    }
}
