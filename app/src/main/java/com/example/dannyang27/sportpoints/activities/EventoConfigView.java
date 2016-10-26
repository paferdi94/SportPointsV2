package com.example.dannyang27.sportpoints.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.dannyang27.sportpoints.R;
import com.example.dannyang27.sportpoints.activities.ClasesBasicas.EventoParcelable;

public class EventoConfigView extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evento_config_view);

        tv = (TextView) findViewById(R.id.textPrueba);

        final EventoParcelable evento = getIntent().getParcelableExtra("PARCELABLE");

        tv.setText(evento.getNombre() + "" + evento.getDescripcion());



    }
}
